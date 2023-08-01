package org.example.domain;

import io.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group extends Model {
  @Id
  long id;

  String name;

  public Group(String name) {
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

  public List<Customer> getMembers() {
    return members;
  }

  public void setMembers(List<Customer> members) {
    this.members = members;
  }

  @OneToMany(mappedBy = "group", cascade = {CascadeType.ALL})
  private List<Customer> members = new ArrayList<>();
}
