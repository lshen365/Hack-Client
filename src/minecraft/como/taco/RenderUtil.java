package como.taco;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glScissor;
import static org.lwjgl.opengl.GL11.glVertex3d;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.AxisAlignedBB;

public class RenderUtil
{
	private static final AxisAlignedBB DEFAULT_AABB =
		new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	
	public static void scissorBox(int x, int y, int xend, int yend)
	{
		int width = xend - x;
		int height = yend - y;
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int factor = sr.getScaleFactor();
		int bottomY = Minecraft.getMinecraft().currentScreen.height - yend;
		glScissor(x * factor, bottomY * factor, width * factor,
			height * factor);
	}
	
	public static void setColor(Color c)
	{
		glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f,
			c.getAlpha() / 255f);
	}
	
	public static void drawSolidBox()
	{
		drawSolidBox(DEFAULT_AABB);
	}
	
	public static void drawSolidBox(AxisAlignedBB bb)
	{
		glBegin(GL_QUADS);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawOutlinedBox()
	{
		drawOutlinedBox(DEFAULT_AABB);
	}
	
	public static void drawOutlinedBox(AxisAlignedBB bb)
	{
		glBegin(GL_LINES);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawCrossBox()
	{
		drawCrossBox(DEFAULT_AABB);
	}
	
	public static void drawCrossBox(AxisAlignedBB bb)
	{
		glBegin(GL_LINES);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawNode(AxisAlignedBB bb)
	{
		double midX = (bb.minX + bb.maxX) / 2;
		double midY = (bb.minY + bb.maxY) / 2;
		double midZ = (bb.minZ + bb.maxZ) / 2;
		
		GL11.glVertex3d(midX, midY, bb.maxZ);
		GL11.glVertex3d(bb.minX, midY, midZ);
		
		GL11.glVertex3d(bb.minX, midY, midZ);
		GL11.glVertex3d(midX, midY, bb.minZ);
		
		GL11.glVertex3d(midX, midY, bb.minZ);
		GL11.glVertex3d(bb.maxX, midY, midZ);
		
		GL11.glVertex3d(bb.maxX, midY, midZ);
		GL11.glVertex3d(midX, midY, bb.maxZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(bb.maxX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(bb.minX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(midX, midY, bb.minZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(midX, midY, bb.maxZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(bb.maxX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(bb.minX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(midX, midY, bb.minZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(midX, midY, bb.maxZ);
	}
	
	public static void initXrayBlocks() {
        Xray.xrayBlocks.add(Block.getBlockFromName("coal_ore"));
        Xray.xrayBlocks.add(Block.getBlockFromName("iron_ore"));
        Xray.xrayBlocks.add(Block.getBlockFromName("gold_ore"));
        Xray.xrayBlocks.add(Block.getBlockFromName("redstone_ore"));
        Xray.xrayBlocks.add(Block.getBlockById(74));
        Xray.xrayBlocks.add(Block.getBlockFromName("lapis_ore"));
        Xray.xrayBlocks.add(Block.getBlockFromName("diamond_ore"));
        Xray.xrayBlocks.add(Block.getBlockFromName("emerald_ore"));
        Xray.xrayBlocks.add(Block.getBlockFromName("quartz_ore"));
        Xray.xrayBlocks.add(Block.getBlockFromName("clay"));
        Xray.xrayBlocks.add(Block.getBlockFromName("glowstone"));
        Xray.xrayBlocks.add(Block.getBlockById(8));
        Xray.xrayBlocks.add(Block.getBlockById(9));
        Xray.xrayBlocks.add(Block.getBlockById(10));
        Xray.xrayBlocks.add(Block.getBlockById(11));
        Xray.xrayBlocks.add(Block.getBlockFromName("crafting_table"));
        Xray.xrayBlocks.add(Block.getBlockById(61));
        Xray.xrayBlocks.add(Block.getBlockById(62));
        Xray.xrayBlocks.add(Block.getBlockFromName("torch"));
        Xray.xrayBlocks.add(Block.getBlockFromName("ladder"));
        Xray.xrayBlocks.add(Block.getBlockFromName("tnt"));
        Xray.xrayBlocks.add(Block.getBlockFromName("coal_block"));
        Xray.xrayBlocks.add(Block.getBlockFromName("iron_block"));
        Xray.xrayBlocks.add(Block.getBlockFromName("gold_block"));
        Xray.xrayBlocks.add(Block.getBlockFromName("diamond_block"));
        Xray.xrayBlocks.add(Block.getBlockFromName("emerald_block"));
        Xray.xrayBlocks.add(Block.getBlockFromName("redstone_block"));
        Xray.xrayBlocks.add(Block.getBlockFromName("lapis_block"));
        Xray.xrayBlocks.add(Block.getBlockFromName("fire"));
        Xray.xrayBlocks.add(Block.getBlockFromName("mossy_cobblestone"));
        Xray.xrayBlocks.add(Block.getBlockFromName("mob_spawner"));
        Xray.xrayBlocks.add(Block.getBlockFromName("end_portal_frame"));
        Xray.xrayBlocks.add(Block.getBlockFromName("enchanting_table"));
        Xray.xrayBlocks.add(Block.getBlockFromName("bookshelf"));
        Xray.xrayBlocks.add(Block.getBlockFromName("command_block"));
	}
	
	public static boolean isXrayBlock(Block name) {
		if(Xray.xrayBlocks.contains(name))
			return true;
		return false;
	}
}