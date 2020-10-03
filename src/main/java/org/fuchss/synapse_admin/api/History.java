package org.fuchss.synapse_admin.api;

import org.fuchss.synapse_admin.dto.MatrixRoom;
import org.fuchss.synapse_admin.server.Server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class History extends Endpoint {

	private static final String PURGE = "/_synapse/admin/v1/purge_history/";
	private static final String STATUS = "/_synapse/admin/v1/purge_history_status/";

	public History(Server server) {
		super(server);
	}

	public String purgeHistoryOfRoom(MatrixRoom room, boolean deleteLocal) {
		String payload = "{\"delete_local_events\": " + deleteLocal + "}";
		var data = this.server.post(History.PURGE + room.getRoomId(), payload);
		if (data == null || data.statusCode() != 200) {
			return null;
		}
		PurgeIdResult result = this.read(data.body(), PurgeIdResult.class);
		return result.purgeId;
	}

	private static class PurgeIdResult {
		@JsonProperty("purge_id")
		public String purgeId;
	}

	public Status getStatusOfPurge(String purgeId) {
		var data = this.server.get(History.STATUS + purgeId);
		if (data == null || data.statusCode() != 200) {
			return null;
		}
		PurgeStatusResult result = this.read(data.body(), PurgeStatusResult.class);
		return Status.valueOf(result.status.toUpperCase());
	}

	private static class PurgeStatusResult {
		@JsonProperty("status")
		public String status;
	}

	public enum Status {
		ACTIVE, COMPLETE, FAILED
	}

}
