package com.seiji.workspace.mock.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ListTest {
    
    @Test 
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        // Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);

        // Act & Assert
        assertEquals(10, list.size());
        assertEquals(10, list.size());
        assertEquals(10, list.size());

    }

    @Test 
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20); // You can define multiple values of return

        // Act & Assert
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(20, list.size());

    }

    @Test 
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnAName() {
        // Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("BillGates"); // For any int argument passed

        // Act & Assert
        assertEquals("BillGates", list.get(999));
        assertEquals("BillGates", list.get(anyInt()));

        verify(list).get(0);

    }

    @Test 
    void testMockingList_When_ThrowsAnException() {
        // Arrange
        var list = mock(List.class);
        when(list.get(anyInt()))
            .thenThrow(new RuntimeException("Foo Bar"));

        // Act & Assert
        assertThrows(RuntimeException.class, 
            () -> {
                list.get(anyInt());
            },
            () -> "Should have thrown an RuntimeException"
        );
    }

}
