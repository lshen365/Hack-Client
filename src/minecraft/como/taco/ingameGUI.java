package como.taco;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
public class ingameGUI {
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void draw() {
		mc.fontRendererObj.drawStringWithShadow("TacoHacks", 0, 0, 0x3611CA);
	}
	
	
}
