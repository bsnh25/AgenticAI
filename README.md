# 🤖 LearnAgenticAI — Android App

> *An educational Jetpack Compose Android app that teaches users the fundamentals of Agentic AI through interactive modules, quizzes, and a searchable glossary.*

[![CI](https://github.com/org/learn-agentic-ai-android/actions/workflows/ci.yml/badge.svg)](https://github.com/org/learn-agentic-ai-android/actions)
[![Coverage](https://img.shields.io/badge/coverage-80%25-brightgreen)](docs/test-reports/)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202025-blue)](https://developer.android.com/jetpack/compose)

---

## 🏗️ Multi-Agent Engineering Workflow

This project uses a **7-agent specialized swarm** following Spec-Driven Development principles.

| Agent | Identity | Primary Responsibility |
|-------|----------|----------------------|
| 🗂️ PM Agent | `agent/pm` | Requirements, backlog, user stories |
| 🏛️ Tech Lead | `agent/tech-lead` | Architecture, orchestration |
| 📱 Frontend | `agent/frontend` | Kotlin + Jetpack Compose UI |
| ⚙️ Backend | `agent/backend` | APIs, Room DB, repositories |
| 🚀 DevOps | `agent/devops` | CI/CD, Gradle, deployment |
| 🧪 SDET | `agent/sdet` | Unit, UI, and integration tests |
| 🔐 Security | `agent/security` | Security reviews, threat model |

## 🚀 Getting Started

```bash
# 1. Clone the repo
git clone https://github.com/org/learn-agentic-ai-android.git
cd learn-agentic-ai-android

# 2. Install git hooks
bash hooks/install-hooks.sh

# 3. Setup agent worktrees (for multi-agent parallel work)
bash .git/worktrees/setup-worktrees.sh

# 4. Open in Android Studio and build
./gradlew assembleDebug
```

## 📁 Project Structure

```
.specify/           → Spec-Driven specs, memory, and agent steering
skills/             → Procedural skills for agent guidance
hooks/              → Pre-commit security and quality hooks
app/                → Android application module
docs/               → Architecture diagrams, ADRs, test reports
.github/workflows/  → CI/CD pipelines
```

## 📖 Key Documents

| Document | Purpose |
|----------|---------|
| [Constitution](.specify/constitution.md) | 22 non-negotiable project rules |
| [Roles](.specify/roles.md) | Agent boundaries and responsibilities |
| [What Vision](.specify/specs/what-vision.md) | Product vision & user stories |
| [How Architecture](.specify/specs/how-architecture.md) | Technical blueprint |

## 🛡️ Security

Before contributing, read the [security policy](docs/security/threat-model.md).
All PRs require Security Agent sign-off before merge.
