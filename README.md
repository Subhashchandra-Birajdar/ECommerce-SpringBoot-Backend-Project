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

## API Root Endpoint

`https://localhost:8009/`

`http://localhost:8009/swagger-ui/index.html#/`


## API Module Endpoints

### Login & Logout Module

* `POST /register/customer` : Register a new customer
* `POST /login/customer` : Logging in customer with valid mobile number & password
* `POST /logout/customer` : Logging out customer based on session token
* `POST /register/seller` : Register a new seller
* `POST /login/seller` : Logging in Seller
* `POST /logout/seller` : Logging out Seller based on session token


### Customer Module

* `GET /customer/current` : Getting currently logged in customer
* `GET /customer/orders` : Getting order history of logged in customer
* `GET /customers` : Getting All customers
* `PUT /customer` : Updates logged in customer
* `PUT /customer/update/password` : Updates customer password
* `PUT /customer/update/card` : Updates credit card details
* `PUT /customer/update/address?type=home` : Updates customer's home address
* `PUT /customer/update/credentials` : Updates email address and mobile number
* `DELETE /customer` : Deletes logged in user with valid session token
* `DELETE /customer/delete/address?type=home` : Deletes customer's home address


### Seller Module

* `GET /seller/{sellerid}` : Gets seller with passed seller Id
* `GET /seller/current` : Gets seller details for currently logged in seller
* `GET /sellers` : Gets all sellers
* `POST /addseller` : Adding new seller
* `PUT /seller` : Updates seller details
* `PUT /seller/update/password` : Updates seller password
* `PUT /seller/update/mobile` : Updates seller mobile number
* `DELETE /seller/{sellerid}` : Deletes seller with passed id


### Product Module

* `GET /product/{id}` : Gets product with given product id
* `GET /products` : Gets all products
* `GET /products/{category}` : Gets product with given category
* `GET /products/seller/{id}` : Gets product of given seller id
* `POST /products` : Adds a new product to database
* `PUT /products` : Updates the product with given product id
* `PUT /products/{id}` : Updates product quantity
* `DELETE /product/{id}` : Deletes product with given id


### Cart Module

* `GET /cart` : Get all items in Customer Cart
* `POST /cart/add` : Add item to Cart
* `DELETE /cart` : Remove item from Cart
* `DELETE /cart/clear` : Clear entire cart


### Order Module

* `GET /orders/{id}` : Gets order details with given order id
* `GET /orders` : Gets all orders
* `GET /orders/by/date` : Gets orders placed on given date (DD-MM-YYYY)
* `POST /order/place` : Places a new order based on cart items
* `PUT /orders/{id}` : Updates a pending order
* `DELETE /orders/{id}` : Cancels an order


### Sample API Response for Customer Login

`POST   localhost:8009/login/customer`

* Request Body

```
    {
        "mobileId": "9999999999",
        "password": "shyam123456"
    }
```

* Response

```
    {
        "sessionId": 23,
        "token": "customer_0ad57094",
        "userId": 19,
        "userType": "customer",
        "sessionStartTime": "2022-06-10T10:48:20.0109626",
        "sessionEndTime": "2022-06-10T11:48:20.0109626"
    }
```
   
