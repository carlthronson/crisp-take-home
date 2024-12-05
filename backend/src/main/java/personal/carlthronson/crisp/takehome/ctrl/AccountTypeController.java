package personal.carlthronson.crisp.takehome.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.AccountTypeEntity;
import personal.carlthronson.crisp.takehome.repo.AccountTypeRepository;

@RestController
@EnableWebMvc
@Transactional
public class AccountTypeController {

  @Autowired
  private AccountTypeRepository repository;

  @GetMapping("/type/findall")
  public Page<AccountTypeEntity> findAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "asc") String direction,
      @RequestParam(defaultValue = "id") String property) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), property));
    return repository.findAll(pageable);
  }
}
