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

}