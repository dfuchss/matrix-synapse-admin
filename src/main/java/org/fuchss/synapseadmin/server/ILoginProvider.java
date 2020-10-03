package org.fuchss.synapseadmin.server;

import org.fuchss.synapseadmin.util.IObserver;

public interface ILoginProvider {
	void registerObserver(IObserver observer);
}
