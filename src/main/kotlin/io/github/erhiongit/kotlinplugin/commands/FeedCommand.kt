package io.github.erhiongit.kotlinplugin.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FeedCommand : CommandExecutor {

    private val intOutOfBoundMsg: String = "The number must be between 0 and 20."
    private val playerNotOnlineMsg: String = "The player is not online."
    private val everyPlayerFedMsg: String = "Every player's hunger has been replenished."
    private val playerFedMsg: String = "'s hunger has been replenished."

    override fun onCommand(s: CommandSender?, c: Command?, str: String?, args: Array<out String>?): Boolean {

        if (args == null || s == null)
            return false

        if (str!!.toLowerCase() == "feedall") {
            feedAll()
            s.sendMessage("${ChatColor.GREEN}$everyPlayerFedMsg")
            return true
        }

        if (s is Player) {
            val p: Player = s

            when (args.size) {
                0 -> feed(p)
                1 -> {
                    val level: Int

                    if (args[0].length < 3)
                        try {
                            level = args[0].toInt()

                            if (level < 0 || level > 20)
                                p.sendMessage("${ChatColor.RED}$intOutOfBoundMsg")
                            else {
                                p.foodLevel = level
                                p.sendMessage("${ChatColor.GREEN}Your hunger has been set to $level")
                            }
                            return true
                        } catch (e: NumberFormatException) {}

                    for (t in Bukkit.getOnlinePlayers())
                        if (t.name.toLowerCase() == args[0].toLowerCase()) {
                            feed(t)
                            p.sendMessage("${ChatColor.GREEN}${t.name.capitalize()}$playerFedMsg")
                            return true
                        }
                    if (args[0] == "*") {
                        for (t in Bukkit.getOnlinePlayers())
                            feed(t)
                        p.sendMessage("${ChatColor.GREEN}$everyPlayerFedMsg")
                        return true
                    }
                    p.sendMessage("${ChatColor.RED}$playerNotOnlineMsg")
                }
                2 -> {
                    val level: Int

                    try {
                        level = args[1].toInt()
                    } catch (e: NumberFormatException) {
                        p.sendMessage("${ChatColor.RED}$intOutOfBoundMsg")
                        return true
                    }
                    if (level < 0 || level > 20) {
                        p.sendMessage("${ChatColor.RED}$intOutOfBoundMsg")
                        return true
                    }

                    for (t in Bukkit.getOnlinePlayers())
                        if (t.name.toLowerCase() == args[0].toLowerCase()) {
                            t.foodLevel = level
                            p.sendMessage("${ChatColor.GREEN}The player's hunger has been set to $level.")
                            return true
                        }
                    p.sendMessage("${ChatColor.RED}$playerNotOnlineMsg")

                }
                else -> return false
            }
            return true
        }

        if (args.size == 1) {
            for (t in Bukkit.getOnlinePlayers())
                if (args[0].toLowerCase() == t.name.toLowerCase()) {
                    feed(t)
                    s.sendMessage("${t.name.capitalize()}$playerFedMsg")
                    return true
                }
            if (args[0] == "*") {
                feedAll()
                s.sendMessage(everyPlayerFedMsg)
            }
            s.sendMessage(playerNotOnlineMsg)
            return true
        }
        else if (args.size == 2) {
            val level: Int

            try {
                level = args[0].toInt()
            } catch (e: NumberFormatException) {
                s.sendMessage(intOutOfBoundMsg)
                return true
            }
            if (level < 0 || level > 20) {
                s.sendMessage(intOutOfBoundMsg)
                return true
            }

            for (t in Bukkit.getOnlinePlayers())
                if (args[0].toLowerCase() == args[0].toLowerCase()) {
                    t.foodLevel = level
                    s.sendMessage("${t.name.capitalize()}$everyPlayerFedMsg")
                    return true
                }
            if (args[0] == "*") {
                for (t in Bukkit.getOnlinePlayers())
                    t.foodLevel = level
                s.sendMessage("Every player's food level has been set to $level")
                return true
            }
            s.sendMessage(playerNotOnlineMsg)
            return true
        }
        return false
    }

    private fun feedAll(){
        for (t in Bukkit.getOnlinePlayers())
            feed(t)
    }

    private fun feed(p: Player) {
        p.foodLevel = 20
        p.saturation = 20F
        p.sendMessage("${ChatColor.GREEN}Your hunger has been replenished.")
    }

}