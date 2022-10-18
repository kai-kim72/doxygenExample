package com.example.hvacapplication;

import android.util.Log;
/**
 * @class Coolable
 * @brief Adjusting Cool level by using this interface instance
 */
public class Coolable implements ILevelAdjustable {

    public final boolean _DEBUG_MSG = true;

    public static final String TAG = Coolable.class.getSimpleName();

    public static final int COOL_LEVEL_MAX = 3;
    public static final int COOL_LEVEL_MED = 2;
    public static final int COOL_LEVEL_MIN = 1;
    public static final int COOL_LEVEL_OFFSET = 1;
    public static final int COOL_OFF  = 0;

    private int mCool = COOL_OFF;

    public Coolable(int mCool) {
        this.mCool = mCool;
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"mCool = " + this.mCool);
        }
    }

    public int getmCool() {
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"getmCool = " + this.mCool);
        }
        return mCool;
    }

    public void setmCool(int mCool) {
        this.mCool = mCool;
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"setmCool = " + this.mCool);
        }
    }

    @Override
    public void LevelUp(int value) {
        int currCool = getmCool() + value;
        if(value >= COOL_LEVEL_MAX)
        {
            value = COOL_LEVEL_MAX;
        }
        else if(value <= COOL_LEVEL_MIN)
        {
            value = COOL_LEVEL_MIN;
        }

        if(currCool >= COOL_LEVEL_MAX)
        {
            setmCool(COOL_LEVEL_MAX);
        }
        else if(currCool <= COOL_LEVEL_MIN)
        {
            setmCool(COOL_LEVEL_MIN);
        }
        else
        {
            setmCool(currCool);
        }


        Log.d(TAG,"LevelUp(" + Integer.toString(getmCool()) + ")");
    }

    @Override
    public void LevelDown(int value) {
        int currCool = getmCool() - value;
        if(value >= COOL_LEVEL_MAX)
        {
            value = COOL_LEVEL_MAX;
        }
        else if(currCool <= COOL_LEVEL_MIN)
        {
            value = COOL_LEVEL_MIN;
        }

        if(currCool >= COOL_LEVEL_MAX)
        {
            setmCool(COOL_LEVEL_MAX);
        }
        else if(currCool <= COOL_LEVEL_MIN)
        {
            setmCool(COOL_LEVEL_MIN);
        }
        else
        {
            setmCool(currCool);
        }

        Log.d(TAG,"LevelDown(" + Integer.toString(getmCool()) + ")");
    }

    @Override
    public void LevelOff(int value) {
        setmCool(COOL_OFF);
        Log.d(TAG,"LevelOff(" + Integer.toString(getmCool()) + ")");
    }
}
