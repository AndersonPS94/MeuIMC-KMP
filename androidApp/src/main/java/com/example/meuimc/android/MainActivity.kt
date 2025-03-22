package com.example.meuimc.android
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meuimc.IMCCalculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                IMCScreen()
            }
        }
    }
}

@Composable
fun IMCScreen() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("Informe os valores") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),



        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF00B4AD), Color(0xFF008EFF))
                        ),
                        shape = RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp)
                    ),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {

            Column(
                modifier = Modifier.padding(32.dp)
            ) {
                Icon(

                    painter = painterResource(R.drawable.ic_peso),
                    contentDescription = "Ícone de IMC",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Calcule seu IMC abaixo:",
                    color = Color.White,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Digite o seu peso atual (kg)") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_peso),
                        contentDescription = "Ícone de peso",
                        modifier = Modifier.size(30.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Digite sua altura (m)") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_altura),
                        contentDescription = "Ícone de altura",
                        modifier = Modifier.size(30.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val pesoDouble = peso.toDoubleOrNull()
                    val alturaDouble = altura.toDoubleOrNull()

                    if (pesoDouble != null && alturaDouble != null) {
                        val imc = IMCCalculator.calcularIMC(pesoDouble, alturaDouble)
                        val interpretacao = IMCCalculator.interpretarIMC(imc)
                        resultado = "IMC: %.2f - %s".format(imc, interpretacao)
                    } else {
                        resultado = "Preencha os campos corretamente!"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008EFF))
            ) {
                Text("Calcular IMC", color = Color.White,
                    fontSize = 32.sp
                    )
            }

            Text(
                text = resultado,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IMCScreenPreview() {
    MyApplicationTheme {
        IMCScreen()
    }
}
