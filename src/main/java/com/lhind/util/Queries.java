package com.lhind.util;

public final class Queries {

    public static String FIND_ALL_EMPLOYEES = "SELECT e FROM Employee e";
    public static String FIND_EMPLOYEE_BY_USERNAME = "SELECT e FROM Employee e WHERE e.username = ?1";
    public static String FIND_EMPLOYEE_BY_USERNAME_METHOD_2 = "SELECT e FROM Employee e WHERE e.username = :username";

    private Queries() {}

}
