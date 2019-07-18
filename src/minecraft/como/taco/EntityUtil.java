package como.taco;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class EntityUtil {
	
	public static List friends = new ArrayList<String>();
	
	public static float[] getRotationsNeeded(Entity entity)
	{
    	if(entity == null)
    		return null;
        double diffX = entity.posX - Minecraft.getMinecraft().player.posX;
        double diffZ = entity.posZ - Minecraft.getMinecraft().player.posZ;
        double diffY;
        if(entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            diffY = entityLivingBase.posY + (double)entityLivingBase.getEyeHeight() * 0.9 - (Minecraft.getMinecraft().player.posY + (double)Minecraft.getMinecraft().player.getEyeHeight());
        }else
            diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D - (Minecraft.getMinecraft().player.posY + (double)Minecraft.getMinecraft().player.getEyeHeight());
        double dist = (double)MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0D / Math.PI));
         return new float[] {
        		 Minecraft.getMinecraft().player.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().player.rotationYaw),
        		 Minecraft.getMinecraft().player.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().player.rotationPitch)
         };
    
	}
	
	public synchronized static void faceEntityClient(EntityLivingBase entity)
    {
    	float[] rotations = getRotationsNeeded(entity);
    	if (rotations != null)
    	{
            Minecraft.getMinecraft().player.rotationYaw = limitAngleChange(Minecraft.getMinecraft().player.prevRotationYaw, rotations[0], 55);//NoCheat+ bypass!!!
    		Minecraft.getMinecraft().player.rotationPitch = rotations[1];
    	}
    }
	
	private final static float limitAngleChange(final float current, final float intended, final float maxChange)
	{
		float change = intended - current;

		if(change > maxChange)
		{
			change = maxChange;
		} else if(change < -maxChange)
		{
			change = -maxChange;
		}

		return current + change;
	}
	
	
	
	public static void initFriends() {
		friends.add("Bobkooper1224");
		friends.add("starjust09");
		friends.add("StonePick");
	}
	public static String getFriend(int index) {
		return (String) friends.get(index);
	}
}
