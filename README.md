# 🌐 OneHub — All-in-One Java Console Application

> A feature-rich, menu-driven Java console application that brings multiple services under one roof — from booking movies and tickets to ordering food and making donations.

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation & Run](#installation--run)
- [Usage](#usage)
- [Tech Stack](#tech-stack)
- [Contributing](#contributing)
- [License](#license)

---

## 🧩 About the Project

**OneHub** is a Java-based console application built as a one-stop platform for multiple everyday services. Users can register, log in with secure credentials, and access a variety of services — all from the terminal. The application uses ANSI color codes to deliver a visually appealing console experience.

---

## ✨ Features

### 👤 User Management
- **Register** with username, mobile number, and a strong password
- **Login** with credential validation
- **Reset Password** via mobile number verification
- **Account Balance** tracking for all transactions
- Password strength enforcement (uppercase, lowercase, digit, special character, min 8 chars)

### 🎬 Movie Booking
- Browse available movies and theaters
- Select show dates and time slots
- Choose and book seats (with seat availability tracking)
- View booking summary with pricing

### ✈️ Travel Booking
An OOP-based booking system supporting multiple transport modes:
- 🚂 **Train Booking** — Book train tickets with passenger count and route selection
- 🚌 **Bus Booking** — Intercity bus reservations
- ✈️ **Flight Booking** — Domestic/international flight tickets
- 🚗 **Car Booking** — Car rental services
- 🚲 **Bike Booking** — Bike rental for short trips

### 🍽️ Food Ordering
- Browse categorized food menu (Soups, Mains, Desserts, etc.)
- Add items to cart and place orders
- Deduct amount from wallet balance

### 💝 Donation
- Donate to causes of your choice
- Track total donations and last donation timestamp
- Donation history per user session

---

## 📁 Project Structure

```
OneHub/
│
├── DB.java              # In-memory database (user credentials & balance)
├── User.java            # User registration, login, password management
├── Movie.java           # Movie booking system
├── Travelling.java      # Travel booking (Train, Bus, Flight, Car, Bike)
│                        # Includes abstract Booking class + subclasses
├── Food.java            # Food ordering system
├── Donation.java        # Donation module with user tracking
│
├── *.class              # Compiled Java bytecode (auto-generated)
└── 1-OneHub.pptx        # Project presentation
```

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK 8 or above** installed on your system
- A terminal / command prompt

Check your Java version:
```bash
java -version
```

### Installation & Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/OneHub.git
   cd OneHub
   ```

2. **Compile all Java files**
   ```bash
   javac *.java
   ```

3. **Run the application**
   ```bash
   java BookingApp
   ```
   *(Replace `BookingApp` with whichever class contains your `main` method)*

---

## 🖥️ Usage

Once the application starts, you'll be greeted with a login/register menu:

```
╔══════════════════════════╗
║       Welcome to OneHub  ║
╚══════════════════════════╝
1. Register
2. Login
3. Exit
```

After logging in, choose from the services:
```
1. Movie Booking
2. Travel Booking
3. Food Ordering
4. Donations
5. View Balance
6. Logout
```

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| **Java**   | Core programming language |
| **OOP**    | Inheritance, Abstraction, Encapsulation |
| **Collections** | `ArrayList`, `HashMap` for data management |
| **java.time** | Date and time handling |
| **ANSI Escape Codes** | Colored console output |
| **Scanner** | Console-based user input |

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add YourFeature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👨‍💻 Author

Made with ❤️ — feel free to connect and contribute!
