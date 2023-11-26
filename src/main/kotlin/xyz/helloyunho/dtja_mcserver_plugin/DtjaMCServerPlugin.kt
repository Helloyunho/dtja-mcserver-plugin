package xyz.helloyunho.dtja_mcserver_plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import xyz.helloyunho.dtja_mcserver_plugin.back.BackCommand
import xyz.helloyunho.dtja_mcserver_plugin.back.BackListener
import xyz.helloyunho.dtja_mcserver_plugin.home.*
import xyz.helloyunho.dtja_mcserver_plugin.motd.MOTDListener
import xyz.helloyunho.dtja_mcserver_plugin.nick.NickCommand
import xyz.helloyunho.dtja_mcserver_plugin.nick.NickListener
import xyz.helloyunho.dtja_mcserver_plugin.utils.UserConfig
import java.util.*

class DtjaMCServerPlugin : JavaPlugin() {
    val userConfigPath = dataFolder.resolve("users/")

    override fun onEnable() {
        // ensure config file exists
        saveDefaultConfig()
        // ensure user config directory exists
        if (!userConfigPath.exists()) {
            userConfigPath.mkdirs()
        }
        // for-each user config file
        userConfigPath.listFiles()?.forEach {
            val player = Bukkit.getPlayer(UUID.fromString(it.nameWithoutExtension))
            if (player != null) {
                // load user config
                val userConfig = UserConfig(this, player)
                if (userConfig.nick != null) {
                    player.setDisplayName(userConfig.nick)
                }
            }
        }
        // register commands
        getCommand("sethome")?.setExecutor(SethomeCommand(this))
        getCommand("home")?.setExecutor(HomeCommand(this))
        getCommand("nick")?.setExecutor(NickCommand(this))
        getCommand("back")?.setExecutor(BackCommand(this))
        getCommand("homelist")?.setExecutor(HomeListCommand(this))
        getCommand("delhome")?.setExecutor(DelhomeCommand(this))
        // register tab completers
        getCommand("sethome")?.tabCompleter = SethomeCompleter()
        getCommand("home")?.tabCompleter = HomeCompleter(this)
        getCommand("delhome")?.tabCompleter = DelhomeCompleter(this)
        // register event listeners
        server.pluginManager.registerEvents(BackListener(this), this)
        server.pluginManager.registerEvents(NickListener(this), this)
        server.pluginManager.registerEvents(SethomeListener(this), this)
        server.pluginManager.registerEvents(MOTDListener(this), this)
        logger.info("${description.name} version ${description.version} enabled!")
    }

    override fun onDisable() {
        logger.info("${description.name} disabled.")
    }
}
