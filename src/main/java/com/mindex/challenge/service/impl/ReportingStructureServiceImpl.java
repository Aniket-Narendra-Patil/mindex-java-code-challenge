// import statements
package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


// class ReportingStructureServiceImpl
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    //    result count
    private int numOfReports;
    //    list to store the direct reports of the current employee
    List<Employee> neighbour = new ArrayList<>();
    //    list to store the current employees direct reports -> direct reports (just like neighbours of neighbours in a graph)
    List<Employee> neighbourChildren = new ArrayList<>();

    /***
     *
     * @param id - employee id
     * @return new ReportingStructure object
     * description: the readNumberOfReports function takes in a employee id and returns a new ReportingStructure object
     * for each of the directReports of the current employee, this function also displays its directReports employee details
     */
    @Override
    public ReportingStructure readNumberOfReports(String id) {
        LOG.debug("Creating ReportingStructure with id [{}]", id);

        neighbour.clear();
        neighbourChildren.clear();

//      get the current employee using the employee id provided by using the findByEmployeeId spring custom query
        Employee employee = employeeRepository.findByEmployeeId(id);

//      return exception if no such employee is present in the employee repo/db
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

/*      invoke dfs search on the current employee to get its children and continue dfs until you reach the leaf node,
        on reaching leaf node, backtrack back to the parent and check if their exists more children.
        continue until all child nodes are visited for a parent node.
        for every child you visit, increment the numOfReports count
        once all child nodes are explored for the given employee parent node,
        stop the algorithm and return program control to the calling function.
*/
        numOfReports = 0;
        DFS_EmployeeSearch(employee);

//      set the directReport list of the employee
        employee.setDirectReports(neighbour);

//      return the ReportingStructure object
        return new ReportingStructure(employee, numOfReports);
    }

    /***
     *
     * @param employee
     * description: this method recursive calls itself to explore all child nodes of the given parent,
     * simultaneously incrementing the count of nodes visited. once all nodes are visited, it returns to calling function.
     */
    public void DFS_EmployeeSearch(Employee employee) {

        List<Employee> directReportList = employee.getDirectReports();
//      base case: if the current employee has no direct reports return program control to calling function or if all directReports
//        have been explored
        if (directReportList == null){
            return;
        }
//      increment count by 1 for every new child visited
        numOfReports += directReportList.size();

//      iterate through all direct reports of the given employee
        for (int index = 0; index < directReportList.size(); index++) {
            String currentEmployeeId = directReportList.get(index).getEmployeeId();
            Employee currentEmployee = employeeRepository.findByEmployeeId(currentEmployeeId);

//          if the current employee's direct report is null, then add that employee to final result with direct reports null
            if (currentEmployee.getDirectReports() == null) {
                neighbour.add(currentEmployee);
            }

//          if the current employee's direct report is NOT null, then find all his direct reports save them
//          in his direct reports list in the final result
            else{
                for (Employee epm :currentEmployee.getDirectReports()) {
                    String currentEmployeeId2 = epm.getEmployeeId();
                    Employee currentEmployee2 = employeeRepository.findByEmployeeId(currentEmployeeId2);
                    neighbourChildren.add(currentEmployee2);
                }
                Employee copyCurrent = null;
                copyCurrent = currentEmployee;
                copyCurrent.setDirectReports(neighbourChildren);
                neighbour.add(copyCurrent);
            }
//          recursive case:
//          recursively invoke DFS search on the current employee (child node)
            DFS_EmployeeSearch(currentEmployee);
        }
    }

} // class ReportingStructureServiceImpl
