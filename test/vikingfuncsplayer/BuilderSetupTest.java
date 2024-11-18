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

    @Test
    public void testRunSetup_EarlyGame_WithPickableFlag() throws GameActionException {
        // Setup - Early game with pickable flag
        when(rc.getRoundNum()).thenReturn(50);
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{mockFlag});
        when(rc.senseMapInfo(any(MapLocation.class))).thenReturn(mockMapInfo);
        when(mockMapInfo.isSpawnZone()).thenReturn(true);
        when(rc.canPickupFlag(any(MapLocation.class))).thenReturn(true);

        // Execute
        BuilderSetup.runSetup(rc);

        // Verify flag pickup attempt
        verify(rc).pickupFlag(mockFlag.getLocation());
    }

    @Test
    public void testRunSetup_LateGame_LegalFlagPlacement() throws GameActionException {
        // Setup - Late game with legal flag placement
        when(rc.getRoundNum()).thenReturn(150);
        when(rc.senseLegalStartingFlagPlacement(currentLocation)).thenReturn(true);
        when(rc.canDropFlag(currentLocation)).thenReturn(true);
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{});

        // Execute
        BuilderSetup.runSetup(rc);

        // Verify flag drop attempt
        verify(rc).dropFlag(currentLocation);
    }

    @Test
    public void testRunSetup_LateGame_WithNearbyFlag_BuildExplosive() throws GameActionException {
        // Setup - Late game near flag with ability to build trap
        when(rc.getRoundNum()).thenReturn(150);
        when(rc.senseLegalStartingFlagPlacement(currentLocation)).thenReturn(false);
        FlagInfo statinaryFlag = mock(FlagInfo.class);
        when(statinaryFlag.isPickedUp()).thenReturn(false);
        when(statinaryFlag.getLocation()).thenReturn(new MapLocation(2, 2));
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{statinaryFlag});
        when(rc.getLocation().distanceSquaredTo(any(MapLocation.class))).thenReturn(4);
        when(rc.canBuild(TrapType.EXPLOSIVE, currentLocation)).thenReturn(true);

        // Execute
        BuilderSetup.runSetup(rc);

        // Verify explosive trap building
        verify(rc).build(TrapType.EXPLOSIVE, currentLocation);
    }

    @Test
    public void testRunSetup_LateGame_WithNearbyFlag_Dig() throws GameActionException {
        // Setup - Late game near flag, can't build trap but can dig
        when(rc.getRoundNum()).thenReturn(150);
        when(rc.senseLegalStartingFlagPlacement(currentLocation)).thenReturn(false);
        FlagInfo stationaryFlag = mock(FlagInfo.class);
        when(stationaryFlag.isPickedUp()).thenReturn(false);
        when(stationaryFlag.getLocation()).thenReturn(new MapLocation(2, 2));
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{stationaryFlag});
        when(rc.getLocation().distanceSquaredTo(any(MapLocation.class))).thenReturn(4);
        when(rc.canBuild(TrapType.EXPLOSIVE, currentLocation)).thenReturn(false);
        when(rc.canDig(any(MapLocation.class))).thenReturn(true);

        // Execute
        BuilderSetup.runSetup(rc);

        // Verify digging attempt
        verify(rc).canDig(any(MapLocation.class));
        verify(rc, times(1)).dig(any(MapLocation.class));
    }

    @Test
    public void testRunSetup_LateGame_NoFlags_Explore() throws GameActionException {
        // Setup - Late game with no flags around
        when(rc.getRoundNum()).thenReturn(150);
        when(rc.senseLegalStartingFlagPlacement(currentLocation)).thenReturn(false);
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{});

        // Execute
        BuilderSetup.runSetup(rc);

        // Verify exploration happens when no flags are nearby
        verify(rc, atLeastOnce()).senseNearbyFlags(-1);
    }

    @Test
    public void testRunSetup_LateGame_AllFlagsPickedUp() throws GameActionException {
        // Setup - Late game with all flags picked up
        when(rc.getRoundNum()).thenReturn(150);
        when(rc.senseLegalStartingFlagPlacement(currentLocation)).thenReturn(false);
        FlagInfo pickedUpFlag = mock(FlagInfo.class);
        when(pickedUpFlag.isPickedUp()).thenReturn(true);
        when(rc.senseNearbyFlags(-1)).thenReturn(new FlagInfo[]{pickedUpFlag});

        // Execute
        BuilderSetup.runSetup(rc);

        // Verify behavior when all flags are picked up
        verify(pickedUpFlag).isPickedUp();
    }