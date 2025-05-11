package com.example.vessel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class OwnerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllOwners() throws Exception {
        mockMvc.perform(get("/api/owners"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testAddOwner() throws Exception {
        OwnerController.OwnerRequest request = new OwnerController.OwnerRequest();
        request.setOwnerName("Test Owner");

        mockMvc.perform(post("/api/owners")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.ownerName").value("Test Owner"));
    }

    @Test
    public void testUpdateOwner() throws Exception {
        OwnerController.OwnerRequest request = new OwnerController.OwnerRequest();
        request.setOwnerName("Updated Owner");

        mockMvc.perform(put("/api/owners/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.ownerName").value("Updated Owner"));
    }

    @Test
    public void testDeleteOwner() throws Exception {
        mockMvc.perform(delete("/api/owners/1"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetOwnerDetails() throws Exception {
        mockMvc.perform(get("/api/owners/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.ownerId").exists())
               .andExpect(jsonPath("$.ownerName").exists());
    }
} 