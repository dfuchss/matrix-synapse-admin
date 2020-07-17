package org.fuchss.synapse_admin.ui.dto;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.fuchss.synapse_admin.api.Room;
import org.fuchss.synapse_admin.dto.MatrixRoom;

public class RoomComposite extends DTOComposite<Room, MatrixRoom> {

	private Text txtName;
	private Text txtId;
	private Text txtCreator;
	private Text txtJoinedMembers;

	public RoomComposite(Composite parent, int style) {
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

		Label lblCreator = new Label(this, SWT.NONE);
		lblCreator.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCreator.setText("Creator");

		this.txtCreator = new Text(this, SWT.BORDER);
		this.txtCreator.setEditable(false);
		this.txtCreator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblJoinedMembers = new Label(this, SWT.NONE);
		lblJoinedMembers.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJoinedMembers.setText("Joined Members");

		this.txtJoinedMembers = new Text(this, SWT.BORDER);
		this.txtJoinedMembers.setEditable(false);
		this.txtJoinedMembers.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Button btnPurge = new Button(composite, SWT.NONE);
		btnPurge.setEnabled(false);
		btnPurge.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnPurge.setBounds(0, 0, 105, 35);
		btnPurge.setText("Purge");

		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		Button btnAddUser = new Button(composite, SWT.NONE);
		btnAddUser.setEnabled(false);
		btnAddUser.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnAddUser.setText("Add User");

	}

	@Override
	public void updateElementData() {
		this.txtName.setText(this.element == null ? "" : this.element.getName());
		this.txtId.setText(this.element == null ? "" : this.element.getRoomId());
		this.txtCreator.setText(this.element == null ? "" : this.element.getCreator());
		this.txtJoinedMembers.setText(this.element == null ? "" : String.valueOf(this.element.getJoinedMembers()));
	}
}
