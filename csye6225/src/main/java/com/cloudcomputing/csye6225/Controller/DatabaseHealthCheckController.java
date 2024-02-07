package com.cloudcomputing.csye6225.Controller;

import com.cloudcomputing.csye6225.Service.DatabaseHealthCheckService;
import com.cloudcomputing.csye6225.Utils.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseHealthCheckController {

    @Autowired
    private DatabaseHealthCheckService databaseHealthCheckService;

    // Method to handle "/healthz" API endpoint, throw "200 OK (Success)" || "503 Service Not Available (Failure)"
    @RequestMapping("/healthz")
    public ResponseEntity<Void> databaseHealthCheckApi(HttpServletRequest request) {
        return databaseHealthCheckService.checkDatabaseConnection(request);
    }

    // Method to handle other API endpoints other than "/healthz" API endpoint, throw "405 Method Not Allowed"
    @RequestMapping(path = "/healthz", method = {RequestMethod.OPTIONS})
    public ResponseEntity<Void> databaseHealthCheckOptionsApi(HttpServletRequest request) {
        return databaseHealthCheckService.checkDatabaseConnection(request);
    }

    // Method to handle other API endpoints other than "/healthz" API endpoint, throw "404 Not Found"
    @RequestMapping("/**")
    public ResponseEntity<Void> defaultApi() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(CommonUtil.setHeaders()).body(null);
    }

}
