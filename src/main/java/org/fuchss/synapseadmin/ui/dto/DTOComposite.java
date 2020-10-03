package org.fuchss.synapseadmin.ui.dto;

import org.eclipse.swt.widgets.Composite;

public abstract class DTOComposite<Endpoint, DTO> extends Composite {
	protected Endpoint endpoint;
	protected DTO element;

	protected DTOComposite(Composite parent, int style) {
		super(parent, style);
	}

	public final void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
		this.element = null;
		this.updateElementData();
	}

	public final void setElement(DTO element) {
		this.element = element;
		this.updateElementData();
	}

	protected abstract void updateElementData();
}
