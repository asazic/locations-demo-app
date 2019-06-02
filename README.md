Locations app with Spring Boot and Angular 

## Requirements

1. Java - 12.x

2. Maven - 3.x.x

3. MongoDB - local database (host: localhost:27017, database: test)

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/asazic/locations-demo-app.git
```

**2. Build and run the backend app using maven**

```bash
cd demo
mvn package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```


**3. Run the frontend app using npm**

```bash
cd client-app
npm install
```

```bash
ng serve --open
```

Frontend server will run on <http://localhost:4200>