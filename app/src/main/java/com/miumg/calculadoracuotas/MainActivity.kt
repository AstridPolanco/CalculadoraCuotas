package com.miumg.calculadoracuotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.miumg.calculadoracuotas.ui.theme.CalculadoraCuotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            LoanCalculatorScreen()
            }
        }

}

@Composable
fun LoanCalculatorScreen() {
    var principal by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var term by remember { mutableStateOf("") }
    var monthlyPayment by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = principal,
            onValueChange = { principal = it },
            label = { Text("Principal") }
        )
        // ... otros campos de texto ...
        Button(onClick = {
            // Lógica de cálculo aquí
            val calculatedPayment = calculateMonthlyPayment(principal.toDouble(), interestRate.toDouble(), term.toInt())
            monthlyPayment = calculatedPayment.toString()
        }) {
            Text("Calcular")
        }
        Text("Cuota mensual: $monthlyPayment")
    }
}

fun calculateMonthlyPayment(principal: Double, annualInterestRate: Double, termInMonths: Int): Double {
    // Fórmula para calcular la cuota mensual
    val monthlyInterestRate = annualInterestRate / 12 / 100
    val numerator = principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, termInMonths.toDouble())
    val denominator = Math.pow(1 + monthlyInterestRate, termInMonths.toDouble()) - 1
    return numerator / denominator
}


@Composable
fun GreetingPreview() {
    CalculadoraCuotasTheme {

    }
}