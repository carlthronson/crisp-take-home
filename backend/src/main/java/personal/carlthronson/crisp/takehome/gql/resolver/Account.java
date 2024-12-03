package personal.carlthronson.crisp.takehome.gql.resolver;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.AccountEntity;
import personal.carlthronson.crisp.takehome.entity.AccountTypeEntity;
import personal.carlthronson.crisp.takehome.gql.schema.AccountType;
import personal.carlthronson.crisp.takehome.repo.AccountRepository;
import personal.carlthronson.crisp.takehome.repo.AccountTypeRepository;

@RestController
@EnableWebMvc
@Transactional
public class Account {

  Logger logger = Logger.getLogger(getClass().getName());

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountTypeRepository accountTypeRepository;

  @MutationMapping(name = "createAccount")
  public AccountType createAccount(@Argument(name = "name") String name, @Argument(name = "label") String label,
      @Argument(name = "accountTypeName") String accountTypeName) {
    logger.info("createAccount");
    AccountTypeEntity accountType = accountTypeRepository.findByName(accountTypeName);
    if (accountType == null) {
      throw new IllegalArgumentException(String.format("Account type %s is not found", accountTypeName));
    }
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setName(name);
    accountEntity.setLabel(label);
    accountEntity.setAccountType(accountType);
    accountEntity = this.accountRepository.save(accountEntity);
    return createAccountResponse(accountEntity);
  }

  @MutationMapping(name = "updateAccount")
  public AccountType updateAccount(@Argument(name = "id") Long id, @Argument(name = "name") String name,
      @Argument(name = "label") String label, @Argument(name = "accountTypeName") String accountTypeName) {

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
    accountEntity = accountRepository.save(accountEntity);
    return createAccountResponse(accountEntity);
  }

  @QueryMapping(name = "accounts")
  public List<AccountType> accounts() {
    return this.accountRepository.findAll().stream().map(accountEntity -> {
      return createAccountResponse(accountEntity);
    }).toList();
  }

  @MutationMapping(name = "deleteAccount")
  public AccountType deleteAccount(@Argument(name = "id") Long id) {
    AccountEntity accountEntity = this.accountRepository.getById(id);
    this.accountRepository.deleteById(id);
    return createAccountResponse(accountEntity);
  }

  private AccountType createAccountResponse(AccountEntity accountEntity) {
    AccountType response = new AccountType();
    response.setId(accountEntity.getId());
    response.setName(accountEntity.getName());
    response.setLabel(accountEntity.getLabel());
    response.setAccountTypeName(accountEntity.getAccountType().getName());
    return response;
  }

}
