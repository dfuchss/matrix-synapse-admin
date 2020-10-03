package org.fuchss.synapse_admin;

import org.eclipse.swt.widgets.Display;
import org.fuchss.synapse_admin.server.ServerConf;
import org.fuchss.synapse_admin.ui.MainFrame;

public class Main {

	public static void main(String args[]) {
		if (args.length >= 1) {
			ServerConf.LOCATION = args[0];
		}

		MainFrame shell = new MainFrame(Display.getDefault());
		shell.startEventLoop();
	}

}
