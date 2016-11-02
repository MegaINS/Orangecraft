package ru.megains

import ru.megains.client.SaveHandlerMP
import ru.megains.client.renderer.world.WorldRenderer
import ru.megains.common.network.NetHandlerPlayClient
import ru.megains.common.world.{ChunkProviderClient, IChunkProvider}
import ru.megains.game.block.Block
import ru.megains.game.blockdata.BlockPos
import ru.megains.game.position.ChunkPosition
import ru.megains.game.world.World

class WorldClient(connection: NetHandlerPlayClient) extends World(new SaveHandlerMP) {

    override var isRemote: Boolean = true
    var worldRenderer: WorldRenderer = _

    def doPreChunk(pos: ChunkPosition, loadChunk: Boolean) = {
        if (loadChunk) chunkProvider.loadChunk(pos)
        else {
            //  this.chunkProvider.unloadChunk(chunkX, chunkZ)
            //    this.markBlockRangeForRenderUpdate(chunkX * 16, 0, chunkZ * 16, chunkX * 16 + 15, 256, chunkZ * 16 + 15)
        }
    }

    def invalidateRegionAndSetBlock(pos: BlockPos, block: Block): Boolean = {
        //  val i: Int = pos.getX
        //  val j: Int = pos.getY
        //   val k: Int = pos.getZ
        // invalidateBlockReceiveRegion(i, j, k, i, j, k)
        setBlock(pos, block, 3)
    }


    override val chunkProvider: IChunkProvider = new ChunkProviderClient(this)

    override def save(): Unit = {}

    override def setBlock(pos: BlockPos, block: Block, flag: Int): Boolean = {
        if (super.setBlock(pos, block, flag)) {
            worldRenderer.reRender(pos)
            true
        } else {
            false
        }
    }

}
