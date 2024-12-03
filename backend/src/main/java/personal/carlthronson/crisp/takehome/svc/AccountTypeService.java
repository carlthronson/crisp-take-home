package personal.carlthronson.crisp.takehome.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.crisp.takehome.conv.BaseConverter;
import personal.carlthronson.crisp.takehome.entity.AccountTypeEntity;
import personal.carlthronson.crisp.takehome.repo.AccountTypeRepository;
import personal.carlthronson.crisp.takehome.repo.BaseRepository;
import personal.carlthronson.crisp.takehome.response.AccountTypeResponse;

/**
 * Handles business logic for service objects
 * 
 * @author carl
 *
 */
@Service
@Transactional
public class AccountTypeService extends BaseService<AccountTypeEntity, AccountTypeResponse> {

  @Autowired
  AccountTypeRepository repository;

  @Override
  public BaseRepository<AccountTypeEntity> getBaseRepository() {
    return this.repository;
  }

  public AccountTypeService() {
    super(new BaseConverter<AccountTypeEntity, AccountTypeResponse>(AccountTypeResponse.class));
  }
}
