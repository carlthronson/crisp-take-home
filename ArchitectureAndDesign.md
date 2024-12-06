# Layers
- The frontend is a standard framework-free react application.
- The backend is a Spring Boot Java application using GraphQL for the client API.
- The database is a postgresql database; but, the backend can be configured to use any RDBMS that supports JPA.

# Frontend

## Main page

### Layout
- There is one column for each account type (there are only 4 account types)
- Within each column there is one box for each account.
- Within each account box there is a list of account entries.
- This UI works okay for a small amount of data which is enough to show that the system works.
- For a real life system there would need to be a different layout

### Buttons
- Create account (automatically places the account into the right account type)
- Update account allows you to modify the name and label
- Delete account allows you to confirm delete or cancel
- Create entry (automatically places the entry into the right account)
- Update entry allows you to modify the name, amount and category
- Delete entry allows you to confirm delete or cancel

### Data access
- Data access is done using the Apollo GraphQL library

# Backend

## Data access
- All data access is done using JPA entities and repositories
- For each entity, there is corresponding controller with a findall endpoint

## GraphQL
- The schema has query fields to get data for each entity type
- The schema also has mutation fields to create, update and delete each entity type
- There is a resolver for each type of entity with all the corresponding mutations
- Each resolver using the entities and repositories to access data

## Configuration
- The application configuration file (application.properties) contains all the configuration

# Database
- The application can be configured to work with any RDBMS that supports JPA
- The database schema is automatically generated the first time the app is started
- There is a data initializer in the backend code that automatically checks for account types and creates them if necessary when the application starts
