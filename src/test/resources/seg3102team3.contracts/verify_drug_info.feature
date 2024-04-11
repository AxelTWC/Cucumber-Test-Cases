Feature: Verifying a Drug Identifier
  Scenario: Given identifier matches a known Drug's name
    Given the given identifier matches a known Drug's name
    When application command verifyDrugInfo is invoked
    Then the System returns the ID of the known Drug

  Scenario: Given identifier matches a known Drug's ATC
    Given the given identifier matches a known Drug's ATC
    When application command verifyDrugInfo is invoked
    Then the System returns the ID of the known Drug

  Scenario: Given identifier matches a known Drug's DIN
    Given the given identifier matches a known Drug's DIN
    When application command verifyDrugInfo is invoked
    Then the system returns the same DIN that was given

  Scenario: Given identifier does not match a known Drug's name, ATC or DIN
    Given the given identifier does not match a known Drug's name, ATC or DIN
    When application command verifyDrugInfo is invoked
    Then the System returns null - expected drug