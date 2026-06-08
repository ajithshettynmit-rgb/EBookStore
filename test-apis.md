# API Testing Guide - Quick Test

The application is now running at: **http://localhost:8080**

## Quick Test Commands

### 1. Test Server is Running
```bash
curl http://localhost:8080/api/products
```
Expected: `[]` (empty array)

### 2. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"test@example.com\",\"password\":\"test123\",\"name\":\"Test User\",\"phone\":\"1234567890\"}"
```

### 3. Login
```bash
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"test@example.com\",\"password\":\"test123\"}"
```

### 4. Create a Product
```bash
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"title\":\"The Great Gatsby\",\"author\":\"F. Scott Fitzgerald\",\"description\":\"A classic novel\",\"price\":15.99,\"category\":\"Fiction\",\"brand\":\"Penguin\",\"stockQuantity\":100}"
```

### 5. Get All Products
```bash
curl http://localhost:8080/api/products
```

### 6. Add to Cart (replace userId with actual ID from registration)
```bash
curl -X POST "http://localhost:8080/api/cart/items?userId=1" ^
  -H "Content-Type: application/json" ^
  -d "{\"productId\":1,\"quantity\":2}"
```

### 7. View Cart
```bash
curl "http://localhost:8080/api/cart?userId=1"
```

### 8. Create Order
```bash
curl -X POST "http://localhost:8080/api/orders?userId=1" ^
  -H "Content-Type: application/json" ^
  -d "{\"deliveryAddress\":\"123 Main St\",\"paymentMethod\":\"CREDIT_CARD\",\"useGiftPoints\":0}"
```

### 9. View Orders
```bash
curl "http://localhost:8080/api/orders?userId=1"
```

## Browser Testing

Open these URLs in your browser:
- http://localhost:8080/api/products
- http://localhost:8080/h2-console (H2 Database Console)
  - JDBC URL: `jdbc:h2:mem:ebookstore`
  - Username: `sa`
  - Password: (leave empty)

## Application Status

✅ Application is running successfully
✅ Using H2 in-memory database
✅ All APIs are accessible
✅ H2 Console is enabled at /h2-console

## To Stop the Application

Press `Ctrl + C` in the terminal where the application is running.