# kotlin-destructors
> Study approach to create Kotlin destructors the same way Node

## Usage

Given a class like:

```kotlin
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
```

I want to be able to pass a map into a function instead of "one-by-one" parameters:

```kotlin
fun main(args: Array<String>) {
    val paragraph = destruct(::Paragraph, mapOf(
        "clazz" to "some-clazz",
        "id" to 42L,
        "content" to "BOOM"
    ))
    
    destruct(paragraph::println, mapOf(
        "pattern" to "?"
    ))
}
```

## My Wish

Be able to use like that:

```kotlin
    @Test
    fun `experimenting with destruct concepts`() {
        fun <T> destruct(params: Map<String, Any>, lambda: (String, Int) -> T): T {
            val f = lambda.reflect()!!
            val args = f
                    .parameters
                    .filter { it.name in params }
                    .map { it to params.getValue(it.name!!) }
                    .toMap()

            return f.callBy(args)
        }

        fun rule(params: Map<String, Any>) = destruct(params, fun (input1: String, input2: Int): String {
            return input1 + input2
        })

        val finalValue = rule(mapOf(
                "input1" to "Hello",
                "input2" to 2
        ))

        assertEquals("Hello2", finalValue)
    }

```

Error: Introspecting local functions, lambdas, anonymous functions and local variables is not yet fully supported in Kotlin reflection

## Meta

Alex Rocha - [about.me](http://about.me/alex.rochas)
