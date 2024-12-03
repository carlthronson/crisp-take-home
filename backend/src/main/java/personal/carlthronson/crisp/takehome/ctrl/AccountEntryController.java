package personal.carlthronson.crisp.takehome.ctrl;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.crisp.takehome.entity.AccountEntryEntity;
import personal.carlthronson.crisp.takehome.response.AccountEntryResponse;
import personal.carlthronson.crisp.takehome.svc.AccountEntryService;

@RestController
@EnableWebMvc
@Transactional
public class AccountEntryController extends BaseController<AccountEntryService, AccountEntryEntity, AccountEntryResponse> {

  @RequestMapping(path = "/entry/{id}", method = RequestMethod.DELETE)
  public Boolean delete(@PathVariable(name = "id") Long id) {
    return super.delete(id);
  }

  @RequestMapping(path = "/entry/getbyid/{id}", method = RequestMethod.GET)
  public AccountEntryResponse getById(@PathVariable("id") Long id) {
    return super.getById(id);
  }

  @RequestMapping(path = "/entry/findbyid/{id}", method = RequestMethod.GET)
  public AccountEntryResponse findById(@PathVariable("id") Long id) {
    return super.findById(id);
  }

  @RequestMapping(path = "/entry/findallbyid/{id}", method = RequestMethod.GET)
  public List<AccountEntryResponse> findAllById(@PathVariable("id") Long id) {
    return super.findAllById(id);
  }

  @RequestMapping(path = "/entry/findbyname/{name}", method = RequestMethod.GET)
  public AccountEntryResponse findByName(@PathVariable("name") String name) {
    return super.findByName(name);
  }

  @RequestMapping(path = "/entry/findallbyname/{name}", method = RequestMethod.GET)
  public List<AccountEntryResponse> findAllByName(@PathVariable("name") String name) {
    return super.findAllByName(name);
  }

  @RequestMapping(path = "/entry/findbylabel/{label}", method = RequestMethod.GET)
  public AccountEntryResponse findByLabel(@PathVariable("label") String label) {
    return super.findByLabel(label);
  }

  @RequestMapping(path = "/entry/findallbylabel/{label}", method = RequestMethod.GET)
  public List<AccountEntryResponse> findAllByLabel(@PathVariable("label") String label) {
    return super.findAllByLabel(label);
  }

  @RequestMapping(path = "/entry/findall", method = RequestMethod.GET)
  public List<AccountEntryResponse> findAll(@Argument(name = "limit") Integer limit) {
    return super.findAll(limit);
  }
}
