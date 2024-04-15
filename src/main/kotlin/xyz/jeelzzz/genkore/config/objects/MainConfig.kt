package xyz.jeelzzz.genkore.config.objects

import com.fasterxml.jackson.annotation.JsonProperty
import org.bukkit.inventory.ItemStack

data class MainConfig(
    val gens: Map<Int,Gen>
) {
    data class Gen(
        val id: String,
        val block: ItemStack,
        val drop: ItemStack,
        val worth: Double,
        @JsonProperty("cost")
        val upgradeCost: Double
    )
}