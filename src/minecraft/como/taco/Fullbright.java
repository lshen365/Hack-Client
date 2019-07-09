package como.taco;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings.Options;
public class Fullbright extends Modules {

	public Fullbright() {
		super("Brightness", Keyboard.KEY_B);
	}

	@Override
	public void onUpdate() {
		if(getStatus()) {
			mc.gameSettings.gammaSetting = 10F;
		} else {
			mc.gameSettings.gammaSetting = 1.0F;
		}
		
	}

	@Override
	public void onRender() {

		
	}
	
}
