package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.ui.components.GradientButton
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
import java.text.NumberFormat
import java.util.Locale

data class EstimatorOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val pricePkr: Int,
    val category: String
)

@Composable
fun EstimatorScreen(
    onRequestQuoteWithEstimate: (serviceSummary: String, budgetEstimate: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val serviceOptions = remember {
        listOf(
            EstimatorOption("web", "Websites & Web Apps", "WordPress CMS, responsive, Hostinger deploy", 45000, "Core"),
            EstimatorOption("logo", "Logo & Vector Design", "CorelDRAW mathematical vector curves", 45000, "Core"),
            EstimatorOption("cards", "Business Cards & Stationery", "Double-sided, 3mm press bleeds, 300 DPI", 35000, "Print"),
            EstimatorOption("pack", "Packaging & Product Dielines", "Custom dielines, 3D mockups & barcodes", 85000, "Packaging"),
            EstimatorOption("flex", "Grand Flex & Banner Design", "Weatherproof outdoor solvent print specs", 30000, "Print"),
            EstimatorOption("brand", "Full Brand Identity Manual", "Logos, palettes, typography & guidelines", 75000, "Branding")
        )
    }

    val addOnOptions = remember {
        listOf(
            EstimatorOption("domain_ssl", "Domain & 1-Year SSL Cloud Setup", "Hostinger server configuration & business emails", 12000, "Add-on"),
            EstimatorOption("mockup_pack", "3D Photorealistic Render Suite", "Ultra-high definition digital staging & social assets", 15000, "Add-on"),
            EstimatorOption("express", "Express Rush Delivery (48h)", "Priority studio execution queue", 20000, "Speed")
        )
    }

    val selectedServices = remember {
        mutableStateMapOf<String, Boolean>().apply {
            put("web", true)
        }
    }

    val selectedAddOns = remember {
        mutableStateMapOf<String, Boolean>()
    }

    val totalPkr = remember(selectedServices.toMap(), selectedAddOns.toMap()) {
        var sum = 0
        serviceOptions.forEach { opt ->
            if (selectedServices[opt.id] == true) sum += opt.pricePkr
        }
        addOnOptions.forEach { opt ->
            if (selectedAddOns[opt.id] == true) sum += opt.pricePkr
        }
        sum
    }

    val totalUsd = (totalPkr / 280)
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("estimator_screen"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                StudioPill(text = "INTERACTIVE BUDGET CALCULATOR", color = StudioEmerald)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Estimate Project Investment",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = StudioTextPrimary
                )
                Text(
                    text = "Select the deliverables and options required for your project to calculate an instant itemized estimate in PKR.",
                    fontSize = 12.sp,
                    color = StudioTextSecondary
                )
            }
        }

        // Selected Total Sticky Card
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF131626)),
                border = BorderStroke(1.dp, StudioAmber.copy(alpha = 0.6f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ESTIMATED TOTAL INVESTMENT",
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = StudioTextMuted
                        )
                        StudioPill(text = "Transparent Pricing", color = StudioAmber)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "PKR ${numberFormat.format(totalPkr)}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            color = StudioAmber
                        )
                        Text(
                            text = "(~$${numberFormat.format(totalUsd)} USD)",
                            fontSize = 14.sp,
                            color = StudioTextSecondary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    GradientButton(
                        text = "Transfer to Quote Request",
                        icon = Icons.AutoMirrored.Filled.ArrowForward,
                        onClick = {
                            val chosenNames = serviceOptions.filter { selectedServices[it.id] == true }.map { it.title } +
                                    addOnOptions.filter { selectedAddOns[it.id] == true }.map { it.title }
                            val summary = if (chosenNames.isNotEmpty()) chosenNames.joinToString(", ") else "Custom Design Package"
                            val budgetBracket = when {
                                totalPkr <= 50000 -> "PKR 25,000 - 50,000"
                                totalPkr <= 100000 -> "PKR 50,000 - 100,000"
                                totalPkr <= 250000 -> "PKR 100,000 - 250,000"
                                else -> "PKR 250,000+"
                            }
                            onRequestQuoteWithEstimate(summary, budgetBracket)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            Text(
                text = "1. SELECT CORE DELIVERABLES",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = StudioTextMuted
            )
        }

        // Service check items
        items(serviceOptions.size) { index ->
            val opt = serviceOptions[index]
            val isChecked = selectedServices[opt.id] == true

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = if (isChecked) StudioSurfaceVariant else StudioSurface),
                border = BorderStroke(1.dp, if (isChecked) StudioAmber.copy(alpha = 0.5f) else StudioBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedServices[opt.id] = !isChecked }
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { selectedServices[opt.id] = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = StudioAmber,
                            checkmarkColor = Color.Black,
                            uncheckedColor = StudioBorder
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = opt.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = StudioTextPrimary
                        )
                        Text(
                            text = opt.subtitle,
                            fontSize = 11.sp,
                            color = StudioTextSecondary
                        )
                    }
                    Text(
                        text = "PKR ${numberFormat.format(opt.pricePkr)}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = if (isChecked) StudioAmber else StudioTextMuted
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "2. ADD-ONS & EXPEDITION",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = StudioTextMuted
            )
        }

        // Addon check items
        items(addOnOptions.size) { index ->
            val opt = addOnOptions[index]
            val isChecked = selectedAddOns[opt.id] == true

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = if (isChecked) StudioSurfaceVariant else StudioSurface),
                border = BorderStroke(1.dp, if (isChecked) StudioPink.copy(alpha = 0.5f) else StudioBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedAddOns[opt.id] = !isChecked }
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { selectedAddOns[opt.id] = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = StudioPink,
                            checkmarkColor = Color.White,
                            uncheckedColor = StudioBorder
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = opt.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = StudioTextPrimary
                        )
                        Text(
                            text = opt.subtitle,
                            fontSize = 11.sp,
                            color = StudioTextSecondary
                        )
                    }
                    Text(
                        text = "+PKR ${numberFormat.format(opt.pricePkr)}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = if (isChecked) StudioPink else StudioTextMuted
                    )
                }
            }
        }
    }
}
