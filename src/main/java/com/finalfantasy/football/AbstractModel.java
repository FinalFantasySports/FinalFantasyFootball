package com.finalfantasy.football;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"createdAt", "updatedAt"},
    allowGetters = true
)
@TypeDefs({
    @TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
    ),
    @TypeDef(
        name = "int-array",
        typeClass = IntArrayType.class
    )
})
public abstract class AbstractModel {

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  private Date createdAt;

  @Column(name = "updated_at", nullable = false)
  @LastModifiedDate
  private Date updatedAt;

  public Date getCreatedAt() {
    return createdAt;
  }
  public Date getUpdatedAt() {
    return updatedAt;
  }
}
