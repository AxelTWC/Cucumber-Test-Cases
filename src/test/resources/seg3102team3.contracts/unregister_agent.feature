Feature: Unregistering an Agent.
    Scenario: Deactivating an agent account
        Given the user account specified by the given ID is registered
        And the user account specified by the given ID is an Agent
        And the user account specified by the given ID is active
        When application command unregisterAgent is invoked
        Then the operation is carried out successfully
        And the user account is marked as deactivated
