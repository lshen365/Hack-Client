package como.taco;

import como.taco.GUI.ModCategories;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import como.taco.GUI.ModCategories;
public class Tracer extends Hack{
	
	private boolean friend = true, player = true, mob = false;
	public static double ticks;
	private final ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();
	private final ArrayList<EntityMob> mobs = new ArrayList<EntityMob>();
	public Tracer() {
		super("Tracer", Keyboard.KEY_P,ModCategories.RENDER);
		
	}
	
	public void changeFriend() {
		friend = !friend;
	}
	public void changePlayer() {
		player = !player;
	}

	public void changeMob() {
		mob = !mob;
	}
	
	public boolean getFriend() {
		return friend;
	}
	public boolean getPlayer() {
		return player;
	}
	public boolean getMob() {
		return mob;
	}
	public String printStatus(boolean name) {
		if (name) {
			return "Enabled";
		}
		return "Disabled";
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
		if(friend || player) {
			players.clear();
			Stream<EntityPlayer> stream = mc.world.playerEntities.parallelStream()
				.filter(e -> !e.isDead && e.getHealth() > 0)
				.filter(e -> e != mc.player)
				.filter(e -> Math.abs(e.posY - mc.player.posY) <= 1e6)
				.filter(e -> !e.isInvisible());
			players.addAll(stream.collect(Collectors.toList()));
		}
		
		if(mob) {
			mobs.clear();
			for(Object obj :mc.world.loadedEntityList) {
				if(!(obj instanceof EntityMob)) {
					continue;
				}
				mobs.add((EntityMob)obj);
			}
		}
		
	}

    private void drawTracer(float red, float green, float blue, float alpha, EntityLivingBase entityLivingBase) {

        double renderPosX = mc.getRenderManager().viewerPosX;
        double renderPosY = mc.getRenderManager().viewerPosY;
        double renderPosZ = mc.getRenderManager().viewerPosZ;
        double xPos = (entityLivingBase.lastTickPosX + (entityLivingBase.posX - entityLivingBase.lastTickPosX) * ticks) - renderPosX;
        double yPos = (entityLivingBase.lastTickPosY + (entityLivingBase.posY - entityLivingBase.lastTickPosY) * ticks) - renderPosY;
        double zPos = (entityLivingBase.lastTickPosZ + (entityLivingBase.posZ - entityLivingBase.lastTickPosZ) * ticks) - renderPosZ;
//        LogHelper.info("X:" + x + " Y:" + y + " Z:" + z);
        RenderUtil.tracer(xPos, yPos, zPos, 1, red, green, blue, alpha);
    }


	@Override
	public void onRender() {
		if(getStatus()) {
			if(friend || player)
				renderPlayers();
			if(mob)
				renderMobs();

		}
		
	}
	
	private void renderPlayers() {
		for(EntityPlayer e : players)
		{
			if(EntityUtil.isFriend(e.getName()) && friend){
				RenderUtil.friendESP(e);
				drawTracer(0, 1, 0, 0.5f, e);
				continue;
			}
			if(player) {
				RenderUtil.playerESP(e);
				drawTracer(1, 0, 0, 0.5f, e);
			}
			

		}
	}
	private void renderMobs() {
		for(EntityMob e: mobs) {
			RenderUtil.entityMobESP(e);
			drawTracer(0, 0, 1, 0.5f, e);
		}
	}

	@Override
	public void changeVariable(int num) {
		// TODO Auto-generated method stub
		
	}

}
