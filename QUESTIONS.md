## Questions and assumptions

- A financial account has an ID (numerical with dashes)
- But the id field for the account table in the provided SQL script does not support dashes

I am assuming that the id field in the account table is not intended to be human readable.
I am assuming there should be an additional field to hold the account ID with numbers and dashes that will be visible to the end user.

