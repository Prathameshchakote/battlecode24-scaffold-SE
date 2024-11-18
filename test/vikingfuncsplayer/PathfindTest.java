
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

    @Test
    public void testMoveTowards_CannotMoveButCanFill() throws GameActionException {
        // Setup
        MapLocation targetLocation = new MapLocation(1, 1);
        MapLocation fillLocation = currentLocation.add(Direction.NORTHEAST);
        when(rc.canMove(any(Direction.class))).thenReturn(false);
        when(rc.canFill(fillLocation)).thenReturn(true);

        // Execute
        Pathfind.moveTowards(rc, targetLocation, true);

        // Verify
        verify(rc, never()).move(any(Direction.class));
        verify(rc).fill(any(MapLocation.class));
    }

    @Test
    public void testMoveTowards_CannotMoveOrFill_MovesRandomly() throws GameActionException {
        // Setup
        MapLocation targetLocation = new MapLocation(1, 1);
        when(rc.canMove(any(Direction.class))).thenReturn(true);
        when(rc.canFill(any(MapLocation.class))).thenReturn(false);

        // Execute
        Pathfind.moveTowards(rc, targetLocation, true);

        // Verify
        verify(rc).move(any(Direction.class));
    }

    @Test
    public void testMoveTowards_FillDisabled() throws GameActionException {
        // Setup
        MapLocation targetLocation = new MapLocation(1, 1);
        when(rc.canMove(any(Direction.class))).thenReturn(false);

        // Execute
        Pathfind.moveTowards(rc, targetLocation, false);

        // Verify
        verify(rc, never()).fill(any(MapLocation.class));
    }

    @Test
    public void testExplore_WithCrumbs() throws GameActionException {
        // Setup
        MapLocation crumbLocation = new MapLocation(1, 1);
        when(rc.isMovementReady()).thenReturn(true);
        when(rc.senseNearbyCrumbs(-1)).thenReturn(new MapLocation[]{crumbLocation});
        when(rc.canMove(any(Direction.class))).thenReturn(true);

        // Execute
        Pathfind.explore(rc);

        // Verify
        verify(rc).move(any(Direction.class));
        verify(rc).senseNearbyCrumbs(-1);
    }

    @Test
    public void testExplore_NoCrumbs_CanMove() throws GameActionException {
        // Setup
        when(rc.isMovementReady()).thenReturn(true);
        when(rc.senseNearbyCrumbs(-1)).thenReturn(new MapLocation[]{});
        when(rc.canMove(any(Direction.class))).thenReturn(true);

        // Execute
        Pathfind.explore(rc);

        // Verify
        verify(rc).move(any(Direction.class));
    }

    @Test
    public void testExplore_MovementNotReady() throws GameActionException {
        // Setup
        when(rc.isMovementReady()).thenReturn(false);

        // Execute
        Pathfind.explore(rc);

        // Verify
        verify(rc, never()).move(any(Direction.class));
        verify(rc, never()).senseNearbyCrumbs(-1);
    }