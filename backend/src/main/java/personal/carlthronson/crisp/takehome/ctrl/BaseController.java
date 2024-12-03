package personal.carlthronson.crisp.takehome.ctrl;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.BaseEntity;
import personal.carlthronson.crisp.takehome.response.BaseResponse;
import personal.carlthronson.crisp.takehome.svc.BaseService;

@RestController
@EnableWebMvc
@Transactional
public abstract class BaseController<SERVICE extends BaseService<ENTITY, RESPONSE>, ENTITY extends BaseEntity, RESPONSE extends BaseResponse> {

  @Autowired
  private SERVICE service;

  Logger logger = Logger.getLogger(getClass().getName());

  public Boolean delete(@PathVariable(name = "id") Long id) {
    return service.delete(id);
  }

  public RESPONSE getById(@PathVariable("id") Long id) {
    logger.info("Path variable: " + id);
    return service.getById(id);
  }

  public RESPONSE findById(@PathVariable("id") Long id) {
    logger.info("Path variable: " + id);
    return service.findById(id);
  }

  public List<RESPONSE> findAllById(@PathVariable("id") Long id) {
    logger.info("Path variable: " + id);
    return service.findAllById(id);
  }

  public RESPONSE findByName(@PathVariable("name") String name) {
    logger.info("Path variable: " + name);
    return service.findByName(name);
  }

  public List<RESPONSE> findAllByName(@PathVariable("name") String name) {
    logger.info("Path variable: " + name);
    return service.findAllByName(name);
  }

  public RESPONSE findByLabel(@PathVariable("label") String label) {
    logger.info("Path variable: " + label);
    return service.findByLabel(label);
  }

  public List<RESPONSE> findAllByLabel(@PathVariable("label") String label) {
    logger.info("Path variable: " + label);
    return service.findAllByLabel(label);
  }

  public List<RESPONSE> findAll(@Argument(name = "limit") Integer limit) {
    logger.info("Request param limit: " + limit);
    Stream<RESPONSE> stream = service.findAll().stream();
    if (limit != null) {
      stream = stream.limit(limit);
    }
    return stream.collect(Collectors.toList());
  }
}
