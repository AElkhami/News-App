# Android Developer Technical Challenge: NewsApp Post Refactoring

## Overview
This project has been thoroughly refactored to meet production ready standards,
ensuring it can scale effectively in an enterprise environment. Although the task
focuses on a single screen that reads data from an asset file, I intentionally applied
a comprehensive refactoring to demonstrate a scalable, maintainable architecture and
adherence to modern Android best practices.

## Architecture Decisions

This project adheres to **Clean Architecture** principles, structured into clearly defined **presentation**, **domain**, and **data** layers.  
This design ensures high **testability**, **scalability**, and strict **separation of concerns**, aligning with modern enterprise Android standards.

### State and Interaction Management

For managing UI state and actions, I implemented a **hybrid MVVM/MVI approach**:

- **State:**  
  Managed as a single, immutable UI state object, following the unidirectional data flow philosophy of MVI.  
  This ensures a single source of truth for the UI and predictable state transitions.

- **Actions:**  
  Handled through direct `ViewModel` method calls in the spirit of MVVM.  
  This approach keeps the implementation clean and avoids unnecessary boilerplate, an intentional choice given the simplicity and low action nature of the current feature.

This approach inherently preserves state across configuration changes, such as screen rotations, ensuring a seamless user experience.

### Modularity

The application is organized as a **multi-module project** to achieve the following benefits:

- **Improved build performance** through parallel compilation of independent modules.
- **Better separation of responsibilities** across layers and features, making the codebase easier to reason about.
- **Enhanced readability and maintainability** as the project scales.

## Design Decisions

Beyond the core architecture, several additional design choices were made to enhance the robustness, observability, and developer experience of the project:

- **Reusable Composables**
  Built the UI using reusable, modular Composables to ensure a consistent design system, improve readability, and reduce maintenance overhead.
 
- **Logging with Timber**  
  Integrated the Timber library for structured, flexible logging during development and debugging.  
  This provides clear and contextual runtime information while keeping the logging implementation lightweight and easy to disable or replace in production builds.

- **Memory Leak Detection with LeakCanary**  
  Added LeakCanary to automatically detect and report potential memory leaks during development.  
  This helps maintain application stability and prevent resource leaks early in the development cycle.

- **Retry Mechanism & Error Handling**  
  Implemented a basic retry mechanism and centralized error handling to make the application more resilient to transient failures.  
  Errors are surfaced to the UI in a user friendly manner while keeping the internal state consistent and predictable.

## Trade-offs & Decisions

In designing this project, certain trade offs were made consciously to balance simplicity, maintainability, and alignment with common Android ecosystem practices:

- **Dependency Injection: Hilt vs. Koin**  
  I chose **Hilt** over **Koin** for dependency injection, despite Koin's more lightweight API.

  **Why this choice:**
    - Hilt is fully supported by Google and integrates seamlessly with Jetpack libraries.
    - Compile time validation with Dagger/Hilt helps catch errors early compared to Koin’s runtime resolution.
    - Better suited for larger, modular, production grade applications.
    - *Trade off:* Slightly steeper learning curve and more boilerplate compared to Koin.

- **Mocking: MockK vs. Mockito**  
  I opted for **MockK** over **Mockito** as the mocking framework.

  **Why this choice:**
    - MockK has first-class support for Kotlin features like `object`, `final` classes, coroutines, and suspending functions.
    - Cleaner and more idiomatic API in Kotlin.
    - *Trade off:* Smaller community and ecosystem compared to Mockito.

These decisions were made with a long term, production focused mindset, prioritizing robustness, ecosystem alignment, and Kotlin first tooling, even at the cost of some additional complexity or less popularity in certain tools.

## UI & Theming

- The application supports both light and dark (night) modes, automatically adapting to the user’s system theme preference. 

- It also integrates **Material You (dynamic color)** on supported Android versions, providing a modern, personalized look and feel aligned with Android’s design guidelines.

- In addition, the application is **Localization ready**. While currently only English strings are provided, the app is designed to automatically adapt to additional locales once translations are added, with no further code changes required.

## Things I Would Implement to Make the Application Production Ready

While this implementation demonstrates a solid foundation for scalability and maintainability, several additional steps would be necessary to make the application fully production ready:

- **Navigation**
    - Integrate **Jetpack Compose Navigation** to support navigation between multiple screens and manage the back stack effectively.
    - Define a clear navigation graph to improve maintainability and testability.

- **Pagination**
    - Implement pagination (e.g., Paging3) to handle large datasets efficiently, with proper loading and error states and a smooth scrolling experience, especially important for a news feed.

- **Offline Support**
    - Implement an **offline-first** strategy, caching data locally (e.g., using Room) when fetched from remote sources, to ensure a seamless user experience in poor network conditions.

- **Logging Abstraction**
    - Introduce a logging abstraction layer to decouple the app from a specific logging implementation (e.g., Timber) and make it easier to swap or disable in production.

- **Analytics**
    - Add analytics tracking (e.g., Firebase Analytics) to collect user engagement metrics, navigation flows, and feature adoption insights.

- **Security Enhancements**
    - Enforce HTTPS for all network communication.
    - Implement **certificate pinning** to prevent man in the middle attacks.
    - Enable Play Integrity API or SafetyNet for device attestation.

- **CI/CD and Quality Gates**
    - Set up CI/CD pipelines with lint checks, unit and UI tests, static code analysis (e.g., with SonarQube), and automated deployments.
    - Include crash reporting (e.g., Firebase Crashlytics) to monitor stability in production.

These enhancements reflect an enterprise grade mindset, focusing on delivering a secure, performant, user friendly, and maintainable application at scale.

## Testing

The project applies a balanced testing strategy to ensure correctness, maintainability, and confidence in the codebase.


- **Unit Tests (~70%)**: Achieved 100% coverage for core logic classes (`ViewModel`, `UseCases`, `Repository`, `DataSource`) using JUnit 4, MockK, and Google Truth.
- **UI Tests (~20%)**: Validated critical individual Composables in isolation with `createComposeRule`, ensuring correct state representation and interactions.
- **UI-Level Integration Tests (~10%)**: Verified full-screen behavior, ensuring that ViewModel state, Composables, and user interactions work together correctly at the screen level.

This testing pyramid strikes the right balance between thorough coverage of critical logic and efficient use of time, emphasizing fast, reliable unit tests while complementing them with targeted UI and integration tests where they deliver the most value.