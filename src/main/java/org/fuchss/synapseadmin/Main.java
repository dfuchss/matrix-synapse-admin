package org.fuchss.synapseadmin;

import org.eclipse.swt.widgets.Display;
import org.fuchss.synapseadmin.server.ServerConf;
import org.fuchss.synapseadmin.ui.MainFrame;

public class Main {

	public static void main(String args[]) {
		if (args.length >= 1) {
			ServerConf.LOCATION = args[0];
		} else {
			System.out.println("INFO: You can specify the config file for this tool via args[0].");
		}

		MainFrame shell = new MainFrame(Display.getDefault());
		shell.startEventLoop();
	}

}
