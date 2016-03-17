package com.rameshrm.serviceclient;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiFunction;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.HttpResponseBodyPart;
import com.ning.http.client.HttpResponseHeaders;
import com.ning.http.client.Request;
import com.ning.http.client.Response;

public class ServiceExecutor<T> {
	private String serviceUrl;
	private final ServiceDetail _serviceDetail;

	/***
	 * 
	 * @param serviceDetails
	 */
	public ServiceExecutor(ServiceDetail serviceDetail) {

		_serviceDetail = serviceDetail;
	}

	/***
	 * 
	 */
	public void get() {
		System.out.println("Hello World");
	}

	/***
	 * 
	 */
	public void post() {
		System.out.println("Hello World");
	}

	@SuppressWarnings("resource")
	public Future<T> executeRequest(Request req, final BiFunction<Exception, ServiceResponse, Boolean> callback)
			throws InterruptedException, ExecutionException {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<T> promise = (Future<T>) asyncHttpClient.prepareRequest(req)
				.execute(new AsyncCompletionHandler<Response>() {

					private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

					@Override
					public com.ning.http.client.AsyncHandler.STATE onBodyPartReceived(HttpResponseBodyPart bodyPart)
							throws Exception {
						bytes.write(bodyPart.getBodyPartBytes());
						return com.ning.http.client.AsyncHandler.STATE.CONTINUE;
					}

					@Override
					public Response onCompleted(Response response) throws Exception {
						ServiceResponse svcResponse = new ServiceResponse(response.getStatusCode(),
								response.getHeaders(), bytes);
						if (callback != null) {
							callback.apply(null, svcResponse);
						}

						return response;
					}

					@Override
					public com.ning.http.client.AsyncHandler.STATE onHeadersReceived(HttpResponseHeaders headers)
							throws Exception {
						// TODO Auto-generated method stub
						return com.ning.http.client.AsyncHandler.STATE.CONTINUE;
					}

					@Override
					public void onThrowable(Throwable t) {
						callback.apply(new Exception("Unknown"), null);
					}
				});
		return promise;
	};

	/***
	 * 
	 * @param method
	 * @param input
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("resource")
	public void execute(String method, HashMap<String, Object> input,
			final BiFunction<Exception, ServiceResponse, Boolean> callback)
			throws InterruptedException, ExecutionException {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<T> promise = (Future<T>) asyncHttpClient.prepareGet(serviceUrl)
				.execute(new AsyncCompletionHandler<Response>() {

					private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

					@Override
					public com.ning.http.client.AsyncHandler.STATE onBodyPartReceived(HttpResponseBodyPart bodyPart)
							throws Exception {
						bytes.write(bodyPart.getBodyPartBytes());
						return com.ning.http.client.AsyncHandler.STATE.CONTINUE;
					}

					@Override
					public Response onCompleted(Response response) throws Exception {
						ServiceResponse svcResponse = new ServiceResponse(response.getStatusCode(),
								response.getHeaders(), bytes);
						if (callback != null) {
							callback.apply(null, svcResponse);
						}

						return response;
					}

					@Override
					public com.ning.http.client.AsyncHandler.STATE onHeadersReceived(HttpResponseHeaders headers)
							throws Exception {
						// TODO Auto-generated method stub
						return com.ning.http.client.AsyncHandler.STATE.CONTINUE;
					}

					@Override
					public void onThrowable(Throwable t) {
						callback.apply(new Exception("Unknown"), null);
					}
				});
		promise.get();
	}

}
