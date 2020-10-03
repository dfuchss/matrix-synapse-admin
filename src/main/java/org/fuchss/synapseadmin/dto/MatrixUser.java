package org.fuchss.synapseadmin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MatrixUser {
	@JsonProperty("name")
	private String id;
	@JsonProperty("password_hash")
	private String passwordHash;
	@JsonProperty("is_guest")
	private boolean isGuest;
	@JsonProperty("admin")
	private boolean isAdmin;
	@JsonProperty("user_type")
	private String userType;
	@JsonProperty("deactivated")
	private boolean deactivated;
	@JsonProperty("display_name")
	private String displayName;
	@JsonProperty("avatar_url")
	private String avatarUrl;

	public String getId() {
		return this.id;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public boolean isGuest() {
		return this.isGuest;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public String getUserType() {
		return this.userType == null ? "" : this.userType;
	}

	public boolean isDeactivated() {
		return this.deactivated;
	}

	public String getDisplayName() {
		return this.displayName == null ? "" : this.displayName;
	}

	public String getAvatarUrl() {
		return this.avatarUrl == null ? "" : this.avatarUrl;
	}

	@Override
	public String toString() {
		return "MatrixUser [id=" + this.id + ", isGuest=" + this.isGuest + ", isAdmin=" + this.isAdmin + ", userType=" + this.userType
				+ ", deactivated=" + this.deactivated + ", displayName=" + this.displayName + ", avatarUrl=" + this.avatarUrl + "]";
	}

	public String prettyString() {
		return (this.displayName == null ? "" : this.displayName + " - ") + this.id;
	}

}
