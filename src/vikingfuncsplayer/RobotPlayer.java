package vikingfuncsplayer;

import battlecode.common.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public strictfp class RobotPlayer {

    static final Random rng = new Random(6147);
    public static Random random = null;

    static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    };
    
    public static void run(RobotController rc) throws GameActionException {
        /* initalizing varabiles */
        int spawned = 0;
        int type = 0;
        int round = 0;
        while(true){
            try{
                /* testing to see if duck has been spawned */
                if(spawned == 0){
                    trySpawn(rc);
                    if(rc.isSpawned()) {
                        spawned = 1;
                        type = rc.getID() % 3;
                    }
                }
                /* logic for the spawned duck based on type and round */
                if(rc.isSpawned()) {
                    
                    round = rc.getRoundNum();
                    
                    if(round <= GameConstants.SETUP_ROUNDS) {
                        if(type == 0 || type == 1){
                            AttackSetup.runSetup(rc);
                        } else{
                            HealerSetup.runSetup(rc);
                        }
                    } else {
                        if(type == 0 || type == 1){
                            Setup.runSetup(rc);
                        } else{
                            Setup.runSetup(rc);
                        }
                    }
                    
                }
            } finally {
                
            }
        }
    }

    private static void trySpawn(RobotController rc) throws GameActionException {
        MapLocation[] locations = rc.getAllySpawnLocations();
        for(MapLocation loc : locations) {
            if(rc.canSpawn(loc)) {
                rc.spawn(loc);
                break;
            }
        }
    }
   
}
