package org.fuchss.synapseadmin.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.fuchss.synapseadmin.server.ILoginProvider;
import org.fuchss.synapseadmin.server.ServerConf;
import org.fuchss.synapseadmin.util.IObserver;

public class Login extends Composite implements ILoginProvider {
	private Text textServer;
	private Text textToken;
	private IObserver callback;

	public Login(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(2, false));

		Label lblServer = new Label(this, SWT.NONE);
		lblServer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblServer.setText("Server");

		this.textServer = new Text(this, SWT.BORDER);
		this.textServer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblToken = new Label(this, SWT.NONE);
		lblToken.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblToken.setText("Token");

		this.textToken = new Text(this, SWT.BORDER);
		this.textToken.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button save = new Button(this, SWT.NONE);
		save.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		save.setText("Save");
		save.addListener(SWT.Selection, e -> this.informCallback());
	}

	public void updateServer() {
		this.textServer.setText(ServerConf.INSTANCE.getServer() == null ? "" : ServerConf.INSTANCE.getServer());
		this.textToken.setText(ServerConf.INSTANCE.getToken() == null ? "" : ServerConf.INSTANCE.getToken());
	}

	private void informCallback() {
		ServerConf.INSTANCE.updateServerLogin(this.textServer.getText(), this.textToken.getText());
		if (this.callback != null) {
			this.callback.inform();
		}
	}

	@Override
	public void registerObserver(IObserver callback) {
		this.callback = callback;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
