package com.tecnojar.service.example.configuration;

public enum API {
	HEALTH("/tecnojar/health"),
    EXAMPLE("/tecnojar/example"),;

    private String url;

    API(String s) {
        this.url = s;
    }

    public String getUrl() {
        return url;
    }
}
