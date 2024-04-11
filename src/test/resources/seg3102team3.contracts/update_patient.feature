Feature: Updating a Patient
    Scenario: Updating a registered patient
        Given the Patient specified by the given ID exists
        And the patient info's language preference matches to a Language value
        And the patient info's gender matches to a Gender value
        And the patient info's address contains 5 comma seperated substrings
        #^ necessary for a complete address
        When the application command updatePatient is invoked
        Then the operation is carried out successfully
        And the patient is updated from the patient information