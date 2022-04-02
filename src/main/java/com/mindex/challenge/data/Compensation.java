// import statements
package com.mindex.challenge.data;

import java.util.Date;

// class Compensation
public class Compensation {

//    attributes
    private Employee employee;
    private double salary;
    private Date effectiveDate;

//    default constructor
    public Compensation() {
    }

//    parameterized constructor
    public Compensation( Employee employee, double salary, Date effectiveDate){
    this.employee = employee;
    this.salary = salary;
    this.effectiveDate = effectiveDate;
}

//    getters and setters
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

} // class Compensation
