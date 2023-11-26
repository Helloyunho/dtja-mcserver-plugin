package xyz.helloyunho.dtja_mcserver_plugin.utils

import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.helloyunho.dtja_mcserver_plugin.home.Home
import java.io.IOException

class UserConfig(val plugin: Plugin, val player: Player) {
    val file = plugin.dataFolder.resolve("users/${player.uniqueId}.yml")
    val config = YamlConfiguration.loadConfiguration(file)
    init {
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun get(key: String): Any? {
        return config.get(key)
    }

    fun set(key: String, value: Any?) {
        config.set(key, value)
        save()
    }

    fun save() {
        try {
            config.save(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun reload() {
        try {
            config.load(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun delete() {
        file.delete()
    }

    var home: MutableMap<String, Home>
        get() {
            val homeMap = mutableMapOf<String, Home>()
            val homeSection = config.getConfigurationSection("home")
            if (homeSection != null) {
                for (key in homeSection.getKeys(false)) {
                    val home = Home(
                        homeSection.getLocation("$key.location")!!
                    )
                    homeMap[key] = home
                }
            }
            return homeMap
        }
        set(value) {
            val homeSection = config.createSection("home")
            for ((key, home) in value) {
                homeSection.set("$key.location", home.location)
            }
            save()
        }
    var nick: String?
        get() {
            return config.getString("nick")
        }
        set(value) {
            config.set("nick", value)
            save()
            player.setDisplayName(value ?: player.name)
        }

    var lastLocation: Location?
        get() {
            return config.getLocation("last-location")
        }
        set(value) {
            config.set("last-location", value)
            save()
        }
}