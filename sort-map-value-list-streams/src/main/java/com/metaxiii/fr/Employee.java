package com.metaxiii.fr;

public class Employee {

  private final String name;
  private final int salary;
  private final String department;
  private final String sex;

  public Employee(final String name, final int salary, final String department, final String sex) {
    this.name = name;
    this.salary = salary;
    this.department = department;
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public int getSalary() {
    return salary;
  }

  public String getDepartment() {
    return department;
  }

  public String getSex() {
    return sex;
  }
}
