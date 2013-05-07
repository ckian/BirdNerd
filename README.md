![A Silly Owl With Glasses on its face](/res/drawable-hdpi/logo.png "Hoo Dat?")



BirdNerd
========
BirdNerd is an Android app which is used to help users identify and learn more about various birds.  


The best way to get this code base working, is to install Android SDK + Eclipse at http://developer.android.com/sdk/index.html.  Next, pull the code repo from git, and import to root folder in Eclipse.

Once you can view the code, you need to create an Android Virtual Device to run the app.  To do this, in Eclipse >> Window >> Android Vertual Device Manager.  From this window, click New.  Next, enter in any name for the device you would like.  
Device: Nexus S
Target: Android 4.2
SD Card Size: 40mb (this is just a precaution)
Then click ok.

Next step is to Right-Click on the project's root folder in the Project Explorer tab and select Run As >> Run Configurations...
Click the Target tab, and check the box corresponding to your newly created Android Virtual Device.  Click Apply and Run to begin.

A couple side-notes: 
First, sometimes the App has trouble compiling (this may be due to my slow computer).  So, to combat this you must set the connection time-out to a higher value.
To do this, go to Window >> Preferences >> Android >> DDMS and change the value at ADB Connection Time Out to 15000 or more.

Second, if the App still doesn't compile, close the Android Virtual Device, go to Project >> Clean... and run again.
This may take more than one try (I've gotten to 5 before success), so be patient, and grab a soda to pass the time.



There are 4 views to the project: Splash, MainActivty, QueryResult, and SingleListItem

Splash:  Just a fancy splash page.  Not much to see here.

MainActivity:  The heart and soul of the App.  From this view, users are able to select different attributes about the bird based off what they want to search.  The spinners (drop-downs) are filled with defining attributes populated from the SQLite database which holds all of the data.  This makes it dynamic and easily maintainable/extendable.  Once finished entering traits, users can hit submit to move on, or reset to.... reset?

QueryResult:  The fun part of the App.  This is where you get to see which birds fit your criteria.  Users are able to scroll through all the birds which match, and can easily see the corresponding image to find the right match.  NOTE: If no birds are populated after your search, it may be due to search criteria which do not fit any birds (There are no Very-Small Hawk-Shaped Pink-Feathered with Green Iris birds!).  Simply press the back button on the device, and try again.

SingleListItem : The part for the BirdNerds out there.  Here is what half the App comes down to, knowing your stuff.  This page will populate unique information on the selected bird.  Once finished, navigate to the bottom of the page to start a whole new search (the fun never ends).
