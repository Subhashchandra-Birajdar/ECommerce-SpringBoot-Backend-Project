# E-Commerce Application REST API

This repository contains a REST API developed for an e-commerce application. The API supports fundamental CRUD operations required by any e-commerce platform, with user authentication and validation at every step.

## Tech Stack
- **Java**
- **Spring Framework**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**

## Modules

- **Login & Logout Module**
- **Seller Module**
- **Customer Module**
- **Product Module**
- **Cart Module**
- **Order Module**

## Features

### General Features
- **Authentication & Validation**: 
  - Both Customer and Seller are authenticated using a session token that is valid for 1 hour to ensure secure transactions.
  
### Seller Features
- **Administrator Role**: The seller has administrative control over the application.
- **Product Management**: Only registered sellers with a valid session token can add, update, or delete products in the main database.
- **Order Management**: Sellers can access details of various customers and their orders.

### Customer Features
- **Registration and Login**: Customers can register on the application and log in to receive a valid session token.
- **Product Browsing**: Customers can view different products, add them to their cart, and place orders.
- **Personalized Access**: Logged-in customers can access their orders, cart, and other features.

## Installation & Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/subhashchandra-birajdar/ecommerce-app.git
   cd ecommerce-app
   
