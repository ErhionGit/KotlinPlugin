package io.github.erhiongit.kotlinplugin

import io.github.erhiongit.kotlinplugin.commands.GmCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class Main : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()
        Bukkit.getLogger().log(Level.INFO, "[KotlinPlugin] The plugin is enabled.")
        getCommand("gm").executor = GmCommand()
    }

}