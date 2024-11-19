package examplefuncsplayer;
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


    @Override
    public boolean canSenseRobotAtLocation(MapLocation loc) {
        return false;
    }

    @Override
    public RobotInfo senseRobotAtLocation(MapLocation loc) throws GameActionException {
        return null;
    }

    @Override
    public boolean canSenseRobot(int id) {
        return false;
    }

    @Override
    public RobotInfo senseRobot(int id) throws GameActionException {
        return null;
    }

    @Override
    public RobotInfo[] senseNearbyRobots() {
        return new RobotInfo[0];
    }

    @Override
    public RobotInfo[] senseNearbyRobots(int radiusSquared) throws GameActionException {
        return new RobotInfo[0];
    }

    @Override
    public RobotInfo[] senseNearbyRobots(int radiusSquared, Team team) throws GameActionException {
        return new RobotInfo[0];
    }

    @Override
    public RobotInfo[] senseNearbyRobots(MapLocation center, int radiusSquared, Team team) throws GameActionException {
        return new RobotInfo[0];
    }


    @Override
    public MapLocation[] senseNearbyCrumbs(int radiusSquared) throws GameActionException {
        return new MapLocation[0];
    }

    @Override
    public boolean sensePassability(MapLocation loc) throws GameActionException {
        return false;
    }

    @Override
    public MapInfo senseMapInfo(MapLocation loc) throws GameActionException {
        return null;
    }

    @Override
    public MapInfo[] senseNearbyMapInfos() {
        return new MapInfo[0];
    }

    @Override
    public MapInfo[] senseNearbyMapInfos(int radiusSquared) throws GameActionException {
        return new MapInfo[0];
    }

    @Override
    public MapInfo[] senseNearbyMapInfos(MapLocation center) throws GameActionException {
        return new MapInfo[0];
    }

    @Override
    public MapInfo[] senseNearbyMapInfos(MapLocation center, int radiusSquared) throws GameActionException {
        return new MapInfo[0];
    }

    @Override
    public FlagInfo[] senseNearbyFlags(int radiusSquared) throws GameActionException {
        return new FlagInfo[0];
    }
    @Override
    public FlagInfo[] senseNearbyFlags(int radiusSquared, Team team) throws GameActionException {
        return new FlagInfo[0];
    }

    @Override
    public MapLocation[] senseBroadcastFlagLocations() {
        return new MapLocation[0];
    }

    @Override
    public boolean senseLegalStartingFlagPlacement(MapLocation loc) throws GameActionException {
        return false;
    }

    @Override
    public MapLocation adjacentLocation(Direction dir) {
        return null;
    }

    @Override
    public MapLocation[] getAllLocationsWithinRadiusSquared(MapLocation center, int radiusSquared) throws GameActionException {
        return new MapLocation[0];
    }

    @Override
    public boolean isSpawned() {
        return false;
    }

    @Override
    public boolean isActionReady() {
        return false;
    }

    @Override
    public int getActionCooldownTurns() {
        return 0;
    }


    @Override
    public boolean isMovementReady() {
        return false;
    }

    @Override
    public int getMovementCooldownTurns() {
        return 0;
    }

    @Override
    public boolean canMove(Direction dir) {
        return false;
    }

    @Override
    public void move(Direction dir) throws GameActionException {

    }

    @Override
    public MapLocation[] getAllySpawnLocations() {
        return new MapLocation[0];
    }

    @Override
    public boolean canSpawn(MapLocation loc) {
        return false;
    }

    @Override
    public void spawn(MapLocation loc) throws GameActionException {

    }

    @Override
    public boolean canDig(MapLocation loc) {
        return false;
    }

    @Override
    public void dig(MapLocation loc) throws GameActionException {

    }

    @Override
    public boolean canFill(MapLocation loc) {
        return false;
    }

    @Override
    public void fill(MapLocation loc) throws GameActionException {

    }

    @Override
    public boolean canBuild(TrapType building, MapLocation loc) {
        return false;
    }

    @Override
    public void build(TrapType building, MapLocation loc) throws GameActionException {

    }

    @Override
    public int getAttackDamage() {
        return 0;
    }

    @Override
    public boolean canAttack(MapLocation loc) {
        return false;
    }

    @Override
    public void attack(MapLocation loc) throws GameActionException {

    }

    @Override
    public int getHealAmount() {
        return 0;
    }

    @Override
    public boolean canHeal(MapLocation loc) {
        return false;
    }

    @Override
    public void heal(MapLocation loc) throws GameActionException {

    }

    @Override
    public boolean hasFlag() {
        return false;
    }

    @Override
    public boolean canPickupFlag(MapLocation loc) {
        return false;
    }

    @Override
    public void pickupFlag(MapLocation loc) throws GameActionException {

    }

    @Override
    public boolean canDropFlag(MapLocation loc) {
        return false;
    }

    @Override
    public void dropFlag(MapLocation loc) throws GameActionException {

    }

    @Override
    public int readSharedArray(int index) throws GameActionException {
        return 0;
    }

    @Override
    public boolean canWriteSharedArray(int index, int value) {
        return false;
    }

    @Override
    public void writeSharedArray(int index, int value) throws GameActionException {

    }

    @Override
    public boolean canBuyGlobal(GlobalUpgrade ug) {
        return false;
    }

    @Override
    public void buyGlobal(GlobalUpgrade ug) throws GameActionException {

    }

    @Override
    public GlobalUpgrade[] getGlobalUpgrades(Team team) {
        return new GlobalUpgrade[0];
    }

    @Override
    public void resign() {

    }

    @Override
    public void setIndicatorString(String string) {

    }

    @Override
    public void setIndicatorDot(MapLocation loc, int red, int green, int blue) {

    }

    @Override
    public void setIndicatorLine(MapLocation startLoc, MapLocation endLoc, int red, int green, int blue) {

    }
}