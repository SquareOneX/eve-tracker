package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Storage;
import squareonex.evetrackerdata.repositories.StorageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StorageServiceImplTest {
    @Mock
    StorageRepository storageRepositoryMock;
    @InjectMocks
    StorageServiceImpl storageService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(storageRepositoryMock.save(any(Storage.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void addItemShouldCreateNewStorageEntity() {
        Item item = new Item();
        long quantityToAdd = 10;
        storageService.add(item, quantityToAdd);

        verify(storageRepositoryMock).save(any(Storage.class));
    }
    @Test
    void addItemShouldUpdateStorageEntity() {
        Item item = new Item();

        when(storageRepositoryMock.findByItem(item)).thenReturn(Optional.of(new Storage(item, 0L)));
        long quantityToAdd = 10;
        long add = storageService.add(item, quantityToAdd);

        verify(storageRepositoryMock).findByItem(item);
        assertEquals(quantityToAdd, add);
    }

    @Test
    void removeShouldReturnEmptyOptionalForItemsWithInsufficientQuantity() {
        Item itemNotInStore = new Item(0L, null);
        Item itemInsufficientQty = new Item(1L, null);
        long quantityToRemove = 5L;
        when(storageRepositoryMock.findByItem(itemInsufficientQty)).thenReturn(Optional.of(new Storage(itemInsufficientQty, 4L)));

        Optional<Long> removeNotInStorage = storageService.remove(itemNotInStore, quantityToRemove);
        Optional<Long> removeNotEnough = storageService.remove(itemInsufficientQty, quantityToRemove);

        verify(storageRepositoryMock, times(2)).findByItem(any(Item.class));
        assertEquals(Optional.empty(), removeNotInStorage);
        assertEquals(Optional.empty(), removeNotEnough);
    }

    @Test
    void removeShouldChangeQuantity() {
        Item item = new Item();
        long quantityToRemove = 5L;
        when(storageRepositoryMock.findByItem(item)).thenReturn(Optional.of(new Storage(item, 10L)));

        Optional<Long> remove = storageService.remove(item, quantityToRemove);

        verify(storageRepositoryMock).findByItem(item);
        assertEquals(5L, remove.get());
    }

    @Test
    void checkShouldReturnExpectedResults() {
        Item item = new Item();
        long quantity = 10L;
        when(storageRepositoryMock.findByItem(item)).thenReturn(Optional.of(new Storage(item, quantity)));

        Optional<Long> checkPresent = storageService.check(item);
        Optional<Long> checkAbsent = storageService.check(new Item(1L, null));

        verify(storageRepositoryMock).findByItem(item);
        assertEquals(quantity, checkPresent.get());
        assertEquals(Optional.empty(), checkAbsent);
    }

    @Test
    void isAvailableShouldReturnExpectedResults() {
        Item availableItem = new Item();
        long quantity = 10L;
        when(storageRepositoryMock.findByItem(availableItem)).thenReturn(Optional.of(new Storage(availableItem, quantity)));

        boolean isAvailable = storageService.isAvailable(availableItem, quantity);
        boolean isUnavailable = storageService.isAvailable(new Item(1L,null), quantity);

        verify(storageRepositoryMock).findByItem(availableItem);
        assertTrue(isAvailable);
        assertFalse(isUnavailable);
    }
}