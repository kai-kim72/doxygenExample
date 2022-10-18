package com.example.hvacapplication;

/**
 * @class Unit abstract class
 * @brief this base class have attribute location coordination mX,mY,mZ and Rotation degree
 */
public abstract class Unit {
    private int mX,mY,mZ;  ///< forward + Y / backward -Y
    private int mRotationDegree;  ///< unit rotation direction :  forward 0, Left/Right (-90/90), backward (-180/180)

    /**
     * @brief creator
     * @param mX
     * @param mY
     * @param mZ
     * @param mRotationDegree
     */
    public Unit(int mX, int mY, int mZ, int mRotationDegree) {
        this.mX = mX;
        this.mY = mY;
        this.mZ = mZ;
        this.mRotationDegree = mRotationDegree;
    }

    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getmY() {
        return mY;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }

    public int getmZ() {
        return mZ;
    }

    public void setmZ(int mZ) {
        this.mZ = mZ;
    }

    public int getmRotationDegree() {
        return mRotationDegree;
    }

    public void setmRotationDegree(int mRotationDegree) {
        this.mRotationDegree = mRotationDegree;
    }

    /**
     * @brief move forward direction : +y
     * @param y
     */
    public abstract void moveForward(int y);

    /**
     * @brief move backward direction : -y
     * @param y
     */
    public abstract void moveBackward(int y);

    /**
     * @brief move left direction : -x
     * @param x
     */
    public abstract void moveLeft(int x);

    /**
     * @brief move right direction : +x
     * @param x
     */
    public abstract void moveRight(int x);

    /**
     * @brief move rotation direction : forward 0, left -90 , right 90 backward -180/ +180
     * @param degree
     */
    public abstract void moveRotation(int degree);

}
