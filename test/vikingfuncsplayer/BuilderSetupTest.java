package vikingfuncsplayer;

import battlecode.common.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class BuilderSetupTest {
    private RobotController rc;
    private MapLocation currentLocation;
    private FlagInfo mockFlag;
    private MapInfo mockMapInfo;

    @Before
    public void setUp() {
        rc = mock(RobotController.class);
        currentLocation = new MapLocation(0, 0);
        mockFlag = mock(FlagInfo.class);
        mockMapInfo = mock(MapInfo.class);

        when(rc.getLocation()).thenReturn(currentLocation);
        when(mockFlag.getLocation()).thenReturn(new MapLocation(1, 1));
    }