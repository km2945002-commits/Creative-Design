package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuoteRepository
import com.example.ui.components.StudioHeader
import com.example.ui.components.launchWhatsApp
import com.example.ui.screens.EstimatorScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PortfolioScreen
import com.example.ui.screens.QuoteContactScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.theme.CreativeDesignTheme
import com.example.ui.theme.StudioAmber
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioBorder
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioTextMuted
import com.example.ui.theme.StudioTextPrimary

enum class StudioScreen(val label: String, val icon: ImageVector, val tag: String) {
    HOME("Showcase", Icons.Default.Home, "tab_home"),
    SERVICES("Services", Icons.Default.DesignServices, "tab_services"),
    PORTFOLIO("Work", Icons.Default.PhotoLibrary, "tab_portfolio"),
    ESTIMATOR("Estimate", Icons.Default.Calculate, "tab_estimator"),
    QUOTE("Quote", Icons.Default.MailOutline, "tab_quote")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreativeDesignTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val context = LocalContext.current
    val quoteRepository = remember { QuoteRepository(context) }

    var currentScreen by remember { mutableStateOf(StudioScreen.HOME) }
    var selectedServiceForQuote by remember { mutableStateOf<String?>(null) }
    var selectedBudgetForQuote by remember { mutableStateOf<String?>(null) }

    // Handle system back navigation
    BackHandler(enabled = currentScreen != StudioScreen.HOME) {
        currentScreen = StudioScreen.HOME
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("studio_scaffold"),
        containerColor = StudioBackground,
        topBar = {
            StudioHeader(
                onWhatsAppClick = { launchWhatsApp(context) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = StudioSurface,
                tonalElevation = 8.dp,
                modifier = Modifier.testTag("studio_bottom_nav")
            ) {
                StudioScreen.entries.forEach { screen ->
                    val isSelected = currentScreen == screen
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.label,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.label,
                                fontSize = 10.sp,
                                maxLines = 1
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = StudioAmber,
                            indicatorColor = StudioAmber,
                            unselectedIconColor = StudioTextMuted,
                            unselectedTextColor = StudioTextMuted
                        ),
                        modifier = Modifier.testTag(screen.tag)
                    )
                }
            }
        }
    ) { innerPadding ->
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "screen_transition",
            modifier = Modifier.padding(innerPadding)
        ) { screen ->
            when (screen) {
                StudioScreen.HOME -> HomeScreen(
                    onNavigateToServices = { currentScreen = StudioScreen.SERVICES },
                    onNavigateToQuote = { serviceName ->
                        selectedServiceForQuote = serviceName
                        currentScreen = StudioScreen.QUOTE
                    },
                    onNavigateToPortfolio = { currentScreen = StudioScreen.PORTFOLIO }
                )

                StudioScreen.SERVICES -> ServicesScreen(
                    onSelectServiceForQuote = { serviceName ->
                        selectedServiceForQuote = serviceName
                        currentScreen = StudioScreen.QUOTE
                    }
                )

                StudioScreen.PORTFOLIO -> PortfolioScreen(
                    onSelectProjectForQuote = { projectName ->
                        selectedServiceForQuote = projectName
                        currentScreen = StudioScreen.QUOTE
                    }
                )

                StudioScreen.ESTIMATOR -> EstimatorScreen(
                    onRequestQuoteWithEstimate = { summary, budget ->
                        selectedServiceForQuote = summary
                        selectedBudgetForQuote = budget
                        currentScreen = StudioScreen.QUOTE
                    }
                )

                StudioScreen.QUOTE -> QuoteContactScreen(
                    quoteRepository = quoteRepository,
                    initialService = selectedServiceForQuote,
                    initialBudget = selectedBudgetForQuote
                )
            }
        }
    }
}
