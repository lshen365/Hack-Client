package como.taco;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class RenderUtil {
	
	public static void tracer(double x, double y, double z, float lineWidth, float red, float green, float blue, float alpha) {
		boolean userViewbobbing = Minecraft.getMinecraft().gameSettings.viewBobbing;
        Minecraft.getMinecraft().gameSettings.viewBobbing = false;
   //     Minecraft.getMinecraft().gameSettings.viewBobbing = userViewbobbing;

        GL11.glBlendFunc( GL11.GL_SRC_ALPHA,  GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable( GL11.GL_BLEND);
        GL11.glEnable( GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(lineWidth);
        GL11.glDisable( GL11.GL_TEXTURE_2D);
        GL11.glDisable( GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        Vec3d eyes = new Vec3d(0, 0, 1).rotatePitch(-(float) Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float) Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        GL11.glBegin( GL11.GL_LINES);
        GL11.glVertex3d(eyes.xCoord, Minecraft.getMinecraft().player.getEyeHeight() + eyes.yCoord, eyes.zCoord);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glEnable( GL11.GL_TEXTURE_2D);
        GL11.glEnable( GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);

	}
	public static void entityMobESP(Entity entity)
    {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(1.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        
        Minecraft.getMinecraft().getRenderManager();
        RenderGlobal.drawSelectionBoundingBox(
            new AxisAlignedBB(
                entity.boundingBox.minX
                    - 0.05
                    - entity.posX
                    + (entity.posX - Minecraft.getMinecraft()
                        .getRenderManager().renderPosX),
                entity.boundingBox.minY
                    - entity.posY
                    + (entity.posY - Minecraft.getMinecraft()
                        .getRenderManager().renderPosY),
                entity.boundingBox.minZ
                    - 0.05
                    - entity.posZ
                    + (entity.posZ - Minecraft.getMinecraft()
                        .getRenderManager().renderPosZ),
                entity.boundingBox.maxX
                    + 0.05
                    - entity.posX
                    + (entity.posX - Minecraft.getMinecraft()
                        .getRenderManager().renderPosX),
                entity.boundingBox.maxY
                    + 0.1
                    - entity.posY
                    + (entity.posY - Minecraft.getMinecraft()
                        .getRenderManager().renderPosY),
                entity.boundingBox.maxZ
                    + 0.05
                    - entity.posZ
                    + (entity.posZ - Minecraft.getMinecraft()
                        .getRenderManager().renderPosZ)), 0,0,1,0.5F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);

    }
	public static void playerESP(Entity entity)
    {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(1.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        
        Minecraft.getMinecraft().getRenderManager();
        RenderGlobal.drawSelectionBoundingBox(
            new AxisAlignedBB(
                entity.boundingBox.minX
                    - 0.05
                    - entity.posX
                    + (entity.posX - Minecraft.getMinecraft()
                        .getRenderManager().renderPosX),
                entity.boundingBox.minY
                    - entity.posY
                    + (entity.posY - Minecraft.getMinecraft()
                        .getRenderManager().renderPosY),
                entity.boundingBox.minZ
                    - 0.05
                    - entity.posZ
                    + (entity.posZ - Minecraft.getMinecraft()
                        .getRenderManager().renderPosZ),
                entity.boundingBox.maxX
                    + 0.05
                    - entity.posX
                    + (entity.posX - Minecraft.getMinecraft()
                        .getRenderManager().renderPosX),
                entity.boundingBox.maxY
                    + 0.1
                    - entity.posY
                    + (entity.posY - Minecraft.getMinecraft()
                        .getRenderManager().renderPosY),
                entity.boundingBox.maxZ
                    + 0.05
                    - entity.posZ
                    + (entity.posZ - Minecraft.getMinecraft()
                        .getRenderManager().renderPosZ)),  1 - Minecraft.getMinecraft().player
            .getDistanceToEntity(entity) / 40,
        Minecraft.getMinecraft().player.getDistanceToEntity(entity) / 40,
        0, 0.5F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }
	public static void friendESP(Entity entity)
    {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(1.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        
        Minecraft.getMinecraft().getRenderManager();
        RenderGlobal.drawSelectionBoundingBox(
            new AxisAlignedBB(
                entity.boundingBox.minX
                    - 0.05
                    - entity.posX
                    + (entity.posX - Minecraft.getMinecraft()
                        .getRenderManager().renderPosX),
                entity.boundingBox.minY
                    - entity.posY
                    + (entity.posY - Minecraft.getMinecraft()
                        .getRenderManager().renderPosY),
                entity.boundingBox.minZ
                    - 0.05
                    - entity.posZ
                    + (entity.posZ - Minecraft.getMinecraft()
                        .getRenderManager().renderPosZ),
                entity.boundingBox.maxX
                    + 0.05
                    - entity.posX
                    + (entity.posX - Minecraft.getMinecraft()
                        .getRenderManager().renderPosX),
                entity.boundingBox.maxY
                    + 0.1
                    - entity.posY
                    + (entity.posY - Minecraft.getMinecraft()
                        .getRenderManager().renderPosY),
                entity.boundingBox.maxZ
                    + 0.05
                    - entity.posZ
                    + (entity.posZ - Minecraft.getMinecraft()
                        .getRenderManager().renderPosZ)), 0,1,0,0.5F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
