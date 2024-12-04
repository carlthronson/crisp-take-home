package personal.carlthronson.crisp.takehome.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class AccountEntryResponse extends BaseResponse {

  @Getter
  @Setter
  private AccountResponse account;

  @Getter
  @Setter
  private String category;

  @Getter
  @Setter
  private BigDecimal amount;
}
