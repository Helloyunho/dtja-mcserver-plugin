package xyz.helloyunho.dtja_mcserver_plugin.home

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class HomeListCommand(val plugin: Plugin): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}This command can only be run by a player.${ChatColor.RESET}")
            return true
        }

        val userConfig = UserConfig(plugin, sender)
        val homes = userConfig.home
        if (homes.isEmpty()) {
            if (sender.locale == "ko_kr") {
                sender.sendMessage("${ChatColor.RED}홈이 존재하지 않습니다.${ChatColor.RESET}")
            } else {
                sender.sendMessage("${ChatColor.RED}No homes exist.${ChatColor.RESET}")
            }
        } else {
            if (sender.locale == "ko_kr") {
                sender.sendMessage("홈 목록:")
            } else {
                sender.sendMessage("Home list:")
            }
            homes.forEach { (name, home) ->
                sender.sendMessage("  $name: ${home.location.x} ${home.location.y} ${home.location.z}")
            }
        }

        return true
    }
}