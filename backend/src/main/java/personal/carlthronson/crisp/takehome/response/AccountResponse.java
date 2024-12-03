package personal.carlthronson.crisp.takehome.response;

import lombok.Getter;
import lombok.Setter;

public class AccountResponse extends BaseResponse {

  @Getter
  @Setter
  private AccountTypeResponse accountType;
}
