package com.example.adoptionproject.services;

import com.example.adoptionproject.entities.*;
import com.example.adoptionproject.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdoptionServicesImplTest {
    @Mock
    private AdoptantRepository adoptantRepository;
    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private AdoptionRepository adoptionRepository;
    @InjectMocks
    private AdoptionServicesImpl adoptionServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAdoptant() {
        Adoptant adoptant = new Adoptant();
        when(adoptantRepository.save(adoptant)).thenReturn(adoptant);
        Adoptant result = adoptionServices.addAdoptant(adoptant);
        assertEquals(adoptant, result);
        verify(adoptantRepository).save(adoptant);
    }

    @Test
    void testAddAnimal() {
        Animal animal = new Animal();
        when(animalRepository.save(animal)).thenReturn(animal);
        Animal result = adoptionServices.addAnimal(animal);
        assertEquals(animal, result);
        verify(animalRepository).save(animal);
    }

    @Test
    void testAddAdoption_Success() {
        Adoption adoption = new Adoption();
        Adoptant adoptant = new Adoptant();
        Animal animal = new Animal();
        when(adoptantRepository.findById(1)).thenReturn(Optional.of(adoptant));
        when(animalRepository.findById(2)).thenReturn(Optional.of(animal));
        when(adoptionRepository.save(any(Adoption.class))).thenReturn(adoption);
        Adoption result = adoptionServices.addAdoption(adoption, 1, 2);
        assertNotNull(result);
        verify(adoptionRepository).save(adoption);
    }

    @Test
    void testAddAdoption_Failure() {
        Adoption adoption = new Adoption();
        when(adoptantRepository.findById(1)).thenReturn(Optional.empty());
        when(animalRepository.findById(2)).thenReturn(Optional.empty());
        Adoption result = adoptionServices.addAdoption(adoption, 1, 2);
        assertNull(result);
    }

    @Test
    void testGetAdoptionsByAdoptant() {
        List<Adoption> adoptions = Arrays.asList(new Adoption(), new Adoption());
        when(adoptionRepository.findByAdoptant_Nom("Dupont")).thenReturn(adoptions);
        List<Adoption> result = adoptionServices.getAdoptionsByAdoptant("Dupont");
        assertEquals(2, result.size());
        verify(adoptionRepository).findByAdoptant_Nom("Dupont");
    }

    @Test
    void testCalculFraisTotalAdoptions() {
        Adoption a1 = new Adoption();
        a1.setFrais(100f);
        Adoption a2 = new Adoption();
        a2.setFrais(200f);
        List<Adoption> adoptions = Arrays.asList(a1, a2);
        when(adoptionRepository.findByAdoptant_IdAdoptant(1)).thenReturn(adoptions);
        float total = adoptionServices.calculFraisTotalAdoptions(1);
        assertEquals(300f, total);
        verify(adoptionRepository).findByAdoptant_IdAdoptant(1);
    }
}
