# What Vision — LearnAgenticAI Android App
## Product Vision & User Stories (MVP)

**Document Owner**: PM Agent (`agent/pm`)
**Last Updated**: 2026-06-30
**Status**: APPROVED — Sprint 1 Ready

---

## Product Vision Statement

> "LearnAgenticAI is an interactive Android learning companion that makes the complex world of Agentic AI accessible, engaging, and actionable. In 15 minutes a day, users will understand how autonomous AI agents think, plan, and collaborate — through bite-sized lessons, interactive simulations, and real-world examples."

---

## Target Users

| Persona | Description | Goal |
|---------|-------------|------|
| **The Curious Developer** | Software engineer, 2-8 years exp., has heard of AI agents but doesn't know how they work | Understand agentic concepts to apply them in their own projects |
| **The AI Enthusiast** | Non-technical user interested in AI trends | Learn concepts at a high level without deep code exposure |
| **The Student** | CS undergraduate or bootcamp student | Build a foundational understanding for coursework or career |

---

## MVP Success Criteria

- [ ] User can complete at least 5 structured learning modules on Agentic AI.
- [ ] Each module includes: Overview, Core Concepts, Interactive Quiz, and Summary card.
- [ ] App works fully offline (all MVP content is local/cached).
- [ ] App achieves a minimum of 4.0-star UX rating in internal usability testing.
- [ ] App passes all accessibility checks (TalkBack, font scaling).
- [ ] App is ready for internal APK distribution (alpha track).

---

## Epic 1 — Onboarding & Home Dashboard

### US-001: Onboarding Flow
**As a** first-time user,
**I want to** see a welcoming onboarding flow that explains what Agentic AI is in 3 swipeable screens,
**So that** I immediately understand the app's value and feel motivated to start learning.

**Acceptance Criteria:**
- [ ] Onboarding has exactly 3 screens with illustrations and taglines.
- [ ] A "Skip" button is available on screens 1 and 2.
- [ ] Screen 3 has a "Get Started" CTA button.
- [ ] Onboarding is only shown on first launch (persisted via SharedPreferences/DataStore).
- [ ] Both light and dark themes are supported.

### US-002: Home Dashboard
**As a** returning user,
**I want to** see my learning progress and available modules on the home screen,
**So that** I can quickly pick up where I left off.

**Acceptance Criteria:**
- [ ] Home screen shows a greeting with the user's progress percentage.
- [ ] A horizontal scrolling row shows featured modules with cover art, title, and estimated reading time.
- [ ] A "Continue Learning" card shows the last accessed module.
- [ ] A bottom navigation bar with icons for: Home, Explore, Glossary, Profile.

---

## Epic 2 — Learning Modules

### US-003: Module List (Explore Screen)
**As a** user,
**I want to** browse all available learning modules organized by topic,
**So that** I can choose what to learn next based on my interests.

**Acceptance Criteria:**
- [ ] Modules are grouped under categories: "Foundations", "Architecture", "Tools & Frameworks", "Real-World Applications".
- [ ] Each module card shows: title, category tag, difficulty badge (Beginner/Intermediate/Advanced), duration, and completion state.
- [ ] A search bar allows filtering modules by keyword.

### US-004: Module Detail & Reader Screen
**As a** user,
**I want to** read a module with rich content including text, icons, and diagrams,
**So that** I can deeply understand each Agentic AI concept.

**Acceptance Criteria:**
- [ ] Module detail screen has sections: Introduction, Core Concepts (with bullet points), Key Diagram (image), Real-World Example, and Quiz CTA.
- [ ] Reading progress is tracked (scroll position saved).
- [ ] A floating "Back to Top" FAB appears after scrolling 300dp.
- [ ] Font size can be adjusted (small, medium, large) from the module toolbar.

### US-005: Interactive Quiz
**As a** user,
**I want to** take a short quiz at the end of each module,
**So that** I can test my understanding and reinforce my learning.

**Acceptance Criteria:**
- [ ] Quiz has 3-5 multiple-choice questions per module.
- [ ] Immediate feedback is given after each answer (correct/incorrect with explanation).
- [ ] A score summary screen is shown at the end with a "Retry" and "Next Module" button.
- [ ] A streak counter tracks consecutive daily quiz completions.

---

## Epic 3 — Glossary

### US-006: AI Glossary
**As a** user,
**I want to** look up key Agentic AI terms in a searchable glossary,
**So that** I can understand technical vocabulary without leaving the app.

**Acceptance Criteria:**
- [ ] Glossary is searchable and alphabetically indexed.
- [ ] Each term has: name, short definition (1-2 sentences), long description, and related terms.
- [ ] Terms referenced in modules are tappable hyperlinks that navigate to the glossary entry.
- [ ] Glossary works fully offline.

---

## Epic 4 — User Profile & Progress

### US-007: User Profile Screen
**As a** user,
**I want to** see my learning statistics and manage my preferences,
**So that** I feel a sense of achievement and can personalize my experience.

**Acceptance Criteria:**
- [ ] Profile shows: avatar (locally selectable), display name, learning streak, total modules completed, total time spent.
- [ ] Settings section includes: Dark/Light/System theme toggle, notification preferences, font size default.
- [ ] Achievement badges are displayed for milestones (e.g., "First Module", "5 Day Streak", "Quiz Master").

---

## Learning Content — MVP Module List

| ID | Module Title | Category | Difficulty | Est. Time |
|----|-------------|----------|-----------|-----------|
| M-01 | What is an AI Agent? | Foundations | Beginner | 8 min |
| M-02 | The Agent Loop: Perceive → Think → Act | Foundations | Beginner | 10 min |
| M-03 | Tools & Tool Use in Agents | Architecture | Beginner | 12 min |
| M-04 | Memory: Short-Term vs Long-Term | Architecture | Intermediate | 10 min |
| M-05 | Multi-Agent Systems & Orchestration | Architecture | Intermediate | 15 min |

---

## Out of Scope for MVP

- User authentication / cloud sync via the Backend (The Spring Boot API framework is being scaffolded in Sprint 1, but offline usage remains the primary fallback for the MVP).
- Social features (leaderboards, sharing).
- Video content.
- In-app purchases or subscriptions.
- AI-powered chat assistant within the app.
- Push notifications (system architecture is designed for it, but not activated in MVP).
