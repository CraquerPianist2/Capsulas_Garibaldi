package com.example.capsulasgaribaldi

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.DrawableResource

import capsulasgaribaldi.app.generated.resources.*

// DEFINICIÓN DE LA CÁPSULA
data class CapsulaCafe(
    val nombre: String,
    val descripcion: String,
    val imagenRes: DrawableResource
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogoCafesScreen(modifier: Modifier = Modifier) {

    // LISTA COMPLETA CON TUS DESCRIPCIONES OPTIMIZADAS (MANTENIENDO TUS PORCENTAJES)
    val listaCafes = listOf(
        CapsulaCafe(
            nombre = "Arábico",
            descripcion = "Un café de aroma sublime y sabor profundo, con un 8% de cafeína para disfrutar de una gran intensidad en cada taza.",
            imagenRes = Res.drawable.arabico
        ),
        CapsulaCafe(
            nombre = "Chocolate",
            descripcion = "Toda la experiencia de un chocolate caliente, denso y reconfortante, listo para disfrutar directamente sin necesidad de añadir leche.",
            imagenRes = Res.drawable.chocolate
        ),
        CapsulaCafe(
            nombre = "Cortado",
            descripcion = "La combinación ideal de nuestro espresso con la dosis justa de leche cremosa, todo integrado en una sola cápsula.",
            imagenRes = Res.drawable.cortado
        ),
        CapsulaCafe(
            nombre = "Descafeinado",
            descripcion = "Todo el cuerpo, aroma y sabor del café clásico, descafeinado mediante un proceso 100% natural.",
            imagenRes = Res.drawable.descafeinado
        ),
        CapsulaCafe(
            nombre = "Escumoso",
            descripcion = "Una textura suave y aterciopelada coronada con una densa capa de crema y un 7% de cafeína para disfrutar con calma.",
            imagenRes = Res.drawable.escumoso
        ),
        CapsulaCafe(
            nombre = "Ginseng",
            descripcion = "Una alternativa energizante sin cafeína, con un aroma dulce e inconfundible que te recordará al mítico caramelo Solano.",
            imagenRes = Res.drawable.gingseng
        ),
        CapsulaCafe(
            nombre = "Intenso",
            descripcion = "Un espresso potente con notas tostadas y un 9% de cafeína, diseñado para quienes buscan un verdadero chute de energía.",
            imagenRes = Res.drawable.intenso
        ),
        CapsulaCafe(
            nombre = "Irlandés",
            descripcion = "La esencia de un carajillo de crema de whisky (tipo Baileys) con un toque de café, algo de cafeína y sin una gota de alcohol.",
            imagenRes = Res.drawable.irlandes
        )
    )

    // Estado para controlar la tarjeta activa
    val pagerState = rememberPagerState(pageCount = { listaCafes.size })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray), // Fondo negro de la app
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp), // Margen para ver los laterales de las otras tarjetas
            pageSpacing = 16.dp, // Espacio entre tarjetas
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val cafe = listaCafes[page]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF7A7979)), // Tarjeta color 7A7979FF
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    // Render de tu cápsula
                    Image(
                        painter = painterResource(resource = cafe.imagenRes),
                        contentDescription = "Cápsula de café ${cafe.nombre}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Nombre del café
                    Text(
                        text = cafe.nombre,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White, // Letras blancas para contraste
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Descripción
                    Text(
                        text = cafe.descripcion,
                        fontSize = 15.sp,
                        color = Color(0xFFE0E0E0), // Gris muy suave para que sea fácil de leer
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}
