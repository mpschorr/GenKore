package xyz.jeelzzz.genkore.guis

import dev.triumphteam.gui.builder.item.ItemBuilder
import dev.triumphteam.gui.guis.Gui
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import xyz.jeelzzz.genkore.GenKorePlugin
import xyz.jeelzzz.genkore.util.mm

class GenListGui(private val plugin: GenKorePlugin, private val player: Player, private val page: Int) {
    private val maxPage = (plugin.config.gens.size / (4 * 7)) + 1

    fun open() {
        val gui = Gui.gui()
            .title("Generator List".mm())
            .rows(6)
            .create()
        gui.setDefaultClickAction { it.isCancelled = true }

        // Border & background
        gui.filler.fillBetweenPoints(
            2, 2, 5, 8,
            ItemBuilder.from(Material.STONE_BUTTON).name(Component.text().asComponent()).asGuiItem()
        )
        gui.filler.fillBorder(ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).name(Component.text().asComponent()).asGuiItem())

        // Pages
        if (page > 1) {
            gui.setItem(2, 6, ItemBuilder.from(Material.ARROW).name("&aPrevious Page".mm()).asGuiItem())
        }
        if (page < maxPage) {
            gui.setItem(8, 6, ItemBuilder.from(Material.ARROW).name("&aNext Page".mm()).asGuiItem())
        }

        plugin.config.gens.entries.forEachIndexed { index, (_, gen) ->
            val col = (index % 7) + 2
            val row = (index / 7) + 2
            gui.setItem(row, col, ItemBuilder.from(gen.block.type).name(gen.block.itemMeta.displayName() ?: Component.translatable(gen.block.type)).asGuiItem())
        }

        gui.open(player)
    }
}