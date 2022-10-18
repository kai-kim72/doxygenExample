package com.example.hvacapplication;

/**
 * @class SteerWheel
 * @brief control steer Wheel location and warm, and volume level
 */
public class SteerWheel extends Wheel implements IVolumeable, ILevelAdjustable {

    public static final int _WHEEL_UP_MAX = 3;
    public static final int _WHEEL_DOWN_MAX = (-_WHEEL_UP_MAX);
    public static final int _LEVEL_OFFSET = 1;
    public static final int _OFF = 0;

    public static final int _WHEEL_WARM_LEVEL_MAX = 2;
    public static final int _WHEEL_WARM_LEVEL_OFF = 0;

    private int mWheelWarm; ///< wheel warm level

    public int getmWheelWarm() {
        return mWheelWarm;
    }

    public void setmWheelWarm(int mWheelWarm) {
        this.mWheelWarm = mWheelWarm;
    }

    public int getmVolumeLevel() {
        return mVolumeLevel;
    }

    public void setmVolumeLevel(int mVolumeLevel) {
        this.mVolumeLevel = mVolumeLevel;
    }

    private int mVolumeLevel;


    /**
     * @param mX
     * @param mY
     * @param mZ
     * @param mRotationDegree
     * @brief creator
     */
    public SteerWheel(int mX, int mY, int mZ, int mRotationDegree) {
        super(mX, mY, mZ, mRotationDegree);
    }

    @Override
    public void moveForward(int y) {

    }

    @Override
    public void moveBackward(int y) {

    }

    @Override
    public void moveLeft(int x) {

    }

    @Override
    public void moveRight(int x) {

    }

    @Override
    public void moveRotation(int degree) {

    }

    /**
     * @breif control Wheel location
     * lift Wheel location up
     */
    @Override
    void WheelLocationUp() {
        int WheelLocaLvl = getmWheelLiftLevel() + _LEVEL_OFFSET;
        if(WheelLocaLvl >= _WHEEL_UP_MAX)
        {
            setmWheelLiftLevel(_WHEEL_UP_MAX);
        }
        else if(WheelLocaLvl <= _WHEEL_DOWN_MAX )
        {
            setmWheelLiftLevel(_WHEEL_UP_MAX);
        }
        else
        {
            setmWheelLiftLevel(WheelLocaLvl);
        }
    }

    /**
     * @breif control Wheel location
     * lift Wheel location down
     */
    @Override
    void WheelLocationDown() {
        int WheelLocaLvl = getmWheelLiftLevel() - _LEVEL_OFFSET;
        if(WheelLocaLvl >= _WHEEL_UP_MAX)
        {
            setmWheelLiftLevel(_WHEEL_UP_MAX);
        }
        else if(WheelLocaLvl <= _WHEEL_DOWN_MAX )
        {
            setmWheelLiftLevel(_WHEEL_UP_MAX);
        }
        else
        {
            setmWheelLiftLevel(WheelLocaLvl);
        }
    }

    /**
     * @brief control volume level
     *        increase volume level
     */
    @Override
    public void VolumeUp() {

    }
    /**
     * @brief control volume level
     *        decrease volume level
     */
    @Override
    public void VolumeDown() {


    }
    /**
     * @brief control volume level
     *        mute volume
     */
    @Override
    public void VolumeMute() {

    }

    /**
     * @brief control wheel warm level
     *        up wheel warm level
     * @param value
     */
    @Override
    public void LevelUp(int value) {
        int warmlevel = getmWheelWarm() + value;
        if(warmlevel >= _WHEEL_WARM_LEVEL_MAX)
        {
            setmWheelWarm(_WHEEL_WARM_LEVEL_MAX);
        }
        else if(warmlevel <= _WHEEL_WARM_LEVEL_OFF)
        {
            setmWheelWarm(_WHEEL_WARM_LEVEL_OFF);
        }
        else
        {
            setmWheelWarm(warmlevel);
        }
    }

    /**
     * @brief control wheel warm level
     *        down wheel warm level
     * @param value
     */
    @Override
    public void LevelDown(int value) {
        int warmlevel = getmWheelWarm() - value;
        if(warmlevel >= _WHEEL_WARM_LEVEL_MAX)
        {
            setmWheelWarm(_WHEEL_WARM_LEVEL_MAX);
        }
        else if(warmlevel <= _WHEEL_WARM_LEVEL_OFF)
        {
            setmWheelWarm(_WHEEL_WARM_LEVEL_OFF);
        }
        else
        {
            setmWheelWarm(warmlevel);
        }
    }

    /**
     * @brief control wheel warm level
     *        off wheel warm
     * @param value
     */
    @Override
    public void LevelOff(int value) {
        setmWheelWarm(_WHEEL_WARM_LEVEL_OFF);
    }

    /**
     * @brief control wheel warm level by using implemented class of LevelAdjustable interface
     *        warm up
     * @param obj
     */
    public void WheelWarmUp(ILevelAdjustable obj)
    {
        obj.LevelUp(_LEVEL_OFFSET);
    }

    /**
     * @brief control wheel warm level by using implemented class of LevelAdjustable interface
     *        warm down
     * @param obj
     */
    public void WheelWarmDown(ILevelAdjustable obj)
    {
        obj.LevelDown(_LEVEL_OFFSET);
    }

    /**
     * @brief control wheel warm level by using implemented class of LevelAdjustable interface
     *        off wheel warm
     * @param obj
     */
    public void WheelWarmOff(ILevelAdjustable obj)
    {
        obj.LevelOff(_OFF);
    }

}
