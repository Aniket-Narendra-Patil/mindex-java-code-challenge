// import statements
package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// class CompensationServiceImpl
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

//      get the employee id from the compensation object passed as a json and use it to get the employee object
        Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);
//      return the newly created compensation object
        return compensation;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation with id [{}]", id);

//      find the employee object using spring custom query findByEmployeeId
        Employee employee = employeeRepository.findByEmployeeId(id);
//      find the compensation object using spring custom query findByEmployee
        Compensation compensation = compensationRepository.findByEmployee(employee);

//      if no such employee exists, output an exception
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

//      if no such compensation exists for a given employee, output an exception
        if (compensation == null) {
            throw new RuntimeException("Invalid compensation: " + id);
        }

        return compensation;
    }

} // class CompensationServiceImpl
