package org.fuchss.synapse_admin;

import java.io.File;
import java.io.IOException;

import org.fuchss.synapse_admin.api.RemoteMedia;
import org.fuchss.synapse_admin.api.Room;
import org.fuchss.synapse_admin.api.User;
import org.fuchss.synapse_admin.api.Version;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Main {
	private static final ObjectMapper MAPPER = new ObjectMapper()//
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)//
			.configure(SerializationFeature.INDENT_OUTPUT, true);

	private static final String CONF = "synapse-admin.conf";

	public static void main(String[] args) throws IOException {
		Conf conf = Main.loadConfig(args);
		Server server = new Server(conf.getServer(), conf.getToken());

		Version ve = new Version(server);
		System.out.println(ve.getVersion());

		Room re = new Room(server);
		System.out.println(re.getRooms());

		User ue = new User(server);
		System.out.println(ue.getUsers());

		RemoteMedia rm = new RemoteMedia(server);
		System.out.println(rm.purgeRemoteMedia(System.currentTimeMillis()));
	}

	private static Conf loadConfig(String[] args) throws IOException {
		String config = args.length < 1 ? Main.CONF : args[0];
		File file = new File(config);
		Conf conf;
		if (file.exists()) {
			conf = Main.MAPPER.readValue(file, Conf.class);
		} else {
			conf = new Conf();
			Main.MAPPER.writeValue(file, conf);
			System.err.println("Set data in config: " + file.getAbsolutePath());
			System.exit(1);
		}
		return conf;
	}

}
