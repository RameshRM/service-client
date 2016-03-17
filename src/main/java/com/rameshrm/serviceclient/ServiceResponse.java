package com.rameshrm.serviceclient;

import java.io.ByteArrayOutputStream;

import com.ning.http.client.FluentCaseInsensitiveStringsMap;

public class ServiceResponse {
	public final Integer statusCode;
	public final Object headers;
	public final ByteArrayOutputStream rawData;

	public ServiceResponse(int statusCode, FluentCaseInsensitiveStringsMap headers, ByteArrayOutputStream bodyParts) {
		this.statusCode = statusCode;
		this.headers = headers;
		this.rawData = bodyParts;

	}


}
