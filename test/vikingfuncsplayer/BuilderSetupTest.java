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

    @Test
    public void testRunSetup_EarlyGame_NoFlags() throws GameActionException {
        // Setup - Early game (round < 100)
        when(rc.getRoundNum()).thenReturn(50);
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{});

        // Execute
        BuilderSetup.runSetup(rc);

        // Verify exploration happens when no flags are nearby
        verify(rc).senseNearbyFlags(-1);
        verifyNoInteractions(mockMapInfo);
    }