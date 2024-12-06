

1. Layers
    1. frontend is a react application without a framework like next.js.  Just Node.
    2. backend is a spring boot java application using graphql for the client api.
    3. the database is a postgresql database.

2. frontend react application
    1. There is one main page that displays all the data.
        1. Layout of the user interface
            1. There is one column for each account type (there are only 4 account types)
            2. Within each column there is one box for each account.
            3. Within each account box there is a list of account entries.
            4. This UI works okay for a small amount of data which is enough to show that the system works.
            5. For a real life system there would need to be a different layout
    2. Buttons for actions
        1. Create account (automatically places the account into the right account type)
        2. Update account allows you to modify the name and label
        3. Delete account allows you to confirm delete or cancel
        4. Create entry (automatically places the entry into the right account)
        5. Update entry allows you to modify the name, amount and category
        6. Delete entry allows you to confirm delete or cancel
    3. Data access
        1. Data access uses Apollo GraphQL queries and mutations

3. backend spring boot java application
    1. Almost all of the code is essentially declarative
        1. Declaring the entities and their fields
        2. Declaring the repository interfaces
        3. Declaring the GraphQL schema
        4. Declaring the application configuration
    2. The GQL resolvers have some small amount of logic
        1. When creating an account, first obtain the account type
        2. When creating an entry, first obtain the account
        3. Each GQL mutation follows this general pattern
            1. create or obtain the identified entity
            2. set or update the appropriate fields
            3. save or delete the entity
    3. Performing persistent entity operations
        1. All data persistence is done using JPA repository interfaces

4. database
    1. The database schema is automatically generated the first time the app is started
    2. If there are no account type objects, they are created automatically


