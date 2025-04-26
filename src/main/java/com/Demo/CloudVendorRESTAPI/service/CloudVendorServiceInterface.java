package com.Demo.CloudVendorRESTAPI.service;

import com.Demo.CloudVendorRESTAPI.model.CloudVendor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CloudVendorServiceInterface {
    public CloudVendor createCloudVendor(CloudVendor cloudVendor);
    public List<CloudVendor> deleteCloudVendor(String  cloudvendorId);
    public CloudVendor updateCloudVendor(CloudVendor cloudVendor);
    public CloudVendor getCloudVendor(String cloudvendorId);
    public List<CloudVendor> getAllCloudVendors();
    public List<CloudVendor> getByVendorName(String name);
}
