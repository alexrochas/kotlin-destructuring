import kotlin.reflect.KFunction

class Paragraph(
    val clazz: String,
    val id: Long,
    val content: String
) {

    fun print() {
        print("<p class=\"$clazz\" id=\"$id\">$content</p>")
    }

    fun println(pattern: String) {
        print(pattern)
    }

}

fun <T> destruct(f: KFunction<T>, params: Map<String, Any>): T {
    val args = f
        .parameters
        .filter { params.containsKey(it.name) }
        .map { it to params.getValue(it.name!!) }
        .toMap()

    return f.callBy(args)
}

fun main(args: Array<String>) {
    val paragraph = destruct(::Paragraph, mapOf(
        "clazz" to "some-clazz",
        "id" to 42L,
        "content" to "BOOM"
    ))

    paragraph.print()
    destruct(paragraph::println, mapOf(
        "pattern" to "?"
    ))
}
