# E-Bookstore Testing Guide

## Prerequisites

1. **Java 17** installed
2. **Maven** installed
3. **PostgreSQL** installed (optional - can use H2 for testing)
4. **Git** installed
5. Modern web browser (Chrome, Firefox, Edge)

## Quick Start with H2 Database (Recommended for Testing)

### Step 1: Build the Application

```bash
mvn clean install
```

### Step 2: Run with H2 Database

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

The application will start on `http://localhost:8080`

### Step 3: Access the Application

Open your browser and navigate to:
```
http://localhost:8080
```

## Complete Testing Flow

### 1. User Registration

**URL:** `http://localhost:8080/register.html`

**Test Steps:**
1. Fill in the registration form:
   - Name: Test User
   - Email: test@example.com
   - Password: password123
   - Phone: 9876543210
   - Address: 123 Test Street, Test City
2. Click "Register"
3. Verify success message
4. Verify redirect to login page

**Expected Result:** User created successfully

### 2. User Login

**URL:** `http://localhost:8080/login.html`

**Test Steps:**
1. Enter credentials:
   - Email: test@example.com
   - Password: password123
2. Click "Login"
3. Verify redirect to products page

**Expected Result:** Login successful, user session stored

### 3. Browse Products

**URL:** `http://localhost:8080/products.html`

**Test Steps:**
1. View the product catalog
2. Test search functionality:
   - Enter "Java" in search box
   - Verify filtered results
3. Test category filter:
   - Select "Programming" category
   - Verify filtered results
4. Test brand filter:
   - Select "O'Reilly" brand
   - Verify filtered results

**Expected Result:** Products displayed and filters working

### 4. Add Products (Admin Only)

**Test Steps:**
1. Click "Add New Product" button
2. Fill in product details:
   - Title: Test Book
   - Author: Test Author
   - Category: Programming
   - Brand: Test Publisher
   - Price: 599.00
   - Stock: 50
   - Description: Test description
3. Click "Add Product"

**Expected Result:** Product added successfully

### 5. Add to Cart

**Test Steps:**
1. Click "Add to Cart" on any product
2. Verify success message
3. Check cart badge count increases

**Expected Result:** Product added to cart

### 6. View Cart

**URL:** `http://localhost:8080/cart.html`

**Test Steps:**
1. View cart items
2. Update quantity using +/- buttons
3. Verify total price updates
4. Test remove item functionality
5. Click "Proceed to Checkout"

**Expected Result:** Cart displays correctly with all operations working

### 7. Checkout

**URL:** `http://localhost:8080/checkout.html`

**Test Steps:**
1. Review order summary
2. Enter delivery address (or use default)
3. Select payment method (Credit Card or Debit Card)
4. Enter gift points to redeem (optional)
5. Click "Place Order"
6. Verify success modal appears

**Expected Result:** Order placed successfully

### 8. View Orders

**URL:** `http://localhost:8080/orders.html`

**Test Steps:**
1. View order history
2. Check order details (items, total, status)
3. Test "Buy Again" functionality
4. Test "Cancel Order" (within 48 hours)

**Expected Result:** Orders displayed with all details

### 9. Reorder

**Test Steps:**
1. Click "Buy Again" on any order
2. Verify items added to cart
3. Verify redirect to cart page

**Expected Result:** Previous order items added to cart

### 10. Cancel Order

**Test Steps:**
1. Click "Cancel Order" on recent order (within 48 hours)
2. Confirm cancellation
3. Verify order status changes to CANCELLED

**Expected Result:** Order cancelled successfully

## API Testing with Postman/Insomnia

### Import Collection

Use the provided `openapi-specification.yaml` to generate API collection.

### Test Endpoints

#### 1. Register User
```
POST http://localhost:8080/api/users/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "phone": "9876543210",
  "address": "123 Main St"
}
```

#### 2. Login User
```
POST http://localhost:8080/api/users/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

#### 3. Get All Products
```
GET http://localhost:8080/api/products
```

#### 4. Search Products
```
GET http://localhost:8080/api/products/search?keyword=Java
```

#### 5. Filter by Category
```
GET http://localhost:8080/api/products/category/Programming
```

#### 6. Add Product (Admin)
```
POST http://localhost:8080/api/products
Content-Type: application/json

{
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "category": "Programming",
  "brand": "Manning",
  "price": 799.00,
  "stockQuantity": 100,
  "description": "Comprehensive guide to Spring Boot"
}
```

#### 7. Add to Cart
```
POST http://localhost:8080/api/cart/add?userId=1&productId=1&quantity=2
```

#### 8. Get Cart
```
GET http://localhost:8080/api/cart?userId=1
```

#### 9. Update Cart Item
```
PUT http://localhost:8080/api/cart/update?userId=1&productId=1&quantity=3
```

#### 10. Remove from Cart
```
DELETE http://localhost:8080/api/cart/remove?userId=1&productId=1
```

#### 11. Place Order
```
POST http://localhost:8080/api/orders
Content-Type: application/json

