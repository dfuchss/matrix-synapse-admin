package org.fuchss.synapse_admin.api;

import org.fuchss.synapse_admin.Server;
import org.fuchss.synapse_admin.dto.MatrixVersion;

public class Version extends Endpoint {

	private static final String VERSIONS = "/_synapse/admin/v1/server_version";

	public Version(Server server) {
		super(server);
	}

	public MatrixVersion getVersion() {
		var data = this.server.get(Version.VERSIONS);
		if (data == null || data.statusCode() != 200) {
			return null;
		}
		MatrixVersion version = this.read(data.body(), MatrixVersion.class);
		return version;
	}

}
