package xyz.jeelzzz.genkore.config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import xyz.jeelzzz.genkore.GenKorePlugin
import xyz.jeelzzz.genkore.config.objects.MainConfig
import xyz.jeelzzz.genkore.util.log
import java.io.File

class ConfigManager(private val plugin: GenKorePlugin) {

    lateinit var main: MainConfig private set

    private val configFolder: File = plugin.dataFolder

    fun reload(initial: Boolean=false) {
        if (!configFolder.exists()) configFolder.mkdirs()

        val prefix = if (initial) "re" else ""
        log("&7Config ${prefix}loading...")
        val start = System.currentTimeMillis()

        main = loadConfigFile("config.yml")

        val end = System.currentTimeMillis()
        log("&aConfig ${prefix}loaded in &f${end - start}ms")
    }

    @OptIn(InternalSerializationApi::class)
    private inline fun <reified T : Any> loadConfigFile(filename: String): T {
        val file = File(this.configFolder, filename)
        if (!file.exists()) {
            plugin.saveResource(filename, false)
        }
        return Yaml.default.decodeFromString(T::class.serializer(), file.readText())
    }

//    private fun createLoader(filename: String): ConfigLoader {
//        return ConfigLoaderBuilder.default()
////            .withClassLoader(ClassLoader.getPlatformClassLoader())
//            .addDefaultDecoders()
//            .addResourceSource(filename)
//            .build()
//    }

//
//    private inline fun <reified T> createHelper(filename: String, vararg serializers: ValueSerialiser<*>): ConfigurationHelper<T> {
//        val yamlOptions = SnakeYamlOptions.Builder().commentMode(CommentMode.fullComments()).build()
//        val optionsBuilder = ConfigurationOptions.Builder()
//        if (serializers.isNotEmpty()) optionsBuilder.addSerialisers(*serializers)
//        optionsBuilder.sorter(AnnotationBasedSorter())
//        val configFactory = SnakeYamlConfigurationFactory.create(T::class.java, optionsBuilder.build(), yamlOptions)
//
//        val file = File(configFolder, filename)
//        if (!file.exists()) {
//            plugin.getResource(filename)?.let { file.writeBytes(it.readAllBytes()) }
//        }
//
//        return ConfigurationHelper(configFolder.toPath(), filename, configFactory)
//    }
}