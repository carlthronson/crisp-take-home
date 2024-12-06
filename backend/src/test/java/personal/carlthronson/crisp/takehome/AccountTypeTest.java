package personal.carlthronson.crisp.takehome;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
class AccountTypeTest {
  @Autowired
  private GraphQlTester graphQlTester;

  @Test
  void testQueryOperation() {
    String query = """
        query {
          accountTypes
        }
        """;
    String expectedValue = """
        ["Accounts Payable","Accounts Receivable","Fixed Assets","Cash"]
        """;

    graphQlTester.document(query).execute().path("accountTypes").matchesJson(expectedValue)
        .path("accountTypes").entityList(String.class).hasSize(4);
  }

}
