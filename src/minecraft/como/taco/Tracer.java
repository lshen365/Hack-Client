package como.taco;

import como.taco.GUI.ModCategories;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;
public class Tracer extends Hack{
	
	private boolean friend = true, player = true, mob = false;
	public static double ticks;
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

		
	}

    private void drawTracer(float red, float green, float blue, float alpha, EntityLivingBase entityLivingBase) {

        double renderPosX = mc.getRenderManager().viewerPosX;
        double renderPosY = mc.getRenderManager().viewerPosY;
        double renderPosZ = mc.getRenderManager().viewerPosZ;
        double xPos = (entityLivingBase.lastTickPosX + (entityLivingBase.posX - entityLivingBase.lastTickPosX) * ticks) - renderPosX;
        double yPos = (entityLivingBase.lastTickPosY + (entityLivingBase.posY - entityLivingBase.lastTickPosY) * ticks) - renderPosY;
        double zPos = (entityLivingBase.lastTickPosZ + (entityLivingBase.posZ - entityLivingBase.lastTickPosZ) * ticks) - renderPosZ;
//        LogHelper.info("X:" + x + " Y:" + y + " Z:" + z);
        RenderUtil.tracer(xPos, yPos, zPos, 2, red, green, blue, alpha);
    }


	@Override
	public void onRender() {
		if(getStatus()) {
			for(Object obj: mc.world.loadedEntityList) {
				if(!(obj instanceof EntityLivingBase))
					continue;
				Entity friend = (Entity)obj;
				if(obj instanceof EntityPlayer && EntityUtil.isFriend(friend.getName()) && friend != mc.player && this.friend) {
					RenderUtil.friendESP(friend);
					drawTracer(0f, 1f, 1f, 0.5f, (EntityLivingBase)friend);
				}
				
				if(obj instanceof EntityPlayer && !EntityUtil.isFriend(friend.getName()) && friend != mc.player && player) {
					RenderUtil.playerESP(friend);
					drawTracer(1f, .3f, .6f, 0.5f, (EntityLivingBase)friend);
				}
				if(obj instanceof EntityMob && mob) {
					RenderUtil.entityMobESP(friend);
					drawTracer(0f, 0.3f, 0.3f, 0.5f, (EntityLivingBase)friend);
				}
			}

		}
		
	}

	@Override
	public void changeVariable(int num) {
		// TODO Auto-generated method stub
		
	}

}
