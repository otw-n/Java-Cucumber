$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("sample.feature");
formatter.feature({
  "line": 2,
  "name": "Login Action",
  "description": "",
  "id": "login-action",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@java-sample"
    }
  ]
});
formatter.scenario({
  "line": 5,
  "name": "Successful Login with Valid Credentials",
  "description": "",
  "id": "login-action;successful-login-with-valid-credentials",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@sample-run"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "User is on Home Page2",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "User Navigate to Login Page",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "User enters UserName and Password",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Message displayed Login Successfully",
  "keyword": "Then "
});
formatter.match({
  "location": "GoToPageStepdefs.userIsOnHomePage()"
});
formatter.result({
  "duration": 11461249600,
  "status": "passed"
});
formatter.match({
  "location": "NavigateLoginStepdefs.userNavigateToLogInPage()"
});
formatter.result({
  "duration": 73076,
  "status": "passed"
});
formatter.match({
  "location": "UserPassStepdefs.userEntersUserNameAndPassword()"
});
formatter.result({
  "duration": 48854,
  "status": "passed"
});
formatter.match({
  "location": "MessageDisplayStepdefs.messageDisplayedLoginSuccessfully()"
});
formatter.result({
  "duration": 45570,
  "status": "passed"
});
formatter.uri("sample2.feature");
formatter.feature({
  "line": 2,
  "name": "Login Action 2",
  "description": "",
  "id": "login-action-2",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@java-sample"
    }
  ]
});
formatter.scenario({
  "line": 5,
  "name": "Successful Login with Valid Credentials",
  "description": "",
  "id": "login-action-2;successful-login-with-valid-credentials",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@sample-run"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "User is on Home Page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "User Navigate to Login Page",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "User enters UserName and Password",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Message displayed Login Successfully",
  "keyword": "Then "
});
formatter.match({
  "location": "ChromeTestStepdefs.userIsOnHomePage()"
});
formatter.result({
  "duration": 12269275234,
  "status": "passed"
});
formatter.match({
  "location": "NavigateLoginStepdefs.userNavigateToLogInPage()"
});
formatter.result({
  "duration": 51728,
  "status": "passed"
});
formatter.match({
  "location": "UserPassStepdefs.userEntersUserNameAndPassword()"
});
formatter.result({
  "duration": 41875,
  "status": "passed"
});
formatter.match({
  "location": "MessageDisplayStepdefs.messageDisplayedLoginSuccessfully()"
});
formatter.result({
  "duration": 42285,
  "status": "passed"
});
});