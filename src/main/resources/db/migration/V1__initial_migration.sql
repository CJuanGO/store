CREATE TABLE users (
    id BIGINT IDENTITY(1,1) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT PK_users PRIMARY KEY (id)
);

CREATE TABLE addresses (
    id BIGINT IDENTITY(1,1) NOT NULL,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    zip VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT PK_addresses PRIMARY KEY (id),
    CONSTRAINT FK_addresses_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION
);

CREATE TABLE categories (
    id TINYINT IDENTITY(1,1) NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT PK_categories PRIMARY KEY (id)
);

CREATE TABLE products (
    id BIGINT IDENTITY(1,1) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description VARCHAR(MAX) NOT NULL,
    category_id TINYINT NULL,
    CONSTRAINT PK_products PRIMARY KEY (id),
    CONSTRAINT FK_products_categories FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE NO ACTION
);

CREATE TABLE profiles (
    id BIGINT NOT NULL,
    bio VARCHAR(MAX) NULL,
    phone_number VARCHAR(15) NULL,
    date_of_birth DATE NULL,
    loyalty_points INT DEFAULT 0 CHECK (loyalty_points >= 0),
    CONSTRAINT PK_profiles PRIMARY KEY (id),
    CONSTRAINT FK_profiles_users FOREIGN KEY (id) REFERENCES users (id) ON DELETE NO ACTION
);

CREATE TABLE wishlist (
    product_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT PK_wishlist PRIMARY KEY (product_id, user_id),
    CONSTRAINT FK_wishlist_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT FK_wishlist_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION
);

-- Índices
CREATE INDEX IX_addresses_user_id ON addresses (user_id);
CREATE INDEX IX_products_category_id ON products (category_id);
CREATE INDEX IX_wishlist_user_id ON wishlist (user_id);
