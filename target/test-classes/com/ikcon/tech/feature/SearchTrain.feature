Feature: Train Ticket Booking

Background: Login to application
Given User is at IRCTC Landing Page
When User enters the Username "PVaishnav05",Password "Race@race1" and SignIn
Then User should be loggedin with Home page displayed

Scenario Outline: Search a train with required details
Given User is on IRCTC home page
When User searches for a train with following details <From>,<To>,<TicketType>,<Year>,<Month>,<Date>,<TicketClass>
Then Train results should be loaded and displayed

Examples:
|        From       |       To      | TicketType | Year |    Month   | Date | TicketClass     |
|   HYDERABAD DECAN |  VIJAYAWADA   |   LADIES   | 2022 |  December  |   2  | Sleeper (SL)    |
|   TRIVANDRUM CNTL |  SECUNDERABAD |   TATKAL   | 2022 |  August    |  20  | AC 3 Tier (3A)  |
