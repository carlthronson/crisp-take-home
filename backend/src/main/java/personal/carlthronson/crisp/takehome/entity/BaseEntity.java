package personal.carlthronson.crisp.takehome.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction(value = "deleted_at IS NULL")
public class BaseEntity {

  static ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
  
  // Every entity needs an id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  @Version
  private Long version;

  @Getter
  @Setter
  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Getter
  @Setter
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Getter
  @Setter
  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  public boolean isDeleted() {
    return this.getDeletedAt() != null;
  }

  @Override
  public String toString() {
    try {
      return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      return e.getLocalizedMessage();
    }
  }
}
