package org.fuchss.synapse_admin.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.fuchss.swt.SWTShell;
import org.fuchss.synapse_admin.api.Room;
import org.fuchss.synapse_admin.api.User;
import org.fuchss.synapse_admin.api.Version;
import org.fuchss.synapse_admin.server.Server;
import org.fuchss.synapse_admin.server.ServerConf;
import org.fuchss.synapse_admin.ui.endpoint.RoomEndpointComposite;
import org.fuchss.synapse_admin.ui.endpoint.UserEndpointComposite;
import org.fuchss.synapse_admin.util.IObserver;

public class MainFrame extends SWTShell implements IObserver {

	private Server server;

	private StackLayout stackLayout;

	private UserEndpointComposite userEndpoint;
	private RoomEndpointComposite roomEndpoint;

	private Label matrixVersion;
	private Login login;

	public MainFrame(Display display) {
		super(display, SWT.SHELL_TRIM, false);
		this.setLayout(new GridLayout(1, false));
		this.createContents();
		this.centerMe();
		this.inform();
	}

	@Override
	protected void createContents() {
		this.setText("Matrix Server Admin");
		this.setSize(1024, 800);

		this.createMainView();
		this.createMenu();
		this.createFooter();
	}

	private void setServer(Server server) {
		this.server = server;
		this.userEndpoint.setEndpoint(new User(this.server));
		this.roomEndpoint.setEndpoint(new Room(this.server));
		this.updateFooter();
	}

	private void createMainView() {
		Composite view = new Composite(this, SWT.NONE);
		view.setLayout(this.stackLayout = new StackLayout());
		view.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		view.setBounds(0, 0, 64, 64);

		this.login = new Login(view, SWT.NONE);
		this.login.registerObserver(this);

		this.userEndpoint = new UserEndpointComposite(view, SWT.NONE);
		this.roomEndpoint = new RoomEndpointComposite(view, SWT.NONE);
		this.stackLayout.topControl = this.userEndpoint;
	}

	private void createMenu() {
		Menu menu = new Menu(this, SWT.BAR);
		this.setMenuBar(menu);

		MenuItem mntmServer = new MenuItem(menu, SWT.CASCADE);
		mntmServer.setText("Server");
		mntmServer.addListener(SWT.Selection, e -> this.switchTo(this.login));

		MenuItem mntmEndpoint = new MenuItem(menu, SWT.CASCADE);
		mntmEndpoint.setText("Endpoint");

		Menu menuEndpoint = new Menu(mntmEndpoint);
		mntmEndpoint.setMenu(menuEndpoint);

		MenuItem mntmRooms = new MenuItem(menuEndpoint, SWT.NONE);
		mntmRooms.setText("Rooms");
		mntmRooms.addListener(SWT.Selection, e -> this.switchTo(this.roomEndpoint));

		MenuItem mntmUsers = new MenuItem(menuEndpoint, SWT.NONE);
		mntmUsers.setText("Users");
		mntmUsers.addListener(SWT.Selection, e -> this.switchTo(this.userEndpoint));
	}

	private void switchTo(Composite top) {
		this.stackLayout.topControl = top;
		top.getParent().layout();
	}

	private void createFooter() {
		Composite footer = new Composite(this, SWT.NONE);
		footer.setLayout(new GridLayout(1, false));
		footer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		footer.setBounds(0, 0, 64, 64);

		this.matrixVersion = new Label(footer, SWT.NONE);
		this.matrixVersion.setAlignment(SWT.RIGHT);
		this.matrixVersion.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		this.matrixVersion.setBounds(0, 0, 81, 25);
	}

	private void updateFooter() {
		this.matrixVersion.setText(this.server == null ? "Not Connected" : String.valueOf((new Version(this.server).getVersion())));
	}

	@Override
	public void inform() {
		this.setServer(ServerConf.newServerInstance());
		this.login.updateServer();
	}
}
