Feature: Verifying a Patient Identifier
  Scenario: Given identifier matches an existing Patient's name
    Given the given identifier matches an existing Patient's name
    When application command verifyPatientInfo is invoked
    Then the System returns the ID of the Patient

  Scenario: Given identifier matches an existing Patient's email
    Given the given identifier matches an existing Patient's email
    When application command verifyPatientInfo is invoked
    Then the System returns the ID of the Patient

  Scenario: Given identifier matches an existing Patient's PHIN
    Given the given identifier matches an existing Patient's PHIN
    When application command verifyPatientInfo is invoked
    Then the system returns the same PHIN that was given

  Scenario: Given identifier does not match an existing Patient's name, email or PHIN
    Given the given identifier does not match an existing Patient's name, email or PHIN
    When application command verifyPatientInfo is invoked
    Then the System returns null - expected patient