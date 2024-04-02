package xyz.jeelzzz.genkore.config.decoders

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bukkit.Material

typealias SerializableMaterial = @Serializable(MaterialSerializer::class) Material

class MaterialSerializer : KSerializer<Material> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Material", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Material) {
        encoder.encodeString(value.key.asString())
    }

    override fun deserialize(decoder: Decoder): Material {
        val str = decoder.decodeString()
        val material = Material.matchMaterial(str) ?: throw SerializationException("Material $str was not found")
        return material
    }
}