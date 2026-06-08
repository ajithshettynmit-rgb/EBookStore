# E-Bookstore Backend Application

A comprehensive Spring Boot backend application for an online bookstore with features including user authentication, product catalog, shopping cart, order management, and gift points system.

## Features

### User Management
- User registration and login
- User profile management
- Gift points system

### Product Catalog
- Browse products with filtering by category and brand
- Search products by title or author
- View product details
- Related product recommendations

### Shopping Cart
- Add products to cart
- Update item quantities
- Remove items from cart
- View cart total

### Order Management
- Create orders from cart
- View order history
- Cancel orders (within 48 hours)
- Reorder from previous orders
- Apply gift points for discounts

### Payment
- Support for Credit Card and Debit Card
- Gift points redemption

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.0
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **ORM**: Spring Data JPA / Hibernate

## Prerequisites

Before running this application, ensure you have the following installed:

1. **Java Development Kit (JDK) 17 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **Maven 3.6 or higher**
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **PostgreSQL 12 or higher**
   - Download from: https://www.postgresql.org/download/
   - Default port: 5432

## Database Setup

1. **Install PostgreSQL Community Edition**
   - Follow the installation wizard
   - Set a password for the postgres user (default: postgres)

2. **Create Database**
   ```sql
   -- Connect to PostgreSQL using psql or pgAdmin
   CREATE DATABASE ebookstore;
   ```

3. **Update Database Configuration**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ebookstore
   spring.datasource.username=postgres
   spring.datasource.password=your_password_here
   ```

## Installation & Setup

1. **Clone or Download the Project**
   ```bash
   cd c:/ApplicationCode/EBookStore
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login

### Products
- `GET /api/products` - Get all products (with optional filters)
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/{id}/related` - Get related products
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Cart
- `GET /api/cart?userId={userId}` - Get user's cart
- `POST /api/cart/items?userId={userId}` - Add item to cart
- `PUT /api/cart/items/{id}` - Update cart item quantity
- `DELETE /api/cart/items/{id}` - Remove item from cart

### Orders
- `GET /api/orders?userId={userId}` - Get user's order history
- `GET /api/orders/{id}` - Get order details
- `POST /api/orders?userId={userId}` - Create new order
- `POST /api/orders/{id}/cancel?userId={userId}` - Cancel order
- `POST /api/orders/{id}/reorder?userId={userId}` - Reorder from previous order

### Users
- `GET /api/users/profile?userId={userId}` - Get user profile

## Testing the APIs

### Using Postman or Insomnia

1. **Register a User**
   ```
   POST http://localhost:8080/api/auth/register
   Content-Type: application/json

   {
     "email": "user@example.com",
     "password": "password123",
     "name": "John Doe",
     "phone": "1234567890"
   }
   ```

2. **Login**
   ```
   POST http://localhost:8080/api/auth/login
   Content-Type: application/json

   {
     "email": "user@example.com",
     "password": "password123"
   }
   ```

3. **Create a Product**
   ```
   POST http://localhost:8080/api/products
   Content-Type: application/json

   {
     "title": "The Great Gatsby",
     "author": "F. Scott Fitzgerald",
     "description": "A classic American novel",
     "price": 15.99,
     "category": "Fiction",
     "brand": "Penguin Classics",
     "stockQuantity": 100,
     "imageUrl": "https://example.com/gatsby.jpg"
   }
   ```

4. **Get All Products**
   ```
   GET http://localhost:8080/api/products
   ```

5. **Add to Cart**
   ```
   POST http://localhost:8080/api/cart/items?userId=1
   Content-Type: application/json

   {
     "productId": 1,
     "quantity": 2
   }
   ```

6. **Create Order**
   ```
   POST http://localhost:8080/api/orders?userId=1
   Content-Type: application/json

   {
     "deliveryAddress": "123 Main St, City, State 12345",
     "paymentMethod": "CREDIT_CARD",
     "useGiftPoints": 0
   }
   ```

## Project Structure

```
EBookStore/
├── src/
│   ├── main/
│   │   ├── java/com/ebookstore/
│   │   │   ├── controller/          # REST Controllers
│   │   │   ├── service/              # Business Logic
│   │   │   ├── repository/           # Data Access Layer
│   │   │   ├── entity/               # JPA Entities
│   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   └── EbookstoreApplication.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
├── openapi-specification.yaml
└── README.md
```

## Database Schema

The application automatically creates the following tables:
- `users` - User information
- `products` - Product catalog
- `carts` - Shopping carts
- `cart_items` - Items in shopping carts
- `orders` - Order information
- `order_items` - Items in orders

## Configuration

Key configuration properties in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/ebookstore
spring.datasource.username=postgres
spring.datasource.password=postgres

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   - Change the port in `application.properties`: `server.port=8081`

2. **Database connection failed**
   - Verify PostgreSQL is running
   - Check database credentials in `application.properties`
   - Ensure database `ebookstore` exists

3. **Build fails**
   - Ensure Java 17 is installed: `java -version`
   - Clear Maven cache: `mvn clean`

## Git Repository Setup

```bash
# Initialize Git repository
git init

# Add all files
git add .

# Commit
git commit -m "Initial commit: E-Bookstore backend application"

# Create feature branch
git checkout -b feature/api-implementation

# Push to remote (replace with your repository URL)
git remote add origin https://github.com/yourusername/ebookstore-backend.git
git push -u origin feature/api-implementation
```

## Future Enhancements

- JWT-based authentication
- Password encryption (BCrypt)
- Email notifications
- Payment gateway integration
- Product reviews and ratings
- Advanced search with Elasticsearch
- Caching with Redis
- API documentation with Swagger/OpenAPI UI

## License

This project is created for educational purposes as part of the AI Specialist Capstone Project.

## Contact

For questions or support, please contact the development team.