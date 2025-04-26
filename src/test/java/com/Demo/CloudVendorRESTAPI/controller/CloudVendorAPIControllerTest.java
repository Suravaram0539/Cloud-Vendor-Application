package com.Demo.CloudVendorRESTAPI.controller;

import com.Demo.CloudVendorRESTAPI.HomePage.CloudVendorHome;
import com.Demo.CloudVendorRESTAPI.model.CloudVendor;
import com.Demo.CloudVendorRESTAPI.service.CloudVendorAPIServiceImpl;
import com.Demo.CloudVendorRESTAPI.service.CloudVendorServiceInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//All the Endpoint URls are working fine or not

@WebMvcTest(CloudVendorAPIController.class)
class CloudVendorAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CloudVendorHome cloudVendorHome;

    @InjectMocks
    private CloudVendorAPIController cloudVendorAPIController;

    @MockBean
    private CloudVendorServiceInterface cloudVendorServiceInterface;


    CloudVendor cloudVendorone;
    CloudVendor cloudVendortwo;

    List<CloudVendor> cloudVendorList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cloudVendorone=new CloudVendor("1","AWS",
                "USA","XXXXXXX");

        cloudVendortwo=new CloudVendor("2","AZURE",
                "London","XXXXXXX");
        cloudVendorList.add(cloudVendorone);
        cloudVendorList.add(cloudVendortwo);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    void testGetCloudVendorDetails() throws Exception {


        when(cloudVendorServiceInterface.getCloudVendor("1")).thenReturn(cloudVendorone);
        this.mockMvc.perform(get("/cloudvendor/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cloud vendor details fetched successfully"))
                .andExpect(jsonPath("$.data.vendorName").value("AWS"))
                .andExpect(jsonPath("$.data.vendorAddress").value("USA"))
                .andExpect(jsonPath("$.data.vendorId").value("1"))
                .andExpect(jsonPath("$.data.vendorPhoneNumber").value("XXXXXXX"));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetALLCloudVendorDetails() throws Exception {
        when(cloudVendorServiceInterface.getAllCloudVendors()).thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/cloudvendor/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("All the Cloud vendor details fetched successfully"))
                .andExpect(jsonPath("$.data[0].vendorName").value("AWS"))
                .andExpect(jsonPath("$.data[0].vendorAddress").value("USA"))
                .andExpect(jsonPath("$.data[0].vendorId").value("1"))
                .andExpect(jsonPath("$.data[0].vendorPhoneNumber").value("XXXXXXX"));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreateCloudVendorDetails() throws Exception {
        when(cloudVendorServiceInterface.createCloudVendor(cloudVendorone)).thenReturn(cloudVendorone);
        this.mockMvc.perform(post("/cloudvendor")
                        .contentType("application/json")
                        .content("{\"vendorId\":\"1\",\"vendorName\":\"AWS\",\"vendorAddress\":\"USA\",\"vendorPhoneNumber\":\"XXXXXXX\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Cloud vendor details created successfully"));
//                .andExpect(jsonPath("$.data.vendorName").value("AWS"))
//                .andExpect(jsonPath("$.data.vendorAddress").value("USA"))
//                .andExpect(jsonPath("$.data.vendorId").value("1"))
//                .andExpect(jsonPath("$.data.vendorPhoneNumber").value("XXXXXXX"));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateCloudVendor() throws Exception {
        when(cloudVendorServiceInterface.updateCloudVendor(cloudVendorone)).thenReturn(cloudVendorone);
        this.mockMvc.perform(put("/cloudvendor")
                        .contentType("application/json")
                        .content("{\"vendorId\":\"1\",\"vendorName\":\"AWS\",\"vendorAddress\":\"USA\",\"vendorPhoneNumber\":\"XXXXXXX\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cloud vendor details updated successfully"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeleteCloudVendor() throws Exception {
        when(cloudVendorServiceInterface.deleteCloudVendor("1")).thenReturn(cloudVendorList);
        this.mockMvc.perform(delete("/cloudvendor/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cloud vendor details Deleted successfully. The remaining cloud vendors are listed above"))
                .andExpect(jsonPath("$.data[0].vendorName").value("AWS"))
                .andExpect(jsonPath("$.data[0].vendorAddress").value("USA"))
                .andExpect(jsonPath("$.data[0].vendorId").value("1"))
                .andExpect(jsonPath("$.data[0].vendorPhoneNumber").value("XXXXXXX"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getCloudVendorByName() {
    }
}