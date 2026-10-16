# Preuve de l'environnement de développement (Atelier 0)

Vérifié le 16/10/2026 sur Windows 11 Pro.

| Outil | Version | Vérification |
|---|---|---|
| JDK | OpenJDK 17.0.20.1 LTS (Microsoft) | `java -version` |
| Maven | 3.10.0 (Maven Wrapper) | `mvnw -v` |
| IntelliJ IDEA | licence étudiante | annotation processing Lombok activé |
| MySQL | Community Server 8.4.9, service `MySQL84` démarré | `mysql --version` |
| MySQL Workbench | 8.0 CE | — |
| Postman | 12.32 | — |
| Git | 2.55.0 | `git --version` |

## Base de données

```
mysql> SHOW DATABASES;
autoloc_db
information_schema
mysql
performance_schema
sys
```

## Démarrage de l'API (Atelier 1)

```
Started AutolocApiApplication in 2.809 seconds

mysql> SHOW TABLES FROM autoloc_db;
agence
client
contrat
employe
equipement
maintenance
paiement
reservation
vehicule
```
