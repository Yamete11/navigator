package org.example.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphDrawerTest {

    private INavigator navigator;

    @BeforeEach
    void setUp() {
        this.navigator = new NavigatorMockImpl();
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
        GraphDrawer graphDrawer = new GraphDrawer(navigator);
        //When
        String result = graphDrawer.draw();
        //Then
        assertEquals(expectedOutput, result);
}

}
