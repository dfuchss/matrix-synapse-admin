package org.fuchss.synapse_admin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.function.Function;

public final class Server {
	private String url;
	private String token;
	private HttpClient client;

	public Server(String url, String token) {
		this.url = url;
		this.token = token;
		this.client = HttpClient.newHttpClient();
	}

	public HttpResponse<String> get(String endpoint) {
		return this.request(endpoint, Builder::GET);
	}

	public HttpResponse<String> post(String endpoint, String data) {
		return this.request(endpoint, b -> b.POST(data == null ? BodyPublishers.noBody() : BodyPublishers.ofString(data)));
	}

	public HttpResponse<String> put(String endpoint, String data) {
		return this.request(endpoint, b -> b.PUT(data == null ? BodyPublishers.noBody() : BodyPublishers.ofString(data)));
	}

	private HttpResponse<String> request(String endpoint, Function<Builder, Builder> method) {
		try {
			Builder builder = HttpRequest.newBuilder() //
					.uri(URI.create(this.url + endpoint)) //
					.setHeader("Authorization", "Bearer " + this.token);
			builder = method.apply(builder);

			HttpResponse<String> response = this.client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
