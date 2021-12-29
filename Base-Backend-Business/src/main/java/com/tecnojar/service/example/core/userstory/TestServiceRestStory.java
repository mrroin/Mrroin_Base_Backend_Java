package com.tecnojar.service.example.core.userstory;

import com.tecnojar.service.example.core.boundary.enter.ExampleService;
import com.tecnojar.service.example.core.boundary.exit.RestService;
import com.tecnojar.service.example.core.domain.Data;
import com.tecnojar.service.example.core.domain.ResponseTecnoJar;

public class TestServiceRestStory implements ExampleService{
    private final RestService service;

    public TestServiceRestStory(RestService service) {
        this.service = service;
    }

	@Override
	public ResponseTecnoJar getExampleService(Data data) throws Exception {
		System.out.println("Estoy en la historia de usuario... " + data.getId());
		ResponseTecnoJar res = this.service.exampleRestGet(data);
		System.out.println("Estoy en la historia de usuario... respuesta servicio rest" + res.toString());
		return res;
	}

    

}
