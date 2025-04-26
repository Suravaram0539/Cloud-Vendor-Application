package com.Demo.CloudVendorRESTAPI.repository;

import com.Demo.CloudVendorRESTAPI.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CloudVendorRepoTest {

    //given-when-then

    @Autowired
    private CloudvendorRepo cloudvendorRepo;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {

        cloudVendor=new CloudVendor("1","AWS",
                "USA","XXXXX");
        cloudvendorRepo.save(cloudVendor);

    }

    @AfterEach
    void tearDown() {
        cloudVendor=null;
        cloudvendorRepo.deleteAll();
    }

    // Test case for success
    @Test
    void testFindByVendorName_Found(){
        List<CloudVendor> cloudVendorList= cloudvendorRepo.findByVendorName("AWS");


        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(
                cloudVendor.getVendorId()
        );
        assertThat(cloudVendorList.get(0).getVendorName()).isEqualTo(
                cloudVendor.getVendorName()
        );
        assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(
                cloudVendor.getVendorAddress()
        );
        assertThat(cloudVendorList.get(0).getVendorPhoneNumber()).isEqualTo(
                cloudVendor.getVendorPhoneNumber());
    }

    // Test case for failure

    @Test
    void testFindByVendorName_NotFound(){
        List<CloudVendor> cloudVendorList= cloudvendorRepo.findByVendorName("Azure");

        assertThat(cloudVendorList.isEmpty()).isTrue();

    }
}
