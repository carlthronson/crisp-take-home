package personal.carlthronson.crisp.takehome.gql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.AccountEntity;
import personal.carlthronson.crisp.takehome.entity.AccountTypeEntity;
import personal.carlthronson.crisp.takehome.repo.AccountRepository;
import personal.carlthronson.crisp.takehome.repo.AccountTypeRepository;

@RestController
@EnableWebMvc
@Transactional
public class Account {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountTypeRepository accountTypeRepository;

  @MutationMapping(name = "createAccount")
  public Long createAccount(
      @Argument(name = "name") String name,
      @Argument(name = "label") String label,
      @Argument(name = "accountTypeName") String accountTypeName) {
    AccountTypeEntity accountType = accountTypeRepository.findByName(accountTypeName);
    if (accountType == null) {
      throw new IllegalArgumentException(String.format("Account type %s is not found", accountTypeName));
    }
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setName(name);
    accountEntity.setLabel(label);
    accountEntity.setAccountType(accountType);
    return accountRepository.save(accountEntity).getId();
  }

  @MutationMapping(name = "updateAccount")
  public Long updateAccount(
      @Argument(name = "id") Long id,
      @Argument(name = "name") String name,
      @Argument(name = "label") String label,
      @Argument(name = "accountTypeName") String accountTypeName) {
    
    AccountEntity accountEntity = accountRepository.getById(id);
    if (accountEntity == null) {
      throw new IllegalArgumentException(String.format("Account %d is not found", id));
    }
    if (name != null) {
      accountEntity.setName(name);
    }
    if (label != null) {
      accountEntity.setLabel(label);
    }
    if (accountTypeName != null) {
      AccountTypeEntity accountType = accountTypeRepository.findByName(accountTypeName);
      if (accountType == null) {
        throw new IllegalArgumentException(String.format("Account type %s is not found", accountTypeName));
      }
      accountEntity.setAccountType(accountType);
    }
    return accountRepository.save(accountEntity).getId();
  }

//  @MutationMapping(name = "deleteAccount")
//  public Boolean deleteAccount(@Argument(name = "id") Long id) {
//    this.accountRepository.deleteById(id);
//    return true;
//  }
}
