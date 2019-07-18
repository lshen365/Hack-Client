package como.taco;

import java.util.ArrayList;
import java.util.List;

import como.taco.Events.Event;
import net.minecraft.client.Minecraft;

public abstract class Modules {
	String name;
	int keybind;
	boolean status; // If hacks are on or off

	protected Minecraft mc = Minecraft.getMinecraft();

	public Modules(String name, int key) {
		this.name = name;
		keybind = key;
	}
	
	public Modules(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKeybind() {
		return keybind;
	}

	public void setKeybind(int keybind) {
		this.keybind = keybind;
	}

	public boolean getStatus() {
		return status;
	}

	public void changeStatus() { // Toggles the hacks to the opposite of what it was
		this.status = !this.status;
		if(status == true) {
			onEnable();
		}
		if(status == false) {
			onDisable();
		}
	}
	
	public abstract void onDisable();
	
	public abstract void onEnable();

	public void onPressed(int key) {
		if (keybind == key)
			changeStatus();
	}
	
	public void Event(Event e) {
		
	}
	

	public abstract void onUpdate();

	public abstract void onRender();
	
	public abstract void changeVariable(int num);

}
