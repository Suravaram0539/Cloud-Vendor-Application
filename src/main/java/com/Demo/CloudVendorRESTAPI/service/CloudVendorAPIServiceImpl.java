package com.Demo.CloudVendorRESTAPI.service;

import com.Demo.CloudVendorRESTAPI.global_exception.CloudVendorNotFoundException;
import com.Demo.CloudVendorRESTAPI.model.CloudVendor;
import com.Demo.CloudVendorRESTAPI.repository.CloudvendorRepo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorAPIServiceImpl implements CloudVendorServiceInterface{

    private CloudvendorRepo cloudvendorRepo;

    public CloudVendorAPIServiceImpl(CloudvendorRepo cloudvendorRepo) {
        this.cloudvendorRepo = cloudvendorRepo;
    }

    @Override
    public CloudVendor createCloudVendor(CloudVendor cloudVendor) {
        cloudvendorRepo.save(cloudVendor);
        return cloudVendor;
    }

    @Override
    public CloudVendor updateCloudVendor(CloudVendor cloudVendor) {
        cloudvendorRepo.save(cloudVendor);
        return cloudVendor;
    }

    @Override
    public CloudVendor getCloudVendor(String cloudvendorId) {

        if (cloudvendorRepo.findById(cloudvendorId).isEmpty())
            throw new CloudVendorNotFoundException( "Cloud vendor not found");
        return cloudvendorRepo.findById(cloudvendorId).get();


//        return cloudvendorRepo.findById(cloudvendorId).orElseThrow(()->
//                new RuntimeException("Cloud vendor not found"));


    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
       return cloudvendorRepo.findAll();

    }

    @Override
    public List<CloudVendor> deleteCloudVendor(String cloudvendorId) {

        if (cloudvendorRepo.existsById(cloudvendorId)) {
            cloudvendorRepo.deleteById(cloudvendorId);
            return cloudvendorRepo.findAll();
        }
        else {
            throw new CloudVendorNotFoundException("Cloud vendor not found");
        }


    }
    @Override
    public List<CloudVendor> getByVendorName(String vendorName)
    {
        return cloudvendorRepo.findByVendorName(vendorName);
    }
}
