# 📚 E-Bookstore Application

A full-stack e-commerce application for an online bookstore built with Spring Boot backend and vanilla JavaScript frontend.

## 🎯 Project Overview

This is an AI Specialist Capstone Project demonstrating the use of AI-assisted development tools to build a complete e-commerce backend application with REST APIs, database integration, and a functional web interface.

### Key Features

- 👤 **User Management**: Registration, login, and profile management
- 📖 **Product Catalog**: Browse, search, and filter books by category and brand
- 🛒 **Shopping Cart**: Add, update, and remove items
- 💳 **Checkout Process**: Complete purchases with multiple payment options
- 🎁 **Gift Points System**: Earn and redeem points on purchases
- 📦 **Order Management**: View order history, reorder, and cancel orders
- 🔍 **Search & Filter**: Advanced product search and filtering capabilities
- 📱 **Responsive UI**: Mobile-friendly web interface

## 🛠️ Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Web**
- **Maven**

### Database
- **PostgreSQL** (Production)
- **H2 Database** (Testing/Development)

### Frontend
- **HTML5**
- **CSS3**
- **Vanilla JavaScript**
- **Fetch API**

### Tools
- **Git** for version control
- **Postman/Insomnia** for API testing
- **H2 Console** for database inspection

## 📋 Prerequisites

Before running this application, ensure you have:

- ✅ Java 17 or higher installed
- ✅ Maven 3.6+ installed
- ✅ PostgreSQL 12+ installed (optional - can use H2)
- ✅ Git installed
- ✅ Modern web browser (Chrome, Firefox, Edge)

## 🚀 Quick Start

### Option 1: Run with H2 Database (Recommended for Testing)

1. **Clone the repository**
```bash
git clone <repository-url>
cd EBookStore
```

2. **Build the application**
```bash
mvn clean install
```

3. **Run with H2 profile**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

4. **Access the application**
```
http://localhost:8080
```

### Option 2: Run with PostgreSQL

1. **Install and start PostgreSQL**

2. **Create database**
```sql
CREATE DATABASE ebookstore;
```

3. **Update database credentials** in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ebookstore
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Build and run**
```bash
mvn clean install
mvn spring-boot:run
```

## 📁 Project Structure

```
EBookStore/
├── src/
│   ├── main/
│   │   ├── java/com/ebookstore/
│   │   │   ├── EbookstoreApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── UserController.java
│   │   │   │   ├── ProductController.java
│   │   │   │   ├── CartController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   └── RecommendationController.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── ProductService.java
│   │   │   │   ├── CartService.java
│   │   │   │   └── OrderService.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── ProductRepository.java
│   │   │   │   ├── CartRepository.java
│   │   │   │   ├── CartItemRepository.java
│   │   │   │   └── OrderRepository.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── Product.java
│   │   │   │   ├── Cart.java
│   │   │   │   ├── CartItem.java
│   │   │   │   ├── Order.java
│   │   │   │   └── OrderItem.java
│   │   │   └── dto/
│   │   │       ├── LoginRequest.java
│   │   │       ├── UserRegistrationRequest.java
│   │   │       ├── ProductRequest.java
│   │   │       ├── OrderRequest.java
│   │   │       ├── CartItemRequest.java
│   │   │       └── RecommendationResponse.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-h2.properties
│   │       └── static/
│   │           ├── index.html
│   │           ├── register.html
│   │           ├── login.html
│   │           ├── products.html
│   │           ├── cart.html
│   │           ├── checkout.html
│   │           └── orders.html
│   └── test/
│       └── java/com/ebookstore/
├── pom.xml
├── README.md
├── TESTING_GUIDE.md
├── test-apis.md
└── openapi-specification.yaml
```

## 🔌 API Endpoints

### User Management
- `POST /api/users/register` - Register new user
- `POST /api/users/login` - User login
- `GET /api/users/{id}` - Get user details
- `PUT /api/users/{id}` - Update user profile

### Product Management
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Add new product (Admin)
- `PUT /api/products/{id}` - Update product (Admin)
- `DELETE /api/products/{id}` - Delete product (Admin)
- `GET /api/products/search?keyword={keyword}` - Search products
- `GET /api/products/category/{category}` - Filter by category
- `GET /api/products/brand/{brand}` - Filter by brand

### Shopping Cart
- `GET /api/cart?userId={userId}` - Get user's cart
- `POST /api/cart/add?userId={userId}&productId={productId}&quantity={quantity}` - Add to cart
- `PUT /api/cart/update?userId={userId}&productId={productId}&quantity={quantity}` - Update cart item
- `DELETE /api/cart/remove?userId={userId}&productId={productId}` - Remove from cart
- `DELETE /api/cart/clear?userId={userId}` - Clear cart

