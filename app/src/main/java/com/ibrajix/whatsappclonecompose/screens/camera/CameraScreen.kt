package com.ibrajix.whatsappclonecompose.screens.camera

import android.net.Uri
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.extension.executor
import com.ibrajix.whatsappclonecompose.extension.getCameraProvider
import com.ibrajix.whatsappclonecompose.extension.takePicture
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun CameraScreen(modifier: Modifier = Modifier){

    val emptyImageUri = Uri.parse("file://dev/null")
    var imageUri by remember {
        mutableStateOf(emptyImageUri)
    }

    if (imageUri != emptyImageUri){

        Box(modifier = modifier){

           GlideImage(
               imageModel = imageUri
           )

            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    imageUri = emptyImageUri
                }
            ) {
                Text("Remove image")
            }
        }

    }

    else {

        CameraCapture(
            modifier = modifier,
            onImageFile = {file->
                imageUri = file.toUri()
            }
        )
    }

}

@Composable
fun CameraCapture(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA,
    onImageFile: (File) -> Unit = { }
){
    
    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder()
                .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()
        )
    }

    Box(modifier = modifier){
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }

        CameraPreview(
            modifier = modifier.fillMaxSize(),
            onUseCase =  {
                previewUseCase = it
            }
        )

        val coroutineScope = rememberCoroutineScope()

        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_flash_off),
                contentDescription = stringResource(id = R.string.icon)
            )

            OutlinedButton(
                onClick = {
                    coroutineScope.launch {
                        imageCaptureUseCase.takePicture(context.executor).let {
                            onImageFile(it)
                        }
                    }
                },
                modifier = modifier.size(80.dp),
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(3.dp, Color.White),
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            )
            {
                //no image or text for button
            }

            Image(
                painter = painterResource(id = R.drawable.ic_camera_switch),
                contentDescription = stringResource(id = R.string.camera)
            )

        }

        LaunchedEffect(previewUseCase){
            val cameraProvider = context.getCameraProvider()
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
                )
            } catch (ex: Exception){
                //log exception
            }
        }
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    onUseCase: (UseCase) -> Unit = { }
){

    AndroidView(
        modifier = modifier,
        factory = { context ->

            val previewView = PreviewView(context).apply {
                this.scaleType = scaleType
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }


            onUseCase(Preview.Builder()
                .build()
                .also{
                    it.setSurfaceProvider(previewView.surfaceProvider)
                })

            previewView
      }
    )
}

