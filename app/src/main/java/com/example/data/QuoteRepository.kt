package com.example.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class QuoteRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("cds_quote_store", Context.MODE_PRIVATE)
    private val _quotes = MutableStateFlow<List<QuoteTicket>>(emptyList())
    val quotes: StateFlow<List<QuoteTicket>> = _quotes.asStateFlow()

    init {
        loadQuotes()
    }

    private fun loadQuotes() {
        val jsonStr = prefs.getString("quote_list", "[]") ?: "[]"
        try {
            val arr = JSONArray(jsonStr)
            val list = mutableListOf<QuoteTicket>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                list.add(
                    QuoteTicket(
                        ticketId = obj.optString("ticketId", "CDS-000000"),
                        clientName = obj.optString("clientName", "Client"),
                        email = obj.optString("email", ""),
                        whatsapp = obj.optString("whatsapp", ""),
                        requiredService = obj.optString("requiredService", "Websites & Apps Design"),
                        budget = obj.optString("budget", "PKR 50,000 - 100,000"),
                        description = obj.optString("description", ""),
                        createdAtFormatted = obj.optString("createdAtFormatted", "Just now"),
                        status = obj.optString("status", "Under Review")
                    )
                )
            }
            _quotes.value = list.reversed()
        } catch (_: Exception) {
            _quotes.value = emptyList()
        }
    }

    fun submitQuote(
        clientName: String,
        email: String,
        whatsapp: String,
        requiredService: String,
        budget: String,
        description: String
    ): QuoteTicket {
        val randomNum = Random.nextInt(100000, 999999)
        val ticketId = "CDS-$randomNum"
        val dateFormat = SimpleDateFormat("MMM dd, yyyy · hh:mm a", Locale.getDefault())
        val formattedDate = dateFormat.format(Date())

        val newTicket = QuoteTicket(
            ticketId = ticketId,
            clientName = clientName,
            email = email,
            whatsapp = whatsapp,
            requiredService = requiredService,
            budget = budget,
            description = description,
            createdAtFormatted = formattedDate,
            status = "Under Review"
        )

        val jsonStr = prefs.getString("quote_list", "[]") ?: "[]"
        try {
            val arr = JSONArray(jsonStr)
            val obj = JSONObject().apply {
                put("ticketId", newTicket.ticketId)
                put("clientName", newTicket.clientName)
                put("email", newTicket.email)
                put("whatsapp", newTicket.whatsapp)
                put("requiredService", newTicket.requiredService)
                put("budget", newTicket.budget)
                put("description", newTicket.description)
                put("createdAtFormatted", newTicket.createdAtFormatted)
                put("status", newTicket.status)
            }
            arr.put(obj)
            prefs.edit().putString("quote_list", arr.toString()).apply()
            loadQuotes()
        } catch (_: Exception) {
            // fallback
        }

        return newTicket
    }
}
