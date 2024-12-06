package personal.carlthronson.crisp.takehome.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "account")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountEntity extends BaseEntity {

  @Getter
  @Setter
  String name;

  /**
   * Human readable account ID consisting of numbers and dashes
   */
  @Getter
  @Setter
  private String label;
  
  @Getter
  @Setter
  @ManyToOne(optional = false)
  @JoinColumn(name = "account_type_id", unique = false)
  private AccountTypeEntity accountType;

  @Getter
  @Setter
  @JsonIgnore
  @OneToMany(mappedBy = "account")
  private List<AccountEntryEntity> entries;
}
