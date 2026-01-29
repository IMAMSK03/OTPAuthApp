# OTP Authentication App (Jetpack Compose)

## Overview

This Android app demonstrates a **passwordless authentication flow using Email + OTP**, followed by a **session screen with a live timer**. All logic is implemented **locally (no backend)** using **Kotlin, Jetpack Compose, ViewModel, and StateFlow**.

---

## Features

* Email + OTP login (6-digit OTP)
* OTP rules:

    * Expires after **60 seconds**
    * **Maximum 3 attempts**
    * Regenerating OTP **invalidates the old one** and resets attempts
* Session screen:

    * Shows session start time
    * Live session duration (mm:ss)
    * Logout button
* Timer survives recomposition
* External SDK integration using **Timber** for analytics logging

---

## Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Architecture:** MVVM (ViewModel + UI State)
* **Async / State:** StateFlow
* **Logging SDK:** Timber

---

## OTP Logic & Expiry Handling

* OTPs are generated locally using a random 6-digit number.
* OTPs are stored in memory using a `Map<Email, OtpData>`.
* Each OTP has:

    * `createdAt` timestamp
    * `attemptsLeft` counter
* On validation:

    * If expired → validation fails
    * If attempts exceeded → validation fails
    * If correct → login success and OTP is removed

> **Note:** OTPs are logged to **Logcat** for demo/testing purposes (no real email sending).

---

## Data Structures Used

* `MutableMap<String, OtpData>`

    * Key: Email
    * Value: OTP data (otp, created time, attempts)

**Why Map?**

* Allows OTPs to be tracked **per email**
* Easy replacement when generating a new OTP
* Constant-time lookup during validation

---

## External SDK (Timber)

**Why Timber?**

* Lightweight
* Simple setup
* Ideal for logging analytics events in demo apps

**Events Logged:**

* OTP generated
* OTP validation success
* OTP validation failure
* Logout

---

## Architecture Explanation

* **ViewModel** holds all business logic and UI state
* **StateFlow** exposes immutable UI state to the UI layer
* **Composable screens** observe state and react automatically
* No UI logic inside ViewModel
* One-way data flow

---

## Screen Rotation Handling

* UI state is stored in ViewModel
* Timers are derived from timestamps, not counters
* Rotation does not reset OTP/session state

---

## What I Used GPT For

* Understanding project requirements
* Clarifying Jetpack Compose best practices
* Debugging Gradle and dependency issues

## What I Implemented & Understood Myself

* OTP generation and validation logic
* State management using ViewModel + StateFlow
* Compose UI screens and recomposition handling
* Session timer logic
* Timber integration

---

## How to Run

1. Open the project in Android Studio
2. Sync Gradle
3. Run on emulator or physical device
4. Enter email → Send OTP
5. Check OTP in **Logcat**
6. Verify OTP → Session screen

---

## Notes

* This project intentionally avoids real email/SMS services
* Designed for learning, assignments, and interviews

---

## Author

**Shaik Imam Mahabu**
