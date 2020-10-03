package org.fuchss.synapse_admin.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fuchss.synapse_admin.dto.MatrixUser;
import org.fuchss.synapse_admin.server.Server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User extends Endpoint {

	private static final String USERS = "/_synapse/admin/v2/users";
	private static final String DEACTIVATE = "/_synapse/admin/v1/deactivate/";

	public User(Server server) {
		super(server);
	}

	public List<MatrixUser> getUsers() {
		return this.getUsers(true, false);
	}

	public List<MatrixUser> getUsers(boolean showGuests, boolean showDeactivated) {
		List<MatrixUser> users = new ArrayList<>();

		for (int offset = 0;;) {
			var data = this.server == null //
					? null //
					: this.server.get(User.USERS + "?from=" + offset + "&guests=" + showGuests + "&deactivated=" + showDeactivated);
			if (data == null || data.statusCode() != 200) {
				break;
			}
			UserResult loaded = this.read(data.body(), UserResult.class);
			if (loaded == null || loaded.users.length == 0) {
				break;
			}
			users.addAll(Arrays.asList(loaded.users));
			offset += loaded.users.length;
		}

		return users;
	}

	private static class UserResult {
		@JsonProperty("users")
		public MatrixUser[] users;
		@JsonProperty("next_token")
		public int offset;
		@JsonProperty("total")
		public int totalUsers;
	}

	public int deactivate(MatrixUser user, boolean erase) {
		String payload = "{\"erase\":" + erase + "}";
		var data = this.server.post(User.DEACTIVATE + user.getId(), payload);
		return data.statusCode();
	}
}
