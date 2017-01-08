
Feature: some simple test to verify content

  Scenario: As a website visitor i want to check some posts

    Given a user is surfed to the website "http://www.otwn.nl" in Chrome browser
    When the user clicks on menu item "MAVEN" in bottom menu
    Then the browser title should be "Maven – otwn"


  Scenario Outline: As a website visitor i want to check some posts (with Examples)

    Given a user is surfed to the website "http://www.otwn.nl" in <browser> browser
    When the user clicks on menu item "<menu-item>" in bottom menu
    Then the browser title should be "<page-title>"

    Examples:
      | browser | menu-item | page-title                          |
      | Chrome  | MAVEN     | Maven – otwn                        |
      | Firefox | GIT       | GIT – otwn                          |
      | IE      | HOME      | otwn – Werkgroep testautomatisering |

  Scenario: As a visitor i want to check some text on the page (with a datatable)

    Given a user is surfed to the website "http://www.otwn.nl" in Chrome browser
    When the user clicks on menu item "MAVEN" in bottom menu
    Then the page should contain the following texts
      | Apache Maven                                     |
      | testen                                           |
      | Bron: https://nl.wikipedia.org/wiki/Apache_Maven |