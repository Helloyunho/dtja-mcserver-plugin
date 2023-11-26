package xyz.helloyunho.dtja_mcserver_plugin.home

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class SethomeCompleter: TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        return if (args.size > 1) {
            null
        } else if (args.isEmpty() || "default".startsWith(args[0])) {
            mutableListOf("default")
        } else {
            null
        }
    }
}