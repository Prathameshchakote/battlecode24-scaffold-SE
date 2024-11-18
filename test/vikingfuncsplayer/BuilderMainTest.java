package vikingfuncsplayer;

import battlecode.common.*;
import vikingfuncsplayer.BuilderMain;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class BuilderMainTest {
    private RobotController rc;
    private MapLocation currentLocation;
    private Direction[] directions;

    @Before
    public void setUp() {
        rc = mock(RobotController.class);
        currentLocation = new MapLocation(10, 10);
        directions = Direction.values();
        when(rc.getLocation()).thenReturn(currentLocation);
    }

    @Test
    public void testRunMain_WithUnpickedFlag() throws GameActionException {
        // Set up flag info
        MapLocation flagLocation = new MapLocation(12, 12);
        FlagInfo flag = mock(FlagInfo.class);
        when(flag.getLocation()).thenReturn(flagLocation);
        when(flag.isPickedUp()).thenReturn(false);
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{flag});

        // Set up building conditions
        when(rc.canBuild(any(TrapType.class), any(MapLocation.class))).thenReturn(true);

        // Execute
        BuilderMain.runMain(rc);

        // Verify trap building attempt
        verify(rc).build(eq(TrapType.EXPLOSIVE), any(MapLocation.class));
    }

    @Test
    public void testRunMain_WithPickedUpFlag() throws GameActionException {
        // Set up flag info for picked up flag
        FlagInfo flag = mock(FlagInfo.class);
        when(flag.isPickedUp()).thenReturn(true);
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{flag});

        // Execute
        BuilderMain.runMain(rc);

        // Verify exploration behavior
        verify(rc).isMovementReady();
    }