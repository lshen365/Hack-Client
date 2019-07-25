package como.taco;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockFixed extends Block {

	protected BlockFixed(Material materialIn) {
		super(materialIn);
		// TODO Auto-generated constructor stub
	}
	
	//Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	
    	if(Client.checkForModule(new Xray())) {
    		return RenderUtil.isXrayBlock(this);
    	}
        AxisAlignedBB axisalignedbb = blockState.getBoundingBox(blockAccess, pos);

        switch (side)
        {
            case DOWN:
                if (axisalignedbb.minY > 0.0D)
                {
                    return true;
                }

                break;

            case UP:
                if (axisalignedbb.maxY < 1.0D)
                {
                    return true;
                }

                break;

            case NORTH:
                if (axisalignedbb.minZ > 0.0D)
                {
                    return true;
                }

                break;

            case SOUTH:
                if (axisalignedbb.maxZ < 1.0D)
                {
                    return true;
                }

                break;

            case WEST:
                if (axisalignedbb.minX > 0.0D)
                {
                    return true;
                }

                break;

            case EAST:
                if (axisalignedbb.maxX < 1.0D)
                {
                    return true;
                }
        }

        return !blockAccess.getBlockState(pos.offset(side)).isOpaqueCube();
    }

}
