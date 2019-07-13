package como.taco.Events;

import net.minecraft.network.Packet;

public class EventSendPacket extends Event {
	public boolean cancel = false;
	public Packet p;

	public EventSendPacket(Packet p) {
		this.p = p;
	}
}
