## Mise en recette pour windows
Dans cmd en étant connecté comme admin ! (VIP)
- Se connecter à mysql  
`mysql.exe -uroot -p `  
Mot de passe (local) = `root`
- Créer la bdd  
`create database spring_employee;`
- Créer user dans la bdd et lui donner les permissions  
`create user 'springuser'@'%' identified by 'P@ssw0rd1';`  
`grant all on spring_employee.* to 'springuser'@'%';`

Dans le fichier application.properties modifier la varianble en `create`  
`spring.jpa.hibernate.ddl-auto=create`

Démarrer l'application