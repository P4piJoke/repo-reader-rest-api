
# Repo Reader

This is an api for retrieving data about GitHub users' repositories.


## API Reference

#### Get all repositories

```http
  GET /api/v1/repos/{username}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. GitHub username |




## Deployment

To deploy this project run

```bash
  mvn clean install spring-boot:run
```


## Screenshots
Retrieve all repos
![App Screenshot](https://snipboard.io/X3MwjS.jpg)

Retrieve zero repos
![App Screenshot](https://snipboard.io/vcHpeS.jpg)

Retrieve repos for non exist user
![App Screenshot](https://snipboard.io/UF7L1E.jpg)

Retrieve repos with bad header
![App Screenshot](https://snipboard.io/qNWCBH.jpg)
