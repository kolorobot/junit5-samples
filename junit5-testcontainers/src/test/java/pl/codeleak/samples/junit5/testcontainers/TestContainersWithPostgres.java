package pl.codeleak.samples.junit5.testcontainers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Testcontainers
class TestContainersWithPostgres {

    @Container
    public PostgreSQLContainer container = new PostgreSQLContainer<>()
            .withInitScript("init-test-database.sql")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(7868);

    @Test
    public void queryAllEmployees() throws SQLException {
        var employees = getAllEmployees();

        Assertions.assertEquals(2, employees.size());
        System.out.println(employees);
    }

    private List<Employee> getAllEmployees() throws SQLException {

        Connection connection = DriverManager.getConnection(
                container.getJdbcUrl(),
                container.getUsername(),
                container.getPassword()
        );

        try (var statement = connection.createStatement()) {
            var rs = statement.executeQuery("SELECT * FROM employees");
            var employees = new ArrayList<Employee>();
            while (rs.next()) {
                employees.add(new Employee(rs.getString("name"), rs.getInt("age")));
            }
            return employees;
        }
    }

    private class Employee {

        final String name;
        final int age;

        Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("age=" + age)
                    .toString();
        }
    }
}
