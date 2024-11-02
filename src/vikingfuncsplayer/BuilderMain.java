package vikingfuncsplayer;

import battlecode.common.*;

public class BuilderMain {
    
    public static void runMain(RobotController rc) throws GameActionException {

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
            Pathfind.moveTowards(rc, targetFlag.getLocation(), false);
            if(rc.getLocation().distanceSquaredTo(flags[0].getLocation()) < 9) {
                if(rc.canBuild(TrapType.EXPLOSIVE, rc.getLocation())) {
                    rc.build(TrapType.EXPLOSIVE, rc.getLocation());
                }
                else {
                    MapLocation waterLoc = rc.getLocation().add(RobotPlayer.directions[RobotPlayer.random.nextInt(8)]);
                    if(rc.canDig(waterLoc)) rc.dig(waterLoc);
                }
            }
        }
        else Pathfind.explore(rc);

    }

}
