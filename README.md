# Recipe Generator Application

## Overview

This application allows users to generate recipes based on the ingredients available in their refrigerator. It includes features for adjusting recipes based on the number of servings, calories per dish, and dietary or allergy criteria.

## Features

- **Ingredient Management**: Users can input available ingredients and get recipe suggestions.
- **Recipe Adjustment**: Adjust recipes based on the number of servings.
- **Calorie Calculation**: Display estimated calories per serving.
- **Dietary Filters**: Filter recipes based on dietary restrictions and allergies.
- **User Profile Management**: Users can create accounts, save favorite recipes, and manage personal information.
- **Admin Management**: Admins can create, delete, and modify recipes and manage users.

## Technologies

- **Front-end**: Angular
- **Back-end**: Spring Boot
- **Build Tool**: Maven

## Prerequisites

- **Java**: JDK 11 or higher
- **Node.js**: Version 14 or higher
- **Angular CLI**: Version 12 or higher
- **Maven**: Version 3.6 or higher
- **Database**: PostgreSQL (or any other supported database)

## Installation

### Backend Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/emericb/Java-project.git
    cd Java-project
   
    git clone https://github.com/emericb/angular-project.git
    cd angular-project
    ```

2. **Configure the database**:
    - Update the `application-secrets.properties` file in `src/main/resources` with your database configuration:
    ```properties
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. **Build the project**:
    ```sh
    mvn clean install
    ```

4. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```

### Frontend Setup

1. **Navigate to the frontend directory**:

2. **Install dependencies**:
    ```sh
    npm install
    ```

3. **Run the Angular application**:
    ```sh
    ng serve
    ```

## Usage

- **Access the application**:
    - Backend: `http://localhost:8080`
    - Frontend: `http://localhost:4200`

- **API Endpoints**:
    - `GET /api/recipes`: Retrieve all recipes
    - `POST /api/recipes`: Create a new recipe
    - `PUT /api/recipes/{id}`: Update an existing recipe
    - `DELETE /api/recipes/{id}`: Delete a recipe
    - `POST /api/recipes/createWithIngredients`: Create a recipe with ingredients
    - `POST /api/recipes/suggest`: Suggest a recipe based on ingredients provided
    - `PUT /api/recipes/{id}/servings/{servings}`: Update the servings for a recipe and return the calorie count

## Contributing

1. **Fork the repository**.
2. **Create a new branch**:
    ```sh
    git checkout -b feature/your-feature-name
    ```
3. **Commit your changes**:
    ```sh
    git commit -m 'Add some feature'
    ```
4. **Push to the branch**:
    ```sh
    git push origin feature/your-feature-name
    ```
5. **Open a pull request**.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For any inquiries, please contact [emericb](https://github.com/emericb).
