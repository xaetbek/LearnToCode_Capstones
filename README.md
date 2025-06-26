# EasyShop E-Commerce API

A comprehensive Spring Boot REST API for an e-commerce platform that provides backend services for product management, user authentication, shopping cart functionality, and order processing.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [Database Setup](#database-setup)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Development Phases](#development-phases)
- [Screenshots](#screenshots)
- [Interesting Code Highlight](#interesting-code-highlight)

## 🎯 Project Overview

EasyShop is a full-featured e-commerce API built with Spring Boot that serves as the backend for an online shopping platform. This project represents Version 2 of the EasyShop platform, featuring enhanced functionality including category management, product search/filtering, shopping cart operations, user profiles, and a complete checkout system.

The API supports role-based access control with separate permissions for regular users and administrators, ensuring secure operations for sensitive functions like product and category management.

## ✨ Features

### Core Functionality
- **User Authentication & Authorization** - JWT-based authentication with role-based access control
- **Product Management** - Full CRUD operations for products (Admin only)
- **Category Management** - Complete category system with admin controls
- **Advanced Search & Filtering** - Product search by category, price range, and color
- **Shopping Cart** - Persistent cart functionality for logged-in users
- **User Profiles** - User profile management and updates
- **Order Processing** - Complete checkout system converting carts to orders

### Security Features
- JWT token-based authentication
- Role-based authorization (USER/ADMIN)
- CORS configuration for frontend integration
- Secure password handling

### Additional Features
- RESTful API design
- MySQL database integration
- Comprehensive error handling
- Unit testing implementation
- Postman collection for API testing

## 🛠 Technology Stack

- **Backend Framework:** Spring Boot
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Tokens)
- **Build Tool:** Maven
- **Testing:** JUnit, Postman
- **Documentation:** Postman Collections

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher
- IntelliJ IDEA or similar IDE
- Postman (for API testing)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/xaetbek/LearnToCode_Capstones.git
   cd EasyShop-API-Debugging
   ```

2. **Set up the database**
   - Open MySQL Workbench
   - Execute the `create_database.sql` script located in the database folder
   - This will create the `easyshop` database with sample data

3. **Configure application properties**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/easyshop
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080`

## 🗄 Database Setup

The database script creates the following tables:
- `users` - User authentication and basic info
- `profiles` - Extended user profile information
- `categories` - Product categories
- `products` - Product catalog
- `shopping_cart` - User shopping cart items
- `orders` - Order records
- `order_line_items` - Individual order items

### Sample Users
- **Admin User:** username: `admin`, password: `password`
- **Regular User:** username: `user`, password: `password`
- **Test User:** username: `george`, password: `password`

## 🔗 API Endpoints

### Authentication
```
POST /register     # User registration
POST /login        # User authentication
```

### Categories (Admin only for CUD operations)
```
GET    /categories           # Get all categories
GET    /categories/{id}      # Get category by ID
POST   /categories           # Create new category (Admin)
PUT    /categories/{id}      # Update category (Admin)
DELETE /categories/{id}      # Delete category (Admin)
```

### Products (Admin only for CUD operations)
```
GET    /products                    # Get all products (with filtering)
GET    /products/{id}               # Get product by ID
POST   /products                    # Create new product (Admin)
PUT    /products/{id}               # Update product (Admin)
DELETE /products/{id}               # Delete product (Admin)
```

#### Product Filtering Parameters
- `cat` - Filter by category ID
- `minPrice` - Minimum price filter
- `maxPrice` - Maximum price filter
- `color` - Filter by product color

### Shopping Cart (Authenticated users only)
```
GET    /cart                       # Get user's cart
POST   /cart/products/{productId}  # Add product to cart
PUT    /cart/products/{productId}  # Update quantity in cart
DELETE /cart                      # Clear cart
```

### User Profile (Authenticated users only)
```
GET /profile      # Get user profile
PUT /profile      # Update user profile
```

### Orders (Authenticated users only)
```
POST /orders      # Create order from cart (checkout)
```

## 🔐 Authentication

The API uses JWT (JSON Web Tokens) for authentication. After successful login, include the token in the Authorization header:

```
Authorization: Bearer <eyJhbGciOiJIUzI1NiJ9...>
```

### Sample Login Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "user": {
    "id": 2,
    "username": "admin",
    "authorities": [
      {
        "name": "ROLE_ADMIN"
      }
    ]
  }
}
```

## 🧪 Testing

### Postman Collections
Two Postman collections are provided:
- `easyshop.postman_collection.json` - Core functionality tests
- `easyshop-optional.postman_collection.json` - Optional features tests

### Running Tests
1. Import both collections into Postman
2. Create a new workspace for the project
3. Set up collection variables (userToken, adminToken, etc.)
4. Run the complete test suite to validate all endpoints

### Unit Testing
The project includes comprehensive unit tests for:
- Authentication services
- Product search and filtering logic
- Shopping cart operations
- Category management
- Bug fixes and edge cases

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/yearup/
│   │       ├── controllers/     # REST controllers
│   │       ├── data/           # Data access layer
│   │       ├── models/         # Entity models
│   │       └── security/       # Authentication & authorization
│   └── resources/
│       └── application.properties
├── test/
│   └── java/                   # Unit tests
└── database/
    └── create_database.sql     # Database setup script
```

## 🔄 Development Phases

### Phase 1: Categories Controller ✅
- Implemented complete CRUD operations for categories
- Added admin-only restrictions for modifications
- Created MySQL DAO implementation

### Phase 2: Bug Fixes ✅
- **Bug 1:** Fixed product search/filtering logic
- **Bug 2:** Resolved product update issues causing duplicates
- Added comprehensive unit tests

### Phase 3: Shopping Cart 🛒  -  [LOADING...]
- Implemented persistent shopping cart functionality
- Added cart item management (add, update, remove)
- Created cart-to-order conversion system

### Phase 4: User Profiles 👤  -  [LOADING...]
- Built user profile management system
- Implemented profile viewing and updating
- Integrated with user registration process

### Phase 5: Checkout System 🛍  -  [LOADING...]
- Created complete order processing system
- Implemented cart-to-order conversion
- Added order line item management

## 📸 Screenshots  -  [LOADING...]

### API Testing in Postman
![Postman Testing](screenshots/postman-testing.png)

### Frontend Integration
![EasyShop Website](screenshots/easyshop-frontend.png)

### Database Schema
![Database Structure](screenshots/database-schema.png)

## 💡 Interesting Code Highlight

One of the most interesting aspects of this project is the shopping cart implementation that maintains state across user sessions. Here's a key piece of code from the PROJECT:

  [LOADING...]

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## 👥 Authors

- **Khayotbek Azimov** - *Full Stack Software Engineer*

## 🙏 Acknowledgments

- Remsey Maijlard - Pluralsight Instructor
- Pluralsight LearnToCode Java Development Bootcamp
- Spring Boot Documentation
- MySQL Community
- JWT.io for token debugging tools
