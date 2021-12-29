package com.tecnojar.service.example.periphery.service.controller;

import flexjson.JSONSerializer;
import spark.Request;
import spark.Response;
import com.tecnojar.service.example.adapter.JsonToData;
import com.tecnojar.service.example.core.boundary.exit.RestService;
import com.tecnojar.service.example.core.domain.Data;
import com.tecnojar.service.example.core.domain.ResponseTecnoJar;
import com.tecnojar.service.example.core.userstory.TestServiceRestStory;

public class ExampleController {
    private final RestService service;
    private final TestServiceRestStory testServiceRestStory;
        

    public ExampleController(RestService exampleService) {
    	this.service = exampleService;
        this.testServiceRestStory = new TestServiceRestStory(this.service);
    }

    public Object example(Request request, Response response) {
        try {
        	System.out.println(request.body());
			Data data = JsonToData.fromStringTokenData(request.body());
			response.status(200);
			ResponseTecnoJar _response = testServiceRestStory.getExampleService(data);
			System.out.println("Estoy en controller... enviando respuesta..." + _response.toString());
			return new JSONSerializer().exclude("*.class")
					.serialize(_response);

		} catch (Exception e) {
			response.status(500);
			return generateErrorResponse(" => " + e.getMessage());
		}
    }
    
    private String generateErrorResponse(String message) {
		return "{\"status\":\"BAD\", \"details\":\"" + message + "\"}";
	}
    
}
