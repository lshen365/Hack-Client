package como.taco;
import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;
public class Flight extends Hack {
	public Flight() {
		super("Flying" , Keyboard.KEY_F, ModCategories.MOVEMENT);
	}
	
	public void onDisable() {
		mc.player.capabilities.isFlying = false;
	}

	@Override
	public void onUpdate() {
		if(getStatus()) {
			mc.player.capabilities.isFlying = true;
		}	
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub
		
	}
}