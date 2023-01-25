# Tree Service

## How to set up and run service
### 1. W/O Docker
1. You need Java to be installed and env variable `JAVA_HOME` to be set.
2. You need PostgreSQL running with configuration:
   * port: `5432`
   * username: `postgres`
   * password: `password`
3. To start service run command:
```
.\mvnw spring-boot:run
```

### 2. W/ Docker
```
docker-compose up
```
2 containers will be created: `tree-service` and `tree-postgres`. Both will be build and initialized automatically.

### Run tests:
```
.\mvnw test
```

## HTTP API
### 1. Get Tree:
```
curl --location --request GET 'http://localhost:3001/api/tree'
```
Sample response:
```
{
    "id": 1,
    "label": "root",
    "parentId": null,
    "children": [
        {
            "id": 2,
            "label": "dog",
            "parentId": 1,
            "children": []
        },
        {
            "id": 3,
            "label": "cat",
            "parentId": 1,
            "children": []
        }
    ]
}
```

### 2. Add Node:
```
curl --location --request POST 'http://localhost:3001/api/tree' \
--header 'Content-Type: application/json' \
--data-raw '{
    "label": "dog",
    "parent": 1
}'
```
Sample response:
```
{
    "id": 2,
    "label": "dog",
    "parentId": 1,
    "children": null
}
```

### Improvements:
1. Prevent cycles in the tree structure by excluding rows where the id and parent_id are equal
```
ALTER TABLE tree
ADD CONSTRAINT no_cycle_constraint
EXCLUDE USING GIST (id WITH =, parent_id WITH =);
```
2. Use Closure Table for managing a tree structure in PostgreSQL. 
It involves creating a separate table to store the ancestor-descendant relationships between nodes in the tree. 
This solution allows for fast traversals and queries, but it does come with the cost of additional storage and complexity 
in managing the relationship table:
```
CREATE TABLE tree (
    id SERIAL PRIMARY KEY,
    label TEXT NOT NULL
);

CREATE TABLE tree_closure (
    ancestor INTEGER REFERENCES tree(id),
    descendant INTEGER REFERENCES tree(id),
    depth INTEGER NOT NULL,
    PRIMARY KEY (ancestor, descendant)
);
```