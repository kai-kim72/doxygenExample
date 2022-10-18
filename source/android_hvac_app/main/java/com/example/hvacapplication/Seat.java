package com.example.hvacapplication;

/**
 * @class Seat  abstract class
 * @brief provide interfaces;
 *        move forward/backward/right/left/Rotation for Seat
 *        move forward/backward for seat back
 *        adjust heat / cool level for Seat
 */
public abstract class Seat extends Unit implements ISeatBackMovable {
    private int mHeat;  ///< heat level
    private int mCool;  ///< cool level
    private int SeatBackDegree = 0; ///< seat back move forward 0, 10, 20 ~90 : backward -10, -20 ~ -90

    public int getSeatBackDegree() {
        return SeatBackDegree;
    }

    public void setSeatBackDegree(int seatBackDegree) {
        SeatBackDegree = seatBackDegree;
    }

    /**
     * @brief creator
     * @param mX
     * @param mY
     * @param mZ
     * @param mRotationDegree
     */
    public Seat(int mX, int mY, int mZ, int mRotationDegree) {
        super(mX, mY, mZ, mRotationDegree);
    }

    /**
     * @brief move forward direction : +y
     * @param y
     */
    @Override
    public void moveForward(int y) {
        setmY(getmY() + y);
    }

    /**
     * @brief move backward direction : -y
     * @param y
     */
    @Override
    public void moveBackward(int y) {
        setmY(getmY() - y);
    }

    /**
     * @brief move left direction : -x
     * @param x
     */
    @Override
    public void moveLeft(int x) {
        setmX(getmX() - x);
    }

    /**
     * @brief move right direction : +x
     * @param x
     */
    @Override
    public void moveRight(int x) {
        setmZ(getmZ() + x);
    }

    /**
     * @brief rotate seat
     *        Direction Right : +90 / + 180
     *        Direction Left  : -90 / - 180
     * @param degree
     */
    @Override
    public void moveRotation(int degree) {
        setmRotationDegree(getmRotationDegree() + degree);
    }

    public int getmHeat() {
        return mHeat;
    }

    public void setmHeat(int mHeat) {
        this.mHeat = mHeat;
    }

    public int getmCool() {
        return mCool;
    }

    public void setmCool(int mCool) {
        this.mCool = mCool;
    }

    /**
     * @brief Seat back forward Interface
     * _leveldegree[in] : 0, 10, 20 ,30,40,50,60,70,80, 90
     */
    public abstract void moveSeatBackForward(int _leveldegree);
    /**
     * @brief Seat back backward Interface
     * _leveldegree[in] : 0, -10, -20 ,-30,-40,-50,-60,-70,-80, -90
     */
    public abstract void moveSeatBackBackward(int _leveldegree);

    /**
     * @brief use below API in order to adjust level of the heat/cool of this seat
     * @param _IFace
     */
    public abstract void Increase(ILevelAdjustable _IFace );
    public abstract void Decrease(ILevelAdjustable _IFace );
    public abstract void Off(ILevelAdjustable _IFace );

}
