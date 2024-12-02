package vikingfuncsplayer;

import battlecode.common.*;

public class BuilderMain {
    //default direction to move
    private static Direction dir = Direction.NORTH;
    
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
            if(rc.getLocation() == targetFlag.getLocation()){
                dir = RobotPlayer.directions[rc.getRoundNum() % 8];
                if(rc.canMove(dir)) {
                    rc.move(dir);
                } else if(rc.canFill(rc.getLocation())) {
                    rc.fill(rc.getLocation());
                }
            }
            if(rc.getLocation().distanceSquaredTo(flags[0].getLocation()) < 9) {
                MapLocation waterLoc = rc.getLocation().add(RobotPlayer.directions[RobotPlayer.random.nextInt(8)]);
                if(rc.canDig(waterLoc)) rc.dig(waterLoc);
                Pathfind.moveTowards(rc, targetFlag.getLocation(), true);
            } else{
                Pathfind.moveTowards(rc, targetFlag.getLocation(), true);
            }
            
        }
    }
}
