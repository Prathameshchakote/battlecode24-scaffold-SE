package vikingfuncsplayer;

import battlecode.common.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public strictfp class RobotPlayer {

    static final Random rng = new Random();
    public static Random random = null;
    public static int drop = 0;

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
        GlobalUpgrade attackUpgrade = GlobalUpgrade.ATTACK; 
        while(true){
            try{
                /* sets up random */
                if(random == null) random = new Random(rc.getID());
                /* testing to see if duck has been spawned */
                if(spawned == 0||rc.isSpawned()== false){
                    trySpawn(rc);
                    if(rc.isSpawned() ) {
                        spawned = 1;
                        FlagInfo[] flags = rc.senseNearbyFlags(-1);
                        for(FlagInfo flag : flags) {
                            MapLocation flagLoc = flag.getLocation();
                            if(rc.canPickupFlag(flagLoc)){
                                type = 4;
                                System.out.println("I am a bulder.");
                            }
                            else {
                                type = rc.getID() % 3;
                            }
                        }
                    }
                }
                /* logic for the spawned duck based on type and round */
                if(rc.isSpawned()) {
                    
                    round = rc.getRoundNum();
                    
                    if(round <= GameConstants.SETUP_ROUNDS) {
                        if(type == 4){
                            BuilderSetup.runSetup(rc);
                        }
                        if(type == 0 || type == 1){
                            AttackSetup.runSetup(rc);
                        } else{
                            HealerSetup.runSetup(rc);
                        }
                    } else {
                        /* Builder Duck */
                        if(type == 4){
                            BuilderMain.runMain(rc);
                        }
                        /* Attack Duck */
                        if(type == 0 || type == 1){
                            AttackMainPhase.attackPhase(rc);
                        /* healer duck */
                        } else{
                            HealerSetup.runSetup(rc);
                        }
                    }
                    if(rc.canBuyGlobal(attackUpgrade))
                    {
                        rc.buyGlobal(attackUpgrade);
                    }
                }
            } finally {
                
            }
        }
    }

    private static void trySpawn(RobotController rc) throws GameActionException {
        MapLocation[] locations = rc.getAllySpawnLocations();
        int a = random.nextInt(2);
        if(a == 1){
            for(MapLocation loc : locations) {
                if(rc.canSpawn(loc)) {
                    rc.spawn(loc);
                    break;
                }
            }
        }
        else{
            for(int x=locations.length-1; x >=0; x--) {
                if(rc.canSpawn(locations[x])) {
                    rc.spawn(locations[x]);
                    break;
                }
            }
        }
    }
   
}
