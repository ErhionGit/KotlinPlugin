package io.github.erhiongit.kotlinplugin

import io.github.erhiongit.kotlinplugin.commands.FeedCommand
import io.github.erhiongit.kotlinplugin.commands.GmCommand
import io.github.erhiongit.kotlinplugin.commands.GodCommand
import io.github.erhiongit.kotlinplugin.listeners.HealthLossListener
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class Main : JavaPlugin() {

    val gods : ArrayList<Player> = ArrayList()

    override fun onEnable() {
        super.onEnable()
        getCommand("gm").executor = GmCommand()
        getCommand("god").executor = GodCommand(this)
        getCommand("feed").executor = FeedCommand()
        Bukkit.getPluginManager().registerEvents(HealthLossListener(this), this)
        Bukkit.getLogger().log(Level.INFO, "[KotlinPlugin] The plugin is enabled.")
    }

}