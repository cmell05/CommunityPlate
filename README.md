# Community Plate

Community Plate is a community-driven platform for sharing food resources. It allows users to donate or request food items, aiming to reduce food waste and promote food sustainability. The project implements Object Oriented Programming (OOP) principles.

---

## Project Overview
This project includes two main classes:
- **User**: Manages user authentication, role (Donor/Recipient), password, and ratings.
- **FoodItem**: Manages food donations, requests, and status updates (available, requested, claimed).

---

## 🌟 Features
- **User Authentication**: Secure authentication based on username and password.
- **Role-Based Access**: Users are assigned either a donor or recipient role.
- **Food Item Management**: Donors can list food items, and recipients can request or claim items.
- **Status Tracking**: Track the status of food items (Available, Requested, Claimed).
- **User Rating System**: Allows users to submit and view ratings for each other.

---

## 🛠️ Technology Stack
- **Java**: Programming language used for backend logic.
- **JUnit**: Testing framework for unit testing the application.
- **LocalDate**: Used for managing expiration dates of food items.

---

## 📁 Folder Structure
community-plate/
├── src/
│   ├── junit_test/
│   │   ├── TestFoodItem.java
│   │   ├── TestUser.java
│   ├── model/
│   │   ├── FoodItem.java
│   │   ├── User.java
│   │   ├── Main.java
│   │   ├── InvalidNameException.java
│   │   ├── InvalidPasswordException.java
│   │   ├── InvalidRoleException.java
├── README.md
└── .gitignore

---

## 🚀 Prerequisites
- Java Development Kit (JDK) version 11 or higher
- An IDE (e.g., IntelliJ IDEA, Eclipse) for Java development

---

## 🔧 Installation

1. Clone the repository:
**   git clone https://github.com/cmell05/communityplate.git
   cd communityplate**
2. Compile and run the Java files.
3. Run the JUnit tests to verify functionality.
