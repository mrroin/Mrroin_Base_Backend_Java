package com.tecnojar.service.example.adapter;

import com.tecnojar.service.example.core.domain.Data;
import flexjson.JSONDeserializer;

public class JsonToData {
    public static Data fromStringTokenData(String json) {
    	Data request = new JSONDeserializer<Data>()
				.deserialize(json, Data.class);
        return request;
    }

}
