package xyz.jeelzzz.genkore

import dev.triumphteam.cmd.bukkit.BukkitCommandManager
import org.bukkit.command.CommandSender
import xyz.jeelzzz.genkore.config.ConfigManager
import xyz.jeelzzz.genkore.config.objects.MainConfig
import org.bukkit.plugin.java.JavaPlugin
import xyz.jeelzzz.genkore.commands.GenListCommand

class GenKorePlugin : JavaPlugin() {
    private lateinit var configManager: ConfigManager
    lateinit var config: MainConfig private set

    private lateinit var commandManager: BukkitCommandManager<CommandSender>

    override fun onEnable() {
        loadConfig()
        loadCommands()
        logger.info(config.toString())
    }

    private fun loadConfig() {
        configManager = ConfigManager(this)
        configManager.reload(true)
        config = configManager.main
    }

    private fun loadCommands() {
        commandManager = BukkitCommandManager.create(this)
        commandManager.registerCommand(GenListCommand(this))
    }

    override fun onDisable() {
        // TODO Cleanup tasks
    }
}