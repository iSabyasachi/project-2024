# Constants
P = 100  # Monthly deposit in dollars
n = 12   # Compounding periods per year (monthly)
t = 18   # Number of years

# Annual return rates
rates = [0.04, 0.06, 0.08]  # 4%, 6%, 8%

# Future value formula
def future_value(P, r, n, t):
    return P * ((1 + r / n) ** (n * t) - 1) / (r / n) * (1 + r / n)

# Calculate future values for each rate
results = {f"{int(rate * 100)}% Return": future_value(P, rate, n, t) for rate in rates}

import pandas as pd
results_df = pd.DataFrame(list(results.items()), columns=["Rate of Return", "Future Value"])
results_df["Future Value"] = results_df["Future Value"].apply(lambda x: round(x, 2))

import ace_tools as tools; tools.display_dataframe_to_user(name="529 Plan Future Value Estimates", dataframe=results_df)