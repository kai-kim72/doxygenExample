package com.example.hvacapplication;

import android.util.Log;

/**
 * @class Heatable
 * @brief Adjusting Heat level by using this interface instance
 */
public class Heatable implements ILevelAdjustable {
   public final boolean _DEBUG_MSG = true;

   public static final String TAG = Heatable.class.getSimpleName();

   public static final int HEAT_LEVEL_MAX = 3;
    public static final int HEAT_LEVEL_MED = 2;
   public static final int HEAT_LEVEL_MIN = 1;
   public final int HEAT_OFF  = 0;

    private int mHeat = HEAT_OFF;

    public Heatable(int mHeat) {
        this.mHeat = mHeat;
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"mHeat = " + this.mHeat);
        }
    }

    public int getmHeat() {
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"getmHeat = " + this.mHeat);
        }
        return mHeat;
    }

    public void setmHeat(int mHeat) {
        this.mHeat = mHeat;
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"setmHeat = " + this.mHeat);
        }
    }

    @Override
    public void LevelUp(int value) {
        int currHeat = getmHeat() + value;
        if(currHeat >= HEAT_LEVEL_MAX)
        {
            setmHeat(HEAT_LEVEL_MAX);
        }
        else if(currHeat < HEAT_LEVEL_MIN)
        {
            setmHeat(HEAT_LEVEL_MIN);
        }
        else
        {
            if((currHeat) >= HEAT_LEVEL_MAX)
            {
                setmHeat(HEAT_LEVEL_MAX);
            }
            else if((currHeat) <= HEAT_LEVEL_MIN)
            {
                setmHeat(HEAT_LEVEL_MIN);
            }
            else
            {
                setmHeat(currHeat);
            }
        }

        Log.d(TAG,"LevelUp(" + Integer.toString(getmHeat()) + ")");

    }

    @Override
    public void LevelDown(int value) {
        int currHeat = getmHeat() - value;
        if(currHeat >= HEAT_LEVEL_MAX)
        {
            setmHeat(HEAT_LEVEL_MAX);
        }
        else if(currHeat < HEAT_LEVEL_MIN)
        {
            setmHeat(HEAT_LEVEL_MIN);
        }
        else
        {
            if(currHeat  >= HEAT_LEVEL_MAX)
            {
                setmHeat(HEAT_LEVEL_MAX);
            }
            else if(currHeat <= HEAT_LEVEL_MIN)
            {
                setmHeat(HEAT_LEVEL_MIN);
            }
            else
            {
                setmHeat(currHeat );
            }
        }

        Log.d(TAG,"LevelDown(" + Integer.toString(getmHeat()) + ")");
    }

    @Override
    public void LevelOff(int value) {
        setmHeat(HEAT_OFF);
        Log.d(TAG,"LevelOff(" + Integer.toString(getmHeat()) + ")");
    }
}
