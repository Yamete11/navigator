package org.example.ui;

import org.example.model.City;
import org.example.model.CityConnection;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CityConnectionCheckerTest implements ICityConnectionChecker {

    @Override
    public Set<CityConnection> uniqueCityConnection(List<CityConnection> connections) {
            Set<CityConnection> uniqueCityConnections = new TreeSet<>((c1, c2) ->
                    isCityConnectionIdDuplicate(c1,c2)?0:1);
            uniqueCityConnections.addAll(connections);
            return uniqueCityConnections;
        }

    @Test
    void testUniqueCityConnection_NoDuplicates() {
        // Given
        CityConnection conn1 = new CityConnection(1L, 2.0, new City(1L, "Wro", 2.0, 4.0), new City(2L, "Poz", 2.0, 6.0));
        CityConnection conn2 = new CityConnection(2L, 3.0, new City(3L, "Szcz", 8.0, 6.0), new City(4L, "Gda", 10.0, 8.0));
        List<CityConnection> connections = Arrays.asList(conn1, conn2);

        // When
        Set<CityConnection> uniqueConnections = uniqueCityConnection(connections);

        // Then
        String message = "Should return 2 unique connections: %s".formatted(uniqueConnections);
        assertEquals(2, uniqueConnections.size(), message);
    }

    @Test
    void testUniqueCityConnection_WithDuplicates() {
        // Given
        CityConnection conn1 = new CityConnection(1L, 2.0, new City(1L, "Wro", 2.0, 4.0), new City(2L, "Poz", 2.0, 6.0));
        CityConnection conn2 = new CityConnection(2L, 3.0, new City(3L, "Szcz", 8.0, 6.0), new City(1L, "Wro", 2.0, 4.0));
        CityConnection conn3 = new CityConnection(3L, 6.0, new City(2L, "Poz", 2.0, 6.0), new City(1L, "Wro", 2.0, 4.0));
        List<CityConnection> connections = Arrays.asList(conn1, conn2, conn3);

        // When
        Set<CityConnection> uniqueConnections = uniqueCityConnection(connections);

        // Then
        String message = "Should return 2 unique connections: %s".formatted(uniqueConnections);
        assertEquals(2, uniqueConnections.size(), message);
    }

    @Test
    void testIsCityConnectionDuplicate_SameCitiesDifferentOrder() {
        // Given
        CityConnection conn1 = new CityConnection(1L, 6.0, new City(2L, "Poz", 2.0, 6.0), new City(3L, "Szcz", 8.0, 6.0));
        CityConnection conn2 = new CityConnection(4L, 6.0, new City(3L, "Szcz", 8.0, 6.0), new City(2L,"Poz", 2.0 ,6.0));

        // When
        boolean result = isCityConnectionIdDuplicate(conn1, conn2);

        // Then
        assertTrue(result, "Connections should be considered duplicates.");
    }

    @Test
    void testIsCityConnectionDuplicate_DifferentCities() {
        // Given
        CityConnection conn1 = new CityConnection(1L, 6.0,new City(1L,"Wro",2.0 ,4.0),new City(2L,"Poz",2.0 ,6.0));
        CityConnection conn2 = new CityConnection(4L,6.0,new City(3L,"Szcz",8.0 ,6.0),new City(4L,"Gda",10.0 ,8.0));

        // When
        boolean result = isCityConnectionIdDuplicate(conn1 ,conn2);

        // Then
        assertFalse(result,"Connections should not be considered duplicates.");
    }
}
