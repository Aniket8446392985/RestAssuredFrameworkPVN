Feature:
  #Scenario:
   # Given user hits the post request
    #Then Response code should be pass
  #Scenario:
   # Given user hits get request
   # When success response received
    #Then first name is string
  Scenario:
    Given User hits Put request
    When success response is received
    Then Last message is validated

