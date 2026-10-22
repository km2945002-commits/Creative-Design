package com.example.ui.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.StudioData
import com.example.ui.components.GradientButton
import com.example.ui.components.InteractiveMockupViewer
import com.example.ui.components.StudioPill
import com.example.ui.components.launchWhatsApp
import com.example.ui.theme.BrandGradient
import com.example.ui.theme.StudioAmber
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onNavigateToServices: () -> Unit,
    onNavigateToQuote: (String?) -> Unit,
    onNavigateToPortfolio: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 32.dp)
    ) {
        // Hero Image Showcase
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cds_hero_banner_1792661099548),
                    contentDescription = "Studio Workspace",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0x9908090E),
                                    Color(0xFF08090E)
                                )
                            )
                        )
                )

                // Floating live badge
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.Black.copy(alpha = 0.75f),
                    border = BorderStroke(1.dp, StudioBorder),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(StudioEmerald)
                        )
                        Text(
                            text = "Islamabad Design Hub",
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            color = StudioTextPrimary
                        )
                    }
                }
            }
        }

        // Hero Typography & Call-To-Action
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                StudioPill(text = "AI + CRAFT ARCHITECTURE", color = StudioPink)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Creative Ideas.",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = StudioTextPrimary,
                    lineHeight = 36.sp
                )
                Text(
                    text = "Smart AI.",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = StudioAmber,
                    lineHeight = 36.sp
                )
                Text(
                    text = "Powerful Digital Experiences.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = StudioTextPrimary,
                    lineHeight = 30.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "We transform ideas into professional websites, apps, logos, and brand identities using modern AI tools, WordPress, Hostinger, CorelDRAW, and Adobe Illustrator. Surgical vector precision and high-performance digital execution.",
                    fontSize = 13.sp,
                    color = StudioTextSecondary,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Actions row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    GradientButton(
                        text = "Get a Quote",
                        icon = Icons.AutoMirrored.Filled.ArrowForward,
                        onClick = { onNavigateToQuote(null) },
                        modifier = Modifier.weight(1f),
                        testTag = "hero_quote_button"
                    )

                    OutlinedButton(
                        onClick = onNavigateToServices,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, StudioBorder),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("hero_explore_button")
                    ) {
                        Text(
                            text = "Services",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = StudioTextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Direct WhatsApp Button
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = StudioEmerald.copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, StudioEmerald.copy(alpha = 0.4f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { launchWhatsApp(context) }
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                            tint = StudioEmerald,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Chat Directly on WhatsApp: +92 319 9774681",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = StudioEmerald
                        )
                    }
                }
            }
        }

        // Quick Service Pillars
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "PILLARS OF EXECUTION",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = StudioTextMuted,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StudioData.services.forEachIndexed { index, srv ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = StudioSurface,
                            border = BorderStroke(1.dp, StudioBorder),
                            modifier = Modifier.clickable { onNavigateToQuote(srv.name) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "${index + 1}.",
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = StudioAmber,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = srv.name,
                                    fontSize = 11.sp,
                                    color = StudioTextPrimary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Live Interactive Mockup Simulator
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "STUDIO PREVIEW LAB",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = StudioTextMuted,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Interactive",
                        fontSize = 10.sp,
                        color = StudioEmerald,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                InteractiveMockupViewer()
            }
        }

        // Studio Metric Badges
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Stat 1
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = StudioSurface),
                        border = BorderStroke(1.dp, StudioBorder),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = "100%", fontSize = 22.sp, fontWeight = FontWeight.Black, color = StudioAmber)
                            Text(text = "Finished Human Vector Quality", fontSize = 10.sp, color = StudioTextSecondary)
                        }
                    }
                    // Stat 2
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = StudioSurface),
                        border = BorderStroke(1.dp, StudioBorder),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = "0.72s", fontSize = 22.sp, fontWeight = FontWeight.Black, color = StudioCyan)
                            Text(text = "Ultra-Fast Website Performance", fontSize = 10.sp, color = StudioTextSecondary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Stat 3
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = StudioSurface),
                        border = BorderStroke(1.dp, StudioBorder),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = "300 DPI", fontSize = 22.sp, fontWeight = FontWeight.Black, color = StudioPink)
                            Text(text = "CMYK Print Press Standardization", fontSize = 10.sp, color = StudioTextSecondary)
                        }
                    }
                    // Stat 4
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = StudioSurface),
                        border = BorderStroke(1.dp, StudioBorder),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = "24h", fontSize = 22.sp, fontWeight = FontWeight.Black, color = StudioEmerald)
                            Text(text = "Quote & Proposal Response Time", fontSize = 10.sp, color = StudioTextSecondary)
                        }
                    }
                }
            }
        }

        // Tools vs Deliverables Guarantee
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF131522)),
                border = BorderStroke(1.dp, StudioPurple.copy(alpha = 0.4f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = StudioPurple,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "TOOLS VS DELIVERABLES GUARANTEE",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = StudioPurple
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "You receive 100% finished, human-perfected, and print-tested project deliverables.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = StudioTextPrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "AI accelerates our ideation and layout testing, but every line curve, color code, cut-line, and deploy script is reviewed and perfected by human design engineers before handover.",
                        fontSize = 12.sp,
                        color = StudioTextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
