# Online Voting System

This project is an online voting system developed using Spring Boot for the backend and Vue.js for the frontend.

## Requirements

- Java 17 or higher
- Node.js and npm
- MySQL database

## Installation and Setup

### Backend Setup

1. Clone the repository:
   ```
   git clone https://github.com/Du411/online-voting-system.git
   ```

2. Navigate to the backend directory:
   ```
   cd online-voting-system
   ```

3. Copy the example properties file:
   ```
   copy src\main\resources\application.properties.example src\main\resources\application.properties
   ```

4. Open `src\main\resources\application.properties` and update the database username and password.

5. Execute the SQL script to create the database:
   - Open MySQL Workbench or your preferred MySQL client
   - Open the schema.sql file located in the `DB` folder
   - Run the script to create the database and necessary tables

6. Start the backend server on 8080 port:
   - On Windows:
     ```
     .\mvnw.cmd spring-boot:run
     ```
   - On macOS/Linux:
     ```
     ./mvnw spring-boot:run
     ```

### Frontend Setup

1. Navigate to the frontend directory:
   ```
   cd frontend
   ```

2. Install dependencies:
   ```
   npm install
   ```

3. Start the Vue.js development server on 43221 port:
   ```
   npm run serve
   ```

## Usage

1. Ensure both backend and frontend servers are running.
2. Open your web browser and go to `http://localhost:8080`.
3. You can now use the online voting system.
