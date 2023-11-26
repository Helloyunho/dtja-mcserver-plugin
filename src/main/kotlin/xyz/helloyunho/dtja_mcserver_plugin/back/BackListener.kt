package xyz.helloyunho.dtja_mcserver_plugin.back

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class BackListener(val plugin: Plugin): Listener {
    @EventHandler(priority = EventPriority.LOW)
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity
        val location = player.location
        val userConfig = UserConfig(plugin, player)
        userConfig.lastLocation = location
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onPlayerTeleport(event: PlayerTeleportEvent) {
        val player = event.player
        val location = player.location
        val userConfig = UserConfig(plugin, player)
        userConfig.lastLocation = location
    }
}