Feature: X formerly known as twitter account creation and tweeting

Background: 
Given user opens x on thier browser


Scenario: New Account Creation
When user clicks on new account creation option
Then user fillup the account details
And user clicks on create account icon

@post
Scenario: User login and share a tweet
When user clicks on sign in option
Then user enters the username and password
Then user creates a new tweet
And user shares the tweet on their feeds

