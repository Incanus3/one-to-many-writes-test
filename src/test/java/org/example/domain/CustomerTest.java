package org.example.domain;

import io.ebean.DB;
import io.ebean.Database;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * When running tests in the IDE install the "Enhancement plugin".
 * <p>
 * <a href="http://ebean-orm.github.io/docs/setup/enhancement#ide">...</a>
 */
@SuppressWarnings("DataFlowIssue")
class CustomerTest {
  /**
   * Get the "default database" and save().
   */
  @Test
  void set_from_many_to_one_side() {
    Customer rob = new Customer("Rob");

    Database server = DB.getDefault();
    server.save(rob);

    assertThat(rob.getId()).isGreaterThan(0);

    Group devs = new Group("Devs");

    server.save(devs);

    assertThat(devs.getId()).isGreaterThan(0);

    rob.setGroup(devs);
    server.save(rob);

    Customer reloadedRob = server.find(Customer.class, rob.getId());
    Group reloadedDevs = server.find(Group.class, devs.getId());

    // this works
    assertThat(reloadedRob.getGroup()).isEqualTo(devs);
    assertThat(reloadedDevs.getMembers()).isEqualTo(List.of(rob));
  }


  @Test
  void set_from_one_to_many_side() {
    Customer rob = new Customer("Rob");

    Database server = DB.getDefault();
    server.save(rob);

    assertThat(rob.getId()).isGreaterThan(0);

    Group devs = new Group("Devs");

    server.save(devs);

    assertThat(devs.getId()).isGreaterThan(0);

    devs.setMembers(List.of(rob));
    server.save(devs);

    Customer reloadedRob = server.find(Customer.class, rob.getId());
    Group reloadedDevs = server.find(Group.class, devs.getId());

    // THESE FAIL
    assertThat(reloadedRob.getGroup()).isEqualTo(devs);
    assertThat(reloadedDevs.getMembers()).isEqualTo(List.of(rob));
  }

  @Test
  void add_from_one_to_many_side() {
    Customer rob = new Customer("Rob");

    Database server = DB.getDefault();
    server.save(rob);

    assertThat(rob.getId()).isGreaterThan(0);

    Group devs = new Group("Devs");

    server.save(devs);

    assertThat(devs.getId()).isGreaterThan(0);

    devs.addMember(rob);
    server.save(devs);

    Customer reloadedRob = server.find(Customer.class, rob.getId());
    Group reloadedDevs = server.find(Group.class, devs.getId());

    // THESE FAIL
    assertThat(reloadedRob.getGroup()).isEqualTo(devs);
    assertThat(reloadedDevs.getMembers()).isEqualTo(List.of(rob));
  }
}
