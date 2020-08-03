package org.fuchss.synapse_admin.ui.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.fuchss.synapse_admin.ui.dto.DTOComposite;
import org.fuchss.tools.tuple.Tuple2;

public abstract class EndpointComposite<Endpoint, DTO, C extends DTOComposite<Endpoint, DTO>> extends Composite {
	private Endpoint endpoint;
	private C dtoComposite;
	private Combo combo;
	private List<DTO> dtos;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public EndpointComposite(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(1, false));

		this.combo = new Combo(this, SWT.READ_ONLY);
		this.combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		this.combo.addListener(SWT.Selection, e -> this.select());

		this.dtoComposite = this.createComposite(this, SWT.NONE);
		this.dtoComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}

	public final void reload() {
		this.setEndpoint(this.endpoint);
	}

	protected abstract C createComposite(EndpointComposite<Endpoint, DTO, C> parent, int style);

	public EndpointComposite(Composite parent, int style, Endpoint endpoint) {
		this(parent, style);
		this.setEndpoint(endpoint);
	}

	public final void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
		this.dtoComposite.setEndpoint(endpoint);
		this.dtos = null;
		this.combo.removeAll();

		this.dtos = new ArrayList<>();
		var data = this.fill();
		for (var datum : data) {
			this.dtos.add(datum.getFirst());
			this.combo.add(datum.getSecond());
		}

		if (!this.dtos.isEmpty()) {
			this.combo.select(0);
			this.select();
		}
	}

	protected abstract List<Tuple2<DTO, String>> fill();

	protected Endpoint getEndpoint() {
		return this.endpoint;
	}

	private void select() {
		if (this.dtos == null) {
			return;
		}

		int idx = this.combo.getSelectionIndex();
		this.dtoComposite.setElement(this.dtos.get(idx));
	}

}
