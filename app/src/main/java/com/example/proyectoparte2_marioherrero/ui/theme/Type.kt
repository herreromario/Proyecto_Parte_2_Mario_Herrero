package com.example.proyectoparte2_marioherrero.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyectoparte2_marioherrero.R

val manropeFamily = FontFamily(
    Font(R.font.manrope_bold, FontWeight.Bold),
    Font(R.font.manrope_extrabold, FontWeight.ExtraBold),
    Font(R.font.manrope_extralight, FontWeight.ExtraLight),
    Font(R.font.manrope_light, FontWeight.Light),
    Font(R.font.manrope_medium, FontWeight.Medium),
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_semibold, FontWeight.SemiBold)
)
val Typography = Typography(
    // Pantallas principales, nombre de la app
    displayLarge = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),

    // Título principal dentro de pantallas (ej. "Realizar pedido")
    headlineLarge = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),

    // Títulos de secciones (ej. “Tus pedidos”, “Metodo de Pago")
    titleLarge = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),

    // Subtítulos o etiquetas más pequeñas
    titleMedium = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    // Texto normal (descripciones, cuerpo)
    bodyLarge = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    // Texto más pequeño (observaciones, notas)
    bodyMedium = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // Botones, etiquetas o chips
    labelLarge = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),

    // Texto auxiliar o de ayuda
    labelSmall = TextStyle(
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp
    )
)
