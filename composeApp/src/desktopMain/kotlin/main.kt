import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.faye.kotlinconf.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinConf2024",
    ) {
        App()
    }
}