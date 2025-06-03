
Feature: Sample feature for element interaction

  Scenario: Interact with all basic elements
    Given I open the application
    When I enter "example text" into the input with id "textInput"
    And I click the button with id "submitBtn"
    And I select "Option1" from dropdown with id "dropdownMenu"
    And I check the checkbox with id "agreeTerms"
    And I upload file "testfile.txt" into input with id "fileUpload"
    Then I should see text "Success" on the page
