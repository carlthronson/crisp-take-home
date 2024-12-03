package personal.carlthronson.crisp.takehome.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "account_type")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountTypeEntity extends BaseEntity {

  // Every entity needs a name
  @Getter
  @Setter
  @Column(name = "type")
  String name;

  // Every entity needs a label
  @Getter
  @Setter
  private String label;
  
  @Getter
  @Setter
  @JsonIgnore
  @OneToMany(mappedBy = "accountType")
  private List<AccountEntity> businesses;
}
