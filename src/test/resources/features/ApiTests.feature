Feature: API Testing with REST-assured
#
#  @API
#  Scenario: Check Google maps
#    When I send a GET request to "https://www.google.com/maps"
#    Then the response status code should be 200
#    And the response content type should be "text/html; charset=UTF-8"
#
#  @API
#  Scenario: Check JSONPlaceholder comments
#    When I send a GET request to "https://jsonplaceholder.typicode.com/comments?postId=1"
#    Then the response status code should be 200
#    And the response body with id 3 should be the following JSON:
#
#  @API
#  Scenario: Check JSONPlaceholder post 1
#    When I send a GET request to "https://jsonplaceholder.typicode.com/posts/1/"
#    Then the response status code should be 200
#    And the response body should be the following JSON:
#    """
#  {
#  "userId": 1,
#  "id": 1,
#  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
#  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
#  }
#    """
#
#  @API
#  Scenario: Check JSONPlaceholder post 2
#    When I send a GET request to "https://jsonplaceholder.typicode.com/posts/2/"
#    Then the response status code should be 200
#    And the response body should be the following Data table:
#      | userId | id | title        | body                                                                                                                                                                                                              |
#      | 1      | 2  | qui est esse | est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla |

  @API
  Scenario:
    When I send a get request to "https://api.americanwagering.com/regions/us/locations/mi/brands/czr/igaming/bonus-engine/api/v1/promotions" with header
    Then the response status code should be 200