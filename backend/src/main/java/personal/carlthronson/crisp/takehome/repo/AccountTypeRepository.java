package personal.carlthronson.crisp.takehome.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.crisp.takehome.entity.AccountTypeEntity;

@Repository
@Transactional
public interface AccountTypeRepository extends BaseRepository<AccountTypeEntity> {

  AccountTypeEntity findByType(String accountTypeName);

}
