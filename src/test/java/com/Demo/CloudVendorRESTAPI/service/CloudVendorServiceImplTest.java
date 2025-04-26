package com.Demo.CloudVendorRESTAPI.service;

import com.Demo.CloudVendorRESTAPI.model.CloudVendor;
import com.Demo.CloudVendorRESTAPI.repository.CloudvendorRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class CloudVendorServiceImplTest {

    @Mock
    private CloudvendorRepo cloudvendorRepo;
    private CloudVendorServiceInterface cloudVendorServiceInterface;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        cloudVendorServiceInterface = new CloudVendorAPIServiceImpl(cloudvendorRepo);
        cloudVendor = new CloudVendor("1","AWS","USA","XXXXXX");

    }

    @AfterEach
    void tearDown() throws Exception{

        autoCloseable.close();
    }

    @Test
    void testcreateCloudVendor(){
        mock(CloudVendor.class);
        mock(CloudvendorRepo.class);

        when(cloudvendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorServiceInterface.createCloudVendor(cloudVendor)).isEqualTo(cloudVendor);


    }

    @Test
    void testupdateCloudVendor(){

        mock(CloudVendor.class);
        mock(CloudvendorRepo.class);

        when(cloudvendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorServiceInterface.updateCloudVendor(cloudVendor)).isEqualTo(cloudVendor);


    }

    @Test
    void testgetCloudVendor(){

        mock(CloudVendor.class);
        mock(CloudvendorRepo.class);

        when(cloudvendorRepo.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorServiceInterface.getCloudVendor("1").getVendorName())
                .isEqualTo(cloudVendor.getVendorName());


    }

    @Test
    void testgetAllCloudVendors(){

        mock(CloudVendor.class);
        mock(CloudvendorRepo.class);

        when(cloudvendorRepo.findAll()).thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorServiceInterface.getAllCloudVendors().get(0).getVendorPhoneNumber())
                .isEqualTo(cloudVendor.getVendorPhoneNumber());


    }

    @Test
    void testgetByVendorName(){
        mock(CloudVendor.class);
        mock(CloudvendorRepo.class);

        when(cloudvendorRepo.findByVendorName("AWS")).thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorServiceInterface.getByVendorName("AWS").get(0).getVendorId())
                .isEqualTo(cloudVendor.getVendorId());



    }
//
//    @Test
//    void testdeleteCloudVendor(){
//
//        mock(CloudVendor.class);
//        mock(CloudvendorRepo.class, Mockito.CALLS_REAL_METHODS);
//
//        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudvendorRepo).deleteById(any());
//        assertThat(cloudVendorServiceInterface.deleteCloudVendor("1")).isEqualTo(cloudVendor);
//
//
//    }


}

