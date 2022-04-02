// import statements
package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

// interface CompensationService
public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String id);
} // interface CompensationService
