package com.example.locafyapp.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.locafyapp.R
import com.example.locafyapp.alertas.mostrarAlerta
import com.example.locafyapp.viewModel.LoginViewModel

class LoginScreen(private val navController: NavController? = null) {

    @Composable
    fun login(){

        val viewModel = viewModel<LoginViewModel>()
        val username = viewModel.loginViewModel.username
        val password = viewModel.loginViewModel.password

        val navegan = viewModel.navegan

        if(navegan == true){
            navController?.navigate("inicio")
            viewModel.cambiarNavegan()
        }

        if(viewModel.verAlerta == true){
            mostrarAlerta(
                titulo = viewModel.tituloAlerta,
                mensaje = viewModel.mensajeAlerta,
                onDismiss = {viewModel.descartarAlerta()},
                onConfirm = {viewModel.descartarAlerta()},
                textoBtnConfirmar = viewModel.textoBtnAlerta
            )
        }

        var transicion = rememberInfiniteTransition()

        val offsetY by transicion.animateFloat(
            initialValue = 0f,
            targetValue = 20f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
        val colorCampo by animateColorAsState(
            Color.White
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorCampo)
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        )
        {
            Image(
                painter = painterResource(id = R.drawable.logolocafy),
                contentDescription = "logo",
                modifier = Modifier.offset(y= offsetY.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text ="Iniciar Sesion",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorCampo,
                modifier = Modifier.padding(32.dp).fillMaxWidth().offset(y= offsetY.dp),
                textAlign = TextAlign.Center
            )
            TextField(
                value = username,
                onValueChange = {viewModel.cambioUsername(it)},
                label ={Text("username")},
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = {viewModel.cambioPassword(it)},
                label = {Text("password")},
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {viewModel.hacerLogin()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                Text("Acceder")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun verlogin(){
    LoginScreen().login()
}