import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                javaClass.classLoader.getResource("html/index.html")?.let { url ->
                    val htmlFile = File(url.toURI())
                    call.respondFile(htmlFile)
                }
            }

            get("/nav-test") {
                javaClass.classLoader.getResource("html/nav-test-page.html")?.let { url ->
                    val htmlFile = File(url.toURI())
                    call.respondFile(htmlFile)
                }
            }

            get("/.well-known/assetlinks.json") {
                javaClass.classLoader.getResource("assetlinks.json")?.let { url ->
                    val jsonFile = File(url.toURI())
                    call.respondFile(jsonFile)
                }
            }

            staticResources("/static", "static")
        }
    }.start(wait = true)
}