# 📒 Accounting Ledger Application

## 🖋️ Description
Welcome to my **first capstone project** for the Learn to Code Academy!  
This is a **Command-Line Interface (CLI)** application designed to **track financial transactions** for personal or business use.  
Users can easily **add deposits**, **record payments**, and **generate filtered ledger reports** by type, vendor, or date range.  
All transaction data is saved persistently in a **CSV file**.

This project showcases my **Java development fundamentals** including:
- File input/output operations
- Command-line interaction
- User input handling
- Data filtering and reporting

---

## ✨ Features
- ➕ Add new deposits
- ➖ Record payments (debits)
- 📄 View full ledger (latest transactions first)
- 🔎 Filter ledger:
    - Deposits only
    - Payments only
- 📊 Generate reports:
    - Month-to-Date
    - Previous Month
    - Year-to-Date
    - Previous Year
    - Search by Vendor
- 🛠️ (Bonus) Perform Custom Searches by multiple fields

---

## 🛠️ Installation
1. **Clone** the repository:
   ```bash
    https://github.com/xaetbek/LearnToCode_Capstones.git
   ```
2. **Navigate** to the project directory:
   ```bash
    cd AccountingLedgerApp
   ```
3. **Compile** the Java source code:
   ```bash
    javac src/main/java/com/pluralsight/*.java
   ```
4. Run the code:
   ```bash
    java -cp src/main/java com.pluralsight.Main
   ```
Notes:
- You can also open and run this project inside an IDE like IntelliJ IDEA or Eclipse.
- Make sure Java 23 (or a compatible version) is installed on your machine.
- If using Maven, run:
  ``` bash
    mvn compile
    mvn exec:java
  ```
  
## 🖥️ Usage
Launch the application through the Command Line Interface (CLI) or your IDE terminal.
Use the main menu options to:

➕ Add new deposits

➖ Record outgoing payments

📄 View and filter the transaction ledger

📊 Generate a variety of detailed reports

All transactions are automatically saved to the **`transactions.csv`** file in your project directory.

## 📸 Screenshots
(Add screenshots of Home Screen, Ledger View, Reports Screen once available)

## ⚙️ Technologies Used
☕ Java 17

📂 Java File I/O (BufferedReader, BufferedWriter)

🕒 Java Time API (LocalDate, LocalTime)

🛠️ Maven (optional for dependency management)

🖥️ IntelliJ IDEA

🐙 Git & GitHub

📟 Command-Line Interface (CLI)

## 🗂️ Project Structure (DRAFT)
Capstone1-AccountingLedger/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── com.pluralrsight/
│                   ├── Main.java
│                   ├── Ledger.java
│                   ├── Transaction.java
│                   ├── TransactionService.java
│                   └── ReportService.java
├── resources/
├── .gitignore
├── transactions.csv
├── README.md
├── pom.xml (if using Maven)
└── LICENSE (optional)
