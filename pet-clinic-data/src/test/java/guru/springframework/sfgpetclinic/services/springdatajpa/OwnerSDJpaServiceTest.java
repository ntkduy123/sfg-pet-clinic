package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";

    Owner owner;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerSDJpaService ownerSDJpaService;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setLastName(LAST_NAME);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();

        Owner owner1 = new Owner();
        owner1.setId(1L);
        Owner owner2 = new Owner();
        owner2.setId(2L);

        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> ownerSet = ownerSDJpaService.findAll();

        assertNotNull(ownerSet);
        assertEquals(2, ownerSet.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        Owner owner = ownerSDJpaService.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner);

        Owner savedOwner = ownerSDJpaService.save(owner);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(any());
    }
}