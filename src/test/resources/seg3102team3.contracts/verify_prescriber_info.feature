Feature: Verifying a Prescriber Identifier
  Scenario: Given identifier matches a known Prescriber's name
    Given the given identifier matches a known Prescriber's name
    When application command verifyPrescriberInfo is invoked
    Then the System returns the ID of the known Prescriber

  Scenario: Given identifier matches a known Prescriber's email
    Given the given identifier matches a known Prescriber's email
    When application command verifyPrescriberInfo is invoked
    Then the System returns the ID of the known Prescriber

  Scenario: Given identifier matches a known Prescriber's license number
    Given the given identifier matches a known Prescriber's license number
    When application command verifyPrescriberInfo is invoked
    Then the system returns the same license number that was given

  Scenario: Given identifier does not match a known Prescriber's name, email or license number
    Given the given identifier does not match a known Prescriber's name, email or license number
    When application command verifyPrescriberInfo is invoked
    Then the System returns null - expected prescriber