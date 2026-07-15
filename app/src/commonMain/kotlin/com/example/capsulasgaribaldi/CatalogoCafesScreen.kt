package com.example.capsulasgaribaldi

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.glance.layout.height
import androidx.glance.layout.size
// Importaciones de Recursos Multiplatform
import capsulasgaribaldi.app.generated.resources.Res
import capsulasgaribaldi.app.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * 2. Adaptación de la Data Class:
 * Se cambia el ID de recurso nativo (Int) por DrawableResource de Compose Multiplatform.
 */
data class CapsulaCafe(
    val nombre: String,
    val descripcion: String,
    val imagenRes: DrawableResource
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogoCafesScreen(modifier: Modifier = Modifier) {

    /**
     * 3. Referencias de imágenes adaptadas:
     * Se usa 'Res.drawable.nombre' en lugar de 'R.drawable.nombre'.
     */
    val listaCafes = kotlin.collections.listOf(
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

    val pagerState = rememberPagerState(pageCount = { listaCafes.size })

    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 40.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val cafe = listaCafes[page]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF7A7979)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {

                    /**
                     * 4. Pintado de imágenes compatible:
                     * Se utiliza 'painterResource(resource = ...)' de la API multiplataforma.
                     */
                    Image(
                        painter = painterResource(resource = cafe.imagenRes),
                        contentDescription = "Cápsula de café ${cafe.nombre}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )

                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = cafe.nombre,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = cafe.descripcion,
                        fontSize = 15.sp,
                        color = Color(0xFFE0E0E0),
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}