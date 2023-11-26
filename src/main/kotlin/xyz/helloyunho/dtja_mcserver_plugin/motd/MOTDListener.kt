package xyz.helloyunho.dtja_mcserver_plugin.motd

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin

class MOTDListener(val plugin: Plugin): Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        if (player.locale == "ko_kr") {
            player.sendMessage("${ChatColor.GRAY}안녕하세요, ${player.name}님!\n" +
                    "아직 닉네임을 변경하지 않으셨다면 /nick <닉네임> 명령어로 변경해주세요.\n" +
                    "서버에 오신 것을 환영합니다!")
        } else {
            player.sendMessage("${ChatColor.GRAY}Hello, ${player.name}!\n" +
                    "If you haven't changed your nickname yet, please change it with /nick <nickname> command.\n" +
                    "Welcome to the server!")
        }
    }
}