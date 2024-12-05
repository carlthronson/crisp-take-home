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
      accountsPayable.setType("Accounts Payable");
      accountTypeRepository.save(accountsPayable);
      AccountTypeEntity accountsReceivable = new AccountTypeEntity();
      accountsReceivable.setType("Accounts Receivable");
      accountTypeRepository.save(accountsReceivable);
      AccountTypeEntity fixedAssets = new AccountTypeEntity();
      fixedAssets.setType("Fixed Assets");
      accountTypeRepository.save(fixedAssets);
      AccountTypeEntity cash = new AccountTypeEntity();
      cash.setType("Cash");
      accountTypeRepository.save(cash);
    }
  }
}
