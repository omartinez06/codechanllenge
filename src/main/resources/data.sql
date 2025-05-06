INSERT INTO call_history (endpoint, parameters, response, error, created_at)
VALUES ('/api/calculate', '{"num1": 5, "num2": 3}', '200 OK', NULL, NOW()),
       ('/api/calculate', '{"num1": 10, "num2": 5}', '200 OK', NULL, NOW());
