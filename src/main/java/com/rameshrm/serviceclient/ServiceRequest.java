package com.rameshrm.serviceclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.cookie.Cookie;
import com.ning.http.client.uri.Uri;

public class ServiceRequest {
	private final String verb;
	private final String url;
	private final Uri _uri;
	private List<Cookie> _cookies = new ArrayList<>();
	private final HashMap<String, Object> _options;
	public static final String OPTIONS_TIMEOUT = "timeout";

	public ServiceRequest(String httpVerb, String url, HashMap<String, Object> options) {
		this.verb = httpVerb;
		this.url = url;
		_uri = trySetupURI(url);
		_options = options;

	}

	private void buildHeaders(String key, String value) {

	}

	public Request getRequest() {
		return tryBuild();
	}

	private Request tryBuild() {
		RequestBuilder rb = new RequestBuilder().setUri(_uri);
		if (_options != null) {

		}
		rb.setRequestTimeout(getRequestTimeout());
		return rb.build();
	}

	private Uri trySetupURI(String url) {
		return Uri.create(url);
	}

	private int getRequestTimeout() {
		return _options != null && _options.containsKey(OPTIONS_TIMEOUT)
				? Integer.getInteger(_options.get(OPTIONS_TIMEOUT).toString()) : 500;
	}
}
