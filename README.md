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

## Meta

Alex Rocha - [about.me](http://about.me/alex.rochas)
