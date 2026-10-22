package com.example.data

data class QuoteTicket(
    val ticketId: String,
    val clientName: String,
    val email: String,
    val whatsapp: String,
    val requiredService: String,
    val budget: String,
    val description: String,
    val createdAtFormatted: String,
    val status: String = "Under Review"
)
