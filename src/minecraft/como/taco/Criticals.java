package como.taco;

import como.taco.GUI.ModCategories;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketEntityVelocity;

public class Criticals extends Hack {
	boolean legit = false;

	public Criticals() {
		super("Criticals", ModCategories.COMBAT);

	}

	public void Crit() {
		if (getStatus()) {

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
		if (mc.player.isAirBorne) {
			System.out.println(mc.player.posY);
		}

	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	public void sendFallPacket() {
		if (!legit) {
			mc.player.connection.sendPacket(
					new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.2D, mc.player.posZ, false));
			mc.player.connection.sendPacket(
					new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.015D, mc.player.posZ, false));
			mc.player.connection
					.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
		} else {
			if (!mc.player.isInWater() && !mc.player.isInsideOfMaterial(Material.LAVA) && mc.player.onGround)
				mc.player.posY = mc.player.posY + 0.1;
			mc.player.connection.handleEntityVelocity(new SPacketEntityVelocity(mc.player.getEntityId(), 0, -0.1, 0));
			mc.player.fallDistance = 0.1F;
			mc.player.onGround = false;
		}
	}

	@Override
	public void changeVariable(float num) {
		// TODO Auto-generated method stub

	}

}
