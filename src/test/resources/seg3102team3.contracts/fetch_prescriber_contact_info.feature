Feature: Fetch Prescriber Contact Info.
  Scenario: Fetching a prescriber's contact info
    Given the given prescriber exists
    When application command fetchPrescriberContactInfo is invoked
    Then the System fetches and returns contact information from the Prescriber Registry