package io.github.erhiongit.kotlinplugin.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GmCommand : CommandExecutor {

    override fun onCommand(s: CommandSender?, c: Command?, str: String?, args: Array<out String>?): Boolean {

        if (s == null || args == null) return false

        if (s !is Player) {
            when (args.size) {
                0 -> s.sendMessage("No gamemode specified.")
                1 -> s.sendMessage("No player specified.")
                2 -> {
                    for (p in Bukkit.getOnlinePlayers())
                        if (p.name.toLowerCase() == args[1].toLowerCase()) {
                            when (args[0]) {
                                "0", "survival", "s" -> p.gameMode = GameMode.SURVIVAL
                                "1", "creative", "c" -> p.gameMode = GameMode.CREATIVE
                                "2", "adventure", "a" -> p.gameMode = GameMode.ADVENTURE
                                "3", "spectator" -> p.gameMode = GameMode.SPECTATOR
                                else -> return false
                            }
                            s.sendMessage("The player's gamemode has been set to ${p.gameMode.name.toLowerCase()}.")
                            return true
                        }
                    s.sendMessage("The player is not online.")
                    return true
                }
                else -> return false
            }
            return true
        }

        val p: Player = s

        when (args.size) {
            0 -> p.sendMessage("${ChatColor.RED}No gamemode specified.")
            1 -> {
                when (args[0]) {
                    "0", "survival", "s" -> p.gameMode = GameMode.SURVIVAL
                    "1", "creative", "c" -> p.gameMode = GameMode.CREATIVE
                    "2", "adventure", "a" -> p.gameMode = GameMode.ADVENTURE
                    "3", "spectator" -> p.gameMode = GameMode.SPECTATOR
                    else -> return false
                }
                p.sendMessage("${ChatColor.GREEN}Your gamemode has been set to ${p.gameMode.name.toLowerCase()}.")
            }
            2 -> {
                for (t in Bukkit.getOnlinePlayers())
                    if (t.name.toLowerCase() == args[1].toLowerCase()) {
                        when (args[0]) {
                            "0", "survival", "s" -> t.gameMode = GameMode.SURVIVAL
                            "1", "creative", "c" -> t.gameMode = GameMode.CREATIVE
                            "2", "adventure", "a" -> t.gameMode = GameMode.ADVENTURE
                            "3", "spectator" -> t.gameMode = GameMode.SPECTATOR
                            else -> return false
                        }
                        p.sendMessage("${ChatColor.GREEN}The player's gamemode has been set to ${t.gameMode.name.toLowerCase()}.")
                        return true
                    }
                s.sendMessage("${ChatColor.RED}The player is not online.")
                return true
            }
            else -> return false
        }

        return true
    }

}