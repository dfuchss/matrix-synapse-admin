package org.fuchss.synapseadmin.server;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.function.UnaryOperator;

import org.fuchss.synapseadmin.SystemConf;

public final class Server {
	private String url;
	private String token;
	private HttpClient client;
	private boolean debug = SystemConf.DEBUG;

	Server(ServerConf conf) {
		this.url = conf.getServer();
		this.token = conf.getToken();
		this.client = HttpClient.newHttpClient();
	}

	public HttpResponse<String> get(String endpoint) {
		var result = this.request(endpoint, Builder::GET);
		if (this.debug && result != null) {
			System.err.println(result + result.body());
		}
		return result;
	}

	public HttpResponse<String> post(String endpoint, String data) {
		var result = this.request(endpoint, b -> b.POST(data == null ? BodyPublishers.noBody() : BodyPublishers.ofString(data)));
		if (this.debug && result != null) {
			System.err.println(result + result.body());
		}
		return result;
	}

	private HttpResponse<String> request(String endpoint, UnaryOperator<Builder> method) {
		try {
			Builder builder = HttpRequest.newBuilder() //
					.uri(URI.create(this.url + endpoint)) //
					.setHeader("Authorization", "Bearer " + this.token);
			builder = method.apply(builder);

			return this.client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			if (this.debug) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
