Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  a valid username "liisa" and password "salainen1" and matching password confirmation are entered
        Then  a new user is created

    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  a too short username "li" and a valid password "salainen1" and matching password confirmation are entered
        Then user is not created and error "username should have at least 3 characters" is reported 