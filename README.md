Swagger:
http://localhost:8080/swagger-ui.html

Please implement a service that reads in the provided XML and JSON files and exposes the information via a RESTful API.

File structure:
- `products.xml` contains master data for all products
- `prices.json` contains price information for all products

The API should be designed in a way, that it allows the following use cases:
1. List all products with their master data and without prices
2. Show single product with master data and all available prices
3. Show single price for one product and specific unit

You can use external libraries.

Out of scope:
- Persistence of data
- Authentication/Authorization
