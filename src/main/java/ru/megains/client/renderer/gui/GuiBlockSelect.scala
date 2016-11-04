package ru.megains.client.renderer.gui

import java.awt.Color

import org.joml.Vector3f
import org.lwjgl.opengl.Display
import ru.megains.client.OrangeCraft
import ru.megains.common.block.Block
import ru.megains.common.block.blockdata.BlockPos
import ru.megains.common.item.ItemStack
import ru.megains.common.util.RayTraceResult

class GuiBlockSelect extends GuiInGame with GuiText {

    var ray: RayTraceResult = _
    var blockSelect: Block = _

    override def initGui(orangeCraft: OrangeCraft): Unit = {


        addText("BlockWorldPos", createString("", Color.WHITE))
        addText("BlockWorldPos.side", createString("", Color.WHITE))
        addText("BlockWorldPos.x", createString("", Color.WHITE))
        addText("BlockWorldPos.y", createString("", Color.WHITE))
        addText("BlockWorldPos.z", createString("", Color.WHITE))
    }

    override def tick(): Unit = {
        if (oc.objectMouseOver ne ray) {
            ray = oc.objectMouseOver
            if (ray ne null) {
                val posB: BlockPos = ray.getBlockWorldPos
                val vec: Vector3f = ray.hitVec
                blockSelect = ray.block
                addText("BlockWorldPos", createString(ray.block.name, Color.WHITE))
                addText("BlockWorldPos.x", createString("x: " + posB.worldX + "  " + vec.x, Color.WHITE))
                addText("BlockWorldPos.y", createString("y: " + posB.worldY + "  " + vec.y, Color.WHITE))
                addText("BlockWorldPos.z", createString("z: " + posB.worldZ + "  " + vec.z, Color.WHITE))
                addText("BlockWorldPos.side", createString("side: " + ray.sideHit.name, Color.WHITE))
            }
        }
    }

    override def drawScreen(mouseX: Int, mouseY: Int): Unit = {
        if (ray ne null) {
            val weight = Display.getWidth / 2 - 100
            drawObject(weight, Display.getHeight - 20, 2, text("BlockWorldPos"))
            drawObject(weight, Display.getHeight - 40, 2, text("BlockWorldPos.x"))
            drawObject(weight, Display.getHeight - 60, 2, text("BlockWorldPos.y"))
            drawObject(weight, Display.getHeight - 80, 2, text("BlockWorldPos.z"))
            drawObject(weight, Display.getHeight - 100, 2, text("BlockWorldPos.side"))
            drawItemStack(new ItemStack(blockSelect), Display.getWidth / 2 - 150, Display.getHeight - 50)
        }

    }

}
