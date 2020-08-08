package com.sfgpetclinic.controllers;

import com.sfgpetclinic.model.Owner;
import com.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {


    MockMvc mockMvc;
    @Mock
    private OwnerService ownerService;
    @InjectMocks
    private OwnerController ownerController;
    private Set<Owner> owners;
    @Mock
    private Model model;


    @BeforeEach
    void setUp() {

        owners = new HashSet<>();

        owners.add(Owner.builder().firstName("John").lastName("Smith").build());
        owners.add(Owner.builder().firstName("Jacky").lastName("Taylor").build());

        WebApplicationContext context;
        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();

    }

    @Test
    void listOwners() throws Exception {

        //when
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        String strModel = ownerController.listOwners(model);

        assertEquals("owners/index", strModel);
    }

    @Test
    void listOwnersByIndex() throws Exception {

        //when
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        String strModel = ownerController.listOwners(model);

        assertEquals("owners/index", strModel);
    }

    @Deprecated
    @Test
    void findOwners() throws Exception {

        String s = ownerController.findOwners(model);

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));

        verifyNoInteractions(ownerService);
    }
}