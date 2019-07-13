package como.taco.Events;

import como.taco.Client;
import como.taco.Modules;

public class EventHandler {
	public static void onEvent(Event e) {
		for(Modules m: Client.enabledMods) {
			m.Event(e);
		}
		
	}
	
}
