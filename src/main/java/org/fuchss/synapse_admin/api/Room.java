package org.fuchss.synapse_admin.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fuchss.synapse_admin.Server;
import org.fuchss.synapse_admin.dto.MatrixRoom;
import org.fuchss.synapse_admin.dto.MatrixUser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room extends Endpoint {

	private static final String ID = "<room_id>";

	private static final String ROOMS = "/_synapse/admin/v1/rooms";
	private static final String PURGE = "/_synapse/admin/v1/purge_room";
	private static final String DELETE = "/_synapse/admin/v1/rooms/<room_id>/delete";
	private static final String JOIN = "/_synapse/admin/v1/join/";

	public Room(Server server) {
		super(server);
	}

	public List<MatrixRoom> getRooms() {
		List<MatrixRoom> rooms = new ArrayList<>();

		for (int offset = 0;;) {
			var data = this.server.get(Room.ROOMS + "?from=" + offset);
			if (data == null || data.statusCode() != 200) {
				break;
			}
			RoomResult loaded = this.read(data.body(), RoomResult.class);
			if (loaded == null || loaded.rooms.length == 0) {
				break;
			}
			rooms.addAll(Arrays.asList(loaded.rooms));
			offset += loaded.rooms.length;
		}

		return rooms;
	}

	public int purgeRoom(MatrixRoom room) {
		String payload = "{\"room_id\":\"" + room.getRoomId() + "\"}";
		var data = this.server.post(Room.PURGE, payload);
		return data.statusCode();
	}

	public int deleteRoom(MatrixRoom room) {
		String payload = "{}";
		String request = Room.DELETE.replace(Room.ID, room.getRoomId());
		var data = this.server.post(request, payload);
		return data.statusCode();
	}

	public int joinRoom(MatrixRoom room, MatrixUser user) {
		String payload = "{\"user_id\":\"" + user.getId() + "\"}";
		var data = this.server.post(Room.JOIN + room.getRoomId(), payload);
		return data.statusCode();
	}

	private static class RoomResult {
		@JsonProperty("rooms")
		public MatrixRoom[] rooms;
		@JsonProperty("offset")
		public int offset;
		@JsonProperty("total_rooms")
		public int totalRooms;
	}

}
