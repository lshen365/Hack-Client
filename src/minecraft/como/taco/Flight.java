package como.taco;
import org.lwjgl.input.Keyboard;
public class Flight extends Modules {
	public Flight() {
		super("Flying" , Keyboard.KEY_F);
	}

	@Override
	public void onUpdate() {
		if(getStatus()) {
			mc.player.capabilities.isFlying = true;
		}else {
			mc.player.capabilities.isFlying = false;
		}
		
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub
		
	}
}
