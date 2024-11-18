
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

    @Test
    public void testMoveTowards_CanMoveDirectly() throws GameActionException {
        // Setup
        MapLocation targetLocation = new MapLocation(1, 1);
        when(rc.canMove(any(Direction.class))).thenReturn(true);

        // Execute
        Pathfind.moveTowards(rc, targetLocation, true);

        // Verify
        verify(rc).move(any(Direction.class));
        verify(rc, never()).fill(any(MapLocation.class));
    }