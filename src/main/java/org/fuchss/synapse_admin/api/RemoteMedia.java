package org.fuchss.synapse_admin.api;

import org.fuchss.synapse_admin.server.Server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteMedia extends Endpoint {

	private static final String PURGE_CACHE = "/_synapse/admin/v1/purge_media_cache";

	public RemoteMedia(Server server) {
		super(server);
	}

	public int purgeRemoteMedia(long beforeMS) {
		var data = this.server.post(RemoteMedia.PURGE_CACHE + "?before_ts=" + beforeMS, null);
		if (data == null || data.statusCode() != 200) {
			return -1;
		}
		PurgeResult result = this.read(data.body(), PurgeResult.class);
		return result.deleted;
	}

	private static class PurgeResult {
		@JsonProperty("deleted")
		public int deleted;
	}
}
