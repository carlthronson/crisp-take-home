package personal.carlthronson.crisp.takehome.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.crisp.takehome.conv.BaseConverter;
import personal.carlthronson.crisp.takehome.entity.AccountEntity;
import personal.carlthronson.crisp.takehome.repo.AccountRepository;
import personal.carlthronson.crisp.takehome.repo.BaseRepository;
import personal.carlthronson.crisp.takehome.response.AccountResponse;

/**
 * Handles business logic for service objects
 * 
 * @author carl
 *
 */
@Service
@Transactional
public class AccountService extends BaseService<AccountEntity, AccountResponse> {

  @Autowired
  AccountRepository repository;

  @Override
  public BaseRepository<AccountEntity> getBaseRepository() {
    return this.repository;
  }

  public AccountService() {
    super(new BaseConverter<AccountEntity, AccountResponse>(AccountResponse.class));
  }
}
