/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * This class is used for all communication to the Aurora server
 * @author Justin Maneri
 *
 */
public class AuroraNetworkRequest {
	private static final String TAG = "AuroraNetworkRequest";
	
	private HttpClient mHttpClient;
	
	private HttpRequestBase mHttpRequest;
	private HashMap<String, String> mHttpRequestHeaders;
	private String mHttpRequestBody;
	private HttpRequestType mHttpRequestType;
	
	private HttpResponse mHttpResponse;
	
	/**
	 * @category Constructors
	 */
	
	public AuroraNetworkRequest(String url) {
		this(url, HttpRequestType.GET, false);
	}
	
	public AuroraNetworkRequest(String url, HttpRequestType requestType) {
		this(url, HttpRequestType.GET, false);
	}
	
	// Default Constructor
	public AuroraNetworkRequest(String url, HttpRequestType requestType, boolean useSSL) {
		mHttpRequestType = requestType;
		this.addHttpRequestHeader("Content-Type", "application/json");
		
		switch (requestType) {
		case POST: 
			mHttpRequest = new HttpPost(url);
			break;
		case GET:
			mHttpRequest = new HttpGet(url);
			break;
		case PUT:
			mHttpRequest = new HttpPut(url);
			break;
		}
		
		// This function changes the HttpClient when using SSL to
		// allow all certificates until the demo site has a Root Certificate
		// TODO CustomHttpsClient can be removed once a Root Certificate is in place
		if(useSSL) {
			mHttpClient = CustomHttpsClient.createHttpsClient();
		} else {
			mHttpClient = new DefaultHttpClient();
		}
	}
	
	/**
	 * Execute the network request. This command cannot be executed on the main UI thread
	 * @return HttpResponse
	 */
	public HttpResponse execute() {
		try {
			// set request body if using POST
			if (mHttpRequestBody != null && mHttpRequestType == HttpRequestType.POST) {
				((HttpPost) mHttpRequest).setEntity(new StringEntity(mHttpRequestBody, HTTP.UTF_8));
			}
	
			// set custom headers if there are any
			if (mHttpRequestHeaders != null) {
				for (Map.Entry<String, String> header : mHttpRequestHeaders.entrySet()) {
					mHttpRequest.setHeader(header.getKey(), header.getValue());
				}
			}
		
			mHttpResponse = mHttpClient.execute(mHttpRequest);
			Log.d(TAG, "Network request completed without errors:\n" + this.mHttpRequest.getURI());
			
			return mHttpResponse;
		} catch (Exception e) {
			Log.d(TAG, "Network request failed:\n" + e.getMessage());
			return null;
		}
	}

	/**
	 * @category Getters/Setters
	 */
	
	public HttpResponse getHttpResponse() {
		return mHttpResponse;
	}

	public void setHttpRequestHeaders(HashMap<String, String> mHttpRequestHeaders) {
		this.mHttpRequestHeaders = mHttpRequestHeaders;
	}
	
	public void addHttpRequestHeader(String key, String value) {
		if (mHttpRequestHeaders == null) {
			mHttpRequestHeaders = new HashMap<String, String>();
		}
		this.mHttpRequestHeaders.put(key, value);
	}

	public void setHttpRequestBody(String mHttpRequestBody) {
		this.mHttpRequestBody = mHttpRequestBody;
	}
	
	public String getHttpResponseMessage() {
		if(mHttpResponse == null) {
			return null;
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			mHttpResponse.getEntity().writeTo(baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toString();
	}
	
	public Bitmap getHttpResponseImage() {
		if(mHttpResponse == null) {
			return null;
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			mHttpResponse.getEntity().writeTo(baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] b = baos.toByteArray();
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}
	
	public int getHttpResponseStatusCode() {
		if(mHttpResponse == null) {
			return -1;
		}
		
		return mHttpResponse.getStatusLine().getStatusCode();
	}
}
