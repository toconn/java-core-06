package ua.core.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import ua.core.enums.MediaType;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ItemNotFound;
import ua.core.exceptions.RemoteServerException;

public class HttpUtils {
	
	public static final int HTTP_RESPONSE_OK = 200;
	public static final int HTTP_NOT_FOUND = 404;
	
	public static String sendDelete (String urlString) throws RemoteServerException, ItemNotFound {

		HttpURLConnection	httpConnection = null;

		httpConnection = newHttpConnection (urlString, "DELETE");
		return connectRead (httpConnection);
	}
	
	public static String sendGet (String urlString) throws RemoteServerException, ItemNotFound {

		HttpURLConnection	httpConnection = null;

		httpConnection = newHttpConnection (urlString, "GET");
		return connectRead (httpConnection);
	}
	
	public static String sendPost (String urlString, String requestData, MediaType mediaType) throws RemoteServerException, ItemNotFound {
		
		HttpURLConnection	httpConnection = null;

		httpConnection = newHttpConnection (urlString, "POST");
		return connectWriteRead (httpConnection, requestData, mediaType);
	}
	
	
	
	private static String connectRead (HttpURLConnection httpConnnection) throws ItemNotFound {
		
		String response;
		
		try {
			
			httpConnnection.connect();
			validateStatus (httpConnnection);
			response = readResponse (httpConnnection);

			return response;
		}
		catch (ItemNotFound e) {
			
			throw e;
		}
		catch (IOException e) {
	
			throw new RemoteServerException (e, "Unable to make http request.");
		}
		catch (Exception e) {
			
			throw new ExceptionRuntime (e);
		}
		finally {
			
			if (httpConnnection != null) {
				httpConnnection.disconnect();
			}
		}
	}
	
	private static String connectWriteRead (HttpURLConnection httpConnection, String requestData, MediaType mediaType) throws RemoteServerException, ExceptionRuntime, ItemNotFound {
		
		byte[]	dataBytes;
		String	response;
		
		try {

			dataBytes = requestData.getBytes (StandardCharsets.UTF_8);
			
			httpConnection.setDoOutput(true);										// Required to send data.
			httpConnection.setRequestProperty ("charset", "utf-8");
			httpConnection.setRequestProperty ("Content-Length", Integer.toString (dataBytes.length));
			
			if (mediaType != null) {
				httpConnection.setRequestProperty ("content-type", mediaType.getTypeString());
			}
			
			httpConnection.connect();
			
			try	(DataOutputStream dataOutputStream = new DataOutputStream (httpConnection.getOutputStream())) {
				dataOutputStream.write (dataBytes);
			}
			
			validateStatus (httpConnection);
			
			response = readResponse (httpConnection);
			
			return response;
		}
		catch (ItemNotFound e) {
			
			throw e;
		}
		catch (IOException e) {
			
			throw new RemoteServerException (e, "Unable to make http request.");
		}
		catch (Exception e) {
			
			throw new ExceptionRuntime (e);
		}
		finally {
			
			if (httpConnection != null) {
				httpConnection.disconnect();
			}
		}
	}
	
	private static HttpURLConnection newHttpConnection (String urlString, String httpMethod) throws RemoteServerException {
		
		URL					url;
		HttpURLConnection	httpConnection = null;

		try {
			
			url = new URL (urlString);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod (httpMethod);
		
			return httpConnection;
		}
		catch (IOException e) {
			
			throw new RemoteServerException (e, "Unable to setup http request.");
		}
	}
	
	private static String readResponse (HttpURLConnection httpConnection) throws IOException {
		
		String				line;
		StringBuilder		stringBuilder;
		BufferedReader		reader = null;
		
		stringBuilder = new StringBuilder();
		
		reader = new BufferedReader (new InputStreamReader (httpConnection.getInputStream()));	
		
		line = null;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line + "\n");
		}
		
		return stringBuilder.toString();
	}
	
	private static void validateStatus (HttpURLConnection httpConnection) throws RemoteServerException, ItemNotFound, IOException {
		
		if (httpConnection.getResponseCode() != HTTP_RESPONSE_OK) {
			if (httpConnection.getResponseCode() == HTTP_NOT_FOUND) {
				throw new ItemNotFound ("");
			}
			else {
				throw new RemoteServerException ("Http request faied: " + httpConnection.getResponseCode() + " " + httpConnection.getResponseMessage());
			}
		}
	}
}
