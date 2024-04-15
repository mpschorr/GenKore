package xyz.jeelzzz.genkore.config.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.jeelzzz.genkore.util.log
import xyz.jeelzzz.genkore.util.mm
import java.io.IOException


class ItemStackDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<ItemStack?>(vc) {
    @Throws(IOException::class)
    override fun deserialize(jp: JsonParser, ctx: DeserializationContext?): ItemStack {
        val node: JsonNode = jp.codec.readTree(jp)

        if (node.isTextual) {
            return ItemStack(matchMaterial(jp, node.asText()))
        }

        log(node.fields().asSequence().joinToString("\n"))

        val materialStr: String? = node["type"]?.textValue() ?: node["material"]?.textValue() ?: node["item"]?.textValue()
        val material = matchMaterial(jp, materialStr)
        val item = ItemStack(material)
        val meta = item.itemMeta

        // Name
        if (node.has("name")) {
            val nameNode = node["name"]
            if (nameNode.isTextual) {
                meta.displayName(nameNode.asText().mm())
            } else {
                throw InvalidFormatException(
                    jp, "Expected name to be string, but found " + nameNode.nodeType.name, nameNode,
                    ItemStack::class.java
                )
            }
        }

//        println("deser dn " + meta.displayName())

        // Lore
        if (node.has("lore")) {
            val loreNode = node["lore"]
            if (loreNode.isArray) {
                val lore = mutableListOf<Component>()
                for ((i, loreLine) in loreNode.withIndex()) {
                    if (!loreLine.isTextual) {
                        throw InvalidFormatException(
                            jp,
                            "Expected lore to be string array, but item " + i + " was " + loreLine.nodeType.name,
                            loreLine,
                            ItemStack::class.java
                        )
                    }
                    lore.add(loreLine.asText().mm())
                }
                meta.lore(lore)
            } else {
                throw InvalidFormatException(
                    jp, "Expected lore to be string array, but found " + loreNode.nodeType.name, loreNode,
                    ItemStack::class.java
                )
            }
        }

        // Meta & return
        item.setItemMeta(meta)
        return item
    }

    private fun matchMaterial(jp: JsonParser?, text: String?): Material {
        val material = Material.matchMaterial(text!!)
        if (material != null) {
            return material
        } else {
            throw InvalidFormatException(jp, "Material was provided, but is not valid", text, ItemStack::class.java)
        }
    }

}