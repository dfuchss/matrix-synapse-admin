package org.fuchss.synapseadmin.ui.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.fuchss.synapseadmin.api.User;
import org.fuchss.synapseadmin.dto.MatrixUser;
import org.fuchss.synapseadmin.ui.dto.UserComposite;
import org.fuchss.tools.tuple.Tuple2;

public class UserEndpointComposite extends EndpointComposite<User, MatrixUser, UserComposite> {

	public UserEndpointComposite(Composite parent, int style) {
		super(parent, style);

	}

	public UserEndpointComposite(Composite parent, int style, User endpoint) {
		super(parent, style, endpoint);

	}

	@Override
	protected List<Tuple2<MatrixUser, String>> fill() {
		List<Tuple2<MatrixUser, String>> data = new ArrayList<>();
		for (MatrixUser user : this.getEndpoint().getUsers()) {
			data.add(Tuple2.of(user, user.prettyString()));
		}
		return data;
	}

	@Override
	protected UserComposite createComposite(EndpointComposite<User, MatrixUser, UserComposite> parent, int style) {
		return new UserComposite(parent, style);
	}

}
