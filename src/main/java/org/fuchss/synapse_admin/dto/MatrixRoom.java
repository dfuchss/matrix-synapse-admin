package org.fuchss.synapse_admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MatrixRoom {
	@JsonProperty("room_id")
	private String roomId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("creator")
	private String creator;
	@JsonProperty("joined_members")
	private int joinedMembers;

	@JsonProperty("joined_local_members")
	private int joinedLocalMembers;

	public String getRoomId() {
		return this.roomId;
	}

	public String getName() {
		return this.name == null ? "" : this.name;
	}

	public String getCreator() {
		return this.creator;
	}

	public int getJoinedMembers() {
		return this.joinedMembers;
	}

	public int getJoinedLocalMembers() {
		return this.joinedLocalMembers;
	}

	@Override
	public String toString() {
		return "MatrixRoom [name=" + this.name + "]";
	}

	public String prettyString() {
		return (this.name == null ? "" : this.name + " - ") + this.roomId;
	}
}
