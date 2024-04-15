package xyz.jeelzzz.genkore.config.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import net.kyori.adventure.text.Component
import xyz.jeelzzz.genkore.util.mm
import java.io.IOException

class MiniMessageDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<Component>(vc) {
    @Throws(IOException::class)
    override fun deserialize(jp: JsonParser, ctx: DeserializationContext?): Component {
        val node: JsonNode = jp.codec.readTree(jp)
        return node.asText().mm()
    }
}