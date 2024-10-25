package vikingfuncsplayer;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class BuilderSetup {

    static final Direction[] southbound ={
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST
    };

    static final Direction[] northbound ={
        Direction.NORTHEAST,
        Direction.NORTH,
        Direction.NORTHWEST
    };

    public static void runSetup(RobotController rc) throws GameActionException {
        String team = rc.getTeam().toString();
        if(team == "A"){
            for(int i = 0; i < 3; i++){
                if(rc.canMove(southbound[i])) rc.move(southbound[i]);
            }
        } else{
            for(int i = 0; i < 3; i++){
                if(rc.canMove(northbound[i])) rc.move(northbound[i]);
            }
        }

    }
    
}
