package vikingfuncsplayer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;


public class AttackSetupTest {
    @Mock
    private RobotController rc;

    private MapLocation currentLocation;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        currentLocation = new MapLocation(0, 0);
        when(rc.getLocation()).thenReturn(currentLocation);
    }

    @Test
    public void testRunSetup_DuringSetupPhase() throws GameActionException {
        // Setup
        when(rc.getRoundNum()).thenReturn(GameConstants.SETUP_ROUNDS - 1);
        when(rc.senseNearbyCrumbs(-1)).thenReturn(new MapLocation[]{});
        when(rc.canMove(any(Direction.class))).thenReturn(true);

        // Execute
        AttackSetup.runSetup(rc);

        // Verify
        verify(rc).getRoundNum();
        verify(rc).senseNearbyCrumbs(-1);
        verify(rc).canMove(any(Direction.class));
        verify(rc).move(any(Direction.class));
    }
