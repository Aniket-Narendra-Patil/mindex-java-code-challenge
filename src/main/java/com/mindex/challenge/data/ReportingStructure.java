// import statements
package com.mindex.challenge.data;

// class ReportingStructure
public class ReportingStructure {

//    attributes
    private Employee employee;
    private int numberOfReports;

//    default constructor
    public ReportingStructure() {
    }

//    parameterized constructor
    public ReportingStructure(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

//    getters and setters
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return this.numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

} // class ReportingStructure
