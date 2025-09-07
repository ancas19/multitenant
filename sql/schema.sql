--- Schema databse for clients
CREATE TABLE company (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    domain VARCHAR(255) NOT NULL UNIQUE,
    company_name VARCHAR(255) NOT NULL,
    join_date DATE NOT NULL,
    active VARCHAR NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_company_domain ON company(domain);
CREATE INDEX idx_company_join_date ON company(join_date);

CREATE TABLE database_configuration (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    database_url VARCHAR(500) NOT NULL UNIQUE,
    database_username VARCHAR(100) NOT NULL,
    database_password VARCHAR(200) NOT NULL,
    default_schema VARCHAR(100) DEFAULT 'public',
    company_id UUID NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_database_company FOREIGN KEY (company_id)
        REFERENCES company(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_database_company_id ON database_configuration(company_id);
CREATE INDEX idx_database_url ON database_configuration(database_url);


INSERT INTO company ( domain, company_name, join_date,active, created_by, updated_by, created_at, updated_at)
VALUES
    ('DEFAULT', 'Tenant Default', '2023-01-10','YES', 'SYSTEM', NULL, NOW(), NOW()),
    ('COMPANY_ONE', 'Tenant Company One', '2023-03-15', 'YES', 'SYSTEM', NULL, NOW(), NOW()),
    ('COMPANY_TWO', 'Tenant Company Two', '2023-06-20', 'YES', 'SYSTEM', NULL, NOW(), NOW());


INSERT INTO database_configuration (database_url, database_username, database_password, default_schema, company_id, created_by, updated_by, created_at, updated_at)
VALUES
    ('jdbc:postgresql://localhost:5432/tenant_default', '', '#', 'public', (select  p.id from company p where p.domain='default'), 'SYSTEM', NULL, NOW(), NOW()),
    ('jdbc:postgresql://localhost:5432/tenant_company_one', '#', '#P4ssw0rd', 'public', (select  p.id from company p where p.domain='company_one'), 'SYSTEM', NULL, NOW(), NOW()),
    ('jdbc:postgresql://localhost:5432/tenant_company_two', '#', '#P4ssw0rd', 'public', (select  p.id from company p where p.domain='company_two'), 'SYSTEM', NULL, NOW(), NOW());
-- Schema database for users

CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id UUID REFERENCES roles(id) ON DELETE SET NULL,
    status BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO roles (name, description) VALUES
('ADMIN', 'Full access to the system'),
('USER', 'Can manage their own account');


