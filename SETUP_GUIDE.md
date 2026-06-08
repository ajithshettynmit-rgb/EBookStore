# E-Bookstore Backend - Complete Setup Guide

This guide will walk you through setting up and running the E-Bookstore backend application locally.

## Table of Contents
1. [Prerequisites Installation](#prerequisites-installation)
2. [Database Setup](#database-setup)
3. [Application Configuration](#application-configuration)
4. [Building and Running](#building-and-running)
5. [Testing the APIs](#testing-the-apis)
6. [Troubleshooting](#troubleshooting)

---

## Prerequisites Installation

### 1. Install Java Development Kit (JDK) 17

**Windows:**
1. Download JDK 17 from [Oracle](https://www.oracle.com/java/technologies/downloads/#java17) or [Adoptium](https://adoptium.net/)
2. Run the installer
3. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced System Settings
   - Click "Environment Variables"
   - Add new System Variable:
     - Variable name: `JAVA_HOME`
     - Variable value: `C:\Program Files\Java\jdk-17` (adjust path as needed)
   - Add to PATH: `%JAVA_HOME%\bin`

4. Verify installation:
   ```bash
   java -version
   ```
   Expected output: `java version "17.x.x"`

### 2. Install Apache Maven

**Windows:**
1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Add to System Environment Variables:
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\Program Files\Apache\maven`
   - Add to PATH: `%MAVEN_HOME%\bin`

4. Verify installation:
   ```bash
   mvn -version
   ```

### 3. Install PostgreSQL

**Windows:**
1. Download PostgreSQL from [PostgreSQL Downloads](https://www.postgresql.org/download/windows/)
2. Run the installer
3. During installation:
   - Set password for postgres user (remember this!)
   - Default port: 5432
   - Install pgAdmin 4 (recommended for database management)

4. Verify installation:
   - Open pgAdmin 4
   - Connect to PostgreSQL server

---

## Database Setup

### Option 1: Using pgAdmin 4 (GUI)

1. Open pgAdmin 4
2. Connect to PostgreSQL server (localhost)
3. Right-click "Databases" → Create → Database
4. Database name: `ebookstore`
5. Click "Save"

### Option 2: Using psql (Command Line)

1. Open Command Prompt or PowerShell
2. Connect to PostgreSQL:
   ```bash
   psql -U postgres
   ```
3. Enter your postgres password
4. Create database:
   ```sql
   CREATE DATABASE ebookstore;
   ```
5. Verify:
   ```sql
   \l
   ```
6. Exit:
   ```sql
   \q
   ```

### Option 3: Using SQL Script

1. Navigate to project directory
2. Run the provided SQL script:
   ```bash
   psql -U postgres -f database-setup.sql
   ```

---

## Application Configuration

### 1. Update Database Credentials

Edit `src/main/resources/application.properties`:

```properties
# Update these values based on your PostgreSQL setup
spring.datasource.url=jdbc:postgresql://localhost:5432/ebookstore
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD_HERE
```

**Important:** Replace `YOUR_PASSWORD_HERE` with your actual PostgreSQL password.

### 2. Verify Configuration

The application is configured to:
- Run on port 8080
- Auto-create database tables (Hibernate DDL auto-update)
- Show SQL queries in console (for debugging)

---

## Building and Running

### Step 1: Navigate to Project Directory

```bash
cd c:/ApplicationCode/EBookStore
```

### Step 2: Clean and Build

```bash
mvn clean install
```

**Expected Output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
```

**If build fails:**
- Check Java version: `java -version`
- Check Maven version: `mvn -version`
- Ensure all dependencies are downloaded

### Step 3: Run the Application

```bash
mvn spring-boot:run
```

**Expected Output:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.0)

...
Started EbookstoreApplication in X.XXX seconds
```

### Step 4: Verify Application is Running

Open browser and navigate to:
```
http://localhost:8080/api/products
```

You should see an empty array `[]` or a JSON response.

---

## Testing the APIs

### Method 1: Using Postman

1. **Install Postman:**
   - Download from [Postman](https://www.postman.com/downloads/)
   - Install and open

2. **Import Collection:**
   - Click "Import" in Postman
   - Select `EBookstore-API-Collection.postman_collection.json`
   - Collection will be imported with all endpoints

3. **Test Endpoints:**
   - Start with "Register User"
   - Then "Login"
   - Create products
   - Test cart and order operations

### Method 2: Using cURL (Command Line)

**Register a User:**
```bash
curl -X POST http://localhost:8080/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"user@example.com\",\"password\":\"password123\",\"name\":\"John Doe\",\"phone\":\"1234567890\"}"
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"user@example.com\",\"password\":\"password123\"}"
```

**Create a Product:**
```bash
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"title\":\"The Great Gatsby\",\"author\":\"F. Scott Fitzgerald\",\"description\":\"A classic novel\",\"price\":15.99,\"category\":\"Fiction\",\"brand\":\"Penguin\",\"stockQuantity\":100}"
```

**Get All Products:**
```bash
curl http://localhost:8080/api/products
```

### Method 3: Using Browser

For GET requests, simply open in browser:
- `http://localhost:8080/api/products`
- `http://localhost:8080/api/products/1`
- `http://localhost:8080/api/cart?userId=1`

---

## Complete Testing Workflow

### 1. Register and Login
```bash
# Register
POST http://localhost:8080/api/auth/register
Body: {"email":"test@example.com","password":"test123","name":"Test User","phone":"1234567890"}

# Login (note the userId from response)
POST http://localhost:8080/api/auth/login
Body: {"email":"test@example.com","password":"test123"}
```

### 2. Create Products
```bash
POST http://localhost:8080/api/products
Body: {
  "title": "Book Title",
  "author": "Author Name",
  "description": "Book description",
  "price": 19.99,
  "category": "Fiction",
  "brand": "Publisher",
  "stockQuantity": 50
}
```

### 3. Browse Products
```bash
# Get all products
GET http://localhost:8080/api/products

# Filter by category
GET http://localhost:8080/api/products?category=Fiction

# Search
GET http://localhost:8080/api/products?search=gatsby
```

### 4. Add to Cart
```bash
POST http://localhost:8080/api/cart/items?userId=1
Body: {"productId": 1, "quantity": 2}
```

### 5. View Cart
```bash
GET http://localhost:8080/api/cart?userId=1
```

### 6. Create Order
```bash
POST http://localhost:8080/api/orders?userId=1
Body: {
  "deliveryAddress": "123 Main St, City, State 12345",
  "paymentMethod": "CREDIT_CARD",
  "useGiftPoints": 0
}
```

### 7. View Order History
```bash
GET http://localhost:8080/api/orders?userId=1
```

---

## Troubleshooting

### Issue: Port 8080 already in use

**Solution:**
1. Change port in `application.properties`:
   ```properties
   server.port=8081
   ```
2. Or stop the process using port 8080:
   ```bash
   # Windows
   netstat -ano | findstr :8080
   taskkill /PID <PID> /F
   ```

### Issue: Database connection failed

**Symptoms:**
```
java.sql.SQLException: Connection refused
```

**Solutions:**
1. Verify PostgreSQL is running:
   - Open Services (Windows + R → services.msc)
   - Find "postgresql-x64-XX"
   - Ensure it's running

2. Check database credentials in `application.properties`

3. Verify database exists:
   ```bash
   psql -U postgres -c "\l"
   ```

### Issue: Build fails with "package does not exist"

**Solution:**
```bash
mvn clean install -U
```

### Issue: Application starts but APIs return 404

**Solution:**
1. Check application logs for errors
2. Verify base URL: `http://localhost:8080/api/`
3. Ensure controllers are in correct package

### Issue: Hibernate creates tables but data not persisting

**Solution:**
1. Check transaction management
2. Verify `spring.jpa.hibernate.ddl-auto=update`
3. Check database logs in pgAdmin

---

## Stopping the Application

**In the terminal where application is running:**
- Press `Ctrl + C`

**Or kill the process:**
```bash
# Windows
taskkill /F /IM java.exe
```

---

## Next Steps

1. **Populate Sample Data:**
   - Run `database-setup.sql` to add sample products and users

2. **Test All Endpoints:**
   - Use the Postman collection
   - Test each feature thoroughly

3. **Create Pull Request:**
   - Push to GitHub
   - Create PR from feature branch to main

4. **Create Demo Video:**
   - Record application setup
   - Demonstrate API functionality
   - Show database operations

---

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Postman Learning Center](https://learning.postman.com/)

---

## Support

For issues or questions:
1. Check the troubleshooting section
2. Review application logs
3. Verify all prerequisites are installed correctly
4. Check database connectivity

---

**Happy Coding! 🚀**