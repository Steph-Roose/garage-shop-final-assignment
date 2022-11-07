package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.CarDto;
import com.example.garageshopfinalassignment.dtos.CustomerDto;
import com.example.garageshopfinalassignment.models.Car;
import com.example.garageshopfinalassignment.models.Customer;
import com.example.garageshopfinalassignment.security.JwtRequestFilter;
import com.example.garageshopfinalassignment.security.JwtService;
import com.example.garageshopfinalassignment.services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private CustomerService customerService;

    Customer customer1;
    Customer customer2;
    CustomerDto customerDto1;
    CustomerDto customerDto2;
    Car car1;
    CarDto carDto1;

    @BeforeEach
    void setUp() {
        customer1 = new Customer(1L, "Max", "Verstappen", "2 Bradbourne Drive", "MK78AT", "Milton Keynes", "0612345678", "max@verstappen.nl");
        customer2 = new Customer(2L, "Charles", "Leclerc", "Via Abetone Inferiore 4", "f41053", "Maranello", "0612345678", "charles@leclerc.nl", car1);
        customerDto1 = new CustomerDto(1L, "Max", "Verstappen", "2 Bradbourne Drive", "MK78AT", "Milton Keynes", "0612345678", "max@verstappen.nl");
        customerDto2 = new CustomerDto(2L, "Charles", "Leclerc", "Via Abetone Inferiore 4", "f41053", "Maranello", "0612345678", "charles@leclerc.nl", carDto1);
        car1 = new Car(1L, "Aston Martin", "DB11", "12-34-56");
        carDto1 = new CarDto(1L, "Aston Martin", "DB11", "12-34-56");
    }

    @Test
    void addCustomer() throws Exception {
        given(customerService.addCustomer(customerDto1)).willReturn(customerDto1);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("firstName").value("Max"))
                .andExpect(jsonPath("lastName").value("Verstappen"))
                .andExpect(jsonPath("address").value("2 Bradbourne Drive"))
                .andExpect(jsonPath("postcode").value("MK78AT"))
                .andExpect(jsonPath("residence").value("Milton Keynes"))
                .andExpect(jsonPath("phone").value("0612345678"))
                .andExpect(jsonPath("email").value("max@verstappen.nl"));
    }

    @Test
    void getAllCustomers() throws Exception {
        given(customerService.getAllCustomers()).willReturn(List.of(customerDto1, customerDto2));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("Max"))
                .andExpect(jsonPath("$[0].lastName").value("Verstappen"))
                .andExpect(jsonPath("$[0].address").value("2 Bradbourne Drive"))
                .andExpect(jsonPath("$[0].postcode").value("MK78AT"))
                .andExpect(jsonPath("$[0].residence").value("Milton Keynes"))
                .andExpect(jsonPath("$[0].phone").value("0612345678"))
                .andExpect(jsonPath("$[0].email").value("max@verstappen.nl"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].firstName").value("Charles"))
                .andExpect(jsonPath("$[1].lastName").value("Leclerc"))
                .andExpect(jsonPath("$[1].address").value("Via Abetone Inferiore 4"))
                .andExpect(jsonPath("$[1].postcode").value("f41053"))
                .andExpect(jsonPath("$[1].residence").value("Maranello"))
                .andExpect(jsonPath("$[1].phone").value("0612345678"))
                .andExpect(jsonPath("$[1].email").value("charles@leclerc.nl"));
    }

    @Test
    void getCustomer() throws Exception {
        given(customerService.getCustomerById(2L)).willReturn(customerDto2);

        mockMvc.perform(get("/customers/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(2L))
                .andExpect(jsonPath("firstName").value("Charles"))
                .andExpect(jsonPath("lastName").value("Leclerc"))
                .andExpect(jsonPath("address").value("Via Abetone Inferiore 4"))
                .andExpect(jsonPath("postcode").value("f41053"))
                .andExpect(jsonPath("residence").value("Maranello"))
                .andExpect(jsonPath("phone").value("0612345678"))
                .andExpect(jsonPath("email").value("charles@leclerc.nl"));
    }

    @Test
    void updateCustomer() throws Exception {
        given(customerService.updateCustomer(2L, customerDto2)).willReturn(customerDto2);

        mockMvc.perform(put("/customers/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(2L))
                .andExpect(jsonPath("firstName").value("Charles"))
                .andExpect(jsonPath("lastName").value("Leclerc"))
                .andExpect(jsonPath("address").value("Via Abetone Inferiore 4"))
                .andExpect(jsonPath("postcode").value("f41053"))
                .andExpect(jsonPath("residence").value("Maranello"))
                .andExpect(jsonPath("phone").value("0612345678"))
                .andExpect(jsonPath("email").value("charles@leclerc.nl"));
    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final CustomerDto obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}