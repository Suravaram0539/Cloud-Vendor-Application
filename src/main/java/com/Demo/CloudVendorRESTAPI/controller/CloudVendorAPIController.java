package com.Demo.CloudVendorRESTAPI.controller;

import com.Demo.CloudVendorRESTAPI.HomePage.CloudVendorHome;
import com.Demo.CloudVendorRESTAPI.model.CloudVendor;
import com.Demo.CloudVendorRESTAPI.response.Responsehandler;
import com.Demo.CloudVendorRESTAPI.service.CloudVendorAPIServiceImpl;
import com.Demo.CloudVendorRESTAPI.service.CloudVendorServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIController {


    private CloudVendorServiceInterface cloudVendorServiceInterface;

    private CloudVendorHome cloudVendorHome;

    public CloudVendorAPIController(CloudVendorServiceInterface cloudVendorServiceInterface, CloudVendorHome cloudVendorHome)  {
        this.cloudVendorServiceInterface = cloudVendorServiceInterface;
        this.cloudVendorHome = cloudVendorHome;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        //return new ResponseEntity<>("Welcome to Cloud Vendor API Application \uD83D\uDE00",HttpStatus.OK);
        return new ResponseEntity<>(cloudVendorHome.homePage(),
                HttpStatus.OK);
    }


    @GetMapping("{vendorId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get Cloud Vendor details by vendorId",
            description = "Provide cloud vendor details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Operation"),
                    @ApiResponse(responseCode = "404", description = "Vendor Not Found")
            })
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable String vendorId) {

        return Responsehandler.responseBuilder("Cloud vendor details fetched successfully",
                cloudVendorServiceInterface.getCloudVendor(vendorId), HttpStatus.OK);

//        return cloudVendorServiceInterface.getCloudVendor(vendorId);
    }

    @GetMapping("/all")//test
    //@GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getALLCloudVendorDetails() {
        return Responsehandler.responseBuilder("All the Cloud vendor details fetched successfully",
                cloudVendorServiceInterface.getAllCloudVendors(), HttpStatus.OK);

        //return cloudVendorServiceInterface.getAllCloudVendors();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {

        return Responsehandler.responseBuilder("Cloud vendor details created successfully",
                cloudVendorServiceInterface.createCloudVendor(cloudVendor), HttpStatus.CREATED);
        //return cloudVendorServiceInterface.createCloudVendor(cloudVendor);
//        return "Cloud vendor created successfully";
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateCloudVendor(@RequestBody CloudVendor cloudVendor) {

        return Responsehandler.responseBuilder("Cloud vendor details updated successfully",
                cloudVendorServiceInterface.updateCloudVendor(cloudVendor), HttpStatus.OK);

        //return cloudVendorServiceInterface.updateCloudVendor(cloudVendor);
//        return "Cloud vendor updated successfully";
    }

    //@DeleteMapping("{vendorId}")
    @DeleteMapping("/delete/{vendorId}")//test
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteCloudVendor(@PathVariable String vendorId) {

        return Responsehandler.responseBuilder("Cloud vendor details Deleted successfully. The remaining cloud vendors are listed above",
                cloudVendorServiceInterface.deleteCloudVendor(vendorId), HttpStatus.OK);
        //return cloudVendorServiceInterface.deleteCloudVendor(vendorId);
//        return "Cloud vendor deleted successfully";
    }

    @GetMapping("/name/{vendorName}")//test
    //@GetMapping("{vendorName}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getCloudVendorByName(@PathVariable String vendorName) {
        return Responsehandler.responseBuilder("All Cloud vendor details fetched successfully with same name",
                cloudVendorServiceInterface.getByVendorName(vendorName), HttpStatus.OK);
        //return cloudVendorServiceInterface.getCloudVendorByName(vendorName);


    }
}
