## Mise en recette pour windows
Dans cmd en étant connecté comme admin ! (VIP)
- Se connecter à mysql  
`mysql.exe -uroot -p `  
Mot de passe (local) = `root`
- Créer la bdd  
`create database spring_colomb;`
- Créer user dans la bdd et lui donner les permissions  
`create user 'springuser'@'%' identified by 'P@ssw0rd1';`  
`grant all on spring_colomb.* to 'springuser'@'%';`

Dans le fichier application.properties modifier la varianble en `create`  
`spring.jpa.hibernate.ddl-auto=create`

Démarrer l'application

Une fois terminé remettre à `none` cette variable  
`spring.jpa.hibernate.ddl-auto=none`

### Accéder à la page de swagger
Remplacer "localhost" par le nom du site et changer aussi le port "8080" si nécessaire.  
http://localhost:8080/swagger-ui/index.html#/

### Créer roles en base sur la table roles

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_COMPANY');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

### Créer user

POST http://localhost:8080/api/auth/signup
Body (raw): 
{
"username": "test",
"email": "test@mail.fr",
"password": "test@123",
"role": ["user"]
}

### Créer entreprise

POST http://localhost:8080/api/auth/signup
Body (raw):
{
"username": "test",
"email": "test@mail.fr",
"password": "test@123",
"role": ["com"]
}

### Créer admin

POST http://localhost:8080/api/auth/signup
Body (raw):
{
"username": "test",
"email": "test@mail.fr",
"password": "test@123",
"role": ["admin"]
}

### Se connecter avec le compte

POST http://localhost:8080/api/auth/signin
Body (raw):
{
"username": "test",
"password": "test@123"
}

### Get user
http://localhost:8080/api/test/user

### Get admin
http://localhost:8080/api/test/admin

### Get company
http://localhost:8080/api/test/com


