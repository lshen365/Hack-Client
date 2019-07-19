package como.taco.Events;

import net.minecraft.network.Packet;

public class EventReceived extends Event {
	public boolean cancelled = false;
	
	public Packet p;
	
	public EventReceived(Packet p) {
		this.p = p;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
