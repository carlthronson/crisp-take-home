package personal.carlthronson.crisp.takehome.gql.resolver;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.AccountEntity;
import personal.carlthronson.crisp.takehome.entity.AccountEntryEntity;
import personal.carlthronson.crisp.takehome.gql.schema.AccountEntryType;
import personal.carlthronson.crisp.takehome.repo.AccountEntryRepository;
import personal.carlthronson.crisp.takehome.repo.AccountRepository;

@RestController
@EnableWebMvc
@Transactional
public class AccountEntry {

  Logger logger = Logger.getLogger(getClass().getName());

  @Autowired
  private AccountEntryRepository accountEntryRepository;

  @Autowired
  private AccountRepository accountRepository;

  @MutationMapping(name = "createEntry")
  public AccountEntryType createEntry(
      @Argument(name = "name") String name,
      @Argument(name = "category") String category,
      @Argument(name = "amount") BigDecimal amount,
      @Argument(name = "account") String accountName) {

    logger.info(String.format("createEntry name: %s category: %s amount: %s account: %s",
        name, category, amount.toString(), accountName));
    AccountEntity account = accountRepository.findByName(accountName);
    if (account == null) {
      throw new IllegalArgumentException(String.format("Account %s is not found", account));
    }
    logger.info("account: " + account.toString());
    AccountEntryEntity accountEntryEntity = new AccountEntryEntity();
    accountEntryEntity.setName(name);
    accountEntryEntity.setCategory(category);
    accountEntryEntity.setAmount(amount);
    accountEntryEntity.setAccount(account);
    logger.info("entity: " + accountEntryEntity.toString());
    accountEntryEntity = accountEntryRepository.save(accountEntryEntity);
    return createAccountEntryResponse(accountEntryEntity);
  }

  @MutationMapping(name = "updateEntry")
  public AccountEntryType updateEntry(
      @Argument(name = "id") Long id,
      @Argument(name = "name") String name,
      @Argument(name = "category") String category,
      @Argument(name = "amount") BigDecimal amount,
      @Argument(name = "account") String accountName) {

    logger.info(String.format("updateEntry name: %s category: %s amount: %s account: %s",
        name, category, amount.toString(), accountName));
    Optional<AccountEntryEntity> accountEntryEntityOptional = accountEntryRepository.findById(id);
    if (accountEntryEntityOptional.isEmpty()) {
      throw new IllegalArgumentException(String.format("Account entry %d is not found", id));
    }
    AccountEntryEntity accountEntryEntity = accountEntryEntityOptional.get();
    if (name != null) {
      accountEntryEntity.setName(name);
    }
    if (category != null) {
      accountEntryEntity.setCategory(category);
    }
    if (amount != null) {
      accountEntryEntity.setAmount(amount);
    }
    if (accountName != null) {
      AccountEntity account = accountRepository.findByName(accountName);
      if (account == null) {
        throw new IllegalArgumentException(String.format("Account %s is not found", accountName));
      }
      accountEntryEntity.setAccount(account);
    }
    accountEntryEntity = accountEntryRepository.save(accountEntryEntity);
    return this.createAccountEntryResponse(accountEntryEntity);
  }

  @QueryMapping(name = "entries")
  public List<AccountEntryType> entries() {
    return this.accountEntryRepository.findAll().stream().map(accountEntity -> {
      return createAccountEntryResponse(accountEntity);
    }).toList();
  }

  @MutationMapping(name = "deleteEntry")
  public AccountEntryType deleteEntry(@Argument(name = "id") Long id) {
    Optional<AccountEntryEntity> accountEntryEntityOptional = accountEntryRepository.findById(id);
    if (accountEntryEntityOptional.isEmpty()) {
      throw new IllegalArgumentException(String.format("Account entry %d is not found", id));
    }
    AccountEntryEntity accountEntryEntity = accountEntryEntityOptional.get();
    this.accountEntryRepository.deleteById(id);
    return this.createAccountEntryResponse(accountEntryEntity);
  }

  private AccountEntryType createAccountEntryResponse(AccountEntryEntity accountEntryEntity) {
    AccountEntryType response = new AccountEntryType();
    response.setId(accountEntryEntity.getId());
    response.setName(accountEntryEntity.getName());
    response.setAmount(accountEntryEntity.getAmount());
    response.setCategory(accountEntryEntity.getCategory());
    response.setAccount(accountEntryEntity.getAccount().getName());
    return response;
  }
}
