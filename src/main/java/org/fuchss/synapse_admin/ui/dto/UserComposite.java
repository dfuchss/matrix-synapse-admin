package org.fuchss.synapse_admin.ui.dto;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.fuchss.synapse_admin.api.User;
import org.fuchss.synapse_admin.dto.MatrixUser;

public class UserComposite extends DTOComposite<User, MatrixUser> {

	private Text txtName;
	private Text txtId;
	private Text txtUsertype;
	private Text txtAvatar;
	private Button btnGuest;
	private Button btnAdmin;
	private Button btnDeactivated;
	private Button btnDeactivate;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public UserComposite(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(2, false));

		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name");

		this.txtName = new Text(this, SWT.BORDER);
		this.txtName.setEditable(false);
		this.txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblId = new Label(this, SWT.NONE);
		lblId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblId.setText("ID");

		this.txtId = new Text(this, SWT.BORDER);
		this.txtId.setEditable(false);
		this.txtId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGuest = new Label(this, SWT.NONE);
		lblGuest.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGuest.setText("Guest ?");

		this.btnGuest = new Button(this, SWT.CHECK);
		this.btnGuest.setEnabled(false);

		Label lblAdmin = new Label(this, SWT.NONE);
		lblAdmin.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAdmin.setText("Admin ?");

		this.btnAdmin = new Button(this, SWT.CHECK);
		this.btnAdmin.setEnabled(false);

		Label lblUsertype = new Label(this, SWT.NONE);
		lblUsertype.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsertype.setText("UserType");

		this.txtUsertype = new Text(this, SWT.BORDER);
		this.txtUsertype.setEditable(false);
		this.txtUsertype.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblDeactivated = new Label(this, SWT.NONE);
		lblDeactivated.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDeactivated.setText("Deactivated ?");

		this.btnDeactivated = new Button(this, SWT.CHECK);
		this.btnDeactivated.setEnabled(false);

		Label lblAvatar = new Label(this, SWT.NONE);
		lblAvatar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAvatar.setText("Avatar");

		this.txtAvatar = new Text(this, SWT.BORDER);
		this.txtAvatar.setEditable(false);
		this.txtAvatar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		this.btnDeactivate = new Button(this, SWT.NONE);
		this.btnDeactivate.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		this.btnDeactivate.setText("Deactivate Account");
		this.btnDeactivate.setEnabled(false);
		this.btnDeactivate.addListener(SWT.Selection, e -> this.deactivate());
	}

	private void deactivate() {
		if (this.element == null || this.endpoint == null) {
			return;
		}
		// TODO WIP ..
		// this.endpoint.deactivate(this.element, false);
	}

	@Override
	public void updateElementData() {
		this.txtName.setText(this.element == null ? "" : this.element.getDisplayName());
		this.txtId.setText(this.element == null ? "" : this.element.getId());
		this.txtUsertype.setText(this.element == null ? "" : this.element.getUserType());
		this.txtAvatar.setText(this.element == null ? "" : this.element.getAvatarUrl());
		this.btnGuest.setSelection(this.element == null ? false : this.element.isGuest());
		this.btnAdmin.setSelection(this.element == null ? false : this.element.isAdmin());
		this.btnDeactivated.setSelection(this.element == null ? false : this.element.isDeactivated());
	}
}
