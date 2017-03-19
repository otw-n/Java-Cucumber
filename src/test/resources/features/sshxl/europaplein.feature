#language: nl
@SSH
Functionaliteit: online berekeningen testen

Achtergrond: Feature: controleer woningen van de woningcorperatie sshxl

Scenario: 1 controleer woningen europaplein
   Gegeven open woningaanbod van sshxl met "Utrecht"
   Als er type Woning is geselecteerd
   Dan wordt fotoweergave geselecteerd
   Dan worden alle resultaten met naam "Europaplein" gefilterd en verstuurd
