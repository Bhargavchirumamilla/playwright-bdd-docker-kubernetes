Feature: Search
  @search
  Scenario Outline: Valid Search
    Given User provide <username> content to search
    Then Top Search Results screen <searchResults>
    And User navigate to player and able play the content
    Examples:
      | username | searchResults |
      | RRR      | 0             |