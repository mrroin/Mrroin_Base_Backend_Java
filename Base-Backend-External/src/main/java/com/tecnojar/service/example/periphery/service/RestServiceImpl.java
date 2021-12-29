package com.tecnojar.service.example.periphery.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.tecnojar.service.example.core.boundary.exit.RestService;
import com.tecnojar.service.example.core.domain.Data;
import com.tecnojar.service.example.core.domain.ResponseTecnoJar;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestServiceImpl implements RestService {
	private String endPoint;
	private String pathQuery;
	private OkHttpClient client;
	public RestServiceImpl() {
		this.endPoint = "http://www.mocky.io/v2";
		this.pathQuery = "/5cfbf7343000006f240a8c2c";
		this.client = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
	}

	@Override
	public ResponseTecnoJar exampleRestGet(Data data) throws Exception {
		ResponseTecnoJar response = null;
		Response responseService = null;
		String json = null;
		String jsonResponse = null;
		try {
			json = new JSONSerializer().exclude("*.class")
					.serialize(data);
			System.out.println("consultando a::: " + endPoint + pathQuery);
			System.out.println("con:::: " + json);
			Request request = new Request.Builder().url(endPoint + pathQuery).get().build();
			responseService = this.client.newCall(request).execute();
			if (404 != responseService.code()) {
				jsonResponse = responseService.body().string();
				System.out.println("respuesta de servicio mock.");
				System.out.println(jsonResponse);
				response = new JSONDeserializer<ResponseTecnoJar>()
						.deserialize(jsonResponse, ResponseTecnoJar.class);

			}
			responseService.close();
		} catch (Exception e) {
			if (response != null) {
				responseService.close();
			}
			e.printStackTrace();

		}
		return response;
	}
        
}
