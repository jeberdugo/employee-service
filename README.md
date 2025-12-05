# Employee Service

REST and SOAP service for employee management.

## Prerequisites

- Java 21
- Maven 3.6+
- Docker and Docker Compose (for MySQL database)

## Running the Application

### 1. Start MySQL Database

First, start the MySQL database using Docker Compose:

```bash
docker-compose -f docker-compose.mysql.yml up -d
```

This will start a MySQL 8.0 container with:
- Database name: `empleados_db`
- Username: `root`
- Password: `root`
- Port: `3306`

### 2. Run the Application with Local Profile

#### Option 1: Using Maven

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

#### Option 2: Using Java directly

First, build the application:

```bash
mvn clean package
```

Then run with the local profile:

```bash
java -jar -Dspring.profiles.active=local target/employee-service-0.0.1-SNAPSHOT.jar
```

#### Option 3: Using Maven Wrapper (Windows)

```bash
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

#### Option 4: Using Maven Wrapper (Linux/Mac)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### 3. Verify the Application

The application will start on port `8080`. You can verify it's running by accessing:

```
http://localhost:8080/api/employees
```

## API Endpoint

### Create Employee (GET)

```
GET /api/employees?firstName=John&lastName=Doe&documentType=CC&documentNumber=1234567890&birthDate=1990-05-15&hiringDate=2020-01-10&position=Developer&salary=5000.00
```

**Query Parameters:**
- `firstName` (required): Employee first name
- `lastName` (required): Employee last name
- `documentType` (required): Document type
- `documentNumber` (required): Document number
- `birthDate` (required): Birth date in format `yyyy-MM-dd`
- `hiringDate` (required): Hiring date in format `yyyy-MM-dd`
- `position` (required): Employee position
- `salary` (required): Employee salary (decimal)

**Example Response:**

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "documentType": "CC",
  "documentNumber": "1234567890",
  "birthDate": "1990-05-15",
  "hiringDate": "2020-01-10",
  "position": "Developer",
  "salary": 5000.00,
  "currentAge": {
    "years": 34,
    "months": 6,
    "days": 20
  },
  "timeWithCompany": {
    "years": 4,
    "months": 10,
    "days": 25
  }
}
```

## Stopping the Application

To stop the MySQL container:

```bash
docker-compose -f docker-compose.mysql.yml down
```

## Configuration

The local profile configuration is in `src/main/resources/application-local.yml`:

- Database: MySQL on `localhost:3306`
- Database name: `empleados_db`
- Username: `root`
- Password: `root`
- Server port: `8080`
- JPA DDL: `update` (automatically creates/updates tables)

