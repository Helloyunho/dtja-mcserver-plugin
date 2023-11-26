package xyz.helloyunho.dtja_mcserver_plugin.nick

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class NickCommand(val plugin: Plugin): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}This command can only be run by a player.${ChatColor.RESET}")
            return true
        }

        val userConfig = UserConfig(plugin, sender)
        if (args.isEmpty()) {
            userConfig.nick = null
        } else {
            userConfig.nick = args[0]
        }

        if (sender.locale == "ko_kr") {
            sender.sendMessage("닉네임을 ${userConfig.nick ?: sender.name}으로 설정했습니다.")
        } else {
            sender.sendMessage("Set nickname to ${userConfig.nick ?: sender.name}.")
        }
        return true
    }
}