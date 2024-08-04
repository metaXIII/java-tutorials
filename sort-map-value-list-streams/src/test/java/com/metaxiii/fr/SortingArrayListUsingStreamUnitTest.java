package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortingArrayListUsingStreamUnitTest {

  private static final Map<String, Map<String, List<Employee>>> MAP_OF_DEPT_TO_MAP_OF_SEX_TO_EMPLOYEES =
    new HashMap<>();

  @Test
  void givenHashMapContainingEmployeeList_whenSortWithStreamAPI_thenSort() throws IOException {
    final var lstOfEmployees = new ArrayList<Employee>();
    MAP_OF_DEPT_TO_MAP_OF_SEX_TO_EMPLOYEES.forEach((dept, deptToSexToEmps) ->
      deptToSexToEmps.forEach((sex, emps) -> {
        final List<Employee> employees = emps
          .stream()
          .sorted(Comparator.comparingInt(Employee::getSalary).thenComparing(Employee::getName))
          .toList();
        lstOfEmployees.addAll(employees);
      })
    );
    final String[] expectedArray = readLinesFromFile(getFilePath("emp_sorted.csv"));
    final String[] actualArray = getCSVDelimitedLines(lstOfEmployees);
    assertArrayEquals(expectedArray, actualArray);
  }

  private static String[] readLinesFromFile(final String filePath) throws IOException {
    return Files.readAllLines(new File(filePath).toPath()).toArray(new String[0]);
  }

  private static String getFilePath(final String fileName) {
    return Objects
      .requireNonNull(SortingArrayListUsingStreamUnitTest.class.getClassLoader().getResource(fileName))
      .getPath();
  }

  private static String[] getCSVDelimitedLines(final List<Employee> emps) {
    return emps
      .stream()
      .map(emp -> emp.getDepartment() + "," + emp.getName() + "," + emp.getSalary() + "," + emp.getSex())
      .toArray(String[]::new);
  }

  @Test
  void givenHashMapContainingEmployeeList_whenSortWithoutStreamAPI_thenSort() throws IOException {
    final List<Employee> lstOfEmployees = new ArrayList<>();

    MAP_OF_DEPT_TO_MAP_OF_SEX_TO_EMPLOYEES.forEach((dept, deptToSexToEmps) ->
      deptToSexToEmps.forEach((sex, emps) -> {
        emps.sort(Comparator.comparingInt(Employee::getSalary).thenComparing(Employee::getName));
        lstOfEmployees.addAll(emps);
      })
    );
    final String[] expectedArray = readLinesFromFile(getFilePath("emp_sorted.csv"));
    final String[] actualArray = getCSVDelimitedLines(lstOfEmployees);
    assertArrayEquals(expectedArray, actualArray);
  }

  @BeforeEach
  void setup() throws IOException {
    populateMap(getFilePath("emp_not_sorted.csv"));
  }

  private static void populateMap(final String filePath) throws IOException {
    final String[] lines = readLinesFromFile(filePath);
    MAP_OF_DEPT_TO_MAP_OF_SEX_TO_EMPLOYEES.clear();
    Arrays
      .asList(lines)
      .forEach(e -> {
        final String[] strArr = e.split(",");
        final Employee emp = new Employee(strArr[1], Integer.parseInt(strArr[2]), strArr[0], strArr[3]);

        MAP_OF_DEPT_TO_MAP_OF_SEX_TO_EMPLOYEES
          .computeIfAbsent(emp.getDepartment(), k -> new HashMap<>())
          .computeIfAbsent(emp.getSex(), k -> new ArrayList<>())
          .add(emp);
      });
  }
}
