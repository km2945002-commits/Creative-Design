package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.QuoteRepository
import com.example.data.StudioData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Creative Design Studio", appName)
    }

    @Test
    fun `verify studio data integrity`() {
        assertEquals(6, StudioData.services.size)
        assertEquals(6, StudioData.projects.size)
        assertEquals(7, StudioData.techStack.size)
        assertEquals(4, StudioData.websiteSolutions.size)
        assertTrue(StudioData.STUDIO_PHONE.contains("319 9774681"))
    }

    @Test
    fun `test quote ticket generation and persistence`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = QuoteRepository(context)
        val ticket = repo.submitQuote(
            clientName = "Mortaza Khan",
            email = "km2945002@gmail.com",
            whatsapp = "+92 319 9774681",
            requiredService = "Websites & Apps Design",
            budget = "PKR 50,000 - 100,000",
            description = "Test website brief"
        )
        assertNotNull(ticket)
        assertTrue(ticket.ticketId.startsWith("CDS-"))
        assertEquals("Mortaza Khan", ticket.clientName)
        assertEquals("Under Review", ticket.status)
        assertTrue(repo.quotes.value.isNotEmpty())
    }
}
