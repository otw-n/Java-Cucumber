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
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
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
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.uri("test.feature");
formatter.feature({
  "line": 2,
  "name": "some simple test to verify content",
  "description": "",
  "id": "some-simple-test-to-verify-content",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "As a website visitor i want to check some posts",
  "description": "",
  "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "a user is surfed to the website \"http://www.otwn.nl\" in Chrome browser",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "the user clicks on menu item \"MAVEN\" in bottom menu",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "the browser title should be \"Maven – otwn\"",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenarioOutline({
  "line": 11,
  "name": "As a website visitor i want to check some posts (with Examples)",
  "description": "",
  "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples)",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 13,
  "name": "a user is surfed to the website \"http://www.otwn.nl\" in \u003cbrowser\u003e browser",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "the user clicks on menu item \"\u003cmenu-item\u003e\" in bottom menu",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "the browser title should be \"\u003cpage-title\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 17,
  "name": "",
  "description": "",
  "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);",
  "rows": [
    {
      "cells": [
        "browser",
        "menu-item",
        "page-title"
      ],
      "line": 18,
      "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);;1"
    },
    {
      "cells": [
        "Chrome",
        "MAVEN",
        "Maven – otwn"
      ],
      "line": 19,
      "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);;2"
    },
    {
      "cells": [
        "Firefox",
        "GIT",
        "GIT – otwn"
      ],
      "line": 20,
      "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);;3"
    },
    {
      "cells": [
        "IE",
        "HOME",
        "otwn – Werkgroep testautomatisering"
      ],
      "line": 21,
      "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);;4"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 19,
  "name": "As a website visitor i want to check some posts (with Examples)",
  "description": "",
  "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 13,
  "name": "a user is surfed to the website \"http://www.otwn.nl\" in Chrome browser",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "the user clicks on menu item \"MAVEN\" in bottom menu",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "the browser title should be \"Maven – otwn\"",
  "matchedColumns": [
    2
  ],
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 20,
  "name": "As a website visitor i want to check some posts (with Examples)",
  "description": "",
  "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 13,
  "name": "a user is surfed to the website \"http://www.otwn.nl\" in Firefox browser",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "the user clicks on menu item \"GIT\" in bottom menu",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "the browser title should be \"GIT – otwn\"",
  "matchedColumns": [
    2
  ],
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 21,
  "name": "As a website visitor i want to check some posts (with Examples)",
  "description": "",
  "id": "some-simple-test-to-verify-content;as-a-website-visitor-i-want-to-check-some-posts-(with-examples);;4",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 13,
  "name": "a user is surfed to the website \"http://www.otwn.nl\" in IE browser",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "the user clicks on menu item \"HOME\" in bottom menu",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "the browser title should be \"otwn – Werkgroep testautomatisering\"",
  "matchedColumns": [
    2
  ],
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 23,
  "name": "As a visitor i want to check some text on the page (with a datatable)",
  "description": "",
  "id": "some-simple-test-to-verify-content;as-a-visitor-i-want-to-check-some-text-on-the-page-(with-a-datatable)",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 25,
  "name": "a user is surfed to the website \"http://www.otwn.nl\" in Chrome browser",
  "keyword": "Given "
});
formatter.step({
  "line": 26,
  "name": "the user clicks on menu item \"MAVEN\" in bottom menu",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "the page should contain the following texts",
  "rows": [
    {
      "cells": [
        "Apache Maven"
      ],
      "line": 28
    },
    {
      "cells": [
        "testen"
      ],
      "line": 29
    },
    {
      "cells": [
        "Bron: https://nl.wikipedia.org/wiki/Apache_Maven"
      ],
      "line": 30
    }
  ],
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});