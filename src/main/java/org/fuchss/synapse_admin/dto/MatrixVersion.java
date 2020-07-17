package org.fuchss.synapse_admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MatrixVersion {
	@JsonProperty("server_version")
	private String serverVersion;
	@JsonProperty("python_version")
	private String pythonVersion;

	public String getPythonVersion() {
		return this.pythonVersion;
	}

	public String getServerVersion() {
		return this.serverVersion;
	}

	@Override
	public String toString() {
		return "Server Version: " + this.serverVersion + " - Python Version: " + this.pythonVersion;
	}
}