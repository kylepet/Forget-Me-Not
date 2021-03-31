# Forget-Me-Not

## Inspiration

Many people are struggling with dementia and Alzheimers and the impact on their families is devastating. Walking into a room to meet a loved on that doesn't know your name is heartbreaking. That's why we made an app to keep Alzheimers in the back of the mind instead of in the foreground. We help patients recognize their family members and friends and help them improve recognition over time.

## What it does

It recognizes and detects faces and matches it with a database of family members and outputs a match. It also allows you to store faces and view critical information when you meet someone. You can view your relationships as well short descriptions of each person to help you remember them.

## How we built it

We used android studio to develop an application for Android. For the facial detection, we used Google's Firebase ML Kit to detect faces and for recognition on the server, we used the Eigenfaces method to compare faces to the collection of saved faces. We also added voice detection for automatic contact addition.

##Challenges we ran into

Unfortunately, our localhost server stopped working sometime during the early morning so we weren't able to contact our server at all. Our face recognition needed our server to work as well as adding new contacts. However, given a little more time and under more ideal conditions, we can make it work.

## Accomplishments that we're proud of

This is the first ML application that we have made and it was really exciting to learn how the models were trained and how facial recognition algorithms worked

## What's next for ForgetMeNot

This app has the potential to be invaluable for patients with memory loss and given more time, we can make a fully automatic system that saves faces and records them so that you can keep these unfortunate mental conditions in the back of the mind instead of in the foreground .

## Built With
Android Studio, android-speech-recognizer, face-recognition-python, firebase, google-fire-mlkit, kaggle, kotlin, opencv, python, tesnorflow
