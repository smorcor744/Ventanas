import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val icon = BitmapPainter(useResource("sample.png", ::loadImageBitmap))
    val mainWindowState = rememberWindowState()
    val secondWindowState = rememberWindowState()
    var showMainWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }

    if (showMainWindow) {
        MainWindow(
            icon = icon,
            onClose = { showMainWindow = false },
            windowState = mainWindowState,
            onClickOpenSecondWindow ={
                showMainWindow =false
                showSecondWindow = true}
        )
    }

    if (showSecondWindow) {
        SecondaryWindow(
            icon = icon,
            onClose = { showSecondWindow = false },
            windowState = secondWindowState,
            onClickOpenSecondWindow = {
                showMainWindow =true
                showSecondWindow = false}
        )
    }

    if (!showMainWindow && !showSecondWindow) {
        exitApplication()
    }
}

@Composable
fun SecondaryWindow(
    icon : BitmapPainter,
    windowState: WindowState,
    onClose : () -> Unit,
    onClickOpenSecondWindow : () -> Unit
) {
    Window(
        onCloseRequest = onClose,
        title = "Ventana Secundaria",
        state = windowState,
        icon = icon
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(100.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Estas en la ventana Secundaria", color = Color.Magenta)
            Button(
                onClick = onClickOpenSecondWindow
            ) {
                Text("Abrir Ventana Secundaria y cerrar esta.")
            }
        }
    }
}

@Composable
fun MainWindow(
    icon : BitmapPainter,
    windowState: WindowState,
    onClose : () -> Unit,
    onClickOpenSecondWindow : () -> Unit
){
    Window(
        onCloseRequest = onClose,
        title = "Ventana Primaria",
        state = windowState,
        icon = icon
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                100.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ){

            Text(text = "Estas en la ventana Principal", color = Color.Cyan)
            Button(
                onClick = onClickOpenSecondWindow
            ) {
                Text("Abrir Ventana Primaria y cerrar esta.")
            }

        }
    }
}