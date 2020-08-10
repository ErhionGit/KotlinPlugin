package io.github.erhiongit.kotlinplugin.commands

import io.github.erhiongit.kotlinplugin.Main
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GodCommand(private val instance: Main) : CommandExecutor {

    val disabled : String = "You are no longer in god mode."
    val enabled : String = "You are now in god mode."
    val enabledOther : String = "is now in god mode."
    val disabledOther : String = "is no longer in god mode."

    override fun onCommand(s: CommandSender?, c: Command?, str: String?, args: Array<out String>?): Boolean {
        if (s == null || args == null)
            return false

        if (s is Player) {
            val p : Player = s

            if (args.isEmpty()) {
                if (instance.gods.contains(p)) {
                    instance.gods.remove(p)
                    p.sendMessage("${ChatColor.RED}$disabled")
                } else {
                    instance.gods.add(p)
                    p.sendMessage("${ChatColor.GREEN}$enabled")
                }
                return true
            }
            else if (args.size == 1) {
                for (t in Bukkit.getOnlinePlayers()) {
                    if (args[0].toLowerCase() == t.name.toLowerCase()) {
                        if (instance.gods.contains(t)) {
                            instance.gods.remove(t)
                            p.sendMessage("${ChatColor.RED}${t.name.capitalize()} $disabledOther")
                            t.sendMessage("${ChatColor.RED}${p.name.capitalize()} removed you from god mode.")
                        } else {
                            instance.gods.add(t)
                            p.sendMessage("${ChatColor.GREEN}${t.name.capitalize()} $enabledOther")
                            t.sendMessage("${ChatColor.GREEN}${p.name.capitalize()} put you in god mode.")
                        }
                        return true
                    }
                }
            }
        }
        else if (args.size == 1) {
            for (t in Bukkit.getOnlinePlayers()) {
                if (args[0].toLowerCase() == t.name.toLowerCase()) {
                    if (instance.gods.contains(t)) {
                        instance.gods.remove(t)
                        s.sendMessage("${t.name.capitalize()} $disabledOther")
                        t.sendMessage("${ChatColor.RED}The console removed you from god mode.")
                    } else {
                        instance.gods.add(t)
                        s.sendMessage("${t.name.capitalize()} $enabledOther")
                        t.sendMessage("${ChatColor.GREEN}The console put you in god mode.")
                    }
                    return true
                }
            }
        }
        return false
    }

}