package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioProject
import com.example.data.StudioData
import com.example.ui.components.InteractiveMockupViewer
import com.example.ui.components.StudioPill
import com.example.ui.theme.StudioAmber
import com.example.ui.theme.StudioBorder
import com.example.ui.theme.StudioBorderSubtle
import com.example.ui.theme.StudioCardElevated
import com.example.ui.theme.StudioCyan
import com.example.ui.theme.StudioEmerald
import com.example.ui.theme.StudioPink
import com.example.ui.theme.StudioPurple
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant
import com.example.ui.theme.StudioTextMuted
import com.example.ui.theme.StudioTextPrimary
import com.example.ui.theme.StudioTextSecondary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PortfolioScreen(
    onSelectProjectForQuote: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("all") }
    var activeModalProject by remember { mutableStateOf<PortfolioProject?>(null) }
    var showLabSimulator by remember { mutableStateOf(false) }

    val categories = listOf(
        "all" to "All Work (${StudioData.projects.size})",
        "web-app" to "Websites & Apps",
        "logo" to "Logo Marks",
        "business-card" to "Cards & Stationery",
        "product" to "Packaging & Labels",
        "banner" to "Flex & Signage",
        "brand-identity" to "Brand Systems"
    )

    val filteredProjects = if (selectedCategory == "all") {
        StudioData.projects
    } else {
        StudioData.projects.filter { it.category == selectedCategory }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("portfolio_screen")
    ) {
        // Horizontal Category Filter Carousel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { (key, label) ->
                val isSelected = selectedCategory == key
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (isSelected) StudioAmber else StudioSurface,
                    border = BorderStroke(1.dp, if (isSelected) StudioAmber else StudioBorder),
                    modifier = Modifier.clickable { selectedCategory = key }
                ) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.Black else StudioTextSecondary,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Lab toggle banner
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = StudioSurfaceVariant),
                    border = BorderStroke(1.dp, StudioBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showLabSimulator = !showLabSimulator }
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "MOCKUP TEST BENCH & BLEED LAB",
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                color = StudioAmber,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (showLabSimulator) "Hide interactive simulation lab" else "Open live format & bleed simulator",
                                fontSize = 12.sp,
                                color = StudioTextSecondary
                            )
                        }
                        StudioPill(
                            text = if (showLabSimulator) "Close" else "Launch",
                            color = if (showLabSimulator) StudioPink else StudioAmber
                        )
                    }
                }
            }

            if (showLabSimulator) {
                item {
                    InteractiveMockupViewer()
                }
            }

            items(filteredProjects) { project ->
                ProjectItemCard(
                    project = project,
                    onViewDetails = { activeModalProject = project },
                    onQuoteProject = { onSelectProjectForQuote("${project.categoryLabel}: ${project.title}") }
                )
            }
        }
    }

    // Modal dialog for Project Case Study
    activeModalProject?.let { project ->
        BasicAlertDialog(
            onDismissRequest = { activeModalProject = null }
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF10121C),
                border = BorderStroke(1.dp, StudioBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StudioPill(text = project.categoryLabel, color = Color(project.accentColorHex))
                        IconButton(
                            onClick = { activeModalProject = null },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = StudioTextSecondary)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = project.title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = StudioTextPrimary
                    )

                    Text(
                        text = "Client: ${project.client}",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = StudioAmber
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = project.tagline,
                        fontSize = 12.sp,
                        color = StudioTextSecondary,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Specs grid
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(StudioSurface, RoundedCornerShape(10.dp))
                            .border(BorderStroke(1.dp, StudioBorder), RoundedCornerShape(10.dp))
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "SPECIFICATIONS",
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            color = StudioTextMuted,
                            fontWeight = FontWeight.Bold
                        )
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Format:", fontSize = 10.sp, color = StudioTextSecondary)
                            Text(text = project.format, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Tools / Stack:", fontSize = 10.sp, color = StudioTextSecondary)
                            Text(text = project.finishOrStack, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = StudioAmber)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Timeline:", fontSize = 10.sp, color = StudioTextSecondary)
                            Text(text = project.timeline, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = StudioEmerald)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Key Deliverables:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = StudioTextPrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    project.deliverables.forEach { item ->
                        Row(
                            modifier = Modifier.padding(vertical = 2.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = StudioEmerald,
                                modifier = Modifier.size(12.dp).padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = item,
                                fontSize = 11.sp,
                                color = StudioTextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val projectName = project.title
                            activeModalProject = null
                            onSelectProjectForQuote(projectName)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = StudioAmber, contentColor = Color.Black),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Request Similar Project", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectItemCard(
    project: PortfolioProject,
    onViewDetails: () -> Unit,
    onQuoteProject: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = StudioSurface),
        border = BorderStroke(1.dp, StudioBorder),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onViewDetails() }
            .testTag("project_card_${project.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StudioPill(text = project.categoryLabel, color = Color(project.accentColorHex))
                Text(
                    text = project.timeline,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    color = StudioTextMuted
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = StudioTextPrimary,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Client: ${project.client}",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                color = StudioAmber
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = project.tagline,
                fontSize = 12.sp,
                color = StudioTextSecondary,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = StudioSurfaceVariant,
                border = BorderStroke(1.dp, StudioBorderSubtle)
            ) {
                Text(
                    text = "Tools: ${project.toolsUsed}",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    color = StudioTextMuted,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onViewDetails,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, StudioBorder),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = StudioTextPrimary),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Case Study", fontSize = 11.sp)
                }

                Button(
                    onClick = onQuoteProject,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = StudioAmber.copy(alpha = 0.2f),
                        contentColor = StudioAmber
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Inquire", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
