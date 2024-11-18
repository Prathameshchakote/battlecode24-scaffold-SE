package vikingfuncsplayer;

import battlecode.common.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class AttackMainPhaseTest {
    private RobotController rc;
    private MapLocation currentLocation;
    private MapLocation[] spawnLocations;
    private Team myTeam;
    private Team enemyTeam;

    @Before
    public void setUp() {
        rc = mock(RobotController.class);
        currentLocation = new MapLocation(10, 10);
        spawnLocations = new MapLocation[]{
                new MapLocation(0, 0),
                new MapLocation(5, 5),
                new MapLocation(15, 15)
        };
        myTeam = Team.A;
        enemyTeam = Team.B;

        try {
            // Basic setup mocks
            when(rc.getLocation()).thenReturn(currentLocation);
            when(rc.getAllySpawnLocations()).thenReturn(spawnLocations);
            when(rc.getTeam()).thenReturn(myTeam);
            when(rc.getRoundNum()).thenReturn(GameConstants.SETUP_ROUNDS + 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        catch (GameActionException e) {
//            e.printStackTrace();
//        }
    }
    