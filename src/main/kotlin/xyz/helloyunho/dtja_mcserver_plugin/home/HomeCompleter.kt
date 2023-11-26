package xyz.helloyunho.dtja_mcserver_plugin.home

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig

class HomeCompleter(val plugin: Plugin): TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        if (sender !is Player) {
            return null
        }

        val userConfig = UserConfig(plugin, sender)
        val homeNames = userConfig.home.keys

        return if (args.size > 1) {
            null
        } else if (args.isEmpty()) {
            homeNames.toMutableList()
        } else {
            homeNames.filter { it.startsWith(args[0]) }.toMutableList()
        }
    }
}