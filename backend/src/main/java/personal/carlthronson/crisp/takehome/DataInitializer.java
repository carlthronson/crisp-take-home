package personal.carlthronson.crisp.takehome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import personal.carlthronson.crisp.takehome.entity.AccountTypeEntity;
import personal.carlthronson.crisp.takehome.repo.AccountTypeRepository;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private AccountTypeRepository accountTypeRepository;

  @Override
  public void run(String... args) throws Exception {
    if (accountTypeRepository.count() == 0) {
      AccountTypeEntity accountsPayable = new AccountTypeEntity();
      accountsPayable.setName("Accounts Payable");
      accountTypeRepository.save(accountsPayable);
      AccountTypeEntity accountsReceivable = new AccountTypeEntity();
      accountsReceivable.setName("Accounts Receivable");
      accountTypeRepository.save(accountsReceivable);
      AccountTypeEntity fixedAssets = new AccountTypeEntity();
      fixedAssets.setName("Fixed Assets");
      accountTypeRepository.save(fixedAssets);
      AccountTypeEntity cash = new AccountTypeEntity();
      cash.setName("Cash");
      accountTypeRepository.save(cash);
    }
  }
}
