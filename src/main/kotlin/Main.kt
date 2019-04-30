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
        .filter { it.name in params }
        .map { it to params.getValue(it.name!!) }
        .toMap()

    return f.callBy(args)
}

fun <T> destruct(f: KFunction<T>, vararg params: Pair<String, Any>): T {
    return destruct(f, params.toMap())
}

inline fun <reified T> destruct(params: Map<String, Any>): T {
    return destruct(T::class.constructors.first(), params)
}

inline fun <reified T> destruct(vararg params: Pair<String, Any>): T {
    return destruct(params.toMap())
}

fun main(args: Array<String>) {

    // By constructor function

    val paragraph = destruct(::Paragraph,
        "clazz" to "some-clazz",
        "id" to 42L,
        "content" to "BOOM"
    )

    paragraph.print()
    destruct(paragraph::println,
        "pattern" to "?"
    )

    // By default constructor

    val paragraphByConstructor: Paragraph = destruct(
        "clazz" to "some-clazz",
        "id" to 42L,
        "content" to "BOOM"
    )

    paragraph.print()
    destruct(paragraph::println,
        "pattern" to "?"
    )

}
