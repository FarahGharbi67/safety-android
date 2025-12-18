package br.com.utfpr.safety.feature.safe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.utfpr.safety.R
import br.com.utfpr.safety.feature.safe.SafeStatus


@Composable
fun SafeStatusCard(status: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(16.dp, CircleShape)
                        .background(
                            color = if (status == SafeStatus.UNLOCKED.toString())
                                Color(0xFF2E7D32)
                            else
                                Color(0xFFC62828),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = if (status == SafeStatus.UNLOCKED.toString())
                            painterResource(id = R.drawable.safe_unlocked)
                        else painterResource(id = R.drawable.safe_locked),
                        contentDescription = "Safe",
                        modifier = Modifier.size(96.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "STATUS: $status",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (status == SafeStatus.UNLOCKED.toString())
                        "Access granted \uD83D\uDD13"
                        else "Access blocked \uD83D\uDD12",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
