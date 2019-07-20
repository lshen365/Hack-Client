package como.taco.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import como.taco.*;

public class GuiSliderFixed extends GuiButton {
	public static ArrayList<GuiSliderFixed> SliderList = new ArrayList<GuiSliderFixed>();
	private Modules Mod;
	private float sliderPosition = 1.0F;
	public boolean isMouseDown;
	private final String name;
	private final float min;
	private final float max;
	private final GuiPageButtonList.GuiResponder responder;
	private GuiSlider.FormatHelper formatHelper;
	public static float lastKnownPosition;

	public GuiSliderFixed(GuiPageButtonList.GuiResponder guiResponder, int idIn, int x, int y, String name, float min,
			float max, float defaultValue, GuiSlider.FormatHelper formatter, Modules Mod) {
		super(idIn, x, y, 150, 20, "");
		this.name = name;
		this.min = min;
		this.max = max;
		this.sliderPosition = (defaultValue - min) / (max - min);
		this.formatHelper = formatter;
		this.responder = guiResponder;
		this.displayString = this.getDisplayString();
		this.Mod = Mod;
		SliderList.add(this);
	}

	public float getSliderValue() {
		return this.min + (this.max - this.min) * this.sliderPosition;
	}

	public void setSliderValue(float p_175218_1_, boolean p_175218_2_) {
		this.sliderPosition = (p_175218_1_ - this.min) / (this.max - this.min);
		this.displayString = this.getDisplayString();

		if (p_175218_2_) {
			this.responder.setEntryValue(this.id, this.getSliderValue());
		}
	}

	public float getSliderPosition() {
		return this.sliderPosition;
	}

	private String getDisplayString() {
		return this.formatHelper == null ? I18n.format(this.name) + ": " + this.getSliderValue()
				: this.formatHelper.getText(this.id, I18n.format(this.name), this.getSliderValue());
	}

	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this
	 * button and 2 if it IS hovering over this button.
	 */
	protected int getHoverState(boolean mouseOver) {
		return 0;
	}

	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			if (this.isMouseDown) {
				this.sliderPosition = (float) (mouseX - (this.xPosition + 4)) / (float) (this.width - 8);
				formatHelper.getText("ma".hashCode(), "ClickSpeed", getSliderValue());
				if (this.sliderPosition < 0.0F) {
					this.sliderPosition = 0.0F;
				}

				if (this.sliderPosition > 1.0F) {
					this.sliderPosition = 1.0F;
				}

				this.displayString = this.getDisplayString();
				this.responder.setEntryValue(this.id, this.getSliderValue());
			}
			changeModValue(Client.getNonEnabledMod(Mod), this.getSliderValue());

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(this.xPosition + (int) (this.sliderPosition * (float) (this.width - 8)),
					this.yPosition, 0, 66, 4, 20);
			this.drawTexturedModalRect(this.xPosition + (int) (this.sliderPosition * (float) (this.width - 8)) + 4,
					this.yPosition, 196, 66, 4, 20);
		}
	}

	public void setSliderPosition(float p_175219_1_) {
		this.sliderPosition = p_175219_1_;
		this.displayString = this.getDisplayString();
		this.responder.setEntryValue(this.id, this.getSliderValue());
	}

	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		if (super.mousePressed(mc, mouseX, mouseY)) {
			this.sliderPosition = (float) (mouseX - (this.xPosition + 4)) / (float) (this.width - 8);

			if (this.sliderPosition < 0.0F) {
				this.sliderPosition = 0.0F;
			}

			if (this.sliderPosition > 1.0F) {
				this.sliderPosition = 1.0F;
			}

			this.displayString = this.getDisplayString();
			this.responder.setEntryValue(this.id, this.getSliderValue());
			this.isMouseDown = true;
			return true;
		} else {
			return false;
		}
	}

	public void changeModValue(Modules name, float position) {
		name.changeVariable(position);
		try {
			changeFile(name.getName());
		} catch (IOException e) {

		}

	}

	private void changeFile(String name) throws IOException {
		Minecraft mc = Minecraft.getMinecraft();
		String computerName = System.getProperty("user.name");
		String filePath = "C:\\Users\\" + computerName + "\\Appdata\\Roaming\\.minecraft\\GUISettings.txt";
		File readFile = new File(filePath);
		Scanner readInput = new Scanner(readFile);
		FileWriter writeOutput = new FileWriter(filePath, false);
		for (int i = 0; i < SliderList.size(); i++) {
			if (i + 1 == SliderList.size()) {
				writeOutput.write(SliderList.get(i).Mod.getName() + " " + SliderList.get(i).getSliderValue());
			} else {
				writeOutput.write(SliderList.get(i).Mod.getName() + " " + SliderList.get(i).getSliderValue() + " ");
			}
		}
		writeOutput.flush();
	}

	/**
	 * Fired when the mouse button is released. Equivalent of
	 * MouseListener.mouseReleased(MouseEvent e).
	 */
	public void mouseReleased(int mouseX, int mouseY) {
		this.isMouseDown = false;

	}

	public interface FormatHelper {
		String getText(int id, String name, float value);
	}

}
