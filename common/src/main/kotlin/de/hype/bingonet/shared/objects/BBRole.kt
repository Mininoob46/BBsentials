package de.hype.bingonet.shared.objects

enum class BBRole(dbRoleName: String, visualRoleName: String) {
    DEVELOPER("dev", "Developer"),
    MODERATOR("mod", "Moderator"),
    CHCHEST_ANNOUNCE_PERM("chchest", "Ch Chest"),
    BETA_TESTER("beta", "Beta Tester"),
    ADMIN("admin", "Admin"),
    MANIAC("maniac", "Maniac"),
    MINING_EVENT_ANNOUNCE_PERM("mining_events", "Mining Events"),
    PREANNOUNCE("preannounce_info", "Preannounce Info"),
    SPLASHER("splasher", "Splasher"),
    ADVANCEDINFO("advancedinfo", "Advanced Info"),
    STRATMAKER("strat_maker", "Strat Maker"),
    DEBUG("debug", "Debug");

    val dBRoleName: String = dbRoleName
    val visualName: String = visualRoleName

    val description: String?
        get() = null

    companion object {
        var roles: MutableMap<String, BBRole>? = null
    }
}
