package personal.carlthronson.crisp.takehome.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.crisp.takehome.entity.AccountEntity;

@Repository
@Transactional
public interface AccountRepository extends BaseRepository<AccountEntity> {

  AccountEntity findByName(String accountName);

}
