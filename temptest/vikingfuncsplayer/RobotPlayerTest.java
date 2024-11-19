package vikingfuncsplayer;


import static org.junit.Assert.*;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import org.junit.Test;

import java.util.Random;

public class RobotPlayerTest {

    @Test
    public void testSanity() {
        assertEquals(2, 1+1);
    }

    @Test
    public void testMoveRandom() throws GameActionException {
        MockRobotController rc = new MockRobotController();
        // Assuming directions and rng are properly defined
        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        Random rng = new Random();

        // Test moving in a random direction
        Direction dir = directions[rng.nextInt(directions.length)];
        boolean canMove = rc.canMove(dir);
        if (canMove) {
            rc.move(dir);
        }

        // Verify that the move was successful (if possible)
        assertEquals("Robot should move if canMove returns true", canMove, rc.hasMoved());
    }
}
