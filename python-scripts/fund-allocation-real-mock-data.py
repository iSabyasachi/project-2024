import random
import datetime

# Define the file name
file_name = "mock_realistic_data.sql"

# Initialize the SQL strings
instruments = "INSERT INTO financials.Instruments (instrument_name, instrument_type, ticker, sector) VALUES\n"
funds = "INSERT INTO financials.Funds (fund_name, fund_type, inception_date, manager) VALUES\n"
allocations = "INSERT INTO financials.Allocations (fund_id, instrument_id, allocation_percentage, allocation_date) VALUES\n"

# Realistic instruments data
instrument_list = [
    ("Apple Inc.", "Stock", "AAPL", "Technology"),
    ("Microsoft Corporation", "Stock", "MSFT", "Technology"),
    ("Amazon.com Inc.", "Stock", "AMZN", "Consumer Discretionary"),
    ("Tesla Inc.", "Stock", "TSLA", "Consumer Discretionary"),
    ("Alphabet Inc.", "Stock", "GOOGL", "Communication Services"),
    ("Johnson & Johnson", "Stock", "JNJ", "Healthcare"),
    ("Procter & Gamble Co.", "Stock", "PG", "Consumer Staples"),
    ("Vanguard Total Bond Market Index Fund", "Bond", "VBTLX", "Bond Market"),
    ("US Treasury Bond", "Bond", "USTB", "Government"),
    ("iShares iBoxx $ Investment Grade Corporate Bond ETF", "Bond", "LQD", "Corporate Bond")
]

# Append instrument values
for i, instrument in enumerate(instrument_list):
    instruments += f"('{instrument[0]}', '{instrument[1]}', '{instrument[2]}', '{instrument[3]}')"
    if i < len(instrument_list) - 1:
        instruments += ",\n"
    else:
        instruments += ";\n"

# Realistic funds data
fund_list = [
    ("Global Equity Fund", "Equity", "2010-01-01", "Jane Doe"),
    ("Balanced Growth Fund", "Balanced", "2012-03-15", "John Smith"),
    ("Tech Innovators Fund", "Equity", "2015-07-22", "Alice Johnson"),
    ("Healthcare Leaders Fund", "Equity", "2018-11-05", "Robert Brown"),
    ("Bond Stability Fund", "Bond", "2019-02-20", "Susan Clark"),
    ("Dividend Achievers Fund", "Equity", "2021-04-12", "Michael Davis"),
    ("Emerging Markets Fund", "Equity", "2017-09-30", "Jessica Wilson"),
    ("Sustainable Future Fund", "Balanced", "2022-01-15", "William Martinez"),
    ("High Yield Bond Fund", "Bond", "2016-06-25", "Patricia Anderson"),
    ("Income Growth Fund", "Equity", "2020-10-05", "Christopher Taylor")
]

# Append fund values
for i, fund in enumerate(fund_list):
    funds += f"('{fund[0]}', '{fund[1]}', '{fund[2]}', '{fund[3]}')"
    if i < len(fund_list) - 1:
        funds += ",\n"
    else:
        funds += ";\n"

# Generate allocations for each fund
allocation_date = "2024-07-01"
for fund_id in range(1, 11):
    allocation_percentage = [random.uniform(5, 30) for _ in range(10)]
    total = sum(allocation_percentage)
    allocation_percentage = [round(p / total * 100, 2) for p in allocation_percentage]

    for i, percentage in enumerate(allocation_percentage):
        instrument_id = i + 1
        allocations += f"({fund_id}, {instrument_id}, {percentage}, '{allocation_date}')"
        if fund_id < 10 or instrument_id < 10:
            allocations += ",\n"
        else:
            allocations += ";\n"

# Write to the SQL file
with open(file_name, 'w') as file:
    file.write(instruments)
    file.write("\n")
    file.write(funds)
    file.write("\n")
    file.write(allocations)

print(f"SQL file '{file_name}' has been created.")