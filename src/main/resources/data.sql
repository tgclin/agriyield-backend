-- ============================================================================
-- 1. CROP BASELINES TABLE & DATA
-- ============================================================================

CREATE TABLE IF NOT EXISTS crop_baselines (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(100) NOT NULL,
    base_yield DOUBLE PRECISION NOT NULL,
    optimal_temp_min DOUBLE PRECISION,
    optimal_temp_max DOUBLE PRECISION,
    optimal_rainfall_min DOUBLE PRECISION,
    optimal_rainfall_max DOUBLE PRECISION
);

INSERT INTO crop_baselines (name, category, base_yield, optimal_temp_min, optimal_temp_max, optimal_rainfall_min, optimal_rainfall_max)
VALUES
-- Cereals & Grains
('Maize', 'Cereal', 4.5, 18.0, 32.0, 500.0, 800.0),
('Rice', 'Cereal', 3.9, 20.0, 35.0, 1000.0, 1500.0),
('Wheat', 'Cereal', 3.4, 15.0, 25.0, 450.0, 650.0),
('Sorghum', 'Cereal', 2.8, 20.0, 34.0, 400.0, 750.0),
('Pearl Millet', 'Cereal', 1.8, 25.0, 35.0, 250.0, 600.0),
('Barley', 'Cereal', 3.1, 12.0, 22.0, 350.0, 500.0),
('Oats', 'Cereal', 2.6, 10.0, 20.0, 400.0, 600.0),
('Rye', 'Cereal', 2.3, 12.0, 20.0, 300.0, 500.0),
('Fonio', 'Cereal', 0.9, 25.0, 32.0, 600.0, 1000.0),
('Teff', 'Cereal', 1.4, 15.0, 27.0, 450.0, 550.0),
('Quinoa', 'Cereal', 1.6, 15.0, 22.0, 300.0, 500.0),
('Buckwheat', 'Cereal', 1.2, 18.0, 26.0, 400.0, 600.0),

-- Roots & Tubers
('Cassava', 'Tuber', 8.2, 22.0, 32.0, 1000.0, 1500.0),
('Yam', 'Tuber', 12.5, 25.0, 32.0, 1100.0, 1600.0),
('Sweet Potato', 'Tuber', 10.4, 21.0, 29.0, 750.0, 1000.0),
('White Potato', 'Tuber', 18.5, 15.0, 22.0, 500.0, 700.0),
('Taro', 'Tuber', 9.0, 21.0, 27.0, 1500.0, 2000.0),
('Plantain', 'Fruit/Tuber', 14.0, 26.0, 30.0, 1500.0, 2500.0),
('Banana', 'Fruit', 20.0, 26.0, 30.0, 1500.0, 2500.0),

-- Legumes & Pulses
('Soybean', 'Legume', 2.5, 20.0, 30.0, 500.0, 800.0),
('Groundnut', 'Legume', 1.9, 22.0, 30.0, 500.0, 700.0),
('Cowpea', 'Legume', 1.2, 20.0, 35.0, 300.0, 600.0),
('Chickpea', 'Legume', 1.4, 18.0, 26.0, 350.0, 500.0),
('Lentil', 'Legume', 1.1, 15.0, 25.0, 300.0, 450.0),
('Common Bean', 'Legume', 1.6, 18.0, 24.0, 400.0, 600.0),
('Pigeon Pea', 'Legume', 1.5, 20.0, 35.0, 600.0, 1000.0),
('Mung Bean', 'Legume', 1.0, 25.0, 35.0, 600.0, 900.0),
('Bambara Groundnut', 'Legume', 0.8, 20.0, 28.0, 500.0, 800.0),

