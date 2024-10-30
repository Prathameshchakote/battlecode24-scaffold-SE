package vikingfuncsplayer;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;
import battlecode.common.FlagInfo;
import java.util.Random;



public class AttackMainPhase {

        public static void attackPhase(RobotController rc) throws GameActionException
    {  
        //if the set up phase is over, its time to push to the opponents side.
        
        //Intital plans are to search for enemy flags, take it, and bring it home, attacking any nearby ducks and targetting any ducks that take our flag. 

        Direction dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
        MapLocation nextLoc;

        //What team are we?
        Team ourTeam = rc.getTeam();
        //if theres a flag, go towards it ASAP
        FlagInfo flagSense[] = rc.senseNearbyFlags(-1,ourTeam.opponent());
        int counter  = 0; 
        if(flagSense.length >= counter)
        {
            if(!flagSense[counter].isPickedUp()){
                dir = rc.getLocation().directionTo(flagSense[counter].getLocation());
            }
            else {
                counter++;
            }
            
        }
        FlagInfo defenseSense[] = rc.senseNearbyFlags(4,ourTeam);
        int defensecounter = 0;
        //if theres a person carrying our flag, target carrier.
        if(defenseSense.length>=defensecounter){
            if(defenseSense[counter].isPickedUp())
                dir = rc.getLocation().directionTo(defenseSense[counter].getLocation());

        }
        //if an enemy is in our way, attack them.
        nextLoc = rc.getLocation().add(dir);
        if (rc.canAttack(nextLoc)){
            rc.attack(nextLoc);
            System.out.println("Take that! Damaged an enemy that was in our way!");
        }
          //otherwise, move randomly until one is found.First filling if needed.
        if(rc.canFill(nextLoc)){
            rc.fill(nextLoc);
        }
        
        if (rc.canMove(dir)){
                rc.move(dir);
        }

    }
}
