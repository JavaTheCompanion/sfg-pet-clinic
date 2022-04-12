package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class OwnerControllerTest {

    private OwnerController ownerController;

    @Mock
    private OwnerService ownerService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.ownerController = new OwnerController(this.ownerService);
    }

    @Test
    void testMockMVC() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.ownerController).build();

        mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/index"));
    }

    @Test
    void listOwners() {
        // given
        final Set<Owner> owners = new HashSet<>();
        final Owner owner1 = new Owner();
        owner1.setId(1L);
        owners.add(owner1);

        final Owner owner2 = new Owner();
        owner2.setId(2L);
        owners.add(owner2);

        when(this.ownerService.findAll()).thenReturn(owners);

        final ArgumentCaptor<Set<Owner>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        final String pageName = this.ownerController.listOwners(this.model);

        // then
        assertEquals("owners/index", pageName);
        verify(this.ownerService, times(1)).findAll();
        verify(this.model, times(1)).addAttribute(eq("owners"), argumentCaptor.capture());

        final Set<Owner> ownersSetInController = argumentCaptor.getValue();
        assertEquals(2L, ownersSetInController.size());
    }

    @Test
    void findOwners() {
    }
}