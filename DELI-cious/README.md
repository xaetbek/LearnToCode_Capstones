# DELI-cious 🍞🥪

**DELI-cious** is a console-based Point-of-Sale (POS) application for a custom sandwich shop. Built as a capstone project, this system allows customers to fully customize their sandwich orders, including size, bread, premium/regular toppings, sides, and drinks.

## 📌 Project Overview

DELI-cious was designed to modernize the order process at a growing sandwich shop that previously handled all transactions using pen and paper. This application streamlines ordering, calculates totals, and generates time-stamped receipts—all while demonstrating Object-Oriented Analysis and Design principles.

## 🛠 Features

- Custom sandwich builder (size, bread, toppings, sauces, toasting)
- Add sides: drinks (multiple sizes/flavors) and chips
- Multiple sandwiches per order
- Price calculation (including premium toppings and extras)
- Order review and confirmation
- Receipt generation with timestamped filenames

## 💡 Technologies Used

- Language: Java
- Object-Oriented Programming (OOP)
- File I/O (for receipt generation)
- Console-based UI
- UML Design and Class Diagrams

## 📷 Screenshots
### 🏠 Home Screen
Main menu with options to View Menu, New Order, Order History, or Exit.
![Home Screen](screenshots/Home%20Screen.png)
---

### 📒 View Menu
Available menu items

![View Menu](screenshots/Menu%20Screen.png)
---

### 🧾 Order Screen
Displays options to build your order.

![New Order Screen](screenshots/New%20Order%20Screen.png)
---

### 🥪 Add Sandwich
Guides the user through sandwich customization.

- **Select your bread**
- **Sandwich size**
- **Toppings** (allows extras):
    - 🥩 Meat
    - 🧀 Cheese
    - 🥗 Other toppings
    - 🥫 Sauces
- **Toasted?** — Option to toast the sandwich

![Add Sandwich Screen](screenshots/Add%20Sandwich%20Screen.png)

---

### 🥤 Add Drink
Select drink **size** and **flavor**.

![Add Drink Screen](screenshots/Add%20Drink%20Screen.png)

---

### 🍟 Add Chips
Choose your **chip type**.

![Add Chips Screen](screenshots/Add%20Chips%20Screen.png)

---

### 💳 Checkout
Displays the full **order details** and **total price**.

- ✅ `Confirm` — Generates receipt and returns to Home Screen
- ❌ `Cancel` — Discards order and returns to Home Screen

![Add Checkout Screen](screenshots/Checkout%20Screen.png)

---

### 🧾 Order History
Displays all the history of confirmed orders from a receipts folder. Newer entries first

![Order History Screen](screenshots/Order%20History%20Screen.png)

---

### 🧾 Full Receipt Screen
Displays the full receipt from receipts history.

![Full Receipt Screen](screenshots/Full%20Receipts%20Screen.png)


## 📁 Folder Structure
```plaintext
DELI-cious/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── pluralsight/
│                   ├── main/
│                   │   └── Main.java 
│                   ├── model/
│                   │   ├── enums/
│                   │   │   ├── BreadType.java
│                   │   │   ├── CheeseType.java
│                   │   │   ├── DrinkSize.java
│                   │   │   ├── MeatType.java
│                   │   │   ├── RegularToppingType.java
│                   │   │   ├── SandwichSize.java
│                   │   │   ├── SauceType.java
│                   │   │   └── SideType.java
│                   │   ├── interfaces/
│                   │   │   └── Priceable.java
│                   │   ├── products/
│                   │   │   ├── AbstractMenuItem.java
│                   │   │   ├── Order.java
│                   │   │   ├── Sandwich.java
│                   │   │   ├── Drink.java
│                   │   │   └── Chips.java
│                   │   └── toppings/
│                   │   │   ├── Cheese.java
│                   │   │   ├── Meat.java
│                   │   │   ├── RegularTopping.java
│                   │   │   ├── Sauce.java
│                   │   │   ├── Side.java
│                   │   │   └── Topping.java
│                   ├── service/
│                   │   ├── BuildOrder.java
│                   │   ├── PriceCalculator.java
│                   │   └── ReceiptGenerator.java
│                   └── ui/
│                       ├── HomeScreen.java
│                       └── OrderScreen.java
├── receipts/
├── docs/
│   └── diagrams/
├── .gitignore
├── README.md  
├── pom.xml
└── LICENSE
```

## ✅ Capstone Goals
- Practice OOP design and development
- Implement real-world problem solving in software
- Deliver a working console-based application
- Maintain source control with GitHub
- Track progress using GitHub Projects and Issues

## Credits
- Instructor: Remsey M.
- Student: Khayotbek Azimov
- Class: Learn To Code Academy - Java Focus
- Learning Group: LG1
- Learning Class: LC1 Code-Blooded
- Capstone: No.2
- Date: May 30th, 2025
- Program: YearUpUnited

## 📄 License
This project is licensed under the MIT License.