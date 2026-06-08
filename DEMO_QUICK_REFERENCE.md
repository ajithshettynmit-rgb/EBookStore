# 🎬 Demo Quick Reference Guide

## 🚀 Quick Start Commands

### Start Application
```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=h2"
```

### Stop Process on Port 8080 (if needed)
```bash
# Check what's running
netstat -ano | findstr :8080

# Kill process (replace PID)
taskkill /F /PID [PID]
```

---

## 🔗 Important URLs

| Page | URL |
|------|-----|
| Home | http://localhost:8080 |
| Register | http://localhost:8080/register.html |
| Login | http://localhost:8080/login.html |
| Products | http://localhost:8080/products.html |
| Cart | http://localhost:8080/cart.html |
| Checkout | http://localhost:8080/checkout.html |
| Orders | http://localhost:8080/orders.html |
| H2 Console | http://localhost:8080/h2-console |

---

## 👤 Test User Data

### User Registration
```
Name: John Doe
Email: john@example.com
Password: password123
Phone: 9876543210
Address: 123 Main Street, Mumbai, Maharashtra 400001
```

### User Login
```
Email: john@example.com
Password: password123
```

---

## 📚 Sample Products to Add

### Product 1: Clean Code
```
Title: Clean Code
Author: Robert C. Martin
Category: Programming
Brand: Prentice Hall
Price: 899.00
Stock: 50
Description: A Handbook of Agile Software Craftsmanship
```

### Product 2: Design Patterns
```
Title: Design Patterns
Author: Gang of Four
Category: Programming
Brand: Addison-Wesley
Price: 1099.00
Stock: 30
Description: Elements of Reusable Object-Oriented Software
```

### Product 3: The Pragmatic Programmer
```
Title: The Pragmatic Programmer
Author: Andrew Hunt
Category: Programming
Brand: Addison-Wesley
Price: 799.00
Stock: 40
Description: Your Journey to Mastery
```

### Product 4: Introduction to Algorithms
```
Title: Introduction to Algorithms
Author: Thomas H. Cormen
Category: Computer Science
Brand: MIT Press
Price: 1299.00
Stock: 25
Description: Comprehensive algorithms textbook
```

### Product 5: JavaScript: The Good Parts
```
Title: JavaScript: The Good Parts
Author: Douglas Crockford
Category: Web Development
Brand: O'Reilly
Price: 599.00
Stock: 60
Description: Unearthing the Excellence in JavaScript
```

---

## 🗄️ H2 Database Console

### Connection Details
```
JDBC URL: jdbc:h2:mem:ebookstore
Username: sa
Password: (leave empty)
```

### Useful SQL Queries

#### View All Users
```sql
SELECT id, name, email, phone, gift_points, created_at FROM users;
```

#### View All Products
```sql
SELECT id, title, author, category, brand, price, stock_quantity FROM products;
```

#### View All Orders
```sql
SELECT id, user_id, order_date, status, total_amount, delivery_address, payment_method, gift_points_used FROM orders;
```

#### View Order Items
```sql
SELECT oi.id, oi.order_id, p.title, oi.quantity, oi.price 
FROM order_items oi 
JOIN products p ON oi.product_id = p.id;
```

#### View Cart Items
```sql
SELECT ci.id, u.name, p.title, ci.quantity 
FROM cart_items ci 
JOIN carts c ON ci.cart_id = c.id 
JOIN users u ON c.user_id = u.id 
JOIN products p ON ci.product_id = p.id;
```

#### Check User Gift Points
```sql
SELECT name, email, gift_points FROM users;
```

---

## 🎯 Demo Flow Checklist

### Phase 1: Setup (1 min)
- [ ] Start application
- [ ] Verify application is running
- [ ] Open browser to home page

### Phase 2: User Journey (2 min)
- [ ] Register new user
- [ ] Login with credentials
- [ ] Show successful authentication

### Phase 3: Product Management (2 min)
- [ ] Add 4-5 products
- [ ] Demonstrate search functionality
- [ ] Show category filter
- [ ] Show brand filter

