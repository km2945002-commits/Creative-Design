package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuoteRepository
import com.example.data.QuoteTicket
import com.example.data.StudioData
import com.example.ui.components.GradientButton
import com.example.ui.components.StudioPill
import com.example.ui.components.launchDialer
import com.example.ui.components.launchEmail
import com.example.ui.components.launchWhatsApp
import com.example.ui.theme.StudioAmber
import com.example.ui.theme.StudioBorder
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteContactScreen(
    quoteRepository: QuoteRepository,
    initialService: String? = null,
    initialBudget: String? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val tickets by quoteRepository.quotes.collectAsState()

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var whatsapp by remember { mutableStateOf("") }
    var selectedService by remember { mutableStateOf(initialService ?: StudioData.services.first().name) }
    var selectedBudget by remember { mutableStateOf(initialBudget ?: StudioData.budgetOptions[1]) }
    var description by remember { mutableStateOf("") }

    var expandedServiceMenu by remember { mutableStateOf(false) }
    var submittedTicket by remember { mutableStateOf<QuoteTicket?>(null) }
    var expandedFaqIndex by remember { mutableStateOf<Int?>(null) }

    val serviceNames = remember { StudioData.services.map { it.name } }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("quote_contact_screen"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                StudioPill(text = "COMMISSIONS & INQUIRIES", color = StudioAmber)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Request a Quote",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = StudioTextPrimary
                )
                Text(
                    text = "Complete the brief below to receive an itemized proposal with milestones and deliverables in PKR, or reach out instantly on WhatsApp.",
                    fontSize = 12.sp,
                    color = StudioTextSecondary
                )
            }
        }

        // Direct Communications Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = BorderStroke(1.dp, StudioBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "STUDIO COMMUNICATIONS",
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        color = StudioTextMuted,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // WhatsApp Instant CTA
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = StudioEmerald,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { launchWhatsApp(context) }
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Chat Instantly on WhatsApp", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Contact info rows
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { launchDialer(context) },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, StudioBorder),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = StudioEmerald, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Call Us", fontSize = 11.sp, color = StudioTextPrimary)
                        }

                        OutlinedButton(
                            onClick = { launchEmail(context) },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, StudioBorder),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = StudioPink, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Email", fontSize = 11.sp, color = StudioTextPrimary)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = StudioAmber, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = StudioData.STUDIO_LOCATION,
                            fontSize = 11.sp,
                            color = StudioTextSecondary,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }

        // Success Card if just submitted
        submittedTicket?.let { ticket ->
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1713)),
                    border = BorderStroke(1.dp, StudioEmerald.copy(alpha = 0.6f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(StudioEmerald.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = StudioEmerald, modifier = Modifier.size(24.dp))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Quote Request Registered", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                        Text(
                            text = "Thank you, ${ticket.clientName}. Our lead designer will review your specifications.",
                            fontSize = 11.sp,
                            color = StudioTextSecondary,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = StudioSurface,
                            border = BorderStroke(1.dp, StudioBorder),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("REFERENCE ID:", fontSize = 10.sp, color = StudioTextMuted, fontFamily = FontFamily.Monospace)
                                    Text(ticket.ticketId, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = StudioAmber, fontFamily = FontFamily.Monospace)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("SERVICE:", fontSize = 10.sp, color = StudioTextMuted)
                                    Text(ticket.requiredService, fontSize = 10.sp, color = StudioTextPrimary)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("BUDGET:", fontSize = 10.sp, color = StudioTextMuted)
                                    Text(ticket.budget, fontSize = 10.sp, color = StudioEmerald)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString("${ticket.ticketId} · ${ticket.requiredService}"))
                                    Toast.makeText(context, "Copied Reference ID", Toast.LENGTH_SHORT).show()
                                },
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, StudioBorder),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Copy ID", fontSize = 11.sp)
                            }

                            Button(
                                onClick = {
                                    val msg = "Hello Creative Design Studio! Following up on Ticket ${ticket.ticketId} for ${ticket.requiredService}. Budget: ${ticket.budget}."
                                    launchWhatsApp(context, msg)
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = StudioEmerald, contentColor = Color.Black),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Quote Request Form
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = BorderStroke(1.dp, StudioBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "NEW PROJECT BRIEF",
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        color = StudioTextMuted,
                        fontWeight = FontWeight.Bold
                    )

                    // Full Name
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Full Name *", fontSize = 11.sp) },
                        placeholder = { Text("e.g. Mortaza Khan", fontSize = 11.sp) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quote_name_input"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = StudioAmber,
                            unfocusedBorderColor = StudioBorder,
                            focusedTextColor = StudioTextPrimary,
                            unfocusedTextColor = StudioTextPrimary
                        )
                    )

                    // Email Address
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address *", fontSize = 11.sp) },
                        placeholder = { Text("e.g. yourname@gmail.com", fontSize = 11.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quote_email_input"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = StudioAmber,
                            unfocusedBorderColor = StudioBorder,
                            focusedTextColor = StudioTextPrimary,
                            unfocusedTextColor = StudioTextPrimary
                        )
                    )

                    // WhatsApp Number
                    OutlinedTextField(
                        value = whatsapp,
                        onValueChange = { whatsapp = it },
                        label = { Text("WhatsApp / Phone Number", fontSize = 11.sp) },
                        placeholder = { Text("+92 319 9774681", fontSize = 11.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quote_whatsapp_input"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = StudioAmber,
                            unfocusedBorderColor = StudioBorder,
                            focusedTextColor = StudioTextPrimary,
                            unfocusedTextColor = StudioTextPrimary
                        )
                    )

                    // Required Service Dropdown
                    ExposedDropdownMenuBox(
                        expanded = expandedServiceMenu,
                        onExpandedChange = { expandedServiceMenu = !expandedServiceMenu }
                    ) {
                        OutlinedTextField(
                            value = selectedService,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Required Service *", fontSize = 11.sp) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedServiceMenu) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = StudioAmber,
                                unfocusedBorderColor = StudioBorder,
                                focusedTextColor = StudioTextPrimary,
                                unfocusedTextColor = StudioTextPrimary
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expandedServiceMenu,
                            onDismissRequest = { expandedServiceMenu = false },
                            modifier = Modifier.background(StudioSurfaceVariant)
                        ) {
                            serviceNames.forEach { serviceName ->
                                DropdownMenuItem(
                                    text = { Text(serviceName, fontSize = 12.sp, color = StudioTextPrimary) },
                                    onClick = {
                                        selectedService = serviceName
                                        expandedServiceMenu = false
                                    }
                                )
                            }
                        }
                    }

                    // Budget Bracket Chips
                    Column {
                        Text(
                            text = "Estimated Project Budget (PKR):",
                            fontSize = 11.sp,
                            color = StudioTextSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            StudioData.budgetOptions.take(2).forEach { budget ->
                                val isSelected = selectedBudget == budget
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) StudioAmber else StudioSurfaceVariant,
                                    border = BorderStroke(1.dp, if (isSelected) StudioAmber else StudioBorder),
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { selectedBudget = budget }
                                ) {
                                    Text(
                                        text = budget,
                                        fontSize = 10.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.Black else StudioTextSecondary,
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            StudioData.budgetOptions.drop(2).forEach { budget ->
                                val isSelected = selectedBudget == budget
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) StudioAmber else StudioSurfaceVariant,
                                    border = BorderStroke(1.dp, if (isSelected) StudioAmber else StudioBorder),
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { selectedBudget = budget }
                                ) {
                                    Text(
                                        text = budget,
                                        fontSize = 10.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.Black else StudioTextSecondary,
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                    // Project Description
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Project Description & Requirements *", fontSize = 11.sp) },
                        placeholder = { Text("Describe your business, design requirements, color preferences, target audience, or timeline...", fontSize = 11.sp) },
                        minLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quote_description_input"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = StudioAmber,
                            unfocusedBorderColor = StudioBorder,
                            focusedTextColor = StudioTextPrimary,
                            unfocusedTextColor = StudioTextPrimary
                        )
                    )

                    // Submit Button
                    GradientButton(
                        text = "Submit Quote Request",
                        icon = Icons.AutoMirrored.Filled.ArrowForward,
                        onClick = {
                            if (fullName.isBlank()) {
                                Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
                                return@GradientButton
                            }
                            if (email.isBlank() || !email.contains("@")) {
                                Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                                return@GradientButton
                            }
                            if (description.isBlank()) {
                                Toast.makeText(context, "Please provide brief project details", Toast.LENGTH_SHORT).show()
                                return@GradientButton
                            }

                            val newTicket = quoteRepository.submitQuote(
                                clientName = fullName.trim(),
                                email = email.trim(),
                                whatsapp = whatsapp.trim(),
                                requiredService = selectedService,
                                budget = selectedBudget,
                                description = description.trim()
                            )
                            submittedTicket = newTicket
                            Toast.makeText(context, "Inquiry registered: ${newTicket.ticketId}", Toast.LENGTH_LONG).show()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "submit_quote_button"
                    )
                }
            }
        }

        // Submitted Tickets History
        if (tickets.isNotEmpty()) {
            item {
                Text(
                    text = "MY SUBMITTED PROJECT INQUIRIES (${tickets.size})",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = StudioTextMuted
                )
            }

            items(tickets) { ticket ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = StudioSurface),
                    border = BorderStroke(1.dp, StudioBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = ticket.ticketId,
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                color = StudioAmber
                            )
                            StudioPill(text = ticket.status, color = StudioEmerald)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = ticket.requiredService, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                        Text(text = "Budget: ${ticket.budget} · ${ticket.createdAtFormatted}", fontSize = 10.sp, color = StudioTextSecondary)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = ticket.description, fontSize = 11.sp, color = StudioTextMuted, maxLines = 2)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(
                                onClick = {
                                    val msg = "Hello Creative Design Studio! Inquiring on my ticket ${ticket.ticketId} (${ticket.requiredService})."
                                    launchWhatsApp(context, msg)
                                },
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, StudioBorder),
                                modifier = Modifier.height(34.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = StudioEmerald, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Chat WhatsApp", fontSize = 10.sp, color = StudioEmerald)
                            }

                            OutlinedButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString("${ticket.ticketId}: ${ticket.requiredService} - ${ticket.budget}"))
                                    Toast.makeText(context, "Ticket details copied", Toast.LENGTH_SHORT).show()
                                },
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, StudioBorder),
                                modifier = Modifier.height(34.dp)
                            ) {
                                Text("Copy", fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }

        // FAQs Accordions
        item {
            Column {
                Text(
                    text = "FREQUENTLY ASKED QUESTIONS",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = StudioTextMuted
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }

        items(StudioData.faqs.indices.toList()) { index ->
            val faq = StudioData.faqs[index]
            val isExpanded = expandedFaqIndex == index

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = BorderStroke(1.dp, if (isExpanded) StudioAmber.copy(alpha = 0.5f) else StudioBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedFaqIndex = if (isExpanded) null else index }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = faq.question,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = StudioTextPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = StudioTextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            Text(
                                text = faq.answer,
                                fontSize = 12.sp,
                                color = StudioTextSecondary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
