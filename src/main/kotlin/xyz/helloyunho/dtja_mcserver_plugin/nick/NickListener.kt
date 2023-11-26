package xyz.helloyunho.dtja_mcserver_plugin.nick

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class NickListener(val plugin: Plugin): Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val userConfig = UserConfig(plugin, player)
        val nick = userConfig.nick
        if (nick != null) {
            player.setDisplayName(nick)
        }
    }
}