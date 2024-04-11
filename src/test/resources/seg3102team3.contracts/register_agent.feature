Feature: Registering an Agent.
    Scenario: Creating and saving a new agent
        Given the agent info's language preference matches to a Language value
        And the agent info's gender matches to a Gender value
        When application command registerAgent is invoked
        Then a new user account is created
        And the new user account is initialized from the agent information