package org.fuchss.synapse_admin.ui.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.fuchss.synapse_admin.api.Room;
import org.fuchss.synapse_admin.dto.MatrixRoom;
import org.fuchss.synapse_admin.ui.dto.RoomComposite;
import org.fuchss.tools.tuple.Tuple2;

public class RoomEndpointComposite extends EndpointComposite<Room, MatrixRoom, RoomComposite> {

	public RoomEndpointComposite(Composite parent, int style) {
		super(parent, style);
	}

	public RoomEndpointComposite(Composite parent, int style, Room endpoint) {
		super(parent, style, endpoint);
	}

	@Override
	protected List<Tuple2<MatrixRoom, String>> fill() {
		List<Tuple2<MatrixRoom, String>> data = new ArrayList<>();
		for (MatrixRoom room : this.getEndpoint().getRooms()) {
			data.add(Tuple2.of(room, room.prettyString()));
		}
		return data;
	}

	@Override
	protected RoomComposite createComposite(EndpointComposite<Room, MatrixRoom, RoomComposite> parent, int style) {
		return new RoomComposite(parent, style);
	}

}
