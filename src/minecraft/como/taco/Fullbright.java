package como.taco;

import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings.Options;

public class Fullbright extends Hack {

	public Fullbright() {
		super("Brightness", Keyboard.KEY_B, ModCategories.RENDER);
	}

	@Override
	public void onUpdate() {
		if (getStatus()) {
			mc.gameSettings.gammaSetting = 10F;
		} else {
			mc.gameSettings.gammaSetting = 1.0F;
		}

	}

	@Override
	public void onRender() {

	}

	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = 1.0F;

	}

<<<<<<< HEAD

	@Override
	public void changeVariable(int num) {
		// TODO Auto-generated method stub
		
	}

=======
>>>>>>> branch 'master' of https://github.com/lshen365/Hack-Client.git
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

}
