Feature: Pick Up Prepared Prescription Fill
    Scenario: Pick up a prescription fill
        Given the Prescription Fill specified by the given ID exists
        And the status of the Prescription Fill specified by the given ID is verified
        And the user account specified by the given ID is registered
        And the user account specified by the given ID is a Pharmacist
        When application command pickUpPrescriptionFill is invoked
        Then the operation is carried out successfully
        And the Prescription Fill's pick-up summary is set to the provided value
        And the Prescription Fill's status is set to retrieved
        And the Prescription Fill's retrieval pharmacist ID is set to the given ID
