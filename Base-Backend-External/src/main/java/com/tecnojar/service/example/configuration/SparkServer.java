package com.tecnojar.service.example.configuration;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tecnojar.service.example.core.boundary.exit.RestService;
import com.tecnojar.service.example.periphery.service.RestServiceImpl;
import com.tecnojar.service.example.periphery.service.controller.ExampleController;
import static spark.Spark.get;

public class SparkServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(SparkServer.class);

	public static void main(String[] args) {
		RestService serviceRest = new RestServiceImpl();
		ExampleController exampleController =
                new ExampleController(serviceRest);
		port(4568);
		System.out.println("Iniciando Spark server para TECNOJAR example...");
		options("/*",
		        (request, response) -> {

		            String accessControlRequestHeaders = request
		                    .headers("Access-Control-Request-Headers");
		            if (accessControlRequestHeaders != null) {
		                response.header("Access-Control-Allow-Headers",
		                        accessControlRequestHeaders);
		            }

		            String accessControlRequestMethod = request
		                    .headers("Access-Control-Request-Method");
		            if (accessControlRequestMethod != null) {
		                response.header("Access-Control-Allow-Methods",
		                        accessControlRequestMethod);
		            }

		            return "OK";
		        });
		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
		path("", () -> {
			before("/*", (request, response) -> LOGGER.info("Received api call { }", request));
			after("/*", (request, response) -> LOGGER.info("Return api call { }", request));
			get(API.HEALTH.getUrl(), (request, response) -> {
				response.header("Content-Type", "application/json");
				response.status(200);
				return "{\"status\":\"OK\"}";
			});
			post(API.EXAMPLE.getUrl(), exampleController::example);

		});
	}

}
