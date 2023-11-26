package xyz.helloyunho.dtja_mcserver_plugin.home

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class SethomeListener(val plugin: Plugin): Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    fun onPlayerBedEnter(event: PlayerBedEnterEvent) {
        val player = event.player
        val userConfig = UserConfig(plugin, player)
        val homes = userConfig.home
        homes["bed"] = Home(player.location)
        userConfig.home = homes
    }
}