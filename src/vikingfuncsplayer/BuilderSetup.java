package vikingfuncsplayer;

import battlecode.common.*;

public class BuilderSetup {

    private static final int EXPLORE_ROUNDS = 125;
    private static Direction dir = Direction.NORTH;

    public static void runSetup(RobotController rc) throws GameActionException {
        if(rc.getRoundNum() < EXPLORE_ROUNDS) {
            //pickup flag if possible, explore randomly
            FlagInfo[] flags = rc.senseNearbyFlags(-1);
            for(FlagInfo flag : flags) {
                MapLocation flagLoc = flag.getLocation();
                if(rc.senseMapInfo(flagLoc).isSpawnZone() && rc.canPickupFlag(flagLoc)) {
                    rc.pickupFlag(flag.getLocation());
                }
            }
            Pathfind.explore(rc);
        }
        else {
            //try to place flag if it is far enough away from other flags
            if(rc.senseLegalStartingFlagPlacement(rc.getLocation())) {
                if(RobotPlayer.drop == 0){
                    RobotPlayer.drop = 1;
                    System.out.println(rc.getRoundNum());
                    System.out.println(rc.getLocation());
                }
                if(rc.canDropFlag(rc.getLocation())) rc.dropFlag(rc.getLocation());
            }
            //move towards flags and place defenses around them
            FlagInfo[] flags = rc.senseNearbyFlags(-1);

            FlagInfo targetFlag = null;
            for(FlagInfo flag : flags) {
                if(!flag.isPickedUp()) {
                    targetFlag = flag;
                    break;
                }
            }

            if(targetFlag != null) {
                
                if(rc.getLocation() == targetFlag.getLocation()){
                    dir = RobotPlayer.directions[rc.getRoundNum() % 8];
                    if(rc.canMove(dir)) {
                        rc.move(dir);
                    } else if(rc.canFill(rc.getLocation())) {
                        rc.fill(rc.getLocation());
                    }
                }
                else{
                    if(rc.getLocation().distanceSquaredTo(flags[0].getLocation()) < 9) {
                        if(rc.canBuild(TrapType.EXPLOSIVE, rc.getLocation())) {
                            rc.build(TrapType.EXPLOSIVE, rc.getLocation());
                        } 
                        else if(rc.canFill(rc.getLocation().add(dir))) {
                            rc.fill(rc.getLocation().add(dir));
                        }
                    }
                    Pathfind.moveTowards(rc, targetFlag.getLocation(), false);
                }
            }
                   
        }
    }
}
