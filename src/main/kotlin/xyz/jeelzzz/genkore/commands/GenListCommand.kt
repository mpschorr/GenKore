package xyz.jeelzzz.genkore.commands

import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import org.bukkit.entity.Player
import xyz.jeelzzz.genkore.GenKorePlugin
import xyz.jeelzzz.genkore.guis.GenListGui

@Command("genlist", alias=["genslist"])
class GenListCommand(private val plugin: GenKorePlugin) : BaseCommand() {
    @Default
    fun executor(player: Player) {
        GenListGui(plugin, player, 1).open()
    }
}