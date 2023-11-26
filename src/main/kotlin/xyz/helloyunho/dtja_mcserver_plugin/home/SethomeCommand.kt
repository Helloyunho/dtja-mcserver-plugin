package xyz.helloyunho.dtja_mcserver_plugin.home

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class SethomeCommand(val plugin: Plugin): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}This command can only be run by a player.${ChatColor.RESET}")
            return true
        }

        val homeName = if (args.isEmpty()) "default" else args[0]
        val home = Home(sender.location)
        val userConfig = UserConfig(plugin, sender)
        val homes = userConfig.home
        homes[homeName] = home
        userConfig.home = homes
        if (sender.locale == "ko_kr") {
            sender.sendMessage("홈 ${homeName}을(를) ${sender.location}에 설정했습니다.")
        } else {
            sender.sendMessage("Home $homeName set at ${sender.location.x} ${sender.location.y} ${sender.location.z}.")
        }
        return true
    }
}