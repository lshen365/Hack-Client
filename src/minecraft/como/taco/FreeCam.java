package como.taco;

import org.lwjgl.input.Keyboard;
import como.taco.Events.Event;
import como.taco.Events.EventSendPacket;
import como.taco.GUI.ModCategories;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.util.math.BlockPos;

public class FreeCam extends Hack {
	public BlockPos initialPos;
	private EntityOtherPlayerMP fakePlayer = null;

	public FreeCam() {
		super("Freecam", Keyboard.KEY_U, ModCategories.WORLD);
		// TODO Auto-generated constructor stub
	}

	public void onUpdate() {
		if (!getStatus()) {
			return;
		}
		mc.player.capabilities.isFlying = true;
		mc.player.noClip = true;
		if (!mc.gameSettings.keyBindForward.isKeyDown() && !mc.gameSettings.keyBindBack.isKeyDown()
				&& !mc.gameSettings.keyBindRight.isKeyDown() && !mc.gameSettings.keyBindLeft.isKeyDown()) {
			mc.player.motionX = 0;
			mc.player.motionZ = 0;
		}

	}

	public void Event(Event e) {
		if (e instanceof EventSendPacket) {
			EventSendPacket ep = (EventSendPacket) e;
			ep.cancel = true;
		}
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisable() {
		mc.player.noClip = false;
		mc.player.capabilities.isFlying = false;
		mc.player.setPosition(initialPos.getX(), initialPos.getY(), initialPos.getZ());
		mc.world.removeEntityFromWorld(-2);
		fakePlayer = null;
	}

	@Override
	public void onEnable() {
		initialPos = mc.player.getPosition();

		fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
		fakePlayer.inventory = mc.player.inventory;
		fakePlayer.inventoryContainer = mc.player.inventoryContainer;
		fakePlayer.setPositionAndRotation(mc.player.posX, mc.player.posY, mc.player.posZ, mc.player.rotationYaw,
				mc.player.rotationPitch);
		fakePlayer.rotationYawHead = mc.player.rotationYawHead;
		mc.world.addEntityToWorld(-2, fakePlayer);

	}

	@Override
	public void changeVariable(int num) {
		// TODO Auto-generated method stub

	}

}
