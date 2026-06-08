-- E-Bookstore Database Setup Script
-- Run this script in PostgreSQL to set up the database

-- Create database (run this as postgres superuser)
CREATE DATABASE ebookstore;

-- Connect to the ebookstore database
\c ebookstore;

-- The tables will be automatically created by Hibernate when you run the application
-- with spring.jpa.hibernate.ddl-auto=update

-- Optional: Create some sample data after the application has created the tables

-- Sample Users (passwords are plain text for demo - should be hashed in production)
INSERT INTO users (email, password, name, phone, gift_points, created_at) VALUES
('john.doe@example.com', 'password123', 'John Doe', '1234567890', 100, NOW()),
('jane.smith@example.com', 'password123', 'Jane Smith', '0987654321', 50, NOW()),
('admin@ebookstore.com', 'admin123', 'Admin User', '5555555555', 0, NOW());

-- Sample Products
INSERT INTO products (title, author, description, price, category, brand, stock_quantity, image_url, created_at) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 'A classic American novel set in the Jazz Age', 15.99, 'Fiction', 'Penguin Classics', 100, 'https://example.com/gatsby.jpg', NOW()),
('To Kill a Mockingbird', 'Harper Lee', 'A gripping tale of racial injustice and childhood innocence', 14.99, 'Fiction', 'Harper Perennial', 85, 'https://example.com/mockingbird.jpg', NOW()),
('1984', 'George Orwell', 'A dystopian social science fiction novel', 13.99, 'Science Fiction', 'Signet Classic', 120, 'https://example.com/1984.jpg', NOW()),
('Pride and Prejudice', 'Jane Austen', 'A romantic novel of manners', 12.99, 'Romance', 'Penguin Classics', 95, 'https://example.com/pride.jpg', NOW()),
('The Hobbit', 'J.R.R. Tolkien', 'A fantasy novel and children''s book', 16.99, 'Fantasy', 'Mariner Books', 110, 'https://example.com/hobbit.jpg', NOW()),
('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'The first novel in the Harry Potter series', 18.99, 'Fantasy', 'Scholastic', 200, 'https://example.com/harry-potter.jpg', NOW()),
('The Catcher in the Rye', 'J.D. Salinger', 'A story about teenage rebellion and alienation', 14.99, 'Fiction', 'Little, Brown and Company', 75, 'https://example.com/catcher.jpg', NOW()),
('Brave New World', 'Aldous Huxley', 'A dystopian novel set in a futuristic World State', 13.99, 'Science Fiction', 'Harper Perennial', 90, 'https://example.com/brave-new-world.jpg', NOW()),
('The Lord of the Rings', 'J.R.R. Tolkien', 'An epic high-fantasy novel', 29.99, 'Fantasy', 'Mariner Books', 80, 'https://example.com/lotr.jpg', NOW()),
('Animal Farm', 'George Orwell', 'An allegorical novella about Soviet totalitarianism', 11.99, 'Fiction', 'Signet Classic', 105, 'https://example.com/animal-farm.jpg', NOW());

-- Verify data
SELECT 'Users created:' as info, COUNT(*) as count FROM users;
SELECT 'Products created:' as info, COUNT(*) as count FROM products;

-- Display sample data
SELECT * FROM users;
SELECT * FROM products;

-- Made with Bob
