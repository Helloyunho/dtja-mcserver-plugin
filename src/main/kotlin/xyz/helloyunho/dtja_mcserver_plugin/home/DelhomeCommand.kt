package xyz.helloyunho.dtja_mcserver_plugin.home

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class DelhomeCommand(val plugin: Plugin): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}This command can only be run by a player.${ChatColor.RESET}")
            return true
        }

        val homeName = if (args.isEmpty()) "default" else args[0]
        val userConfig = UserConfig(plugin, sender)
        val home = userConfig.home[homeName]
        if (home == null) {
            if (sender.locale == "ko_kr") {
                sender.sendMessage("${ChatColor.RED}홈 ${homeName}이(가) 존재하지 않습니다.${ChatColor.RESET}")
            } else {
                sender.sendMessage("${ChatColor.RED}Home $homeName does not exist.${ChatColor.RESET}")
            }
        } else {
            val homes = userConfig.home
            homes.remove(homeName)
            userConfig.home = homes
            if (sender.locale == "ko_kr") {
                sender.sendMessage("홈 ${homeName}을(를) 삭제했습니다.")
            } else {
                sender.sendMessage("Deleted home $homeName.")
            }
        }

        return true
    }
}