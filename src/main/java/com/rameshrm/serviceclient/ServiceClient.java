package com.rameshrm.serviceclient;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiFunction;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * 
 * @author rmahadevan
 *
 */
public class ServiceClient {
	private volatile static Config _config;
	private final String _clientId;
	private BiFunction<Exception, ServiceResponse, Boolean> _callback = null;
	private HashMap<String, Object> _inputArgs = null;
	private HashMap<String, Object> _headers = null;
	private final Config _serviceConfig;
	private String _verb;
	private String uriPath;

	/**
	 * Initialize Service Client to Load Config Factory
	 */
	public synchronized static void init() {
		Config config = ConfigFactory.load();
		if (config != null) {
			_config = config.getConfig("services");
		}
		System.out.println(_config);
	}

	/**
	 * Default Constructor for ServiceClient w/ClientId
	 * 
	 * @param clientId
	 */
	public ServiceClient(String clientId) {
		this._clientId = clientId;
		_serviceConfig = _config.getConfig(clientId);
	}

	/**
	 * Default Constructor for ServiceClient w/ClientId & input arguments
	 * inputArgs can be used to send a queryString parameter for Get (or) a Post
	 * Body JSON payload for POST
	 * 
	 * @param clientId
	 * @param inputArgs
	 */
	public ServiceClient(String clientId, HashMap<String, Object> inputArgs) {
		this(clientId, inputArgs, null);
	}

	/**
	 * Default Constructor for ServiceClient w/ClientId & input arguments
	 * inputArgs can be used to send a queryString parameter for Get (or) a Post
	 * Body JSON payload for POST
	 * 
	 * @param clientId
	 * @param inputArgs
	 */
	public ServiceClient(String clientId, HashMap<String, Object> inputArgs, HashMap<String, Object> headers) {
		_serviceConfig = _config.getConfig(clientId);
		this._clientId = clientId;
		this._inputArgs = inputArgs;
		this.setHeaders(headers);

	}

	public ServiceClient GET(String path) {
		_verb = HttpVerbs.GET;
		uriPath = path;
		return this;
	}

	public ServiceClient POST(String path) {
		_verb = HttpVerbs.POST;
		uriPath = path;
		return this;
	}

	public ServiceClient PUT(String path) {
		_verb = HttpVerbs.PUT;
		uriPath = path;
		return this;
	}

	public ServiceClient DELETE(String path) {
		_verb = HttpVerbs.DELETE;
		uriPath = path;
		return this;
	}

	public ServiceClient execute(String method, String path) {
		_verb = method;
		uriPath = path;
		return this;
	}

	public void end(BiFunction<Exception, ServiceResponse, Boolean> callback)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		this.setCallback(callback);
		transport(_verb, this._inputArgs);
	}

	/**
	 * @return the _callback
	 */
	public BiFunction<Exception, ServiceResponse, Boolean> getCallback() {
		return _callback;
	}

	/**
	 * @param _callback
	 *            the _callback to set
	 */
	public void setCallback(BiFunction<Exception, ServiceResponse, Boolean> _callback) {
		this._callback = _callback;
	}

	/**
	 * @return the _headers
	 */
	public HashMap<String, Object> getHeaders() {
		return _headers;
	}

	/**
	 * Set Any additional Standard / Custom Http Headers for the request
	 * 
	 * @param _headers
	 *            the _headers to set
	 */
	public void setHeaders(HashMap<String, Object> _headers) {
		this._headers = _headers;
	}

	private void transport(String method, HashMap<String, Object> input)
			throws InterruptedException, ExecutionException {
		System.out.println(this._serviceConfig);
		String fullUrl = this._serviceConfig.getString("basePath");
		fullUrl += this.uriPath != null && !this.uriPath.isEmpty() ? uriPath : "";

		ServiceRequest request = new ServiceRequest(this._verb, fullUrl, input);
		ServiceExecutor executor = new ServiceExecutor(null);
		Future promise = executor.executeRequest(request.getRequest(), this._callback);
		promise.get();
	}

}
