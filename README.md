# Teamwork Projects Sample

Sample Android App to list user projects from Teamwork Projects API

This App features a basic master-detail flow.

- The Main Screen: `MainActivity` displays all projects from the user
- When tapping on a Project, it navigates to ProjectDetail
- `ProjectDetailActivity` shows data about the selected project

Installing
----------

Clone this repository and create a file in the project root folder named `teamwork-api.properties`. Paste your Teamwork Api Token on it with this format:

    apiToken=abc12345

where `abc12345` is your Teamwork Api Token. You can see an example in [teamwork-api-sample.properties][1]

Then you can open the project with Android Studio 3.x and launch the App.

This project has been built using Android Studio 3.3 Canary 13

[1]: https://github.com/voghDev/teamwork-sample/blob/master/teamwork-api-sample.properties
