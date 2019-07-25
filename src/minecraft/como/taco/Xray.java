package como.taco;

import como.taco.GUI.ModCategories;
import net.minecraft.block.Block;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class Xray extends Hack{
	
	public static ArrayList<Block> xrayBlocks = new ArrayList();

	public Xray() {
		super("Xray",Keyboard.KEY_X, ModCategories.RENDER);
	}

	@Override
	public void onDisable() {
		mc.renderGlobal.loadRenderers();
		
	}

	@Override
	public void onEnable() {
		mc.renderGlobal.loadRenderers();
		
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeVariable(float position) {
		// TODO Auto-generated method stub
		
	}

}
