import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import joblib

# 1. Load the spreadsheet data
data = pd.read_csv("yield_df.csv")

# 2. Pick the weather features we want to find patterns in
# (We filter down to just numerical weather columns for simplicity)
X = data[['Average Rain (mm)', 'Avg Temp (°C)']] 
y = data['Yield (hg/ha)']

# 3. Split data into training data (80%) and testing data (20%)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 4. Train the model to find the pattern (Linear Regression)
model = LinearRegression()
model.fit(X_train, y_train)

# 5. Check how smart our model is
accuracy = model.score(X_test, y_test)
print(f"Model Pattern Recognition Accuracy: {accuracy * 100:.2f}%")

# 6. Save this mathematical pattern so our Java backend can use it later!
joblib.dump(model, "crop_yield_model.pkl")