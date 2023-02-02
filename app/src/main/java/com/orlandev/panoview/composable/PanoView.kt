package com.orlandev.panoview.composable

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.orlandev.panoview.utils.getCoilBitmap

@Composable
fun PanoView(
    url: String,
    modifier: Modifier = Modifier,
    setInfoButton: Boolean = false,
    setFullScreenButtonEnabled: Boolean = true,
    setStereoModeButtonEnabled: Boolean = true,
    setTouchTrackingEnabled: Boolean = true
) {

    val panoViewState = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val context = LocalContext.current

    //Lanzamos este efecto una sola vez
    LaunchedEffect(Unit) {
        //Como hay que esperar a que se cargue la imagen desde internet
        //Agregaremos un loader
        panoViewState.value = getCoilBitmap(context, url)
    }

    //Si aun no se ha cargado la pano entonces mostramos el cargando
    if (panoViewState.value == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        //Cuando se tenga lista la imagen  en bitmap la cargamos en el componente
        //de VrPanoView de Google

        //Como no existe un composable predefinido para cargar imagenes 360 pues
        // crearemos uno a partir del AndroidView

        AndroidView(factory = {

            VrPanoramaView(context).apply {
                val options = VrPanoramaView.Options()
                options.inputType = VrPanoramaView.Options.TYPE_MONO

                setInfoButtonEnabled(setInfoButton)
                setFullscreenButtonEnabled(setFullScreenButtonEnabled)
                setStereoModeButtonEnabled(setStereoModeButtonEnabled)
                setTouchTrackingEnabled(setTouchTrackingEnabled)

                //:-) Error al nunca cargar el bitmap :-)
                loadImageFromBitmap(panoViewState.value, options)

            }

        }, modifier = modifier)

    }
}