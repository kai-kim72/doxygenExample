package com.example.hvacapplication;

/**
 * @class PassengerSeat
 * @brief handle Passenger seat's Heat/Cool/Lifting/backLocation
 */
public class PassengerSeat extends Seat {

    private final int _LEVEL_OFFSET = 1;
    private final int _OFF = 0;
    private final int _SEAT_LIFTING_MAX = 5;   ///< seat lifting upper level 0 ~ 5
    private final int _SEAT_BACK_DEGREE_MAX = 90;
    private final int _SEAT_BACK_DEGREE_MIN = (-(_SEAT_BACK_DEGREE_MAX));
    private final int _SEAT_BACK_DEGREE_OFFSET = 10;

    /**
     * @brief creator
     * @param mX
     * @param mY
     * @param mZ
     * @param mRotationDegree
     */
    public PassengerSeat(int mX, int mY, int mZ, int mRotationDegree) {
        super(mX, mY, mZ, mRotationDegree);
    }

    /**
     * @brief seat back moving forward
     * _leveldegree 0, +(10, 20 ,30,40,50,60,70,80, 90)
     * @param _leveldegree : 0 ~ 90
     */
    @Override
    public void moveSeatBackForward(int _leveldegree) {
        int readLevel = getSeatBackDegree() + _leveldegree;

        if(readLevel >= _SEAT_BACK_DEGREE_MAX)
        {
            setSeatBackDegree(_SEAT_BACK_DEGREE_MAX);
        }
        else if(readLevel <= _SEAT_BACK_DEGREE_MIN)
        {
            setSeatBackDegree(_SEAT_BACK_DEGREE_MIN);
        }
        else
        {
            setSeatBackDegree(getSeatBackDegree() + _leveldegree);
        }
    }

    /**
     * @brief seat back moving backward
     * _leveldegree 0, -(10, 20 ,30,40,50,60,70,80, 90)
     * @param _leveldegree : 0 ~ -90
     */
    @Override
    public void moveSeatBackBackward(int _leveldegree) {
        int readLevel = getSeatBackDegree() + _leveldegree;

        if(readLevel >= _SEAT_BACK_DEGREE_MAX)
        {
            setSeatBackDegree(_SEAT_BACK_DEGREE_MAX);
        }
        else if(readLevel <= _SEAT_BACK_DEGREE_MIN)
        {
            setSeatBackDegree(_SEAT_BACK_DEGREE_MIN);
        }
        else
        {
            setSeatBackDegree(getSeatBackDegree() + _leveldegree);
        }
    }

    /**
     * @brief provide Increase Interface for heat/cool level
     * @details adjusting level offset is 1 as default and can be changed
     *          by using overriding of new object implementation inheritance of LevelAdjustable
     *
     * @param _IFace : LevelAdjustable interface object
     */
    @Override
    public void Increase(ILevelAdjustable _IFace) {
        _IFace.LevelUp(_LEVEL_OFFSET);
    }

    /**
     * @brief provide Decrease Interface for heat/cool level
     * @details adjusting level offset is 1 as default and can be changed
     *          by using overriding of new object implementation inheritance of LevelAdjustable
     *
     * @param _IFace : LevelAdjustable interface object
     */
    @Override
    public void Decrease(ILevelAdjustable _IFace) {
        _IFace.LevelUp(_LEVEL_OFFSET);
    }

    /**
     * @brief provide Interface to off for heat/cool
     * @param _IFace
     */
    @Override
    public void Off(ILevelAdjustable _IFace) {
        _IFace.LevelOff(_OFF);
    }
}
