package com.learnagentic.feature.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

private val pages = listOf(
    OnboardingPage(
        title = "Welcome to Agentic AI",
        description = "Discover the power of artificial intelligence through interactive, hands-on learning modules.",
        imageRes = R.drawable.ic_onboarding_placeholder
    ),
    OnboardingPage(
        title = "Learn by Doing",
        description = "Engage with real-world scenarios and watch your AI agents solve complex problems.",
        imageRes = R.drawable.ic_onboarding_placeholder
    ),
    OnboardingPage(
        title = "Master the Future",
        description = "Track your progress, build your skills, and master the technology shaping tomorrow.",
        imageRes = R.drawable.ic_onboarding_placeholder
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinished: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { position ->
            PagerScreen(onboardingPage = pages[position])
        }

        BottomSection(
            modifier = Modifier.padding(16.dp),
            size = pages.size,
            index = pagerState.currentPage,
            onSkipClicked = {
                viewModel.completeOnboarding()
                onFinished()
            },
            onNextClicked = {
                if (pagerState.currentPage < pages.size - 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    viewModel.completeOnboarding()
                    onFinished()
                }
            }
        )
    }
}

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = onboardingPage.imageRes),
            contentDescription = "Onboarding Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = onboardingPage.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = onboardingPage.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    size: Int,
    index: Int,
    onSkipClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Skip Button
        TextButton(
            onClick = onSkipClicked,
            modifier = Modifier.alpha(if (index == size - 1) 0f else 1f),
            enabled = index != size - 1
        ) {
            Text(
                text = "Skip",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Indicators
        Indicators(size = size, index = index)

        // Next / Get Started Button
        Button(
            onClick = onNextClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = if (index == size - 1) "Get Started" else "Next",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = if (isSelected) 24.dp else 10.dp
    val color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width)
            .clip(CircleShape)
            .background(color)
    )
}

