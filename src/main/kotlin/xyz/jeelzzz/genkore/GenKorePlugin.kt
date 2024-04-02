package xyz.jeelzzz.genkore

import xyz.jeelzzz.genkore.config.ConfigManager
import xyz.jeelzzz.genkore.config.objects.MainConfig
import org.bukkit.plugin.java.JavaPlugin

class GenKorePlugin : JavaPlugin() {
    private lateinit var configManager: ConfigManager
    lateinit var config: MainConfig private set

    override fun onEnable() {
        configManager = ConfigManager(this)
        configManager.reload(true)
        config = configManager.main

        logger.info(config.toString())
    }

    override fun onDisable() {
        // TODO Cleanup tasks
    }
}