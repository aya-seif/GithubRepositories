# GitHub Repo Viewer App

## Project Overview

This project is a GitHub Repo Viewer app developed in Kotlin using Jetpack Compose, Coroutines, Retrofit, Room Database, Paging3, MVVM, Kotlin Flows and Dagger Hilt for Dependency Injection. It allows users to view GitHub repositories, their details, and associated issues.

## Project Structure

The project is structured as follows:

- `app`: Main Android application module.
  - `src/main`: Contains the main source code.
  - `src/test`: Unit tests for ViewModel logic.
  - `src/androidTest`: UI tests.

- `data`: Data layer module.
  - `local`: Room Database implementation.
  - `remote`: Retrofit API service.
  - `domain`: Use case implementation.

- `domain`: Domain layer module.
  - `models`: Data models.
  - `usecase`: Use case implementations.
  - `repository`: Repository interfaces.

- `ui`: UI layer module.
  - `repos`: Screens and ViewModels for repository list and details.
  - `issues`: Screens and ViewModels for issues list.

## Prerequisites

Ensure you have the following installed on your development machine:

- [Android Studio](https://developer.android.com/studio)
- [Git](https://git-scm.com/)

## Building the App

1. Clone the repository to your local machine.

   ```bash
   git clone https://github.com/aya-seif/GithubRepositories.git
