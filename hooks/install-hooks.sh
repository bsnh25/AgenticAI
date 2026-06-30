#!/bin/bash
# Run this once after cloning: bash hooks/install-hooks.sh
echo "Installing git hooks..."
cp hooks/pre-commit .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit
echo "✅ Pre-commit hook installed"
