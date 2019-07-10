package como.taco;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
public class ingameGUI {
	private Minecraft mc = Minecraft.getMinecraft();
	public void draw() {
		mc.fontRendererObj.drawStringWithShadow(Client.getName(), 0, 0, 0x3611CA);
		int y = 10;
		for(Modules mod:Client.modList) {
			if(mod.getStatus() == true) {
				mc.fontRendererObj.drawStringWithShadow(mod.getName(), 0, y, 0xE54216);
				y+=10;
			}
		}
	}
	
	
}
