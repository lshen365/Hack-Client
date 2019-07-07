package como.taco;

public class Modules {
	String name;
	int keybind;
	boolean status; //If hacks are on or off
	
	public Modules(String name, int key) {
		this.name = name;
		keybind = key;
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
	public void changeStatus(boolean status) { //Toggles the hacks to the opposite of what it was
		this.status = !status;
	}
	
	
	public void onTick() {
		
	}
	
	
}
