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
formatter.match({
  "location": "Sample2Stepdefs.userIsOnHomePage()"
});
formatter.result({
  "duration": 14281700618,
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
formatter.match({
  "location": "SampleStepdefs.userIsOnHomePage()"
});
formatter.result({
  "duration": 11930639785,
  "status": "passed"
});
});