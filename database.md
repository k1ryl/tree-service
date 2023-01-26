PostgreSQL initialization script (schema) could be found by path: `./src/main/resources/postgres/init.sql`

### Add node query:
```sql
INSERT INTO tree (parent_id, label)
VALUES (:parentId, :label);
```

### Get tree query:
```sql
WITH RECURSIVE tree_hierarchy(id, parent_id, label) AS (
    SELECT id, parent_id, label
    FROM tree
    WHERE label = 'root'
    UNION ALL
    SELECT t.id, t.parent_id, t.label
    FROM tree_hierarchy th
             JOIN tree t ON th.id = t.parent_id
)
SELECT * FROM tree_hierarchy;
```

### Possible improvements:
To optimize this schema, one option would be to add an index on the parent_id column to improve the performance of the
recursive query. Another option would be to denormalize the data and add a nested set model to the tree table, which
would allow for faster queries and updates on tree structure. Additionally, using a caching system such as Redis to
store frequently accessed tree data can also improve performance.