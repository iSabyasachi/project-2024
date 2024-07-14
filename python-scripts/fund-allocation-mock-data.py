# Define the file name
file_name = "mock_data.sql"

# Initialize the SQL strings
instruments = "INSERT INTO financials.Instruments (instrument_name, instrument_type, ticker, sector) VALUES\n"
funds = "INSERT INTO financials.Funds (fund_name, fund_type, inception_date, manager) VALUES\n"
allocations = "INSERT INTO financials.Allocations (fund_id, instrument_id, allocation_percentage, allocation_date) VALUES\n"

# Generate 10 instruments
instrument_list = [
    ("Instrument 1", "Stock", "TCK1", "Sector 1"),
    ("Instrument 2", "Stock", "TCK2", "Sector 2"),
    ("Instrument 3", "Stock", "TCK3", "Sector 3"),
    ("Instrument 4", "Stock", "TCK4", "Sector 4"),
    ("Instrument 5", "Stock", "TCK5", "Sector 5"),
    ("Instrument 6", "Bond", "TCK6", "Sector 6"),
    ("Instrument 7", "Bond", "TCK7", "Sector 7"),
    ("Instrument 8", "Bond", "TCK8", "Sector 8"),
    ("Instrument 9", "Bond", "TCK9", "Sector 9"),
    ("Instrument 10", "Bond", "TCK10", "Sector 10")
]

# Append instrument values
for i, instrument in enumerate(instrument_list):
    instruments += f"('{instrument[0]}', '{instrument[1]}', '{instrument[2]}', '{instrument[3]}')"
    if i < len(instrument_list) - 1:
        instruments += ",\n"
    else:
        instruments += ";\n"

# Generate 1000 funds
for i in range(1, 1001):
    funds += f"('Fund {i}', 'Equity', '2020-01-{i % 30 + 1:02}', 'Manager {i}')"
    if i < 1000:
        funds += ",\n"
    else:
        funds += ";\n"

# Generate allocations for each fund
for i in range(1, 1001):
    for j in range(1, 11):
        allocations += f"({i}, {j}, 10.00, '2024-07-01')"
        if i < 1000 or j < 10:
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