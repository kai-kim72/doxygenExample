package com.example.hvacapplication;

/**
 * @class Wheel abstract class
 * @brief can move Wheel location up or down
 *        center 0, down -1, -2, -3 up 1, 2, 3
 */
public abstract class Wheel extends Unit
{
    private int mWheelLiftLevel = 0;    ///< Wheel location up and down level

    /**
     * @param mX
     * @param mY
     * @param mZ
     * @param mRotationDegree
     * @brief creator
     */
    public Wheel(int mX, int mY, int mZ, int mRotationDegree) {
        super(mX, mY, mZ, mRotationDegree);
    }

    public int getmWheelLiftLevel() {
        return mWheelLiftLevel;
    }

    public void setmWheelLiftLevel(int mWheelLiftLevel) {
        this.mWheelLiftLevel = mWheelLiftLevel;
    }

    /**
     * @brief control wheel location up
     */
    abstract void WheelLocationUp();

    /**
     * @brief control wheel location down
     */
    abstract void WheelLocationDown();

}
