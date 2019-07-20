package como.taco;

import como.taco.GUI.ModCategories;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketEntityVelocity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import como.taco.Events.Event;
import como.taco.Events.EventReceived;
import como.taco.Events.Timer;

public class AntiKB extends Hack {

	public Timer timer = new Timer();

	private float percent = 0.5f;

	public AntiKB() {
		super("AntiKB", ModCategories.MOVEMENT);
		setPercent();
	}

	public void setPercent() {
		try {
			String computerName = System.getProperty("user.name");
			String filePath = "C:\\Users\\" + computerName + "\\Appdata\\Roaming\\.minecraft\\GUISettings.txt";
			File readFile = new File(filePath);
			Scanner readInput = new Scanner(readFile);
			readInput.useDelimiter(" ");
			while (readInput.hasNextLine()) {
				readInput.next();
				readInput.next();
				readInput.next();
				percent = Float.parseFloat(readInput.next());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdate() {

	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	public void changePercent(float percent) {
		this.percent = percent;
		if (percent > 1.0f) {
			percent = 1.0f;
		} else if (percent < 0.0f) {
			percent = 0.0f;
		}
	}

	public void changeVariable(float f) {
		percent = f;
	}

	public void Event(Event e) {
		Packet packetIn = null;
		if (e instanceof EventReceived) {
			packetIn = ((EventReceived) e).p;
		} else {
			return;
		}
		if (packetIn instanceof CPacketUseEntity) {
			CPacketUseEntity ue = (CPacketUseEntity) packetIn;
			if (ue.getAction() == CPacketUseEntity.Action.ATTACK) {
				timer.reset();
			}
		} else if (packetIn instanceof SPacketEntityVelocity) {
			SPacketEntityVelocity p = (SPacketEntityVelocity) packetIn;
			if (p.getEntityID() == mc.player.getEntityId()) {
				if (percent >= 1.0f) {
					((EventReceived) e).cancelled = true;
				} else {
					((EventReceived) e).cancelled = true;
					double motionX = (double) (((SPacketEntityVelocity) packetIn).motionX * (1.0f - percent));
					double motionY = (double) (((SPacketEntityVelocity) packetIn).motionY * (1.0f - percent));
					double motionZ = (double) (((SPacketEntityVelocity) packetIn).motionZ * (1.0f - percent));
					mc.player.motionX = motionX / 8000.0D;
					mc.player.motionY = motionY / 8000.0D;
					mc.player.motionZ = motionZ / 8000.0D;

				}
			}
		}
	}

}
