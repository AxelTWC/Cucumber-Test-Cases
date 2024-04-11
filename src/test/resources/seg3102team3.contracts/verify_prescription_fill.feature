Feature: Assessing and Verifying a Prescription Fill
    Scenario: Prescription Fill awaiting verification exists and the assessment is positive
        Given the Prescription Fill specified by the given ID exists
        And the status of the Prescription Fill specified by the given ID is prepared
        And the user account specified by the given ID is registered
        And the user account specified by the given ID is a Pharmacist
        And the assessment was positive
        When application command verifyPrescriptionFill is invoked
        Then the operation is carried out successfully
        And the Prescription Fill's status is set to verified
        And the Prescription Fill's recorded clinical check is set to the one provided by the user
        And the Prescription Fill's clinical check pharmacist ID is set to the given ID

    Scenario: Prescription Fill awaiting verification exists and the assessment is negative
        Given the Prescription Fill specified by the given ID exists
        And the status of the Prescription Fill specified by the given ID is prepared
        And the user account specified by the given ID is registered
        And the user account specified by the given ID is a Pharmacist
        And the assessment was negative
        When application command verifyPrescriptionFill is invoked
        Then the operation is carried out successfully
        And the Prescription Fill's status is set to cancelled
        And the Prescription Fill's recorded clinical check is set to the one provided by the user
        And the Prescription Fill's clinical check pharmacist ID is set to the given ID
