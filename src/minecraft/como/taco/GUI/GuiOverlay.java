package como.taco.GUI;

import org.lwjgl.input.Keyboard;

import como.taco.Client;
import como.taco.Modules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiOverlay extends Modules {

	public GuiOverlay() {
		super("GUI", Keyboard.KEY_RSHIFT);
	}

	@Override
	public void onUpdate() {

		if (getStatus()) {
			changeStatus();
		}
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisable() {

	}

	@Override
	public void changeVariable(float num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		mc.displayGuiScreen(new ClickGUI());

	}

}
