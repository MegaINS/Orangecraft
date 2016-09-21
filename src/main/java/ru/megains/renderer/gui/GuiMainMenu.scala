package ru.megains.renderer.gui

import ru.megains.game.OrangeCraft

class GuiMainMenu extends GuiScreen {


    override def initGui(orangeCraft: OrangeCraft): Unit = {
        buttonList += new GuiButton(0, orangeCraft, "SingleGame", 250, 450, 300, 50)
        buttonList += new GuiButton(1, orangeCraft, "MultiplayerGame", 250, 380, 300, 50)
        buttonList += new GuiButton(2, orangeCraft, "Option", 250, 310, 300, 50)
        buttonList += new GuiButton(3, orangeCraft, "Exit game", 250, 240, 300, 50)

    }

    override def actionPerformed(button: GuiButton): Unit = {
        button.id match {
            case 0 => oc.guiManager.setGuiScreen(new GuiSelectWorld(this))
            case 1 =>
            case 3 => oc.running = false
            case _ =>
        }
    }

    override def drawScreen(mouseX: Int, mouseY: Int): Unit = {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY)
    }

}
