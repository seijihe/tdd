package com.seiji.workspace.mock.business;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ListTestWithBDD {
    
    @Test 
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        // Given
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10);

        // When & Then
        assertThat(list.size()).isEqualTo(10);
        assertThat(list.size()).isEqualTo(10);
        assertThat(list.size()).isEqualTo(10);

    }

    @Test 
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20); // You can define multiple values of return

        // When & Then
        assertThat(list.size()).isEqualTo(10);
        assertThat(list.size()).isEqualTo(20);
        assertThat(list.size()).isEqualTo(20);

    }

    @Test 
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnAName() {
        // Given
        var list = mock(List.class);
        given(list.get(anyInt())).willReturn("BillGates"); // For any int argument passed

        // When & Then
        assertThat("BillGates").isEqualTo(list.get(999));
        assertThat("BillGates").isEqualTo(list.get(anyInt()));

    }

    @Test 
    void testMockingList_When_ParameterMatcheWithTheExpectedOne() {
        // Given
        List<String> list = mock();
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);

        // When
        list.get(0);

        // Then
        then(list).should().get(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(0);
    }

    @Test 
    void testMockingList_When_ThrowsAnException() {
        // Given
        var list = mock(List.class);
        given(list.get(anyInt()))
            .willThrow(new RuntimeException("Foo Bar"));

        // When & Then
        assertThatThrownBy(() -> { throw new RuntimeException("Foo Bar"); })
            .hasMessage("Foo Bar");
    }

}
