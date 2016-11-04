package ru.megains.client.renderer.gui

import ru.megains.client.OrangeCraft

class GuiPlayerSelect extends GuiScreen {


    override def initGui(orangeCraft: OrangeCraft): Unit = {
        buttonList += new GuiButton(0, orangeCraft, "Test_1", 250, 450, 300, 50)
        buttonList += new GuiButton(1, orangeCraft, "Test_2", 250, 380, 300, 50)
        buttonList += new GuiButton(2, orangeCraft, "Test_3", 250, 310, 300, 50)
        buttonList += new GuiButton(3, orangeCraft, "Test_4", 250, 240, 300, 50)

    }

    override def actionPerformed(button: GuiButton): Unit = {
        button.id match {
            case _ => oc.playerName = button.buttonText
                oc.guiManager.setGuiScreen(new GuiMainMenu())
        }
    }

    override def drawScreen(mouseX: Int, mouseY: Int): Unit = {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY)
    }
}
