-- Enum for order types
CREATE TYPE order_type AS ENUM ('incoming', 'outgoing');

-- Brands
CREATE TABLE brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    contact_email VARCHAR,
    industry VARCHAR,
    created_at TIMESTAMP DEFAULT now()
);

-- SKUs
CREATE TABLE skus (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    sku_code VARCHAR UNIQUE NOT NULL,
    brand_id INTEGER NOT NULL REFERENCES brands(id),
    type VARCHAR,
    mfg_date DATE,
    expiry_date DATE,
    status VARCHAR,
    unit_price NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT now()
);

-- Warehouses
CREATE TABLE warehouses (
    id SERIAL PRIMARY KEY,
    location VARCHAR,
    manager_id INTEGER,
    created_at TIMESTAMP DEFAULT now()
);

-- Employees
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
    contact_info VARCHAR,
    assigned_warehouse_id INTEGER REFERENCES warehouses(id),
    created_at TIMESTAMP DEFAULT now()
);

-- Retailers
CREATE TABLE retailers (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    location VARCHAR,
    contact_person VARCHAR,
    contact_number VARCHAR,
    assigned_salesman_id INTEGER REFERENCES employees(id),
    created_at TIMESTAMP DEFAULT now()
);

-- Orders
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    type order_type NOT NULL,
    retailer_id INTEGER REFERENCES retailers(id),
    salesman_id INTEGER REFERENCES employees(id),
    accountant_id INTEGER REFERENCES employees(id),
    status VARCHAR,
    invoice_number VARCHAR,
    total_amount NUMERIC(10,2),
    payment_status VARCHAR,
    order_date TIMESTAMP DEFAULT now(),
    delivery_date TIMESTAMP
);

-- Order Items
CREATE TABLE order_items (
    order_id INTEGER REFERENCES orders(id),
    sku_id INTEGER REFERENCES skus(id),
    quantity INTEGER NOT NULL,
    price_per_unit NUMERIC(10,2),
    total_price NUMERIC(10,2),
    PRIMARY KEY (order_id, sku_id)
);

-- Inventory
CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,
    sku_id INTEGER REFERENCES skus(id),
    warehouse_id INTEGER REFERENCES warehouses(id),
    quantity_available INTEGER DEFAULT 0,
    quantity_reserved INTEGER DEFAULT 0,
    quantity_damaged INTEGER DEFAULT 0,
    last_updated TIMESTAMP DEFAULT now(),
    UNIQUE (sku_id, warehouse_id)
);

-- Accounts (Payment records)
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(id),
    payment_method VARCHAR,
    amount_paid NUMERIC(10,2),
    payment_date TIMESTAMP,
    transaction_reference VARCHAR,
    status VARCHAR
);

-- Credits (Unpaid dues tracking)
CREATE TABLE credits (
    id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(id),
    credited_amount NUMERIC(10,2),
    due_date DATE,
    settled BOOLEAN DEFAULT FALSE,
    party_type VARCHAR CHECK (party_type IN ('brand', 'retailer'))
);

-- Users for authentication
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);
