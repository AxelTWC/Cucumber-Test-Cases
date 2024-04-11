Feature: Create Prescription.
    Scenario: Creating and saving a new prescription
        Given the prescription info's patient exists
        And the prescription info's prescriber exists
        And the prescription info's drug exists
        And the prescription info's admin method matches to an AdministrationMethod value
        And the prescription info's refillable status matches to a RefillableStatus value
        When application command createPrescription is invoked
        Then a new prescription is created
        And the new prescription is initialized from the prescription information
        And the new prescription's patient is set to the provided patient
        And the new prescription is added to the patient's prescriptions