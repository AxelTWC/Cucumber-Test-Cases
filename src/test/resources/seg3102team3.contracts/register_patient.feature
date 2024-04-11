Feature: Registering a Patient.
    Scenario: Creating and saving a new patient
        Given the patient info's language preference matches to a Language value
        And the patient info's gender matches to a Gender value
        And the patient info's address contains 5 comma seperated substrings
        #^ necessary for a complete address
        When application command registerPatient is invoked
        Then a new patient is created
        And the new patient is initialized from the patient information