# Movie Ticket Ordering App

An Android-based movie ticket booking application that allows users to browse movie information, view detailed descriptions, log in or register, and explore keyword-based search recommendations.

## Features

- User login and registration
- Browse popular and upcoming movies
- Movie search with keyword suggestions
- Detailed movie pages (e.g., Godzilla, Furiosa, Panda 4, The Full Guy, etc.)
- User profile management
- 🖼Image carousel and RecyclerView layout

## Project Structure

```
MovieTicketOrdering/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/movieticketordering/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── LoginActivity.java
│   │   │   │   ├── SignUpActivity.java
│   │   │   │   ├── profilePage.java
│   │   │   │   ├── SearchingPage.java
│   │   │   │   ├── Adapter/...
│   │   │   │   ├── KeyWord_package/...
│   │   │   │   └── [Movie detail pages *.java]
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
```

## Tech Stack

- **Android SDK**
- **Java**
- **Gradle Kotlin DSL**
- Firebase (includes `google-services.json`)
- XML Layouts, RecyclerView, Adapter pattern

## Getting Started

1. Open the project in Android Studio
2. Make sure Firebase is configured and `google-services.json` is in place
3. Click "Run" to deploy on an emulator or physical device

## Notes

- Ensure your Android Studio supports Kotlin DSL (recommended: Arctic Fox or newer)
- Firebase Auth is required for login features
- If using Firestore or Storage, configure access rules via Firebase Console

## License

This project for education only.
