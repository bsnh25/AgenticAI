---
name: jetpack-compose-ui
description: >
  Standards and patterns for writing production-quality Jetpack Compose UI code.
  Covers: Composable function conventions, UiState/ViewModel wiring, theming,
  preview annotations, accessibility, and Compose UI testing setup.
agents:
  - agent/frontend
  - agent/sdet
---

# Skill: Jetpack Compose UI Standards

## Composable Function Conventions

```kotlin
// ✅ CORRECT: Screen-level Composable wired to ViewModel
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onModuleClick: (String) -> Unit,
    onNavigateToExplore: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        onModuleClick = onModuleClick,
        onNavigateToExplore = onNavigateToExplore
    )
}

// ✅ CORRECT: Stateless content Composable (testable, previewable)
@Composable
internal fun HomeScreenContent(
    uiState: HomeUiState,
    onModuleClick: (String) -> Unit,
    onNavigateToExplore: () -> Unit
) {
    when (uiState) {
        is HomeUiState.StateInitial -> { /* Initial setup */ }
        is HomeUiState.Loading -> LoadingIndicator()
        is HomeUiState.Success -> { /* Render content */ }
        is HomeUiState.Failed -> ErrorState(message = uiState.message)
    }
}
```

## Preview Annotations

Always provide both Light and Dark previews:

```kotlin
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    AvatarTheme { // Use custom Avatar Design System
        HomeScreenContent(
            uiState = HomeUiState.Success(/* stub data */),
            onModuleClick = {},
            onNavigateToExplore = {}
        )
    }
}
```

## Custom Design System (Avatar)
- Always use `AvatarTheme` for the root composable.
- Prefix custom components with `Ava` (e.g. `AvaButton`, `AvaCard`).
- Use `Interphases` font family for all Text composables.

## Compose UI Testing

```kotlin
// Use ComposeTestRule for all screen-level tests
@get:Rule
val composeTestRule = createComposeRule()

@Test
fun homeScreen_displaysModules_whenStateIsSuccess() {
    composeTestRule.setContent {
        AvatarTheme {
            HomeScreenContent(
                uiState = HomeUiState.Success(featuredModules = fakeModules),
                onModuleClick = {},
                onNavigateToExplore = {}
            )
        }
    }
    composeTestRule.onNodeWithText("What is an AI Agent?").assertIsDisplayed()
}
```

## Accessibility Checklist
- [ ] All `Icon` composables have `contentDescription`.
- [ ] Tappable areas are at least 48dp x 48dp.
- [ ] Color contrast ratio meets WCAG AA (4.5:1 for text).
- [ ] Custom components implement `Modifier.semantics {}` appropriately.
