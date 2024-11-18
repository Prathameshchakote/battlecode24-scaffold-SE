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

    @Test
    public void testFlagDefense() throws GameActionException {
        // Setup flag defense scenario
        MapLocation flagLocation = new MapLocation(11, 11);
        FlagInfo pickedUpFlag = mock(FlagInfo.class);
        when(pickedUpFlag.isPickedUp()).thenReturn(true);
        when(pickedUpFlag.getLocation()).thenReturn(flagLocation);
        when(rc.senseNearbyFlags(-1, myTeam)).thenReturn(new FlagInfo[]{pickedUpFlag});
        when(rc.canMove(any(Direction.class))).thenReturn(true);

        AttackMainPhase.attackPhase(rc);

        verify(rc).getTeam();
        verify(rc).senseNearbyFlags(-1, myTeam);
        verify(rc, atLeastOnce()).canMove(any(Direction.class));
    }

    @Test
    public void testFlagCapture() throws GameActionException {
        // Setup flag capture scenario
        MapLocation enemyFlagLocation = new MapLocation(12, 12);
        FlagInfo enemyFlag = mock(FlagInfo.class);
        when(enemyFlag.isPickedUp()).thenReturn(false);
        when(enemyFlag.getLocation()).thenReturn(enemyFlagLocation);
        when(rc.senseNearbyFlags(-1, enemyTeam)).thenReturn(new FlagInfo[]{enemyFlag});
        when(rc.canMove(any(Direction.class))).thenReturn(true);
        when(rc.canPickupFlag(any(MapLocation.class))).thenReturn(true);

        AttackMainPhase.attackPhase(rc);

        verify(rc).senseNearbyFlags(-1, enemyTeam);
        verify(rc).canPickupFlag(rc.getLocation());
    }

    @Test
    public void testEnemyAttack() throws GameActionException {
        // Setup enemy attack scenario
        MapLocation enemyLocation = new MapLocation(11, 11);
        RobotInfo enemy = mock(RobotInfo.class);
        when(enemy.getLocation()).thenReturn(enemyLocation);
        when(rc.senseNearbyRobots(2, enemyTeam)).thenReturn(new RobotInfo[]{enemy});
        when(rc.canAttack(enemyLocation)).thenReturn(true);
        when(rc.hasFlag()).thenReturn(false);

        AttackMainPhase.attackPhase(rc);

        verify(rc).senseNearbyRobots(2, enemyTeam);
        verify(rc).canAttack(enemyLocation);
        verify(rc).attack(enemyLocation);
    }

    @Test
    public void testMovementWithObstacles() throws GameActionException {
        // Setup movement with obstacles scenario
        when(rc.canMove(any(Direction.class))).thenReturn(false);
        when(rc.canFill(any(MapLocation.class))).thenReturn(true);
        when(rc.sensePassability(any(MapLocation.class))).thenReturn(false);

        AttackMainPhase.attackPhase(rc);

        verify(rc, atLeastOnce()).canMove(any(Direction.class));
        verify(rc, atLeastOnce()).canFill(any(MapLocation.class));
        verify(rc, atLeastOnce()).fill(any(MapLocation.class));
    }

    @Test
    public void testRespawning() throws GameActionException {
        // Setup respawning scenario
        when(rc.getHealth()).thenReturn(0);
        when(rc.canSpawn(any(MapLocation.class))).thenReturn(true);

        AttackMainPhase.attackPhase(rc);

        verify(rc).getHealth();
        verify(rc, atLeastOnce()).canSpawn(any(MapLocation.class));
        verify(rc).spawn(any(MapLocation.class));
    }

    @Test
    public void testFlagReturn() throws GameActionException {
        // Setup flag return scenario
        when(rc.hasFlag()).thenReturn(true);
        when(rc.canMove(any(Direction.class))).thenReturn(true);

        AttackMainPhase.attackPhase(rc);

        verify(rc).hasFlag();
        verify(rc, atLeastOnce()).canMove(any(Direction.class));
        verify(rc, atLeastOnce()).move(any(Direction.class));
    }

    @Test
    public void testHordeMarchBehavior() throws GameActionException {
        // Setup horde march scenario
        MapLocation nearOrigin = new MapLocation(2, 2);
        when(rc.getLocation()).thenReturn(nearOrigin);
        when(rc.canMove(any(Direction.class))).thenReturn(true);

        AttackMainPhase.attackPhase(rc);

        verify(rc, atLeastOnce()).getLocation();
        verify(rc, atLeastOnce()).canMove(any(Direction.class));
        verify(rc, atLeastOnce()).move(any(Direction.class));
    }
}