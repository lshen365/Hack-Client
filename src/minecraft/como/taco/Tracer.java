package como.taco;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import como.taco.Events.*;
import como.taco.GUI.ModCategories;

public class Tracer extends Hack {
	private float partialTicks;
	private int playerBox;
	private boolean tracerOn = true;
	private final ArrayList<EntityPlayer> players = new ArrayList<>();
	
	public Tracer()
	{
		super("ESP",Keyboard.KEY_P,ModCategories.RENDER);
	}
	
	
	@Override
	public void onEnable()
	{
		playerBox = GL11.glGenLists(1);
		GL11.glNewList(playerBox, GL11.GL_COMPILE);
		AxisAlignedBB bb = new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5);
		RenderUtil.drawOutlinedBox(bb);
		GL11.glEndList();
		System.out.println("THIS IS RUN");
	}
	
	@Override
	public void onDisable()
	{
		GL11.glDeleteLists(playerBox, 1);
		playerBox = 0;
		mc.gameSettings.viewBobbing = true;
	}
	
	@Override
	public void onUpdate()
	{
		EntityPlayer player = mc.player;
		World world = mc.world;
		mc.gameSettings.viewBobbing = false;
		players.clear();
		Stream<EntityPlayer> stream = world.playerEntities.parallelStream()
			.filter(e -> !e.isDead && e.getHealth() > 0)
			.filter(e -> e != player)
			.filter(e -> Math.abs(e.posY - mc.player.posY) <= 1e6);
		players.addAll(stream.collect(Collectors.toList()));
	}
	
	public void changeTracer() {
		tracerOn = !tracerOn;
	}
	public boolean getTracerStatus() {
		return tracerOn;
	}
	
	
	@Override
	public void onRender()
	{
		// GL settings
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glLineWidth(2);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		GL11.glPushMatrix();
		GL11.glTranslated(-mc.getRenderManager().renderPosX,
			-mc.getRenderManager().renderPosY,
			-mc.getRenderManager().renderPosZ);
		
		// draw boxes
		renderBoxes(partialTicks);
		
		if(tracerOn)
			renderTracers(partialTicks);
		
		GL11.glPopMatrix();
		
		// GL resets
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}
	
	private void renderBoxes(double partialTicks)
	{
		for(EntityPlayer e : players)
		{
			// set position
			GL11.glPushMatrix();
			GL11.glTranslated(e.prevPosX + (e.posX - e.prevPosX) * partialTicks,
				e.prevPosY + (e.posY - e.prevPosY) * partialTicks,
				e.prevPosZ + (e.posZ - e.prevPosZ) * partialTicks);
			GL11.glScaled(e.width + 0.1, e.height + 0.1, e.width + 0.1);
			

			GL11.glCallList(playerBox);
			
			GL11.glPopMatrix();
		}
	}
	
	private void renderTracers(double partialTicks)
	{
		Vec3d start = RotationUtil.getClientLookVec()
			.addVector(0, mc.player.getEyeHeight(), 0)
			.addVector(mc.getRenderManager().renderPosX,
				mc.getRenderManager().renderPosY,
				mc.getRenderManager().renderPosZ);
		
		GL11.glBegin(GL11.GL_LINES);
		for(EntityPlayer e : players)
		{
			Vec3d end = e.getEntityBoundingBox().getCenter()
				.subtract(new Vec3d(e.posX, e.posY, e.posZ)
					.subtract(e.prevPosX, e.prevPosY, e.prevPosZ)
					.scale(1 - partialTicks));
			if(EntityUtil.isFriend(e.getName())) {
				GL11.glColor4f(0, 0, 1, 0.5F);
			}
			else
			{
				float f = mc.player.getDistanceToEntity(e) / 20F;
				GL11.glColor4f(2 - f, f, 0, 0.5F);
			}
			
			GL11.glVertex3d(start.xCoord, start.yCoord, start.zCoord);
			GL11.glVertex3d(end.xCoord, end.yCoord, end.zCoord);
		}
		GL11.glEnd();
	}




	@Override
	public void changeVariable(float position) {
		// TODO Auto-generated method stub
		
	}
}