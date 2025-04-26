package com.Demo.CloudVendorRESTAPI.repository;

import com.Demo.CloudVendorRESTAPI.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CloudvendorRepo extends JpaRepository<CloudVendor, String> {

    List<CloudVendor> findByVendorName(String name);

}
