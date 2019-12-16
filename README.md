# College_Navigator
Android Application that uses government API to store College's data into local data base and displays it inapp 

College Navigator

Objective: Android Application that uses government API to store College&#39;s data into local data base and displays it in app.

In this android application, a government API containing college information is used as the main source of thedata. The API is formatted in JSON, and using Jackson local libraries my application downloads the information(once) and writes to my database.

DataBase:

For this particular project I decided to use Firebase which is a database on the internet. I had the choice to use mySQL for database but I chose to use firebase since it is a more general and global way to share my data, it doesn&#39;t rely on a device&#39;s storage as much as a local base would.

User Profile Module:

The application has a built-in user login and register system. The User must provide a username, password , email, math SAT Scores and reading SAT Scores when registering and then all of the user&#39;s information is stored into the database. When login in, the username and the password are checked with firebase to make sure both parameters match up. Once matched up, the user may log in.

Search:

This application is able to search by the school&#39;s name and the school&#39;s state, it is able to handle various results such as all the  colleges in a state. This is done by connecting with Firebase and asking for colleges based on one particular parameter. For Firebase there is no such things as &quot;and&quot; and &quot;or&quot;, so if I wanted a college in NY and tuition under 10,000 then I would have to implement my own way of searching.

Add-On Features:

Since I wanted to make this application unique I added features that were not required from the objective. One of the biggest add-ons is the favorites section. A user can search for multiple colleges and tap on the heart button to add it to their favorites arrayList. The arraylist is displayed on the favorites section.  I also added the heart button in multiple places so the user can add the college whenever. Also the heart button can remove a college from the user&#39;s favorites. FAVORITES arraylist is kept in sync with the user&#39;s information.

Another ad-on was a College page on top of the search. In this add on a user is able to tap on a college listView item and a new page comes up, this new page focuses only on that particular college, showing all the information that the data has on it.

Another ad-on was that the user is able to update his/her information. They just need to go to account and tap on the desired field they want to update, this new information is sync with firebase. In the same section a user is able to pick a photo from the gallery and add it to his profile.
