CREATE SCHEMA IF NOT EXISTS financials;
-- Creating the Funds table
CREATE TABLE financials.Funds (
    fund_id SERIAL PRIMARY KEY,
    fund_name VARCHAR(100) NOT NULL,
    fund_type VARCHAR(50),
    inception_date DATE,
    manager VARCHAR(100)
);

-- Creating the Instruments table
CREATE TABLE financials.Instruments (
    instrument_id SERIAL PRIMARY KEY,
    instrument_name VARCHAR(100) NOT NULL,
    instrument_type VARCHAR(50),
    ticker VARCHAR(10),
    sector VARCHAR(50)
);

-- Creating the Allocations table
CREATE TABLE financials.Allocations (
    allocation_id SERIAL PRIMARY KEY,
    fund_id INT NOT NULL,
    instrument_id INT NOT NULL,
    allocation_percentage DECIMAL(5, 2) NOT NULL CHECK (allocation_percentage >= 0 AND allocation_percentage <= 100),
    allocation_date DATE NOT NULL,
    FOREIGN KEY (fund_id) REFERENCES financials.Funds(fund_id),
    FOREIGN KEY (instrument_id) REFERENCES financials.Instruments(instrument_id)
);


-- Sample Indexes
CREATE INDEX idx_fund_name ON financials.Funds(fund_name);
CREATE INDEX idx_instrument_name ON financials.Instruments(instrument_name);


-- Sample Data (Optional)
INSERT INTO financials.Funds (fund_name, fund_type, inception_date, manager) VALUES 
('Global Equity Fund', 'Equity', '2010-01-01', 'Jane Doe'),
('Balanced Fund', 'Balanced', '2015-05-01', 'John Smith');

INSERT INTO financials.Instruments (instrument_name, instrument_type, ticker, sector) VALUES 
('Apple Inc.', 'Stock', 'AAPL', 'Technology'),
('US Treasury Bond', 'Bond', 'USTB', 'Government');

INSERT INTO financials.Allocations (fund_id, instrument_id, allocation_percentage, allocation_date) VALUES 
(1, 1, 30.00, '2024-07-01'),
(1, 2, 70.00, '2024-07-01'),
(2, 2, 50.00, '2024-07-01');