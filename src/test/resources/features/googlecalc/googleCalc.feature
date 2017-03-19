#language: nl
@GTest
Functionaliteit: online berekeningen testen

Achtergrond: Feature: insert en check sums made with Google calculator
  Gegeven open google

Scenario: 1 eerste berekening met google
   Als enter "2+2" in searchbox
   Dan I should get result as "4"

Scenario: 2 tweede berekening met google
   Als enter "666+334" in searchbox
   Dan I should get result as "1000"