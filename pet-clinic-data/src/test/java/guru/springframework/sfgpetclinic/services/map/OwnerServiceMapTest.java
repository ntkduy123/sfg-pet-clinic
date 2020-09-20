package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final long ownerId = 1L;
    final String lastName = "smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName("smith");
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveWithId() {
        Long id = 2L;
        Owner owner = new Owner();
        owner.setId(id);

        Owner savedOwner = ownerServiceMap.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveWithoutId() {
        Owner owner = new Owner();

        Owner savedOwner = ownerServiceMap.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(1L, owner.getId());
    }
}