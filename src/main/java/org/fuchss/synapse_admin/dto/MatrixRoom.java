package org.fuchss.synapse_admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MatrixRoom {
	@JsonProperty("room_id")
	private String roomId;
	@JsonProperty("name")
	private String name;

	public String getRoomId() {
		return this.roomId;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "MatrixRoom [name=" + this.name + "]";
	}

}
