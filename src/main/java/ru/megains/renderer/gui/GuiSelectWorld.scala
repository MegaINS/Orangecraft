package ru.megains.renderer.gui

import ru.megains.game.OrangeCraft
import ru.megains.game.world.World

class GuiSelectWorld(guiMainMenu: GuiScreen) extends GuiScreen {

    var worldsSlot: Array[GuiSlotWorld] = _
    var buttonSelect: GuiButton = _
    var buttonDelete: GuiButton = _
    var selectWorld: GuiSlotWorld = _

    override def initGui(orangeCraft: OrangeCraft): Unit = {

        val savesArray = oc.saveLoader.getSavesArray
        worldsSlot = new Array[GuiSlotWorld](savesArray.length)

        for (i <- savesArray.indices) {
            worldsSlot(i) = new GuiSlotWorld(i, savesArray(i), oc)
        }


        buttonSelect = new GuiButton(0, oc, "Select", 100, 100, 200, 50)
        buttonDelete = new GuiButton(1, oc, "Delete", 100, 20, 200, 50)
        buttonList += new GuiButton(2, oc, "CreateWorld", 500, 100, 200, 50)
        buttonList += new GuiButton(5, oc, "Cancel", 500, 20, 200, 50)
        buttonList += buttonSelect
        buttonList += buttonDelete

        buttonDelete.enable = false
        buttonSelect.enable = false


    }


    override def actionPerformed(button: GuiButton): Unit = {
        button.id match {
            case 0 => val saveHandler = oc.saveLoader.getSaveLoader(selectWorld.worldName)
                oc.setWorld(new World(saveHandler))

            case 1 => oc.saveLoader.deleteWorldDirectory(selectWorld.worldName)
                oc.guiManager.setGuiScreen(this)

            case 5 => oc.guiManager.setGuiScreen(guiMainMenu)
            case _ =>
        }
    }

    override def drawScreen(mouseX: Int, mouseY: Int): Unit = {
        super.drawScreen(mouseX, mouseY)
        worldsSlot.foreach(_.draw(mouseX, mouseY))
    }

    override def mouseClicked(x: Int, y: Int, button: Int): Unit = {
        super.mouseClicked(x, y, button)
        if (button == 0) {
            var isSelect = false
            worldsSlot.foreach(
                (slot) => {
                    if (slot.isMouseOver(x, y)) {
                        slot.select = true
                        selectWorld = slot
                        isSelect = true
                    } else {
                        slot.select = false
                    }
                }
            )
            buttonDelete.enable = isSelect
            buttonSelect.enable = isSelect
            if (!isSelect) selectWorld = null
        }
    }

}
