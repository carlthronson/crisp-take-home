package personal.carlthronson.crisp.takehome.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.crisp.takehome.conv.BaseConverter;
import personal.carlthronson.crisp.takehome.entity.AccountEntryEntity;
import personal.carlthronson.crisp.takehome.repo.BaseRepository;
import personal.carlthronson.crisp.takehome.repo.AccountEntryRepository;
import personal.carlthronson.crisp.takehome.response.AccountEntryResponse;

/**
 * Handles business logic for service objects
 * 
 * @author carl
 *
 */
@Service
@Transactional
public class AccountEntryService extends BaseService<AccountEntryEntity, AccountEntryResponse> {

  @Autowired
  AccountEntryRepository repository;

  @Override
  public BaseRepository<AccountEntryEntity> getBaseRepository() {
    return this.repository;
  }

  public AccountEntryService() {
    super(new BaseConverter<AccountEntryEntity, AccountEntryResponse>(AccountEntryResponse.class));
  }
}
