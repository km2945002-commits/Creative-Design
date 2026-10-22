package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val StudioBackground = Color(0xFF08090E)
val StudioSurface = Color(0xFF11131C)
val StudioSurfaceVariant = Color(0xFF181B28)
val StudioCardElevated = Color(0xFF1E2132)
val StudioBorder = Color(0xFF262A3E)
val StudioBorderSubtle = Color(0xFF1A1D2B)

val StudioAmber = Color(0xFFFBBF24)
val StudioPink = Color(0xFFF43F5E)
val StudioPurple = Color(0xFFA855F7)
val StudioCyan = Color(0xFF06B6D4)
val StudioEmerald = Color(0xFF10B981)
val StudioOrange = Color(0xFFF97316)
val StudioBlue = Color(0xFF3B82F6)

val StudioTextPrimary = Color(0xFFF8FAFC)
val StudioTextSecondary = Color(0xFF94A3B8)
val StudioTextMuted = Color(0xFF64748B)

val BrandGradient = Brush.horizontalGradient(
    colors = listOf(StudioAmber, StudioPink, StudioPurple)
)

val HeroGlowGradient = Brush.radialGradient(
    colors = listOf(StudioPurple.copy(alpha = 0.25f), Color.Transparent)
)
