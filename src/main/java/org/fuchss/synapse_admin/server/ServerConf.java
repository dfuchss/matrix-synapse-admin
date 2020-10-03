package org.fuchss.synapse_admin.server;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class ServerConf {

	public static String LOCATION = "synapse-admin.conf";

	private static final ObjectMapper MAPPER = new ObjectMapper()//
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)//
			.configure(SerializationFeature.INDENT_OUTPUT, true);

	public static final ServerConf INSTANCE = ServerConf.load();

	private String server;
	private String token;

	public ServerConf() {
		this.server = "";
		this.token = "";
	}

	private static ServerConf load() {
		try {
			return ServerConf.loadConfig();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Path: " + ServerConf.LOCATION + " is not suitable. Write access denied.");
			System.exit(1);
			return null;
		}
	}

	private static synchronized ServerConf loadConfig() throws IOException {
		File file = new File(ServerConf.LOCATION);
		ServerConf conf;
		if (file.exists()) {
			conf = ServerConf.MAPPER.readValue(file, ServerConf.class);
		} else {
			conf = new ServerConf();
			ServerConf.MAPPER.writeValue(file, conf);
			System.err.println("Set data in config: " + file.getAbsolutePath());
		}
		return conf;
	}

	public void updateServerLogin(String server, String token) {
		this.server = server == null || server.isBlank() ? null : server.trim();
		this.normalizeServerURL();
		this.token = token;
		try {
			this.store();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void normalizeServerURL() {
		if (this.server == null) {
			return;
		}

		if (!this.server.startsWith("http")) {
			this.server = "https://" + this.server;
		}

		if (this.server.endsWith("/")) {
			this.server = this.server.substring(0, this.server.length() - 1);
		}

	}

	public static Server newServerInstance() {
		return ServerConf.INSTANCE.server == null || ServerConf.INSTANCE.token == null ? null : new Server(ServerConf.INSTANCE);
	}

	private void store() throws IOException {
		File file = new File(ServerConf.LOCATION);
		ServerConf.MAPPER.writeValue(file, this);
	}

	public String getServer() {
		return this.server;
	}

	public String getToken() {
		return this.token;
	}

}
