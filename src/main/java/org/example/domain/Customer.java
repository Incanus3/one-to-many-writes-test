package org.example.domain;

import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Customer extends Model {
  @Id
  long id;

  String name;

  public Customer(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  @ManyToOne(optional = true, cascade = {CascadeType.ALL})
  @JoinColumn(name = "group_id", nullable = true, insertable = true, updatable = true)
  private Group group = null;
}
