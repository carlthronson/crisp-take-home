package personal.carlthronson.crisp.takehome.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import personal.carlthronson.crisp.takehome.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<ENTITY extends BaseEntity> extends JpaRepository<ENTITY, Long>, CrudRepository<ENTITY, Long> {

  @Override
  @Modifying
  @Query("UPDATE #{#entityName} e SET e.deletedAt = CURRENT_TIMESTAMP WHERE e.id = :id")
  void deleteById(@Param("id") Long id);

  @Override
  @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
  List<ENTITY> findAll();

  @Override
  @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
  Page<ENTITY> findAll(Pageable pageable);
}
