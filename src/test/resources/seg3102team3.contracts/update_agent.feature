Feature: Updating One's Own Information as an Agent.
    Scenario: Agent is registered
        Given the user account specified by the given ID is registered
        And the user account specified by the given ID is an Agent
        And the agent info's language preference matches to a Language value
        And the agent info's gender matches to a Gender value
        When the application command updateAgent is invoked
        Then the operation is carried out successfully
        And the user account's UserRole remains the same as before
        And the user account is otherwise updated from the agent information
