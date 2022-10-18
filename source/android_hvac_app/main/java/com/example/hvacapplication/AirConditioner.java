package com.example.hvacapplication;

/**
 * @class AirConditioner
 * @brief control air conditioner level and on off
 */
public class AirConditioner extends Unit implements ILevelAdjustable {

    private int mACLevel = 0;
    public static final int _LEVEL_OFFSET = 1;
    public static final int _LEVEL_MAX = 3;
    public static final int _LEVEL_MED = 2;
    public static final int _LEVEL_MIN = 1;
    public static final int _LEVEL_OFF = 0;


    /**
     * @param mX
     * @param mY
     * @param mZ
     * @param mRotationDegree
     * @brief creator
     */
    public AirConditioner(int mX, int mY, int mZ, int mRotationDegree) {
        super(mX, mY, mZ, mRotationDegree);
    }

    public int getmACLevel() {
        return mACLevel;
    }

    public void setmACLevel(int mACLevel) {
        this.mACLevel = mACLevel;
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
     * @brief control air conditioner
     * up level
     * @param value
     */
    @Override
    public void LevelUp(int value) {
        int aclevel = getmACLevel() + value;

        if(aclevel >= _LEVEL_MAX)
        {
            setmACLevel(_LEVEL_MAX);
        }
        else if(aclevel <= _LEVEL_OFF)
        {
            setmACLevel(_LEVEL_OFF);
        }
        else
        {
            setmACLevel(aclevel);
        }
    }

    /**
     * @brief control air conditioner
     * down level
     * @param value
     */
    @Override
    public void LevelDown(int value) {
        int aclevel = getmACLevel() - value;

        if(aclevel >= _LEVEL_MAX)
        {
            setmACLevel(_LEVEL_MAX);
        }
        else if(aclevel <= _LEVEL_OFF)
        {
            setmACLevel(_LEVEL_OFF);
        }
        else
        {
            setmACLevel(aclevel);
        }
    }

    /**
     * @brief control air conditioner
     * off a/c
     * @param value
     */
    @Override
    public void LevelOff(int value) {
        setmACLevel(_LEVEL_OFF);
    }
}
