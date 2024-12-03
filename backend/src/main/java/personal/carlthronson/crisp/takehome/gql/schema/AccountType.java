package personal.carlthronson.crisp.takehome.gql.schema;

import lombok.Getter;
import lombok.Setter;

public class AccountType {

  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String label;

  @Getter
  @Setter
  private String accountTypeName;
}
