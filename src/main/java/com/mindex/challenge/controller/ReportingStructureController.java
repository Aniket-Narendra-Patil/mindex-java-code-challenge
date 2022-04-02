// import statements
package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// class ReportingStructureController
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

//   ReportingStructureService service obejct
    @Autowired
    private ReportingStructureService reportingStructureService;

    @GetMapping("/readNumberOfReports/{id}")
    public ReportingStructure readNumberOfReports(@PathVariable String id) {
        LOG.debug("Received read number of reports for id [{}]", id);
//      invoke readNumberOfReports function
        return reportingStructureService.readNumberOfReports(id);
    }

} // class ReportingStructureController
