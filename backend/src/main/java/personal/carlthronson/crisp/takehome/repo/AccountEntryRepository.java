package personal.carlthronson.crisp.takehome.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.crisp.takehome.entity.AccountEntryEntity;

@Repository
@Transactional
public interface AccountEntryRepository extends BaseRepository<AccountEntryEntity> {

}
