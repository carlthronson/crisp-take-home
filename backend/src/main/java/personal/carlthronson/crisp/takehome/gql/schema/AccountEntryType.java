package personal.carlthronson.crisp.takehome.gql.schema;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class AccountEntryType {

  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private BigDecimal amount;

  @Getter
  @Setter
  private String category;

  @Getter
  @Setter
  private String account;
}
