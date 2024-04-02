package xyz.jeelzzz.genkore.config.decoders

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import xyz.jeelzzz.genkore.util.mm
import net.kyori.adventure.text.Component

typealias SerializableComponent = @Serializable(ComponentSerializer::class) Component

class ComponentSerializer : KSerializer<Component> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Material", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Component) {
        encoder.encodeString(mm.serialize(value))
    }

    override fun deserialize(decoder: Decoder): Component {
        return decoder.decodeString().mm()
    }
}