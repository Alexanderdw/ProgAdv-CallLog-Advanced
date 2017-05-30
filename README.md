# Algemene oefening Programming Advanced: Java

## Deel 1: JDBC 
In de les zijn we vertrokken vanuit de  [CallLog Oefening](https://github.com/BartClijsnerPXL/PxlJavaAdvanced-CallLog/tree/multithreading) van het vorige semester Java Advanced.

### Aangepaste en nieuwe classes

zie package be.pxl.calllog.app 

 - pom.xml : Dependency toegevoegd voor de MySQL driver.
 - resources: application.properties aangepast en input folder gemaakt.
 - CallLogApp.java: alternatieve constructor gebouwd om data in een database te steken via DAO.
 - CallLogDAO: nieuwe DAO klasse.
 
### Database
 Voor de database hebben we een MySQL database gebruikt, hier heb ik een nieuwe tabel in aangemaakt en als volgt geconfigureerd:
 ![CallLogDB](http://imgur.com/GJ84V47.jpg)

### Resultaat
In het volgend voorbeeldje kan u zien dat er succesvol data in de tabel wordt geplaatst nadat de applicatie wordt gerunt.
![CallLog](http://imgur.com/P5aivWf.jpg)

## Deel 2: Webcomponenten
22/05/2017 hebben we een extra opgave gekregen op blackboard.
CallLog WebApp met Crud

### 1. Search
#### Opzoeken van naam, datum, bedrijf en status. Toon de resultaten in grid waar je per element de calllog kan selecteren en verwijderen
![SearchView](http://imgur.com/oisg3q5.jpg)
![SearchResult](http://imgur.com/P9Ajh0Y.jpg)

### 1a. Details
![Details](http://imgur.com/bGSiPZF.jpg)

### 1b. Delete
![Delete](http://imgur.com/07EXjz5.jpg)

### 2. Create
#### Aanmaken van een nieuwe calllog in de database
![Create](http://imgur.com/j0hlHG1.jpg)
![CreateResult](http://imgur.com/JLymYO4.jpg)
 






