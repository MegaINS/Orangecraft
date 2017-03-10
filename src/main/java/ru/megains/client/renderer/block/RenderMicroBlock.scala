package ru.megains.client.renderer.block

import ru.megains.client.renderer.api.ARenderBlock
import ru.megains.common.block.Block
import ru.megains.common.block.blockdata.{BlockDirection, BlockPos, MultiBlockPos}
import ru.megains.common.world.World

object RenderMicroBlock extends ARenderBlock {

    override def render(block: Block, world: World, posWorld: BlockPos, posRender: BlockPos, offset: MultiBlockPos): Boolean = {


        val AABB = block.getSelectedBoundingBox(posWorld, offset).sum(posRender.worldX, posRender.worldY, posRender.worldZ)
        val minX = AABB.getMinX
        val minY = AABB.getMinY
        val minZ = AABB.getMinZ
        val maxX = AABB.getMaxX
        val maxY = AABB.getMaxY
        val maxZ = AABB.getMaxZ

        RenderBlock.renderSideSouth(minX, maxX, minY, maxY, maxZ, block.getATexture(BlockDirection.SOUTH))
        RenderBlock.renderSideNorth(minX, maxX, minY, maxY, minZ, block.getATexture(BlockDirection.NORTH))
        RenderBlock.renderSideDown(minX, maxX, minY, minZ, maxZ, block.getATexture(BlockDirection.DOWN))
        RenderBlock.renderSideUp(minX, maxX, maxY, minZ, maxZ, block.getATexture(BlockDirection.UP))
        RenderBlock.renderSideWest(minX, minY, maxY, minZ, maxZ, block.getATexture(BlockDirection.WEST))
        RenderBlock.renderSideEast(maxX, minY, maxY, minZ, maxZ, block.getATexture(BlockDirection.EAST))

        true
    }
}
