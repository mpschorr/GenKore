package xyz.jeelzzz.genkore.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit

val mm = MiniMessage.miniMessage()

/**
 * Logs text after converting legacy color codes (&) and minimessage
 */
fun log(message: String) {
    log(message.mm())
}

/**
 * Logs a component by sending to the console sender (no plugin prefix)
 */
fun log(message: Component) {
    Bukkit.getConsoleSender().sendMessage(message)
}

/**
 * Converts a string to a component with legacy color code (&) support
 */
fun String.mm() : Component {
    return mm.deserialize(this.convertLegacyColors()).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE)
}

/**
 * Converts legacy codes to minimessage codes (ex. "&bHello &cWorld" -> "<aqua>Hello <reset><red>World")
 */
fun String.convertLegacyColors() : String {
    return this
        .replace("&0", "&r<black>")
        .replace("&1", "&r<dark_blue>")
        .replace("&2", "&r<dark_green>")
        .replace("&3", "&r<dark_aqua>")
        .replace("&4", "&r<dark_red>")
        .replace("&5", "&r<dark_purple>")
        .replace("&6", "&r<gold>")
        .replace("&7", "&r<gray>")
        .replace("&8", "&r<dark_gray>")
        .replace("&9", "&r<blue>")
        .replace("&a", "&r<green>")
        .replace("&b", "&r<aqua>")
        .replace("&c", "&r<red>")
        .replace("&d", "&r<light_purple>")
        .replace("&e", "&r<yellow>")
        .replace("&f", "&r<white>")
        .replace("&k", "<obf>")
        .replace("&l", "<b>")
        .replace("&m", "<st>")
        .replace("&n", "<u>")
        .replace("&o", "<i>")
        .replace("&r", "<reset>")
}