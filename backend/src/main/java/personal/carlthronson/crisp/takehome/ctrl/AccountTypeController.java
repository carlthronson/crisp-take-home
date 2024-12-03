package personal.carlthronson.crisp.takehome.ctrl;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.AccountTypeEntity;
import personal.carlthronson.crisp.takehome.response.AccountTypeResponse;
import personal.carlthronson.crisp.takehome.svc.AccountTypeService;

@RestController
@EnableWebMvc
@Transactional
public class AccountTypeController extends BaseController<AccountTypeService, AccountTypeEntity, AccountTypeResponse> {

  @MutationMapping(name = "deleteAccountType")
  public Boolean deleteAccountType(@Argument(name = "id") Long id) {
    return delete(id);
  }

  @RequestMapping(path = "/accountType/{id}", method = RequestMethod.DELETE)
  public Boolean delete(@PathVariable(name = "id") Long id) {
    return super.delete(id);
  }

  @RequestMapping(path = "/accountType/getbyid/{id}", method = RequestMethod.GET)
  public AccountTypeResponse getById(@PathVariable("id") Long id) {
    return super.getById(id);
  }

  @RequestMapping(path = "/accountType/findbyid/{id}", method = RequestMethod.GET)
  public AccountTypeResponse findById(@PathVariable("id") Long id) {
    return super.findById(id);
  }

  @RequestMapping(path = "/accountType/findallbyid/{id}", method = RequestMethod.GET)
  public List<AccountTypeResponse> findAllById(@PathVariable("id") Long id) {
    return super.findAllById(id);
  }

  @RequestMapping(path = "/accountType/findbyname/{name}", method = RequestMethod.GET)
  public AccountTypeResponse findByName(@PathVariable("name") String name) {
    return super.findByName(name);
  }

  @RequestMapping(path = "/accountType/findallbyname/{name}", method = RequestMethod.GET)
  public List<AccountTypeResponse> findAllByName(@PathVariable("name") String name) {
    return super.findAllByName(name);
  }

  @RequestMapping(path = "/accountType/findbylabel/{label}", method = RequestMethod.GET)
  public AccountTypeResponse findByLabel(@PathVariable("label") String label) {
    return super.findByLabel(label);
  }

  @RequestMapping(path = "/accountType/findallbylabel/{label}", method = RequestMethod.GET)
  public List<AccountTypeResponse> findAllByLabel(@PathVariable("label") String label) {
    return super.findAllByLabel(label);
  }

  @QueryMapping(name = "findAllAccountTypes")
  @RequestMapping(path = "/accountType/findall", method = RequestMethod.GET)
  public List<AccountTypeResponse> findAll(@Argument(name = "limit") Integer limit) {
    return super.findAll(limit);
  }
}
