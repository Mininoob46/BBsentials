package de.hype.bingonet.client.common.config

class BingoBrewersIntegrationConfig : BingoNetConfig {
    var showSplashes: Boolean = true
    var showPartyWarpSplashes: Boolean = true

    //TODO add post support. Sorry Indigo. I dont want to just leech but I dont want to make a incorrect implementation that may cause faulty data.
    // I plan on adding it very soon.
    var showChests: Boolean = true

    constructor() : super(1) {
        doInit()
    }
}