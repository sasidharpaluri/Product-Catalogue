# Product Catalogue Service

Production-ready REST API for managing product catalogue with advanced Spring Boot features.

## Features
- Full CRUD operations for Products and Categories
- JPA entity relationships (ManyToOne)
- Soft delete pattern using State enum
- Custom JPA queries and derived query methods
- Centralized exception handling with ControllerAdvisor
- Comprehensive test suite (Unit + Integration tests)
- MySQL database persistence

## Tech Stack
- Spring Boot 3.2
- Spring Data JPA
- MySQL
- JUnit & Mockito
- Lombok
- Maven

## Architecture
- **Controller Layer**: REST endpoints and HTTP handling
- **Service Layer**: Business logic and validation
- **Repository Layer**: Data access with JPA
- **Model Layer**: JPA entities with relationships
- **DTO Layer**: Data transfer objects

## Database Schema
```sql
Product (id, name, description, price, image, type, state, category_id)
Category (id, name, description)
```

## API Endpoints

### Products
- `GET /products` - Get all products
- `GET /products/{id}` - Get product by ID
- `POST /products` - Create new product
- `PUT /products/{id}` - Update product
- `DELETE /products/{id}` - Soft delete product

### Request/Response Examples

**Create Product:**
```json
POST /products
{
  "name": "Laptop",
  "description": "Dell XPS 15",
  "price": 1299.99,
  "category": 1,
  "type": "Electronics",
  "image": "laptop.jpg"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "Dell XPS 15",
  "price": 1299.99,
  "category": 1,
  "categoryType": "Electronics",
  "type": "Electronics",
  "image": "laptop.jpg"
}
```

## Key Implementation Details

### Soft Delete Pattern
Products are never deleted from database. Instead, state is changed to DELETED:
```java
public Boolean deleteProduct(int id) {
    Product product = getProductById(id);
    product.setState(State.DELETED);
    return prodrepo.save(product);
}
```

### JPA Relationships
```java
@Entity
public class Product extends BaseModel {
    @ManyToOne
    private Category category;
    // ... other fields
}
```

### Exception Handling
Centralized error handling with ControllerAdvisor:
```java
@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
```

## Testing
- **Unit Tests**: Service layer logic
- **Repository Tests**: JPA queries and relationships
- **MVC Tests**: Controller endpoints and integration

Run tests:
```bash
mvn test
```

## Setup & Run

1. Clone repository
```bash
git clone https://github.com/sasidharpaluri/Product-Catalogue.git
cd Product-Catalogue
```

2. Configure MySQL in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/productdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

3. Run application
```bash
mvn clean install
mvn spring-boot:run
```

4. Test endpoints
```bash
curl http://localhost:8080/products
```

## Project Structure
```
src/main/java/dev/sasidhar/productcatalogue/
├── Controllers/
│   ├── ProductCatalogueController.java
│   └── ControllerAdvisor.java
├── Service/
│   ├── IProductservice.java
│   └── StorageProductService.java
├── Repositories/
│   ├── ProductRepository.java
│   └── CategoryRepository.java
├── Models/
│   ├── Product.java
│   ├── Category.java
│   ├── BaseModel.java
│   └── State.java
├── DTOs/
│   └── ProductDTO.java
└── Configuration/
    └── RestTemplateConfig.java

src/test/java/
├── Controllers/
│   ├── ProductCatalogueControllerTest.java
│   └── productCatalogueMVCtest.java
└── Repositories/
    ├── ProductRepositoryTest.java
    └── CategoryRepositoryTest.java
```

## Design Patterns Used
- **Repository Pattern**: Data access abstraction
- **DTO Pattern**: Data transfer and separation of concerns
- **Service Layer Pattern**: Business logic encapsulation
- **Soft Delete Pattern**: Data integrity and audit trail

## Future Enhancements
- [ ] Add pagination and sorting
- [ ] Implement search and filtering
- [ ] Add caching with Redis
- [ ] Implement API versioning
- [ ] Add Swagger/OpenAPI documentation
- [ ] Add authentication and authorization

## Author
Sasidhar Paluri
- LinkedIn: [linkedin.com/in/sasidharpaluri](https://linkedin.com/in/sasidharpaluri)
- GitHub: [github.com/sasidharpaluri](https://github.com/sasidharpaluri)
- Email: sasidharpaluri@gmail.com