### Phase 4: Shopping (2 min)
- [ ] Add 3 products to cart
- [ ] View cart
- [ ] Update quantities
- [ ] Remove one item
- [ ] Proceed to checkout

### Phase 5: Checkout (2 min)
- [ ] Review order summary
- [ ] Verify delivery address
- [ ] Select payment method
- [ ] Place order
- [ ] Show success message

### Phase 6: Order Management (1.5 min)
- [ ] View order history
- [ ] Show order details
- [ ] Demonstrate reorder
- [ ] Show cancel order option

### Phase 7: Database Verification (1 min)
- [ ] Open H2 console
- [ ] Run queries to show data
- [ ] Verify relationships

### Phase 8: Wrap Up (0.5 min)
- [ ] Summarize features
- [ ] Mention documentation
- [ ] Thank viewer

---

## 🎤 Key Talking Points

### Introduction
- "AI Specialist Capstone Project"
- "Full-stack e-commerce application"
- "Spring Boot backend with REST APIs"
- "Responsive web interface"

### Technical Highlights
- "18 REST APIs"
- "6 entity models with JPA"
- "Layered architecture"
- "PostgreSQL and H2 database support"
- "CORS-enabled APIs"

### Features
- "User authentication and profile management"
- "Product catalog with search and filtering"
- "Real-time shopping cart updates"
- "Gift points reward system"
- "Order management with reorder and cancellation"
- "48-hour cancellation policy"

### Best Practices
- "Clean code architecture"
- "Comprehensive documentation"
- "Git version control"
- "Production-ready application"

---

## ⚡ Quick Troubleshooting

### Application Won't Start
```bash
# Check port
netstat -ano | findstr :8080

# Kill if needed
taskkill /F /PID [PID]

# Restart
mvn spring-boot:run "-Dspring-boot.run.profiles=h2"
```

### Page Not Loading
- Clear browser cache (Ctrl + Shift + Delete)
- Hard refresh (Ctrl + F5)
- Check browser console (F12)

### Database Issues
- Verify H2 console URL: http://localhost:8080/h2-console
- Check JDBC URL: jdbc:h2:mem:ebookstore
- Username: sa, Password: (empty)

### API Not Working
- Check application logs in terminal
- Verify CORS is enabled
- Check network tab in browser DevTools

---

## 📊 Project Statistics

- **Total Files**: 50+
- **Lines of Code**: 3000+
- **REST APIs**: 18
- **Entity Models**: 6
- **Web Pages**: 7
- **Documentation Files**: 6

---

## 🎬 Recording Tips

1. **Before Recording**
   - Close unnecessary apps
   - Clear browser history
   - Test microphone
   - Practice once

2. **During Recording**
   - Speak clearly and slowly
   - Show, don't just tell
   - Maintain steady pace
   - Stay enthusiastic

3. **After Recording**
   - Review video
   - Check audio quality
   - Edit if needed
   - Upload and share

---

## 📧 Email Template

```
Subject: E-Bookstore Capstone Project - Demo Video

Dear [Manager Name],

I'm pleased to share my E-Bookstore Capstone Project demo video.

🎥 Demo Video: [Insert Link]
📁 GitHub Repository: [Insert Link]

Project Summary:
✅ Spring Boot backend (18 REST APIs)
✅ Full web interface (7 pages)
✅ PostgreSQL/H2 database
✅ Complete user journey implementation
✅ Comprehensive documentation

Duration: ~10 minutes

The video demonstrates all features including user registration, 
product management, shopping cart, checkout, and order management.

Please let me know if you need any clarification.

Best regards,
[Your Name]
```

---

## ✅ Final Checklist

- [ ] Application running successfully
- [ ] All URLs accessible
- [ ] Test data prepared
- [ ] Recording software ready
- [ ] Microphone tested
- [ ] Script reviewed
- [ ] Browser clean and ready
- [ ] Confident and prepared

---

**You're ready to record! Good luck! 🎬🚀**