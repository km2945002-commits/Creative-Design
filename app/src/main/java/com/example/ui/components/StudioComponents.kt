package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioProject
import com.example.data.ServiceItem
import com.example.data.StudioData
import com.example.ui.theme.BrandGradient
import com.example.ui.theme.StudioAmber
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioBlue
import com.example.ui.theme.StudioBorder
import com.example.ui.theme.StudioCardElevated
import com.example.ui.theme.StudioCyan
import com.example.ui.theme.StudioEmerald
import com.example.ui.theme.StudioOrange
import com.example.ui.theme.StudioPink
import com.example.ui.theme.StudioPurple
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant
import com.example.ui.theme.StudioTextMuted
import com.example.ui.theme.StudioTextPrimary
import com.example.ui.theme.StudioTextSecondary
import java.net.URLEncoder

fun launchWhatsApp(context: Context, prefilledMessage: String? = null) {
    try {
        val message = prefilledMessage ?: "Hello Creative Design Studio! I am interested in discussing a project."
        val encoded = URLEncoder.encode(message, "UTF-8")
        val uri = Uri.parse("https://wa.me/${StudioData.STUDIO_WHATSAPP_NUMBER}?text=$encoded")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    } catch (_: Exception) {
        Toast.makeText(context, "Could not open WhatsApp. Phone: ${StudioData.STUDIO_PHONE}", Toast.LENGTH_LONG).show()
    }
}

fun launchDialer(context: Context) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${StudioData.STUDIO_PHONE}"))
        context.startActivity(intent)
    } catch (_: Exception) {
        Toast.makeText(context, "Call: ${StudioData.STUDIO_PHONE}", Toast.LENGTH_SHORT).show()
    }
}

fun launchEmail(context: Context, subject: String = "Project Commission Inquiry", body: String = "") {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${StudioData.STUDIO_EMAIL}")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        context.startActivity(intent)
    } catch (_: Exception) {
        Toast.makeText(context, "Email: ${StudioData.STUDIO_EMAIL}", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun StudioHeader(
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(StudioBackground.copy(alpha = 0.95f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BrandGradient),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "CDS",
                    fontWeight = FontWeight.Black,
                    fontSize = 13.sp,
                    color = Color.Black,
                    letterSpacing = (-0.5).sp
                )
            }
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Creative Design ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = StudioTextPrimary
                    )
                    Text(
                        text = "Studio",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = StudioAmber
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                    val alpha by infiniteTransition.animateFloat(
                        initialValue = 0.4f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(800),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "alpha"
                    )
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(StudioEmerald.copy(alpha = alpha))
                    )
                    Text(
                        text = "Suhan Islamabad · Taking Commissions",
                        fontSize = 10.sp,
                        color = StudioTextSecondary,
                        maxLines = 1
                    )
                }
            }
        }

        Surface(
            shape = RoundedCornerShape(20.dp),
            color = StudioEmerald.copy(alpha = 0.12f),
            border = BorderStroke(1.dp, StudioEmerald.copy(alpha = 0.35f)),
            modifier = Modifier.clickable { onWhatsAppClick() }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "WhatsApp",
                    tint = StudioEmerald,
                    modifier = Modifier.size(13.dp)
                )
                Text(
                    text = "WhatsApp",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = StudioEmerald
                )
            }
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    testTag: String = "gradient_button"
) {
    Box(
        modifier = modifier
            .testTag(testTag)
            .clip(RoundedCornerShape(12.dp))
            .background(BrandGradient)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                color = Color(0xFF0F0E05)
            )
            if (icon != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF0F0E05),
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}

