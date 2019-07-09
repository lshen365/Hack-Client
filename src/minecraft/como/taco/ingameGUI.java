package como.taco;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
public class ingameGUI {
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void draw() {
		//System.out.println(name);
		mc.fontRendererObj.drawStringWithShadow(Client.getName(), 0, 0, 0x3611CA);
	}
	
	
}
