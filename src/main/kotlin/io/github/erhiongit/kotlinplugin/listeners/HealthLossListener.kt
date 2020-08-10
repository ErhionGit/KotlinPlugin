package io.github.erhiongit.kotlinplugin.listeners

import io.github.erhiongit.kotlinplugin.Main
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class HealthLossListener(private val instance : Main) : Listener {

    @EventHandler
    fun onHealthLoss(e: EntityDamageEvent) {

        if (instance.gods.contains(e.entity as Player))
            e.isCancelled = true

    }

}