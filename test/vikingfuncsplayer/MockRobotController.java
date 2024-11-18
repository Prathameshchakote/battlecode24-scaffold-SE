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

    @Override
    public MapLocation getLocation() {
        return null;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getExperience(SkillType skill) {
        return 0;
    }

    @Override
    public int getLevel(SkillType skill) {
        return 0;
    }

    @Override
    public int getCrumbs() {
        return 0;
    }

    @Override
    public boolean onTheMap(MapLocation loc) {
        return false;
    }

    @Override
    public boolean canSenseLocation(MapLocation loc) {
        return false;
    }

    @Override
    public boolean isLocationOccupied(MapLocation loc) throws GameActionException {
        return false;
    }