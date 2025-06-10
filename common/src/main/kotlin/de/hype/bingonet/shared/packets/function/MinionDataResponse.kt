package de.hype.bingonet.shared.packets.function

import de.hype.bingonet.environment.packetconfig.AbstractPacket
import de.hype.bingonet.shared.compilation.sbenums.minions.Minion

class MinionDataResponse(val minions: MutableMap<Minion, Int>?, val maxSlots: Int) : AbstractPacket(1, 1) {
    class RequestMinionDataPacket : AbstractPacket(1, 1)
}
