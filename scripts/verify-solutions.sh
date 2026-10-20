#!/usr/bin/env bash
# Runs every test against the reference solutions instead of the exercise stubs.
# Every test should pass. Use this after editing an exercise, test, or solution.
set -euo pipefail
cd "$(dirname "$0")/.."
mvn -B test -Psolutions
