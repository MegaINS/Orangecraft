package ru.megains.common.network.play.server

import ru.megains.common.network.INetHandler


trait INetHandlerPlayClient extends INetHandler {
    def handleWindowItems(items: SPacketWindowItems): Unit

    def handleSetSlot(slot: SPacketSetSlot): Unit


    def handleJoinGame(packetIn: SPacketJoinGame): Unit

    def handleHeldItemChange(packetIn: SPacketHeldItemChange): Unit

    def handlePlayerPosLook(packetIn: SPacketPlayerPosLook): Unit

    def handleChunkData(packetIn: SPacketChunkData): Unit

    def handleBlockChange(packetIn: SPacketBlockChange): Unit

    def handleMultiBlockChange(packetIn: SPacketMultiBlockChange): Unit
}