@Composable
fun StudioPill(
    text: String,
    color: Color = StudioAmber,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = color.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, color.copy(alpha = 0.35f)),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ServiceCard(
    service: ServiceItem,
    onSelectForQuote: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = StudioSurface),
        border = BorderStroke(1.dp, if (expanded) StudioAmber.copy(alpha = 0.5f) else StudioBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("service_card_${service.id}")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StudioPill(text = service.shortTag, color = StudioAmber)
                Text(
                    text = service.estimatedTurnaround,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = StudioTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = service.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = StudioTextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = service.tagline,
                fontSize = 13.sp,
                color = StudioTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "STARTING FROM",
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        color = StudioTextMuted
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = service.startingPricePkr,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = StudioAmber
                        )
                        Text(
                            text = " (${service.startingPriceUsd})",
                            fontSize = 11.sp,
                            color = StudioTextSecondary
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { expanded = !expanded },
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, StudioBorder),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = StudioTextPrimary),
                        modifier = Modifier.testTag("expand_service_${service.id}")
                    ) {
                        Text(
                            text = if (expanded) "Less" else "Specs",
                            fontSize = 11.sp
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                    }

                    Button(
                        onClick = onSelectForQuote,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = StudioAmber.copy(alpha = 0.2f),
                            contentColor = StudioAmber
                        ),
                        modifier = Modifier.testTag("quote_service_${service.id}")
                    ) {
                        Text("Quote", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(StudioBorder)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Core Deliverables:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        color = StudioAmber
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    service.deliverables.forEach { item ->
                        Row(
                            modifier = Modifier.padding(vertical = 3.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = StudioEmerald,
                                modifier = Modifier
                                    .size(13.dp)
                                    .padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = item,
                                fontSize = 12.sp,
                                color = StudioTextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Production Tools & Standards:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        color = StudioPink
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        service.techOrMaterials.forEach { tech ->
                            StudioPill(text = tech, color = StudioTextSecondary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InteractiveMockupViewer(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf("laptop") }
    var showBleedGuides by remember { mutableStateOf(true) }
    var showGrommets by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = StudioSurface),
        border = BorderStroke(1.dp, StudioBorder),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Simulator Mode Selector Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(StudioSurfaceVariant, RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf(
                    "laptop" to "Web",
                    "mobile" to "App",
                    "cards" to "Cards",
                    "packaging" to "Pack",
                    "banner" to "Banner"
                ).forEach { (key, label) ->
                    val isSelected = selectedTab == key
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) StudioAmber else Color.Transparent)
                            .clickable { selectedTab = key }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = label,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.Black else StudioTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Canvas preview simulation
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF07080D))
                    .border(BorderStroke(1.dp, StudioBorder)),
                contentAlignment = Alignment.Center
            ) {
                when (selectedTab) {
                    "laptop" -> {
                        // Laptop / Web frame simulation
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(160.dp)
                                .background(Color(0xFF131520), RoundedCornerShape(10.dp))
                                .border(BorderStroke(1.dp, StudioBorder), RoundedCornerShape(10.dp))
                                .padding(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFFEF4444)))
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFFF59E0B)))
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF10B981)))
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(14.dp)
                                        .background(Color(0xFF1C1F30), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text("creative-design-studio-six.vercel.app", fontSize = 8.sp, color = StudioTextMuted)
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            // Web page wireframe simulation
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(Color(0xFF0C0E17), RoundedCornerShape(6.dp))
                                    .padding(8.dp)
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .width(80.dp)
                                            .height(8.dp)
                                            .background(BrandGradient, RoundedCornerShape(2.dp))
                                    )
                                    Text("Creative Ideas · Smart AI", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                                    Text("WordPress · Hostinger · CorelDRAW", fontSize = 7.sp, color = StudioAmber)
                                    Spacer(modifier = Modifier.weight(1f))
                                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Box(modifier = Modifier.width(40.dp).height(12.dp).background(StudioAmber, RoundedCornerShape(3.dp)))
                                        Box(modifier = Modifier.width(40.dp).height(12.dp).background(StudioBorder, RoundedCornerShape(3.dp)))
                                    }
                                }
                            }
                        }
                    }
                    "mobile" -> {
                        // Smartphone frame simulation
                        Box(
                            modifier = Modifier
                                .width(110.dp)
                                .height(180.dp)
                                .background(Color(0xFF11131E), RoundedCornerShape(16.dp))
                                .border(BorderStroke(2.dp, StudioBorder), RoundedCornerShape(16.dp))
                                .padding(8.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(modifier = Modifier.width(30.dp).height(3.dp).background(Color(0xFF33384D), CircleShape))
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(modifier = Modifier.size(24.dp).background(BrandGradient, RoundedCornerShape(6.dp)), contentAlignment = Alignment.Center) {
                                    Text("CDS", fontSize = 7.sp, fontWeight = FontWeight.Black, color = Color.Black)
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Mobile Responsive", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                                Text("100% Core Web Vitals", fontSize = 6.sp, color = StudioEmerald)
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(modifier = Modifier.fillMaxWidth().height(16.dp).background(StudioCardElevated, RoundedCornerShape(4.dp)))
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(modifier = Modifier.fillMaxWidth().height(16.dp).background(StudioCardElevated, RoundedCornerShape(4.dp)))
                            }
                        }
                    }
                    "cards" -> {
                        // Business Card with Bleed Guides
                        Box(
                            modifier = Modifier
                                .width(220.dp)
                                .height(125.dp)
                                .background(Color(0xFF151824), RoundedCornerShape(6.dp))
                                .border(BorderStroke(1.dp, StudioBorder), RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (showBleedGuides) {
                                Canvas(modifier = Modifier.fillMaxSize()) {
                                    drawRect(
                                        color = Color(0xFFEF4444),
                                        topLeft = Offset(12f, 12f),
                                        size = androidx.compose.ui.geometry.Size(size.width - 24f, size.height - 24f),
                                        style = Stroke(
                                            width = 2f,
                                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                                        )
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(16.dp).background(StudioAmber, RoundedCornerShape(4.dp)))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("CREATIVE STUDIO", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Mortaza Khan", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = StudioAmber)
                                Text("Lead Brand & Web Designer", fontSize = 7.sp, color = StudioTextSecondary)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("+92 319 9774681 · km2945002@gmail.com", fontSize = 6.sp, color = StudioTextMuted)
                            }
                        }
                    }
                    "packaging" -> {
                        // 3D Packaging mockup
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(130.dp)
                                    .background(
                                        Brush.verticalGradient(listOf(Color(0xFF1E2133), Color(0xFF10121D))),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .border(BorderStroke(1.dp, StudioAmber.copy(alpha = 0.5f)), RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(modifier = Modifier.size(20.dp).background(StudioEmerald, CircleShape))
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text("LUMINA", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                                    Text("Botanical Elixir", fontSize = 6.sp, color = StudioAmber)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("30ml Dieline", fontSize = 6.sp, fontFamily = FontFamily.Monospace, color = StudioTextMuted)
                                    Text("||||| |||||||", fontSize = 8.sp, color = StudioTextSecondary)
                                }
                            }
                            Text("3D Structural Packaging Master", fontSize = 9.sp, color = StudioTextSecondary, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                    "banner" -> {
                        // Grand Flex Billboard banner
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(110.dp)
                                .background(Color(0xFF0F111C), RoundedCornerShape(6.dp))
                                .border(BorderStroke(1.dp, StudioBorder), RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (showBleedGuides) {
                                Canvas(modifier = Modifier.fillMaxSize()) {
                                    drawRect(
                                        color = Color(0xFFEF4444),
                                        topLeft = Offset(10f, 10f),
                                        size = androidx.compose.ui.geometry.Size(size.width - 20f, size.height - 20f),
                                        style = Stroke(
                                            width = 2f,
                                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                                        )
                                    )
                                }
                            }
                            if (showGrommets) {
                                Canvas(modifier = Modifier.fillMaxSize()) {
                                    val grommetColor = Color(0xFFEAB308)
                                    drawCircle(color = grommetColor, radius = 5f, center = Offset(14f, 14f))
                                    drawCircle(color = grommetColor, radius = 5f, center = Offset(size.width - 14f, 14f))
                                    drawCircle(color = grommetColor, radius = 5f, center = Offset(14f, size.height - 14f))
                                    drawCircle(color = grommetColor, radius = 5f, center = Offset(size.width - 14f, size.height - 14f))
                                    drawCircle(color = grommetColor, radius = 5f, center = Offset(size.width / 2, 14f))
                                    drawCircle(color = grommetColor, radius = 5f, center = Offset(size.width / 2, size.height - 14f))
                                }
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text("GRAND OUTDOOR PVC FLEX BANNER", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = StudioAmber)
                                Text("Calibrated for UV Solvent Inks", fontSize = 11.sp, fontWeight = FontWeight.Black, color = StudioTextPrimary)
                                Text("Weatherproof · 150 DPI Scale · Hemming", fontSize = 7.sp, color = StudioTextSecondary)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Sub-controls for the current preview mode
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (selectedTab) {
                        "laptop" -> "MODE: DESKTOP / RESPONSIVE VIEWPORT"
                        "mobile" -> "MODE: MOBILE APP INTERFACE"
                        "cards" -> "MODE: 300 DPI CMYK 3mm PRESS BLEED"
                        "packaging" -> "MODE: 3D CARTON DIELINE & LABEL"
                        "banner" -> "MODE: 12m x 6m SOLVENT PVC BANNER"
                        else -> "MODE: STUDIO SIMULATION"
                    },
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    color = StudioAmber
                )

                if (selectedTab == "cards" || selectedTab == "banner") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (showBleedGuides) StudioPink.copy(alpha = 0.2f) else StudioSurfaceVariant,
                            border = BorderStroke(1.dp, if (showBleedGuides) StudioPink else StudioBorder),
                            modifier = Modifier.clickable { showBleedGuides = !showBleedGuides }
                        ) {
                            Text(
                                text = "Bleed lines",
                                fontSize = 9.sp,
                                color = if (showBleedGuides) StudioPink else StudioTextSecondary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        if (selectedTab == "banner") {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = if (showGrommets) StudioAmber.copy(alpha = 0.2f) else StudioSurfaceVariant,
                                border = BorderStroke(1.dp, if (showGrommets) StudioAmber else StudioBorder),
                                modifier = Modifier.clickable { showGrommets = !showGrommets }
                            ) {
                                Text(
                                    text = "Eyelets",
                                    fontSize = 9.sp,
                                    color = if (showGrommets) StudioAmber else StudioTextSecondary,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
