package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.data.StudioData
import com.example.data.TechStackItem
import com.example.data.WebsiteSolution
import com.example.ui.components.ServiceCard
import com.example.ui.components.StudioPill
import com.example.ui.theme.StudioAmber
import com.example.ui.theme.StudioBorder
import com.example.ui.theme.StudioCyan
import com.example.ui.theme.StudioEmerald
import com.example.ui.theme.StudioPink
import com.example.ui.theme.StudioPurple
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant
import com.example.ui.theme.StudioTextMuted
import com.example.ui.theme.StudioTextPrimary
import com.example.ui.theme.StudioTextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ServicesScreen(
    onSelectServiceForQuote: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategoryTab by remember { mutableStateOf("services") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("services_screen")
    ) {
        // Top Section Selector Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .background(StudioSurfaceVariant, RoundedCornerShape(12.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf(
                "services" to "Services (6)",
                "solutions" to "Web Architecture",
                "stack" to "Tech & Workflow"
            ).forEach { (key, label) ->
                val isSelected = selectedCategoryTab == key
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) StudioAmber else Color.Transparent)
                        .clickable { selectedCategoryTab = key }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.Black else StudioTextSecondary
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (selectedCategoryTab) {
                "services" -> {
                    item {
                        Column {
                            Text(
                                text = "OUR CORE OFFERINGS",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                color = StudioTextMuted
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Precision Design & Digital Execution",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = StudioTextPrimary
                            )
                            Text(
                                text = "From responsive WordPress websites to CorelDRAW vector masters and grand format banners.",
                                fontSize = 12.sp,
                                color = StudioTextSecondary
                            )
                        }
                    }

                    items(StudioData.services) { service ->
                        ServiceCard(
                            service = service,
                            onSelectForQuote = { onSelectServiceForQuote(service.name) }
                        )
                    }
                }

                "solutions" -> {
                    item {
                        Column {
                            Text(
                                text = "INDUSTRY WEBSITE ARCHITECTURE",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                color = StudioCyan
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Tailored Web Solutions",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = StudioTextPrimary
                            )
                            Text(
                                text = "Recommended tech stacks, modules, and workflows customized for your business category.",
                                fontSize = 12.sp,
                                color = StudioTextSecondary
                            )
                        }
                    }

                    items(StudioData.websiteSolutions) { solution ->
                        WebsiteSolutionCard(
                            solution = solution,
                            onQuoteSolution = { onSelectServiceForQuote("Websites & Apps Design: ${solution.title}") }
                        )
                    }
                }

                "stack" -> {
                    item {
                        Column {
                            Text(
                                text = "WORKFLOW & TECH STACK",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                color = StudioPink
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Powered by AI & Modern Technology",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = StudioTextPrimary
                            )
                            Text(
                                text = "We clearly distinguish our professional software tools from tangible finished deliverables.",
                                fontSize = 12.sp,
                                color = StudioTextSecondary
                            )
                        }
                    }

                    items(StudioData.techStack) { item ->
                        TechStackCard(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun WebsiteSolutionCard(
    solution: WebsiteSolution,
    onQuoteSolution: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = StudioSurface),
        border = BorderStroke(1.dp, StudioBorder),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StudioPill(text = solution.subtitle, color = StudioCyan)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = StudioAmber.copy(alpha = 0.15f),
                    modifier = Modifier.clickable { onQuoteSolution() }
                ) {
                    Text(
                        text = "Build This",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = StudioAmber,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = solution.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = StudioTextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Ideal For: ${solution.idealFor}",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                color = StudioTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = solution.description,
                fontSize = 12.sp,
                color = StudioTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Key Inclusions:",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = StudioTextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            solution.keyFeatures.forEach { feature ->
                Row(
                    modifier = Modifier.padding(vertical = 2.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = StudioCyan,
                        modifier = Modifier.size(13.dp).padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = feature,
                        fontSize = 11.sp,
                        color = StudioTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = StudioSurfaceVariant,
                border = BorderStroke(1.dp, StudioBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Stack: ",
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        color = StudioTextMuted
                    )
                    Text(
                        text = solution.recommendedTech,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        color = StudioAmber
                    )
                }
            }
        }
    }
}

@Composable
fun TechStackCard(
    item: TechStackItem,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = StudioSurface),
        border = BorderStroke(1.dp, StudioBorder),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = StudioTextPrimary
                )
                StudioPill(text = item.badge, color = StudioPink)
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = item.category,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                color = StudioTextMuted
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.description,
                fontSize = 12.sp,
                color = StudioTextSecondary,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            item.workflowUse.forEach { step ->
                Row(
                    modifier = Modifier.padding(vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(RoundedCornerShape(1.dp))
                            .background(StudioAmber)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = step,
                        fontSize = 11.sp,
                        color = StudioTextSecondary
                    )
                }
            }
        }
    }
}
