package personal.carlthronson.crisp.takehome.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "account_entry")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountEntryEntity extends BaseEntity {

  // Every entity needs a name
  @Getter
  @Setter
  String name;

  // Every entity needs a label
  @Getter
  @Setter
  private String label;

  @Getter
  @Setter
  @OneToOne(optional = true)
  @JoinColumn(name = "account_id", unique = true)
  private AccountEntity account;

  @Getter
  @Setter
  private BigDecimal amount;
}
