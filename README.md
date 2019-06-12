# 52WeeksChallengeSafHackathon
Small and simple app that helps us project how much we would be able to save over a 52-
week period. The app is written in Java. To set it up, clone the repo and open it on Android Studio.  Ensure that offline mode is off to allow 
for the download of dependencies.

Technologies Used:

RxJava and RxBidding:: used to bind 'starting amount' EditText to ensure reactive change on 
'Deposit' and 'Total' fields depending on the value entered.(RxBinding comes in place of TextWatcher).

Unit Tests Have been written in Robolectric

UI tests are in Espresso 

KenBurnsView: Library Android library that provides an extension to ImageView 
that creates an immersive experience by animating its drawable using the Ken Burns Effect.
Has been used on the splash screen

Butterknife for dependency injection