-- Vegetables & Melon
('Tomato', 'Vegetable', 22.0, 18.0, 27.0, 400.0, 600.0),
('Onion', 'Vegetable', 17.5, 13.0, 24.0, 350.0, 550.0),
('Cabbage', 'Vegetable', 25.0, 15.0, 20.0, 350.0, 500.0),
('Eggplant', 'Vegetable', 16.0, 21.0, 30.0, 500.0, 700.0),
('Okra', 'Vegetable', 6.5, 22.0, 35.0, 500.0, 800.0),
('Carrot', 'Vegetable', 20.0, 16.0, 21.0, 450.0, 650.0),
('Cucumber', 'Vegetable', 18.0, 18.0, 30.0, 400.0, 600.0),
('Watermelon', 'Vegetable', 25.0, 22.0, 32.0, 400.0, 600.0),
('Chili Pepper', 'Vegetable', 8.5, 20.0, 30.0, 600.0, 1000.0),

-- Cash Crops & Industrial
('Cocoa', 'Cash Crop', 0.8, 21.0, 32.0, 1250.0, 2000.0),
('Coffee Arabica', 'Cash Crop', 1.2, 18.0, 22.0, 1200.0, 1800.0),
('Coffee Robusta', 'Cash Crop', 1.5, 22.0, 28.0, 1500.0, 2000.0),
('Oil Palm', 'Cash Crop', 15.0, 24.0, 30.0, 1800.0, 2500.0),
('Sugarcane', 'Cash Crop', 65.0, 20.0, 35.0, 1500.0, 2500.0),
('Cotton', 'Cash Crop', 2.1, 21.0, 30.0, 500.0, 1000.0),
('Rubber', 'Cash Crop', 1.8, 23.0, 32.0, 1800.0, 2500.0),
('Tobacco', 'Cash Crop', 2.2, 20.0, 30.0, 500.0, 800.0),
('Tea', 'Cash Crop', 2.0, 18.0, 25.0, 1500.0, 2500.0),
('Cashew', 'Cash Crop', 1.1, 22.0, 32.0, 800.0, 1600.0),

-- Fruits & Tree Crops
('Pineapple', 'Fruit', 45.0, 22.0, 32.0, 1000.0, 1500.0),
('Mango', 'Fruit', 11.0, 24.0, 30.0, 600.0, 1000.0),
('Citrus Orange', 'Fruit', 18.0, 23.0, 30.0, 900.0, 1200.0),
('Avocado', 'Fruit', 9.5, 16.0, 26.0, 1000.0, 1300.0),
('Papaya', 'Fruit', 30.0, 21.0, 33.0, 1000.0, 1500.0),
('Coconut', 'Fruit', 6.0, 27.0, 32.0, 1300.0, 2300.0)
ON CONFLICT (name) DO NOTHING;

-- ============================================================================
-- 2. REGIONAL CLIMATE TABLE & DATA
-- ============================================================================

CREATE TABLE IF NOT EXISTS regional_climate (
    id BIGSERIAL PRIMARY KEY,
    region_name VARCHAR(255) NOT NULL UNIQUE,
    min_lat DOUBLE PRECISION NOT NULL,
    max_lat DOUBLE PRECISION NOT NULL,
    min_lon DOUBLE PRECISION NOT NULL,
    max_lon DOUBLE PRECISION NOT NULL,
    seasonal_rainfall_mm DOUBLE PRECISION NOT NULL
);

INSERT INTO regional_climate (region_name, min_lat, max_lat, min_lon, max_lon, seasonal_rainfall_mm)
VALUES 
    ('Forest Zone (Ashanti / Kumasi / Western)', 5.8, 7.5, -2.5, -0.5, 1400.0),
    ('Coastal Savanna (Accra / Central / Volta Coast)', 4.5, 6.0, -1.0, 1.0, 850.0),
    ('Northern Savanna (Tamale / Bolgatanga / Wa)', 8.0, 11.5, -3.0, 1.0, 1050.0),
    ('Transition Zone (Sunyani / Techiman / Kintampo)', 7.0, 8.5, -3.0, -0.5, 1250.0)
ON CONFLICT (region_name) DO NOTHING;