# Constitution — LearnAgenticAI Android Project
## Core Principles & Non-Negotiable Rules

> *"What gets specified gets built correctly. What gets reviewed gets built safely."*

---

## Article I — Spec-Driven Development (SDD)

1. **No code before a spec.** Every feature MUST have an approved spec in `.specify/specs/` before any agent writes implementation code. A spec is the contract; code is its execution.
2. **Specs are the source of truth.** If code and spec disagree, the spec wins. The Tech Lead arbitrates all conflicts.
3. **Specs evolve via PR, not chat.** Spec changes require a diff review by at least the Tech Lead Agent and the PM Agent before merging.

---

## Article II — Single Responsibility per Agent

4. **One agent, one domain.** No agent shall write code, configs, or tests outside their defined domain (see `roles.md`). The Frontend Agent does not touch CI/CD. The DevOps Agent does not write Composables.
5. **The Tech Lead is the orchestrator.** All cross-domain decisions route through the Tech Lead Agent. It is the only agent permitted to merge cross-domain changes.
6. **Agents do not self-approve.** No agent may approve its own pull request. Cross-agent review is mandatory for all merges.

---

## Article III — Security by Default

7. **Zero hardcoded secrets.** API keys, credentials, signing configs, and tokens are NEVER stored in source code. They MUST use environment variables or Android Keystore.
8. **Security Agent reviews all PRs.** The Security Agent is the final gate before any PR is merged to `main`. No bypass is permitted.
9. **All network traffic is encrypted.** TLS 1.2 minimum is enforced for all API calls. Certificate pinning is required for production endpoints.
10. **Principle of Least Privilege.** The app requests only the Android permissions strictly required for its current features. All permissions MUST be justified in `how-architecture.md`.

---

## Article IV — Quality Gates

11. **Code coverage minimum: 80%.** The SDET Agent enforces a minimum of 80% unit test coverage. PRs that drop coverage below this threshold are automatically rejected by CI.
12. **No merge with failing tests.** The CI pipeline must be green (all tests pass) before any PR can be merged.
13. **Compose UI tests are mandatory for all screens.** Every new screen (Composable) must have a corresponding Compose UI test in the `androidTest` source set.
14. **Static analysis is non-negotiable.** Detekt and Ktlint must pass with zero errors on every commit (enforced via pre-commit hook).

---

## Article V — Workflow & Collaboration

15. **Git worktrees for parallel work.** Each agent operates in its own git worktree to eliminate merge conflicts during parallel development cycles.
16. **Commit messages follow Conventional Commits.** Format: `type(scope): description`. Types: `feat`, `fix`, `test`, `docs`, `chore`, `ci`, `security`. Example: `feat(compose): add AgentCard composable`.
17. **Backlog is sacred.** The PM Agent owns the backlog. Scope creep is a violation. Any new feature not in the current sprint backlog requires a formal requirement change request.
18. **Documentation is a deliverable.** KDoc comments for all public functions, classes, and interfaces. README must be current. Architecture diagrams must match the code.

---

## Article VI — Android-Specific Standards

19. **MVVM is the mandated architecture pattern.** ViewModels expose `StateFlow<UiState>`. Composables observe state via `collectAsStateWithLifecycle()`. No business logic lives in Composables.
20. **Unidirectional Data Flow (UDF).** Data flows down (ViewModel → Composable). Events flow up (Composable → ViewModel). This is mandatory for all screens.
21. **Dark theme is a first-class citizen.** All Composables must support both Light and Dark themes using Material 3 dynamic color with full UI tests for both.
22. **Accessibility is mandatory.** All interactive elements must have `contentDescription`. Font scaling must be tested at 1.0x and 1.5x. TalkBack must be validated before release.

---

## Ratification
This constitution was established on **2026-06-30** and applies to all agents, all code, and all deliverables in the LearnAgenticAI project. Amendments require unanimous approval from all 7 agent roles.
