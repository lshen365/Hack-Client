package como.taco.GUI;

import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import como.taco.Client;
import como.taco.Hack;
import como.taco.MobAura;
import como.taco.Modules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListButton;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiPageButtonList.GuiSlideEntry;
import net.minecraft.client.gui.GuiSlider.FormatHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.ScaledResolution;

public class ClickGUI extends GuiScreen implements GuiSlider.FormatHelper{
	//Creates new Responder and formatter which will be used for all Sliders
	private GuiPageButtonList.GuiResponder guiResponder;
	private FormatHelper formatter;
	
	//Keeps track of all Slider positions
	private float mobAuraSliderPosition, flightSpeed;
	//All the Slider names
	private GuiSliderFixed mobAuraSlider;
	
	
	public void setGuiResponder()
	{
		guiResponder = new GuiResponder() {
			
			@Override
			public void setEntryValue(int id, String value) {

				
			}
			
			@Override
			public void setEntryValue(int id, float value) {

				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setEntryValue(int id, boolean value) {


				// TODO Auto-generated method stub
				
			}
		};

	}

	public void initSlider() {
		try {
			readFile();
			ScaledResolution sr = new ScaledResolution(mc);
			setGuiResponder();

			mobAuraSlider = new GuiSliderFixed(guiResponder,Client.modList.get(4).getName().hashCode() , 5, sr.getScaledHeight() - 25, "Clicks Per Second", 3,14 , mobAuraSliderPosition, this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
 
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
		initSlider();
		buttonList.add(mobAuraSlider);
		
	}

	public void actionPerformed(GuiButton button) throws IOException {
		for (int i = 1; i < Client.modList.size(); i++) {
			if (button.id == i) {
				Client.modList.get(i).changeStatus();
			}
		}

	}
	
	
	private void readFile() throws FileNotFoundException {
		String computerName = System.getProperty("user.name"); //platform independent 
	//	String filePath = Paths.get("GUISettings.txt").toAbsolutePath().toString();
		File readFile = new File("C:\\Users\\"+computerName+"\\Appdata\\Roaming\\.minecraft\\GUISettings.txt");
		
		Scanner readInput = new Scanner(readFile);
		readInput.useDelimiter(" ");
		while(readInput.hasNextLine()) {
			readInput.next();
			mobAuraSliderPosition = Float.parseFloat(readInput.next());
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
	
	//Function that rounds values for the getText which runs faster than Math.round
	private static float round(float value, int scale) {
	    int pow = 10;
	    for (int i = 1; i < scale; i++) {
	        pow *= 10;
	    }
	    float tmp = value * pow;
	    float tmpSub = tmp - (int) tmp;

	    return ( (float) ( (int) (
	            value >= 0
	            ? (tmpSub >= 0.5f ? tmp + 1 : tmp)
	            : (tmpSub >= -0.5f ? tmp : tmp - 1)
	            ) ) ) / pow;

	}
	@Override
	public String getText(int id, String name, float value) {
		
		return name + ": " +round(value, 2);
	}
}
