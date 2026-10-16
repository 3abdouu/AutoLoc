# AutoLoc

Plateforme de gestion de location de véhicules multi-agences — étude de cas du module
**Architecture des Systèmes d'Information** (UP ASI, ESPRIT, 2026-2027).

## Objectifs du projet

- Centraliser la gestion du parc de véhicules de plusieurs agences (disponibilité, tarifs, maintenance).
- Permettre aux clients de réserver un véhicule en ligne et de suivre leurs réservations.
- Outiller les agents d'agence pour la remise et le retour des véhicules, les contrats et les paiements.
- Donner aux responsables d'agence une vue de pilotage (activité, taux d'occupation, chiffre d'affaires).
- Exposer l'ensemble sous forme d'une API REST Spring Boot (`autoloc-api`).

## Acteurs et cas d'utilisation (v0 — Séance 1)

| Acteur | Cas d'utilisation principaux |
|---|---|
| **Client** | S'inscrire, consulter les véhicules disponibles (par agence, catégorie, dates), effectuer une réservation, annuler une réservation, consulter l'historique de ses locations et paiements |
| **Agent d'agence** | Gérer les clients, confirmer une réservation, établir et valider le contrat de location, enregistrer le paiement, effectuer la remise et le retour du véhicule, déclarer un véhicule en maintenance |
| **Responsable d'agence** (Manager) | Gérer le parc de véhicules de l'agence et les tarifs, gérer les équipements, superviser les agents, consulter les statistiques de l'agence |
| **Administrateur** | Gérer les agences, gérer les comptes des employés et leurs rôles, paramétrer la plateforme |

## Structure du dépôt

```
AutoLoc
├── autoloc-api/   API REST Spring Boot (Java 17, Maven, Spring Data JPA, MySQL)
└── docs/          Documentation (preuve d'environnement, ...)
```

## Stack technique

Java 17 · Maven · Spring Boot · Spring Data JPA · MySQL 8 · Lombok · Git/GitHub · Postman · IntelliJ IDEA

## Lancer l'API

Prérequis : JDK 17+, MySQL démarré sur `localhost:3306`.

```bash
cd autoloc-api
# identifiants MySQL (par défaut : root, mot de passe vide)
export DB_USERNAME=root DB_PASSWORD=...
./mvnw spring-boot:run
```

La base `autoloc_db` est créée automatiquement au premier démarrage et les tables sont générées par Hibernate.
