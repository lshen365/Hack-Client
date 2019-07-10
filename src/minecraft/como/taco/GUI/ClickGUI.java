package como.taco.GUI;

import java.io.IOException;

import como.taco.Client;
import como.taco.Hack;
import como.taco.Modules;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ClickGUI extends GuiScreen {

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		/*
		 * Tints the background world.
		 */
		this.drawWorldBackground(3);
		ScaledResolution sr = new ScaledResolution(mc);
		drawRect(sr.getScaledWidth() * 1 / 6 - 10, 8, sr.getScaledWidth() * 1 / 6 + 100, 20, 0x80333366);
		mc.fontRendererObj.drawString("Render", sr.getScaledWidth() * 1 / 6, 10, 0x01579B);
		drawRect(sr.getScaledWidth() * 2 / 6 - 10, 8, sr.getScaledWidth() * 2 / 6 + 100, 20, 0x80333366);
		mc.fontRendererObj.drawString("World", sr.getScaledWidth() * 2 / 6, 10, 0x01579B);
		drawRect(sr.getScaledWidth() * 3 / 6 - 10, 8, sr.getScaledWidth() * 3 / 6 + 100, 20, 0x80333366);
		mc.fontRendererObj.drawString("Movement", sr.getScaledWidth() * 3 / 6, 10, 0x01579B);
		drawRect(sr.getScaledWidth() * 4 / 6 - 10, 8, sr.getScaledWidth() * 4 / 6 + 100, 20, 0x80333366);
		mc.fontRendererObj.drawString("Combat", sr.getScaledWidth() * 4 / 6, 10, 0x01579B);
		drawRect(sr.getScaledWidth() * 5 / 6 - 10, 8, sr.getScaledWidth() * 5 / 6 + 100, 20, 0x80333366);
		mc.fontRendererObj.drawString("Other", sr.getScaledWidth() * 5 / 6, 10, 0x01579B);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	/**
	 * This GUI does not pause the game in singleplayer
	 */
	public boolean doesGuiPauseGame() {
		return false;
	}

	public void initGui() {
		for (int i = 0; i < Client.modList.size(); i++) {
			Modules m = Client.modList.get(i);
			if (m instanceof Hack) {
				buttonList.add(new GuiButton(i, modXPos((Hack) m), modYPos((Hack) m), 110, 14, m.getName()));
			}
		}
	}

	public void actionPerformed(GuiButton button) throws IOException {
		for (int i = 1; i < Client.modList.size(); i++) {
			if (button.id == i) {
				Client.modList.get(i).changeStatus();
			}
		}
	}

	public int modYPos(Hack m) {
		switch (m.getType()) {
		case RENDER:
			return ModCategories.placeInList(m, ModCategories.RENDER) * 14 + 24;
		case WORLD:
			return ModCategories.placeInList(m, ModCategories.WORLD) * 14 + 24;
		case MOVEMENT:
			return ModCategories.placeInList(m, ModCategories.MOVEMENT) * 14 + 24;
		case COMBAT:
			return ModCategories.placeInList(m, ModCategories.COMBAT) * 14 + 24;
		case OTHER:
			return ModCategories.placeInList(m, ModCategories.OTHER) * 24;
		default:
			return 0;
		}
	}

	public int modXPos(Hack m) {
		ScaledResolution sr = new ScaledResolution(mc);
		switch (m.getType()) {
		case RENDER:
			return sr.getScaledWidth() * 1 / 6 - 10;
		case WORLD:
			return sr.getScaledWidth() * 2 / 6 - 10;
		case MOVEMENT:
			return sr.getScaledWidth() * 3 / 6 - 10;
		case COMBAT:
			return sr.getScaledWidth() * 4 / 6 - 10;
		case OTHER:
			return sr.getScaledWidth() * 5 / 6 - 10;
		default:
			return 0;
		}
	}
}
