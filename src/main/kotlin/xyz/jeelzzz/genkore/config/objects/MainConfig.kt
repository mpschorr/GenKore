package xyz.jeelzzz.genkore.config.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.jeelzzz.genkore.config.decoders.SerializableComponent
import xyz.jeelzzz.genkore.config.decoders.SerializableMaterial

@Serializable
data class MainConfig(
    val gens: Map<Int,Gen>
) {
    @Serializable
    data class Gen(
        val id: String,
        @SerialName("block_type")
        val blockType: SerializableMaterial,
        @SerialName("block_name")
        val blockName: SerializableComponent,
        @SerialName("drop_type")
        val dropType: SerializableMaterial,
        @SerialName("drop_name")
        val dropName: SerializableComponent,
        val worth: Double,
        @SerialName("cost")
        val upgradeCost: Double
    )
}

//interface MainConfig {
//
//    @DefaultMap()
//    fun gens(): Map<Int, @SubSection GenConfigData>
//
//}

//interface GenConfigData {
//    @Order(1)
//    fun id(): String
//
//    @ConfKey("block_type")
//    @Order(2)
//    fun blockType(): Material
//    @ConfKey("block_name")
//    @Order(3)
//    fun blockName(): Component
//
//    @ConfKey("drop_type")
//    @Order(4)
//    fun dropType(): Material
//    @ConfKey("drop_name")
//    @Order(5)
//    fun dropName(): Component
//
//    @Order(6)
//    fun worth(): Double
//    @ConfKey("cost")
//    @Order(7)
//    fun upgradeCost(): Double
//}