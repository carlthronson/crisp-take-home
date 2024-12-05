package personal.carlthronson.crisp.takehome.gql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.repo.AccountTypeRepository;

@RestController
@EnableWebMvc
@Transactional
public class AccountType {

  @Autowired
  private AccountTypeRepository accountTypeRepository;

  @QueryMapping(name = "accountTypes")
  public List<String> accountTypes() {
    return this.accountTypeRepository.findAll().stream().map(accountTypeEntity -> {
      return accountTypeEntity.getType();
    }).toList();
  }

}
