---
name: android-security
description: >
  Security review checklist and automation scripts for the LearnAgenticAI
  Android project. Covers: secret scanning, permission auditing, TLS verification,
  OWASP dependency check, and ProGuard/R8 configuration review.
agents:
  - agent/security
---

# Skill: Android Security Standards

## Secret Scanning (Pre-commit)

Run before every commit:
```bash
#!/bin/bash
detect-secrets scan --baseline .secrets.baseline
detect-secrets audit .secrets.baseline
```

## Network Security Config

Mandatory `res/xml/network_security_config.xml`:
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="false">
        <trust-anchors>
            <certificates src="system"/>
        </trust-anchors>
    </base-config>
</network-security-config>
```

## PR Security Review Checklist
- [ ] No hardcoded strings matching pattern `(api_key|secret|password|token)` in source.
- [ ] `AndroidManifest.xml` permissions match the approved list in `how-architecture.md`.
- [ ] No `Log.d/v/i` statements containing PII or sensitive data in release builds.
- [ ] All WebView instances have `setJavaScriptEnabled(false)` unless explicitly justified.
- [ ] `allowBackup="false"` is set in `AndroidManifest.xml`.
- [ ] Network security config is referenced and disables cleartext traffic.
- [ ] ProGuard rules do not expose sensitive class names in release builds.

## OWASP Dependency Check (CI)

```yaml
- name: OWASP Dependency Check
  run: ./gradlew dependencyCheckAnalyze
  env:
    NVD_API_KEY: ${{ secrets.NVD_API_KEY }}
```

## Spring Boot Backend Security Checklist
- [ ] No hardcoded database credentials (Oracle), JWT secrets, or cloud tokens in `application.yml` or `.java` files.
- [ ] Spring Security is configured to enforce authentication on all non-public endpoints.
- [ ] WebFlux endpoints have proper `@PreAuthorize` or functional security rules applied.
- [ ] Password hashes use BCrypt (or similar strong algorithm), never plain text.
- [ ] CORS is strictly configured, no wildcard `*` allowed in production.
