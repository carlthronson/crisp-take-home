package personal.carlthronson.crisp.takehome.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "account_entry")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountEntryEntity extends BaseEntity {

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private BigDecimal amount;

  @Getter
  @Setter
  private String category;

  @Getter
  @Setter
  @ManyToOne(optional = false)
  @JoinColumn(name = "account_id")
  private AccountEntity account;
}
