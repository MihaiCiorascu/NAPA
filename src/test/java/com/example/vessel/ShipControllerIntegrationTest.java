package com.example.vessel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class ShipControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllShips() throws Exception {
        mockMvc.perform(get("/api/ships"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testAddShip() throws Exception {
        ShipController.ShipRequest request = new ShipController.ShipRequest();
        request.setShipName("Test Ship");
        request.setImoNumber(1234567);
        request.setOwnerId(1);
        request.setCategoryId(1);

        mockMvc.perform(post("/api/ships")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.shipName").value("Test Ship"))
               .andExpect(jsonPath("$.imoNumber").value(1234567));
    }

    @Test
    public void testUpdateShip() throws Exception {
        ShipController.ShipRequest request = new ShipController.ShipRequest();
        request.setShipName("Updated Ship");
        request.setImoNumber(7654321);
        request.setOwnerId(1);
        request.setCategoryId(1);

        mockMvc.perform(put("/api/ships/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.shipName").value("Updated Ship"))
               .andExpect(jsonPath("$.imoNumber").value(7654321));
    }

    @Test
    public void testDeleteShip() throws Exception {
        mockMvc.perform(delete("/api/ships/1"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetShipDetails() throws Exception {
        mockMvc.perform(get("/api/ships/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.shipName").exists())
               .andExpect(jsonPath("$.imoNumber").exists());
    }
} 