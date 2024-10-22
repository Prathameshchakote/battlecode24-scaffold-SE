package vikingfuncsplayer;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import java.util.Random;



public class AttackSetup {

    

    public static void runSetup(RobotController rc) throws GameActionException {
        /** Array containing all the possible movement directions. */
    

        // Move and attack randomly if no objective.
        Direction dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
        MapLocation nextLoc = rc.getLocation().add(dir);
        //Check for if we're in the set up phase
        if (rc.getRoundNum() <= GameConstants.SETUP_ROUNDS || rc.getRoundNum() <= 2000)
        {  /* HERE WILL BE CODE FOR ATTACK DUCKS (ID % 3 = 0) */ 
            //check for nearby crumbs
            MapLocation[] crumbMap = rc.senseNearbyCrumbs(-1);
            //If there are crumbs, head to the nearest one
            if(crumbMap.length != 0)
            { 
                MapLocation firstCrumb = crumbMap[0];
                int checker = 1;
                //Check is crumb is available to be picked up through checking if the crumb location is fillable.
                if(crumbMap.length>= 1) 
                while(rc.canFill(firstCrumb))
                {
                    if(crumbMap.length >= checker + 1)
                    firstCrumb = crumbMap[checker];
                    checker++;
                }
                //If crumb is available, head to it.
                if(!rc.canFill(firstCrumb))
                {
                    dir = rc.getLocation().directionTo(firstCrumb);
                        if (rc.canMove(dir)){
                            rc.move(dir);
                        }
                }
            }
            //otherwise, move randomly until one is found.

            dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
                    if (rc.canMove(dir)){
                        rc.move(dir);
                    }
        }
        else if (rc.canAttack(nextLoc)){
            rc.attack(nextLoc);
            System.out.println("Take that! Damaged an enemy that was in our way!");
        }

    }
     
}