### Order Management
- `POST /api/orders` - Place order
- `GET /api/orders?userId={userId}` - Get user orders
- `GET /api/orders/{id}` - Get order details
- `POST /api/orders/{id}/cancel?userId={userId}` - Cancel order
- `POST /api/orders/{id}/reorder?userId={userId}` - Reorder

### Recommendations
- `GET /api/recommendations?userId={userId}` - Get personalized recommendations

## 🧪 Testing

### Run the Application
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

### Access H2 Console
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:ebookstore
Username: sa
Password: (leave empty)
```

### Test the Web Interface

1. **Home Page**: `http://localhost:8080`
2. **Register**: `http://localhost:8080/register.html`
3. **Login**: `http://localhost:8080/login.html`
4. **Products**: `http://localhost:8080/products.html`
5. **Cart**: `http://localhost:8080/cart.html`
6. **Checkout**: `http://localhost:8080/checkout.html`
7. **Orders**: `http://localhost:8080/orders.html`

### API Testing with Postman

Import the `openapi-specification.yaml` file into Postman to get all API endpoints.

For detailed testing instructions, see [TESTING_GUIDE.md](TESTING_GUIDE.md)

## 📊 Database Schema

### Tables
- **users** - User accounts with gift points
- **products** - Product catalog
- **carts** - Shopping carts
- **cart_items** - Items in carts
- **orders** - Order records
- **order_items** - Items in orders

### Relationships
- User → Cart (One-to-One)
- Cart → CartItems (One-to-Many)
- User → Orders (One-to-Many)
- Order → OrderItems (One-to-Many)
- Product → CartItems (One-to-Many)
- Product → OrderItems (One-to-Many)

## 🎨 User Interface

### Pages

1. **Home Page** - Landing page with feature overview
2. **Registration** - New user signup
3. **Login** - User authentication
4. **Products** - Browse and search catalog
5. **Cart** - Manage shopping cart
6. **Checkout** - Complete purchase
7. **Orders** - View order history

### Features

- 🎨 Modern, responsive design
- 🔍 Real-time search and filtering
- 🛒 Dynamic cart updates
- 💰 Gift points display and redemption
- 📱 Mobile-friendly interface
- ✨ Smooth animations and transitions

## 🔐 Security Features

- Password storage (plain text - for demo only, use BCrypt in production)
- Session management via localStorage
- CORS enabled for API access
- Input validation on both frontend and backend

## 🚧 Future Enhancements

- [ ] JWT-based authentication
- [ ] Password encryption with BCrypt
- [ ] Email notifications
- [ ] Payment gateway integration
- [ ] Product reviews and ratings
- [ ] Advanced recommendation engine
- [ ] Admin dashboard
- [ ] Inventory management
- [ ] Analytics and reporting
- [ ] Multi-language support
- [ ] Dark mode

## 📝 Development Notes

### Built With AI Assistance

This project was developed using AI-assisted development tools following the AI Specialist Capstone Project requirements:

1. ✅ Analyzed business requirements and wireframes
2. ✅ Generated OpenAPI specification
3. ✅ Created Spring Boot backend application
4. ✅ Configured PostgreSQL and H2 databases
5. ✅ Implemented all REST APIs
6. ✅ Created web interface for testing
7. ✅ Tested all functionality
8. ✅ Documented the implementation

### Key Decisions

- **H2 Database**: Added for easy testing without PostgreSQL setup
- **Vanilla JavaScript**: Used instead of React/Angular for simplicity
- **LocalStorage**: Used for session management (demo purposes)
- **Static Files**: Served directly from Spring Boot for convenience

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is created for educational purposes as part of the AI Specialist Capstone Project.

## 👥 Author

Created as part of the AI Specialist Capstone Project demonstrating AI-assisted development.

## 📞 Support

For issues or questions:
- Check [TESTING_GUIDE.md](TESTING_GUIDE.md) for detailed testing instructions
- Review [test-apis.md](test-apis.md) for API documentation
- Check application logs in `logs/application.log`

## 🎓 Learning Outcomes

This project demonstrates:
- ✅ Spring Boot application development
- ✅ RESTful API design and implementation
- ✅ Database design and JPA/Hibernate usage
- ✅ Frontend-backend integration
- ✅ Git version control
- ✅ API testing and documentation
- ✅ AI-assisted development workflow

## 🌟 Acknowledgments

- Spring Boot Documentation
- PostgreSQL Documentation
- H2 Database Documentation
- AI-assisted development tools (IBM BOB / AWS Kiro concepts)

---

**Happy Coding! 🚀**