# Emiles To-Do List Applikation
Detta är ett fullstack To-Do projekt där vi skapat applikation byggd med Spring Boot för backend och JavaFX för frontend. Backend tillhandahåller ett RESTful API för CRUD-operationer på to-do uppgifter och frontend erbjuder ett dynamiskt användargränssnitt för att skapa, ändra och ta bort uppgifter.

---

## Funktioner

### Backend (API, Spring Boot framework)
**Skapa, Läs, Uppdatera, Radera (CRUD):**
- Backend tillåter användaren att skapa, läsa, uppdatera och radera to-do uppgifter.

**Persistens:**
- To-do uppgifter lagras i en hashmap.

**RESTful API:**
- Backend tillhandahåller ett RESTful-gränssnitt för att kommunicera med frontend.

### Frontend (JavaFX)
**Dynamiskt Användargränssnitt:**
- Frontend visar to-do uppgifterna och tillåter användaren att med ett gränssnitt lägga till, uppdatera och ta bort uppgifter.

**Responsiv:**
- Applikationen uppdateras dynamiskt baserat på användarens input och backend-data.

**Interaktion med API:**
- Frontend kommunicerar med backend genom HTTP-förfrågningar för att hantera to-do uppgifter.

### Krav
- Java 8 eller senare
- Spring Boot (för backend)
- JavaFX (för frontend)
- Maven (för att bygga och köra projektet)

---

## Använda dependencies och versioner
### Backend (todoAPI)
**Spring Boot, version 2.7.7**
- För att skapa och hantera RESTful API:t

**Spring Web, version 5.3.23**
- För HTTP-förfrågningar och API-hantering

#### Exempel på dependencies i ``pom.xml``
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Frontend (todoFE)
**JavaFX, version 17.0.1**
- För att skapa användargränssnittet.

#### Exempel på dependencies i ``pom.xml``
```
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>17.0.1</version>
    </dependency>
    <dependency>
        <groupId>com.jfoenix</groupId>
        <artifactId>jfoenix</artifactId>
        <version>9.0.10</version>
    </dependency>
</dependencies>
```

## Programflöde
### Backend (todoAPI)
#### Backend består av följande komponenter:
**Model:**
- ``Todo``- klassen representerar en to-do uppgift med attribut som ``id``, ``task`` och ``description``.

**Controller:**
- ``TodoController`` hanterar alla CRUD-operationer via HTTP-förfrågningar.

**Services:**
- ``TodoLogic``- innehåller all logik för hantering av to-do uppgifterna.

### Frontend (todoFE)
#### Frontend är byggd med JavaFX och kommunicerar med backend-API:et. De viktigaste komponenterna är:
**FXML Layout:**
- Huvudanvändargränssnittet är byggt med en FXML-fil som inkluderar en lista för att visa uppgifter och knappar för att kommunicera med API:et.

**Controller:**
- JavaFX-controller hanterar användarens åtgärder som att lägga till, uppdatera och ta bort uppgifter.

---

### Hur använder man programmet?
#### Backend (todoAPI)
**1. Starta Backend:**
- Navigera till ``TodoApiApplication`` och tryck på ``Run``
- Alternativt navigera till backend-projektets rotkatalog och kör applikationen med kommandot:
```
mvn clean spring-boot:run
```
**2. Testa API:et:**
- Du kan testa API-endpointsen med verktyg som Postman eller koppla dem till frontend direkt.

#### Frontend (todoFE)
**1. Starta Frontend:**
- Navigera till ``HelloApplication`` och tryck på ``Run``
- Alternativt navigera till frontend-projektets rotkatalog och kör applikationen med Maven:
```
mvn clean javafx:run
```
- JavaFX-applikationen startar och visar användargränssnittet för to-do listan.

**2. Interagera med UI**
- Lägg till en ny uppgift genom att klicka på knappen "Add".
- Uppdatera en befintlig uppgift genom att klicka på en uppgift i listan och välja knappen "Update".
- Ta bort en uppgift genom att klicka på en uppgift i listan och välja knappen "Delete".
- Frontend kommer att kommunicera med backend för att hantera uppgifterna.

### Exempel på användning
#### Starta programmet
1. Starta backend-API:et genom att köra Spring Boot-applikationen.
2. Starta frontend-applikationen med JavaFX.

#### Använd Frontend

- Applikationen visar en lista med uppgifter. Du kan lägga till, uppdatera eller ta bort uppgifter.

**Lägg till en uppgift:**
1. Ange uppgiftens id, namn och beskrivning.
2. Klicka på "Add" för att skicka en förfrågan till backend.
  
**Uppdatera en uppgift:**
1. Välj en uppgift för att redigera den.
2. Modifiera uppgiften och klicka på "Update".

**Ta bort en uppgift:**
1. Klicka på "Delete"-knappen bredvid en uppgift för att ta bort den från listan.

---

## Hur fungerar programmet?

### 1. Backend API
- Backend lyssnar på HTTP-förfrågningar för att hantera uppgifter.
- ``TodoController`` exponerar endpoints för att utföra CRUD-operationer.
- Data lagras i backend

### 2. Frontend Användargränssnitt
- Frontend visar to-do uppgifterna från backend.
- Användare kan lägga till, uppdatera eller ta bort uppgifter genom att använda knapparna och inmatningsfälten.
- JavaFX frontend gör HTTP-förfrågningar till backend för att uppdatera uppgiftslistan.

---

### Bidra
Vill du bidra till det här projektet är det bara att göra en pull requests.





