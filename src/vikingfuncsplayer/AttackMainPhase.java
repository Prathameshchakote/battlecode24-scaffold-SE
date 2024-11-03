package vikingfuncsplayer;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;
import battlecode.common.FlagInfo;
import battlecode.common.RobotInfo;
import java.util.Random;



public class AttackMainPhase {

        static final Random rng = new Random(6147);
        public static void attackPhase(RobotController rc) throws GameActionException
    {  
        
        //if the set up phase is over, its time to push to the opponents side.
        
        //Intital plans are to search for enemy flags, take it, and bring it home, attacking any nearby ducks and targetting any ducks that take our flag. 

        Direction dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
        MapLocation nextLoc;
        MapLocation[] spawnLocs = rc.getAllySpawnLocations();
        
        //What team are we?
        Team ourTeam = rc.getTeam();
        //head to 0,0 to start the horde.
        MapLocation origin = new MapLocation(0,0);
        dir = rc.getLocation().directionTo(origin);

        //if near origin, start moving around the edge of the map.

        if(rc.getLocation().isWithinDistanceSquared(origin, 4))
        {
            if(spawnLocs[0].x > spawnLocs[0].y)
                dir = RobotPlayer.directions[0];
            else
                dir= RobotPlayer.directions[4];
        }
        FlagInfo defenseSense[] = rc.senseNearbyFlags(-1,ourTeam);
        int defensecounter = 0;
        //if theres a person carrying our flag, target carrier.
        if(defenseSense.length> defensecounter){
            if(defenseSense[defensecounter].isPickedUp())
                dir = rc.getLocation().directionTo(defenseSense[defensecounter].getLocation());

        }
        //if theres a flag, go towards it ASAP
        FlagInfo flagSense[] = rc.senseNearbyFlags(-1,ourTeam.opponent());
        int counter  = 0; 
        if(flagSense.length > counter)
        {
            if(!flagSense[counter].isPickedUp()){
                //dir = rc.getLocation().directionTo(flagSense[counter].getLocation());
                Pathfind.moveTowards(rc, flagSense[counter].getLocation(), true);
            }
            else {
                counter++;
            }
            
        }
       
        
        if(rc.canPickupFlag(rc.getLocation()))
        {
            rc.pickupFlag(rc.getLocation());
        }
        //if we have the flag, its time to GO HOME
        if (rc.hasFlag() && rc.getRoundNum() >= GameConstants.SETUP_ROUNDS){
            MapLocation firstLoc = spawnLocs[0];
            dir = rc.getLocation().directionTo(firstLoc);

        }
        //Make code to attack ducks in radius
        RobotInfo enemies[] = rc.senseNearbyRobots(2, rc.getTeam().opponent());
        for(RobotInfo enemie : enemies)
        {
            if(rc.canAttack(enemie.location) && !rc.hasFlag())
            rc.attack(enemie.location);
            System.out.println("Take that! Damaged an enemy that was nearby!");
        }

        //if an enemy is in our way, attack them.
        nextLoc = rc.getLocation().add(dir);
        if (rc.canAttack(nextLoc)&& !rc.hasFlag()){
            rc.attack(nextLoc);
            System.out.println("Take that! Damaged an enemy that was in our way!");
        }
          //otherwise, we BEGIN THE HORDE RUSH, first filling if needed.
            nextLoc = rc.getLocation().add(dir);
            if(rc.canFill(nextLoc)){
                rc.fill(nextLoc);
            }
            
            if (rc.canMove(dir)){
                    rc.move(dir);
            }
            else{
                for(int i=0; i<8;i++)
                {
                    nextLoc = rc.getLocation().add(dir);
                    if(rc.canFill(nextLoc)){
                        rc.fill(nextLoc);
                    }
                    if(rc.canMove(dir))
                        {
                            rc.move(dir);
                            break;
                        }
                    dir=RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
                    
                }
            }
            

        //if jailed, try respawning.
        if(rc.getHealth() == 0)
        {
              
                    // Pick a random spawn location to attempt spawning in.
                    MapLocation randomLoc = spawnLocs[rng.nextInt(spawnLocs.length)];
                    if (rc.canSpawn(randomLoc)) rc.spawn(randomLoc);
        }

    }
}
