package org.example.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphDrawerTest {

    private CitiConnectionServiceMockImpl citiConnectionService;

    @BeforeEach
    void setUp() {
        this.citiConnectionService = new CitiConnectionServiceMockImpl();
    }

    @Test
    void testGraphDrawer() {
        //Given
        String expectedOutput = """
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#               W                                                                 #
#               .                                                                 #
#               .                                                                 #
#               .                                                                 #
#               .                                                                 #
#               .                                                                 #
#               .                                                                 #
#               .                                                                 #
#               P . . . . . . . . . . . . . . . . . . . . . . . S                 #
#                                                           . .                   #
#                                                         .                       #
#                                                     . .                         #
#                                                   .                             #
#                                               . .                               #
#                                             .                                   #
#                                         . .                                     #
#                                       R                                         #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
#                                                                                 #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
""";
        GraphDrawer graphDrawer = new GraphDrawer(citiConnectionService);
        //When
        String result = graphDrawer.draw();
        //Then
        assertEquals(expectedOutput, result);
}

}
