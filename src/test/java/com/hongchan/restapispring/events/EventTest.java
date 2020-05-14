package com.hongchan.restapispring.events;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("hongchan Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        // Given
        String name = "hongchan";
        String descroption = "Spring";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(descroption);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(descroption);
    }

    @Test
    @Parameters
    public void testFree(int bp, int mp, boolean isFree) {
        // Given
        Event event = Event.builder()
                .basePrice(bp)
                .maxPrice(mp)
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] parametersForTestFree() {
        return new Object[] {
            new Object[] {0, 0, true},
            new Object[] {100, 0, false},
            new Object[] {0, 100, false},
            new Object[] {100, 200, false}
        };
    }

    @Test
    public void testOffline() {
        // Given
        Event event = Event.builder()
                .location("My House")
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isOffline()).isTrue();


        // Given
        Event event2 = Event.builder()
                .build();

        // When
        event2.update();

        // Then
        assertThat(event2.isOffline()).isFalse();
    }

}