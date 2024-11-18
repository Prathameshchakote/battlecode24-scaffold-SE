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