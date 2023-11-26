package xyz.helloyunho.dtja_mcserver_plugin.back

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class BackCommand(val plugin: Plugin): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}This command can only be run by a player.${ChatColor.RESET}")
            return true
        }

        val userConfig = UserConfig(plugin, sender)
        val loc = userConfig.lastLocation
        if (loc == null) {
            if (sender.locale == "ko_kr") {
                sender.sendMessage("${ChatColor.RED}되돌아갈 위치가 없습니다.${ChatColor.RESET}")
            } else {
                sender.sendMessage("${ChatColor.RED}No location to go back to.${ChatColor.RESET}")
            }
        } else {
            sender.teleport(loc)
            if (sender.locale == "ko_kr") {
                sender.sendMessage("이전 위치로 되돌아갔습니다.")
            } else {
                sender.sendMessage("Teleported back to previous location.")
            }
        }

        return true
    }
}