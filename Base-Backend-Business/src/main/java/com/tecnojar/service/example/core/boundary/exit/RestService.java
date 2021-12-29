package com.tecnojar.service.example.core.boundary.exit;

import com.tecnojar.service.example.core.domain.Data;
import com.tecnojar.service.example.core.domain.ResponseTecnoJar;

public interface RestService {
	ResponseTecnoJar exampleRestGet(Data data)  throws Exception;

}
