package xyz.jeelzzz.genkore.config

import com.fasterxml.jackson.core.StreamReadFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLFactoryBuilder
import com.fasterxml.jackson.module.kotlin.KotlinModule
import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemStack
import xyz.jeelzzz.genkore.GenKorePlugin
import xyz.jeelzzz.genkore.config.deserializers.ItemStackDeserializer
import xyz.jeelzzz.genkore.config.deserializers.MiniMessageDeserializer
import xyz.jeelzzz.genkore.config.objects.MainConfig
import xyz.jeelzzz.genkore.util.log
import java.io.File

class ConfigManager(private val plugin: GenKorePlugin) {

    lateinit var main: MainConfig private set

    private val mapper = ObjectMapper(
        YAMLFactoryBuilder(YAMLFactory())
            .enable(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION)
            .build())
        .registerModule(KotlinModule.Builder().build())

    private val configFolder: File = plugin.dataFolder

    init {
        mapper.findAndRegisterModules()
        val module = SimpleModule()
        module.addDeserializer(Component::class.java, MiniMessageDeserializer())
        module.addDeserializer(ItemStack::class.java, ItemStackDeserializer())
        mapper.registerModule(module)
    }

    fun reload(initial: Boolean=false) {
        if (!configFolder.exists()) configFolder.mkdirs()

        val prefix = if (initial) "re" else ""
        log("&7Config ${prefix}loading...")
        val start = System.currentTimeMillis()

        main = loadConfigFile("config.yml")

        val end = System.currentTimeMillis()
        log("&aConfig ${prefix}loaded in &f${end - start}ms")
    }

//    @OptIn(InternalSerializationApi::class)
    private inline fun <reified T : Any> loadConfigFile(filename: String): T {
        val file = File(this.configFolder, filename)
        if (!file.exists()) {
            plugin.saveResource(filename, false)
        }
        return mapper.readValue(file, T::class.java)
//        return Yaml().decodeFromString(T::class.serializer(), file.readText())
    }
}