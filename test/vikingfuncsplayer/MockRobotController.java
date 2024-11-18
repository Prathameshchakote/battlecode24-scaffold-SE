package vikingfuncsplayer;
import battlecode.common.*;

public  class MockRobotController implements RobotController {
    public boolean hasMoved;
    public boolean canMoveResult = true;

    public MockRobotController() {
        this.canMoveResult = canMoveResult;
        this.hasMoved = false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public int getRoundNum() {
        return 0;
    }

    @Override
    public int getMapWidth() {
        return 0;
    }

    @Override
    public int getMapHeight() {
        return 0;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public Team getTeam() {
        return null;
    }