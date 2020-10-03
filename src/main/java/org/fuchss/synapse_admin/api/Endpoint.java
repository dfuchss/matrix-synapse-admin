package org.fuchss.synapse_admin.api;

import java.util.Arrays;
import java.util.List;

import org.fuchss.synapse_admin.server.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Endpoint {

	protected final Server server;
	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	protected Endpoint(Server server) {
		this.server = server;
	}

	protected final <D> D read(String data, Class<D> clazz) {
		try {
			return this.objectMapper.readValue(data, clazz);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected final <D> List<D> readList(String data, Class<D[]> clazz) {
		try {
			D[] elements = this.objectMapper.readValue(data, clazz);
			return Arrays.asList(elements);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}
