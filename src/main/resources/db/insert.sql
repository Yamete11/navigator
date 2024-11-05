INSERT INTO `Cities` (`city_id`, `title`, `x`, `y`) VALUES
                                                        (1, 'CityA', 10, 5),
                                                        (2, 'CityB', 95, 2),
                                                        (3, 'CityC', 20, 15),
                                                        (4, 'CityD', 75, 10),
                                                        (5, 'CityE', 50, 25),
                                                        (6, 'CityF', 90, 18),
                                                        (7, 'CityG', 5, 28);



INSERT INTO City_connections (city_connection_id, first_city_id, second_city_id, distance) VALUES
                                                                                               (1, 1, 7, 40.0),
                                                                                               (2, 1, 3, 20.0),
                                                                                               (3, 7, 5, 40.0),
                                                                                               (4, 3, 5, 30.0),
                                                                                               (5, 3, 4, 80.0),
                                                                                               (6, 5, 6, 30.0),
                                                                                               (7, 4, 6, 20.0),
                                                                                               (8, 4, 2, 10.0),
                                                                                               (9, 2, 6, 40.0);