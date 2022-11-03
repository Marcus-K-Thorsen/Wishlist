# Wishlist
Mini Projekt Digital Ønskeseddel 2. semester. Skab en webside der kan gemme brugeres ønsker på en liste der kan deles med andre til at se disse ønsker


MySql Databasen ligger ikke i cloud, men lokalt, og tilgås vha. application.properties under resources.

Der skal blot ændres i disse to rækker:

        spring.datasource.username={myUsername}
        spring.datasource.password={myPassword}

Databasen oprettes automatisk når applikation køres.

Vi har desuden lavet et fakeData metode under test der insætter test data i databasens tabeller.
