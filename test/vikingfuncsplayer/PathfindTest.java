
package vikingfuncsplayer;

import battlecode.common.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PathfindTest {
    private RobotController rc;
    private MapLocation currentLocation;
    private Direction mockDirection;

    @Before
    public void setUp() {
        rc = mock(RobotController.class);
        currentLocation = new MapLocation(0, 0);
        mockDirection = Direction.NORTH;
        when(rc.getLocation()).thenReturn(currentLocation);
    }