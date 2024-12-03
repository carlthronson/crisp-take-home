package personal.carlthronson.crisp.takehome.gql;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.AccountEntity;
import personal.carlthronson.crisp.takehome.entity.AccountEntryEntity;
import personal.carlthronson.crisp.takehome.repo.AccountEntryRepository;
import personal.carlthronson.crisp.takehome.repo.AccountRepository;

@RestController
@EnableWebMvc
@Transactional
public class AccountEntry {

  @Autowired
  private AccountEntryRepository accountEntryRepository;

  @Autowired
  private AccountRepository accountRepository;

  @MutationMapping(name = "createAccountEntry")
  public Long createAccountEntry(@Argument(name = "name") String name, @Argument(name = "label") String label,
      @Argument(name = "amount") BigDecimal amount, @Argument(name = "accountId") Long accountId) {
    AccountEntity account = accountRepository.getById(accountId);
    if (account == null) {
      throw new IllegalArgumentException(String.format("Account %d is not found", accountId));
    }
    AccountEntryEntity accountEntryEntity = new AccountEntryEntity();
    accountEntryEntity.setName(name);
    accountEntryEntity.setLabel(label);
    accountEntryEntity.setAmount(amount);
    accountEntryEntity.setAccount(account);
    return accountEntryRepository.save(accountEntryEntity).getId();
  }

  @MutationMapping(name = "updateAccountEntry")
  public Long updateAccountEntry(@Argument(name = "id") Long id, @Argument(name = "name") String name,
      @Argument(name = "label") String label, @Argument(name = "amount") BigDecimal amount,
      @Argument(name = "accountId") Long accountId) {

    AccountEntryEntity accountEntryEntity = accountEntryRepository.getById(id);
    if (accountEntryEntity == null) {
      throw new IllegalArgumentException(String.format("Account entry %d is not found", id));
    }
    if (name != null) {
      accountEntryEntity.setName(name);
    }
    if (label != null) {
      accountEntryEntity.setLabel(label);
    }
    if (amount != null) {
      accountEntryEntity.setAmount(amount);
    }
    if (accountId != null) {
      AccountEntity account = accountRepository.getById(accountId);
      if (account == null) {
        throw new IllegalArgumentException(String.format("Account %d is not found", accountId));
      }
      accountEntryEntity.setAccount(account);
    }
    return accountEntryRepository.save(accountEntryEntity).getId();
  }

//  @MutationMapping(name = "deleteAccountEntry")
//  public Boolean deleteAccountEntry(@Argument(name = "id") Long id) {
//    this.accountEntryRepository.deleteById(id);
//    return true;
//  }
}
