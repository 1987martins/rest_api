Feature: This feature tests for ClickUp API

  Scenario: Add several new items on ClickUp - TestSpace space
    Given The created space "TestSpace" exists and contains the correct information
    When User creates new Folder with folder name "MyFolder" and confirms that folder exists in space with correct name
    And User creates new List in the Folder with name "MyFirstList"
    And User confirms that created List name is "MyFirstList" and added to correct Folder - "MyFolder"
    And User adds one Comment in created List and checks that comment is added to the List
    And User creates one Task with unique name inside created List
    And User confirms that Task name is correct
    And User adds one Comment in created Task and checks that comment is added to the Task
    Then User removes the Task from the created List


