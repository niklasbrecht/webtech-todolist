## Webtech-Projekt WiSe22/23 - To-Do-List ##

### Gruppe ###
Niklas Sebastian Brecht, 582766

Timothy Kumm, 582089

### Frontend-Repo
https://github.com/niklasbrecht/webtech-todolist-frontend

### Projektbeschreibung ###
Das Projekt umfasst eine To-Do-Liste mit Aufgaben, welche man bis zu einer bestimmten Deadline erledigen möchte.

### Funktionen ###
1. `Aufgabe erstellen`
2. `Aufgabe entfernen`
3. `Eigenschaften verändern`
4. `Aufgaben nach Eigenschaften sortieren`
5. `Aufgaben nach Eigenschaften filtern`
6. `Nutzer Login/Registrierung`

### Hinweis ### 
Die Spring-Anwendung verwendet zur Verschlüsselung der JSON-Web-Tokens die Zertifikate im Ordner 'testonlyCerts'. Diese Zertifikate sind nur zur erfolgreichen Testausführung auf Github. Die echten Zertifikate wurden aus Sicherheitsgründen nicht nach Github gepusht. Auch das Heroku-Frontend verwendet diese testonlyCerts, da die Verwendung von Umgebungsvariabeln im Dateiformat bei Heroku zusätzlich Geld kostet.

Bei Zugriff auf das Frontend über Heroku kann es bei Erstausführung zu Problemen kommen, da das Backend erst "geweckt" werden muss. Das liegt daran, dass Heroku die Anwendung nach Inaktivität herunterfährt.


### Wie funktioniert die Anwendung ###
Um die App vollständig zu benutzen, muss erst ein Nutzeraccount angelegt werden. Ist der Account angelegt, kann man sich Ein- und Ausloggen. Im eingeloggten Zustand lassen sich unter dem Reiter "Tasks" einzelne Aufgaben mit einem Titel, Inhalt und der jeweiligen Deadline anlegen, verändern und löschen. Mit einem Klick auf das Datums-Feld lassen sich die Aufgaben nach ihrer Deadline sortieren. Mit einer Eingabe in das Suchfeld oberhalb der Tabelle lassen sich die Tasks nach ihrem Titel durchsuchen. 
