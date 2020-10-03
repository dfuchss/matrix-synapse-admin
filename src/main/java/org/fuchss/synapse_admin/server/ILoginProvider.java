package org.fuchss.synapse_admin.server;

import org.fuchss.synapse_admin.util.IObserver;

public interface ILoginProvider {
	void registerObserver(IObserver observer);
}
