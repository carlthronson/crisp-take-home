package personal.carlthronson.crisp.takehome.svc;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import personal.carlthronson.crisp.takehome.conv.BaseConverter;
import personal.carlthronson.crisp.takehome.entity.BaseEntity;
import personal.carlthronson.crisp.takehome.repo.BaseRepository;
import personal.carlthronson.crisp.takehome.response.BaseResponse;

public abstract class BaseService<ENTITY extends BaseEntity, RESPONSE extends BaseResponse> {

  Logger logger = Logger.getLogger(getClass().getName());

  private BaseConverter<ENTITY, RESPONSE> converter;

  public BaseService(BaseConverter<ENTITY, RESPONSE> converter) {
    this.converter = converter;
  }

  protected RESPONSE createResponse(ENTITY entity) {
    return this.converter.convertToResponse(entity);
  }

  public abstract BaseRepository<ENTITY> getBaseRepository();

  public Boolean delete(Long id) {
    getBaseRepository().deleteById(id);
    return true;
  }

  public RESPONSE getById(Long id) {
    Optional<ENTITY> optional = getBaseRepository().findById(id);
    return this.createResponse(optional.isPresent() ? optional.get() : null);
  }

  public RESPONSE findById(Long id) {
    ENTITY entity = getBaseRepository().findById(id).get();
    return this.createResponse(entity);
  }

  public List<RESPONSE> findAllById(Long id) {
    List<ENTITY> entities = getBaseRepository().findAllById(id);
    return entities.stream().map(entity -> {
      return this.createResponse(entity);
    }).collect(Collectors.toList());
  }

  public List<RESPONSE> findAll() {
    List<ENTITY> entities = getBaseRepository().findAll();
    return entities.stream().map(entity -> {
      return this.createResponse(entity);
    }).collect(Collectors.toList());
  }

  public RESPONSE findByName(String name) {
    ENTITY entity = getBaseRepository().findByName(name);
    return this.createResponse(entity);
  }

  public List<RESPONSE> findAllByName(String name) {
    List<ENTITY> entities = getBaseRepository().findAllByName(name);
    return entities.stream().map(entity -> {
      return this.createResponse(entity);
    }).collect(Collectors.toList());
  }

  public RESPONSE findByLabel(String label) {
    ENTITY entity = getBaseRepository().findByLabel(label);
    return this.createResponse(entity);
  }

  public List<RESPONSE> findAllByLabel(String label) {
    List<ENTITY> entities = getBaseRepository().findAllByLabel(label);
    return entities.stream().map(entity -> {
      return this.createResponse(entity);
    }).collect(Collectors.toList());
  }

  public Page<RESPONSE> findAll(Optional<Integer> limit) {
    Pageable pageable = PageRequest.of(0, limit.isPresent() ? limit.get() : 3);
    Page<ENTITY> page = getBaseRepository().findAll(pageable);
    return page.map(entity -> this.createResponse(entity));
  }
}
