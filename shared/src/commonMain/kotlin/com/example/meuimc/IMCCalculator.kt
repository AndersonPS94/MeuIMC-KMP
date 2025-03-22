package com.example.meuimc

object IMCCalculator {
    fun calcularIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }

    fun interpretarIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }
}