{
  "userId": 1,
  "deliveryAddress": "123 Main St, City",
  "paymentMethod": "CREDIT_CARD",
  "giftPointsUsed": 50
}
```

#### 12. Get User Orders
```
GET http://localhost:8080/api/orders?userId=1
```

#### 13. Reorder
```
POST http://localhost:8080/api/orders/1/reorder?userId=1
```

#### 14. Cancel Order
```
POST http://localhost:8080/api/orders/1/cancel?userId=1
```

## Database Verification

### H2 Console (When using H2 profile)

**URL:** `http://localhost:8080/h2-console`

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:ebookstore`
- Username: `sa`
- Password: (leave empty)

**Queries to Run:**

```sql
-- View all users
SELECT * FROM users;

-- View all products
SELECT * FROM products;

-- View all carts
SELECT * FROM carts;

-- View cart items
SELECT * FROM cart_items;

-- View all orders
SELECT * FROM orders;

-- View order items
SELECT * FROM order_items;

-- Check user gift points
SELECT id, name, email, gift_points FROM users;
```

### PostgreSQL (When using PostgreSQL)

```bash
psql -U postgres -d ebookstore
```

Run the same SQL queries as above.

## Common Issues and Solutions

### Issue 1: Port 8080 Already in Use

**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue 2: Database Connection Error

**Solution:** Use H2 profile for testing:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

### Issue 3: CORS Error in Browser

**Solution:** Controllers already have `@CrossOrigin(origins = "*")` annotation.

### Issue 4: Cart Not Loading

**Solution:** 
1. Check if user is logged in (localStorage has user data)
2. Verify backend is running
3. Check browser console for errors

### Issue 5: Products Not Displaying

**Solution:**
1. Add sample products using the "Add Product" form
2. Or use API to add products via Postman

## Performance Testing

### Load Testing with Apache Bench

```bash
# Test product listing
ab -n 1000 -c 10 http://localhost:8080/api/products

# Test user registration
ab -n 100 -c 5 -p register.json -T application/json http://localhost:8080/api/users/register
```

## Security Testing

### Test Cases:

1. **SQL Injection:** Try entering SQL in search fields
2. **XSS:** Try entering `<script>alert('XSS')</script>` in forms
3. **Authentication:** Try accessing cart without login
4. **Authorization:** Try accessing other users' orders

## Test Data

### Sample Users
```json
{
  "name": "Alice Smith",
  "email": "alice@example.com",
  "password": "alice123",
  "phone": "9876543210",
  "address": "456 Oak Avenue"
}

{
  "name": "Bob Johnson",
  "email": "bob@example.com",
  "password": "bob123",
  "phone": "9876543211",
  "address": "789 Pine Street"
}
```

### Sample Products
```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "category": "Programming",
  "brand": "Prentice Hall",
  "price": 899.00,
  "stockQuantity": 50,
  "description": "A Handbook of Agile Software Craftsmanship"
}

{
  "title": "Design Patterns",
  "author": "Gang of Four",
  "category": "Programming",
  "brand": "Addison-Wesley",
  "price": 1099.00,
  "stockQuantity": 30,
  "description": "Elements of Reusable Object-Oriented Software"
}
```

## Automated Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn verify
```

## Test Coverage Report

```bash
mvn clean test jacoco:report
```

View report at: `target/site/jacoco/index.html`

## Continuous Integration

### GitHub Actions Workflow

The project includes a CI workflow that:
1. Builds the application
2. Runs all tests
3. Generates test reports

## Success Criteria

✅ All 18 REST APIs working correctly
✅ User registration and login functional
✅ Product catalog with search and filters working
✅ Cart operations (add, update, remove) working
✅ Checkout process completing successfully
✅ Order history displaying correctly
✅ Reorder functionality working
✅ Cancel order working (within 48 hours)
✅ Gift points system working
✅ Database persistence working
✅ Frontend UI responsive and user-friendly

## Next Steps

1. Deploy to cloud platform (AWS, Azure, GCP)
2. Add email notifications
3. Implement payment gateway integration
4. Add product reviews and ratings
5. Implement recommendation engine
6. Add admin dashboard
7. Implement inventory management
8. Add analytics and reporting

## Support

For issues or questions:
- Check application logs: `logs/application.log`
- Review API documentation: `openapi-specification.yaml`
- Check test documentation: `test-apis.md`