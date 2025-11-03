package com.example.proyectoparte2_marioherrero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectoparte2_marioherrero.ui.theme.ProyectoParte2MarioHerreroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoParte2MarioHerreroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TypographyPreview(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TypographyPreview(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "displayLarge",
            style = MaterialTheme.typography.displayLarge
        )

        Text(
            text = "displayMedium",
            style = MaterialTheme.typography.displayMedium
        )

        Text(
            text = "displaySmall",
            style = MaterialTheme.typography.displaySmall
        )

        Text(
            text = "headlineLarge",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "headlineMedium",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "headlineSmall",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "titleLarge",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "titleMedium",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "titleSmall",
            style = MaterialTheme.typography.titleSmall
        )

        Text(
            text = "bodyLarge",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "bodyMedium",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "bodySmall",
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = "labelLarge",
            style = MaterialTheme.typography.labelLarge
        )

        Text(
            text = "labelMedium",
            style = MaterialTheme.typography.labelMedium
        )

        Text(
            text = "labelSmall",
            style = MaterialTheme.typography.labelSmall
        )
    }
}
