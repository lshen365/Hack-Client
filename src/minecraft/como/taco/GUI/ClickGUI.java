package como.taco.GUI;

import java.io.IOException;

import como.taco.Client;
import como.taco.Hack;
import como.taco.Modules;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class ClickGUI extends GuiScreen {

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		/*
		 * Tints the background world.
		 */
		this.drawWorldBackground(3);
		drawRect(150, 2, 220, 14, 0x01579B);
		mc.fontRendererObj.drawString("Render", 152, 4, 0x01579B);
		drawRect(250, 2, 322, 14, 0x01579B);
		mc.fontRendererObj.drawString("World", 252, 4, 0x01579B);
		drawRect(350, 2, 424, 14, 0x01579B);
		mc.fontRendererObj.drawString("Movement", 352, 4, 0x01579B);
		drawRect(450, 2, 526, 14, 0x01579B);
		mc.fontRendererObj.drawString("Combat", 452, 4, 0x01579B);
		drawRect(550, 2, 628, 14, 0x01579B);
		mc.fontRendererObj.drawString("Other", 552, 4, 0x01579B);
		drawGUI();
		super.drawScreen(mouseX, mouseY, partialTicks);

	}

	/**
	 * This GUI does not pause the game in singleplayer
	 */
	public boolean doesGuiPauseGame() {
		return false;
	}

	public void drawGUI() {
		for (int i = 0; i < Client.modList.size(); i++) {
			Modules m = Client.modList.get(i);
			if (m instanceof Hack) {
				buttonList.add(new GuiButton(i, modXPos((Hack) m), modYPos((Hack) m), 70, 14, m.getName()));
			}
		}
	}

	public void perforAction(GuiButton button) throws IOException {
		for (int i = 1; i < Client.modList.size(); i++) {
			Client.modList.get(i).changeStatus();
		}
	}

	public int modYPos(Hack m) {
		switch (m.getType()) {
		case RENDER:
			return ModCategories.placeInList(m, ModCategories.RENDER) * 14;
		case WORLD:
			return ModCategories.placeInList(m, ModCategories.WORLD) * 14;
		case MOVEMENT:
			return ModCategories.placeInList(m, ModCategories.MOVEMENT) * 14;
		case COMBAT:
			return ModCategories.placeInList(m, ModCategories.COMBAT) * 14;
		case OTHER:
			return ModCategories.placeInList(m, ModCategories.OTHER) * 14;
		default:
			return 0;
		}
	}

	public int modXPos(Hack m) {
		switch (m.getType()) {
		case RENDER:
			return 150;
		case WORLD:
			return 250;
		case MOVEMENT:
			return 350;
		case COMBAT:
			return 450;
		case OTHER:
			return 550;
		default:
			return 0;
		}
	}
}
