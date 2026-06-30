---
name: android-test-automation
description: >
  Standards for writing ViewModel unit tests (JUnit5 + MockK + Turbine),
  Compose UI tests, and Repository integration tests. Includes setup
  for Hilt test injection and coroutine test dispatchers.
agents:
  - agent/sdet
---

# Skill: Android Test Automation

## ViewModel Unit Test Template

```kotlin
@ExtendWith(MockKExtension::class, CoroutinesTestExtension::class)
class HomeViewModelTest {

    @MockK lateinit var getModulesUseCase: GetModulesUseCase
    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setup() {
        viewModel = HomeViewModel(getModulesUseCase)
    }

    @Test
    fun `uiState emits Success when use case returns modules`() = runTest {
        val fakeModules = listOf(FakeData.module1, FakeData.module2)
        coEvery { getModulesUseCase() } returns flowOf(Result.success(fakeModules))

        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(HomeUiState.Loading::class.java)
            val success = awaitItem() as HomeUiState.Success
            assertThat(success.featuredModules).hasSize(2)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
```

## Repository Integration Test Template

```kotlin
@HiltAndroidTest
class ModuleRepositoryTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var repository: ModuleRepository
    @Inject lateinit var moduleDao: ModuleDao

    @Test
    fun getModules_returnsCachedDataFirst_thenRemote() = runTest {
        // Insert local cache
        moduleDao.insertAll(FakeEntities.modules)

        repository.getModules().test {
            val cached = awaitItem()
            assertThat(cached).isNotEmpty()
        }
    }
}
```

## Coverage Configuration

In `build.gradle.kts`:
```kotlin
tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}
```

## Spring Boot WebFlux Testing (Backend)
- **Controller Tests**: Use `@WebFluxTest` with `WebTestClient` for routing and endpoint testing.
- **Service Tests**: Use `StepVerifier` (from `reactor-test`) to properly test `Mono` and `Flux` business logic without blocking `.block()`.
- **Database Tests**: Use `@DataJpaTest` (or equivalent for Oracle) with an in-memory or Testcontainers setup.
