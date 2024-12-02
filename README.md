# crisp-take-home
A financial accounting application that allows users to track, maintain, and review financial accounts and their entries

# Analysis of the Project

## Part 1. Introduction
The introduction is a very open ended description of a financial application.  The description is vague on purpose.

### Entities
- account
- account type
- account entry

### Technical decisions
- application architecture: separate frontend and backend applications and external relational database
- backend: Spring Boot
- frontend: Javascript SPA framework like React, Angular, Vue or Svelte
- database: PostgrSQL or MySQL (provide instructions for connecting to the DB)

## Part 2. Product MVP Requirements

### API CUD Create, Update Delete entities
- Accounts
- Account entries

### Data
- Account
  - Name
  - Account id (number with dashes)
  - Type
 
- Account Entry
  - Value (positive or negative USD)
  - Name
  - Date

## Part 3. User interview highlights

### Data analysis requirements
- It must be possible to aggregate data by categories and time periods (month, quarter and year)
- An account entry can have some characteristics that makes them more random or less random

### Application sizing and other things that can impact the architecture decisions
- There can be thousands of account entries in one year
- The bank has more than one client machine
- The client machines are cheap and have only 4 GB of RAM and are slow and the IT department gets a lot of tickets about them being slow
- The client machines use Chrome

### General
- The bank has an IT department
- Every account entry can have supporting documentation

# STOP

## Part 4. Database seeds
- There is a SQL script to create the tables and insert some initial data
- It is not required to run this script directly
- It is required to use the structures and data as a starting point for the database
- It is required to provide instructions on how to setup the database

## Part 5. Ouf of scope
- Security
- API versioning
- Deployment/environment considerations beyond running on a dev machine
- Scalability, reliability

## Part 6. Deliverables
- Running code and a passing test suite
- Instructions on how to build and run the code with a fully seeded database
- Documentation of your code for anything that isn’t self-documenting or clear to a fellow developer
- Documentation of any assumptions that you made while interpreting the requirements
- Documentation of the architecture and design of your solution
- Explanation of how your solution could be extended, optimized, or updated to account for other
non-functionals that were not required, e.g. security, API versioning, scalability, reliability, etc.
