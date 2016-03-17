package com.rameshrm.serviceclient;

import java.util.HashMap;

public class ServiceDetail {
	private String accepts;
	private String protocol;
	private String host;
	private String port;
	private String baseUrl;
	private String url;
	private HashMap<String, String> headers;
	private HashMap<String, Object> inputs;
	private String fullUrl;

	public ServiceDetail() {

	}

	public ServiceDetail(String jsonData) {

	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol
	 *            the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the headers
	 */
	public HashMap<String, String> getHeaders() {
		return headers;
	}

	/**
	 * @param headers
	 *            the headers to set
	 */
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	/**
	 * @return the inputs
	 */
	public HashMap<String, Object> getInputs() {
		return inputs;
	}

	/**
	 * @param inputs
	 *            the inputs to set
	 */
	public void setInputs(HashMap<String, Object> inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the fullUrl
	 */
	public String getFullUrl() {
		return fullUrl;
	}

	/**
	 * @param fullUrl
	 *            the fullUrl to set
	 */
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String constructFullUrl() {
		return "";
	}

	/**
	 * @return the accepts
	 */
	public String getAccepts() {
		return accepts;
	}

	/**
	 * @param accepts
	 *            the accepts to set
	 */
	public void setAccepts(String accepts) {
		this.accepts = accepts;
	}

	public boolean acceptsJSON() {
		return this.accepts.equalsIgnoreCase("application/json");
	}
}
