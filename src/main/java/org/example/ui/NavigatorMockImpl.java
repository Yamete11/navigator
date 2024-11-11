package org.example.ui;

import org.example.model.City;
import org.example.model.CityConnection;

import java.util.List;

public class NavigatorMockImpl implements INavigator{
    @Override
    public List<CityConnection> findAllCityConnections() {
        CityConnection cityConnection1 =
                new CityConnection(1L,2.0,
                        new City(1L,"Wro",2.0,4.0),
                        new City(2L,"Poz",2.0,6.0)
                );

        CityConnection cityConnection2 =
                new CityConnection(2L,6.0,
                        new City(1L,"Szcz",8.0,6.0),
                        new City(2L,"Poz",2.0,6.0)
                );

        CityConnection cityConnection3 =
                new CityConnection(3L,6.0,
                        new City(1L,"Szcz",8.0,6.0),
                        new City(2L,"Rzesz",5.0,8.0)
                );

        return List.of(cityConnection1,cityConnection2);
    }
}
