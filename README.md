# Algemene oefening Programming Advanced: Java

In de les zijn we vertrokken vanuit de  [CallLog Oefening](https://github.com/BartClijsnerPXL/PxlJavaAdvanced-CallLog/tree/multithreading) van het vorige semester Java Advanced.

## JDBC

### Aangepaste en nieuwe classes

 - pom.xml : Dependency toegevoegd voor de MySQL driver.
 - resources: application.properties aangepast en input folder gemaakt.
 - CallLogApp.java: alternatieve constructor gebouwd om data in een database te steken via DAO.
 - CallLogDAO: nieuwe DAO klasse.
 
### Database
 Voor de database hebben we een MySQL database gebruikt, hier heb ik een nieuwe tabel in aangemaakt en als volgt geconfigureerd:
 ![CallLogDB](http://imgur.com/GJ84V47)

### Resultaat
In het volgend voorbeeldje kan u zien dat er succesvol data in de tabel wordt geplaatst nadat de applicatie wordt gerunt.
![CallLog](http://imgur.com/5xaQCEj)



