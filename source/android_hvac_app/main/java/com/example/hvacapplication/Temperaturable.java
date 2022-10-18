package com.example.hvacapplication;

import android.util.Log;

/**
 * @class Temperaturable interface
 * @brief temperature level setting
 */
public class Temperaturable implements ILevelAdjustable {
    public final boolean _DEBUG_MSG = true;

    public static final String TAG = Temperaturable.class.getSimpleName();

    public static final int TEMP_LEVEL_MAX = 270;       ///< max temperature value 27 ( scaling x 10 )
    public static final int TEMP_LEVEL_MIN = 170;       ///< min temperature value 17 ( scaling x 10 )
    public static final int _TEMP_LEVEL_OFFSET = 5;     ///< temperature level offset 0.5 ( scaling x 10 )

    public static final int TEMP_FLEVEL_MAX = 820;       ///< max temperature value 82 ( scaling x 10 )
    public static final int TEMP_FLEVEL_MIN = 620;       ///< min temperature value 62 ( scaling x 10 )
    public static final int _TEMP_FLEVEL_OFFSET = 10;     ///< temperature level offset 1 ( scaling x 10 )

    public final int TEMP_OFF  = 0;

    private int mTemp = TEMP_OFF;
    private boolean mTempOff = false;

    private boolean mScaleTypeC_degree = true; ///< ture : C degree ( 17 ~ 27 ), false : F degree ( 62 ~ 82 )

    private IGUIUpdateEventListener mGUIUpdateEventListener;    ///< gui update callback for adjusting temperature

    public void setmGUIUpdateEventListener(IGUIUpdateEventListener mGUIUpdateEventListener) {
        this.mGUIUpdateEventListener = mGUIUpdateEventListener;
    }

    public IGUIUpdateEventListener getmGUIUpdateEventListener() {
        return mGUIUpdateEventListener;
    }

    public boolean ismTempOff() {
        return mTempOff;
    }

    public void setmTempOff(boolean mTempOff) {
        this.mTempOff = mTempOff;
    }

    public boolean ismScaleTypeC_degree() {
        return mScaleTypeC_degree;
    }

    public void setmScaleTypeC_degree(boolean mScaleTypeC_degree) {
        this.mScaleTypeC_degree = mScaleTypeC_degree;
    }

    public int getmTemp() {
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"getmTemp = " + this.mTemp);
        }
        return mTemp;
    }

    public void setmTemp(int mTemp) {
        this.mTemp = mTemp;
        if(_DEBUG_MSG)
        {
            Log.d(TAG,"setmTemp = " + this.mTemp);
        }
    }

    /**
     * @brief increase temperature level
     * @param value
     */
    @Override
    public void LevelUp(int value) {
        int currTemp = getmTemp() + value;
        boolean _TypeC = ismScaleTypeC_degree();
        IGUIUpdateEventListener mlister = getmGUIUpdateEventListener();

        if(_TypeC)
        {
            if(currTemp >= TEMP_LEVEL_MAX)
            {
                currTemp = TEMP_LEVEL_MAX;
                setmTemp(TEMP_LEVEL_MAX);
            }
            else if(currTemp < TEMP_LEVEL_MIN)
            {
                currTemp = TEMP_LEVEL_MIN;
                setmTemp(TEMP_LEVEL_MIN);
            }
            else
            {
                if((currTemp) >= TEMP_LEVEL_MAX)
                {
                    currTemp = TEMP_LEVEL_MAX;
                    setmTemp(TEMP_LEVEL_MAX);
                }
                else if((currTemp) <= TEMP_LEVEL_MIN)
                {
                    currTemp = TEMP_LEVEL_MIN;
                    setmTemp(TEMP_LEVEL_MIN);
                }
                else
                {
                    setmTemp(currTemp);
                }
            }

            float _value = (float)(getmTemp()/10);
            // Log.d(TAG,"LevelUp(" + Float.toString(getmTemp()) + ")");
            Log.d(TAG,"LevelUp(" + Float.toString(_value) + ")");

            if(mlister != null)
            {
                mlister.onGUIUpdate(currTemp);  ///< callback for the result of adjusting temperature
            }
        }
        else
        {
            if(currTemp >= TEMP_FLEVEL_MAX)
            {
                currTemp = TEMP_FLEVEL_MAX;
                setmTemp(TEMP_FLEVEL_MAX);
            }
            else if(currTemp < TEMP_FLEVEL_MIN)
            {
                currTemp = TEMP_FLEVEL_MIN;
                setmTemp(TEMP_FLEVEL_MIN);
            }
            else
            {
                if((currTemp) >= TEMP_FLEVEL_MAX)
                {
                    currTemp = TEMP_FLEVEL_MAX;
                    setmTemp(TEMP_FLEVEL_MAX);
                }
                else if((currTemp) <= TEMP_FLEVEL_MIN)
                {
                    currTemp = TEMP_FLEVEL_MIN;
                    setmTemp(TEMP_FLEVEL_MIN);
                }
                else
                {
                    setmTemp(currTemp);
                }
            }

            float _value = (float)(getmTemp()/10);
            // Log.d(TAG,"LevelUp(" + Float.toString(getmTemp()) + ")");
            Log.d(TAG,"LevelUp(" + Float.toString(_value) + ")");
            if(mlister != null)
            {
                mlister.onGUIUpdate(currTemp);  ///< callback for the result of adjusting temperature
            }
        }


    }

    /**
     * @brief decrease temperature level
     * @param value
     */
    @Override
    public void LevelDown(int value) {
        int currTemp = getmTemp() - value;
        boolean _TypeC = ismScaleTypeC_degree();
        IGUIUpdateEventListener mlister = getmGUIUpdateEventListener();

        if(_TypeC)
        {
            if(currTemp >= TEMP_LEVEL_MAX)
            {
                currTemp = TEMP_LEVEL_MAX;
                setmTemp(TEMP_LEVEL_MAX);
            }
            else if(currTemp < TEMP_LEVEL_MIN)
            {
                currTemp = TEMP_LEVEL_MIN;
                setmTemp(TEMP_LEVEL_MIN);
            }
            else
            {
                if((currTemp) >= TEMP_LEVEL_MAX)
                {
                    currTemp = TEMP_LEVEL_MAX;
                    setmTemp(TEMP_LEVEL_MAX);
                }
                else if((currTemp) <= TEMP_LEVEL_MIN)
                {
                    currTemp = TEMP_LEVEL_MIN;
                    setmTemp(TEMP_LEVEL_MIN);
                }
                else
                {
                    setmTemp(currTemp);
                }
            }

            float _value = (float)(getmTemp()/10);
            // Log.d(TAG,"LevelUp(" + Float.toString(getmTemp()) + ")");
            Log.d(TAG,"LevelUp(" + Float.toString(_value) + ")");
            if(mlister != null)
            {
                mlister.onGUIUpdate(currTemp);  ///< callback for the result of adjusting temperature
            }
        }
        else
        {
            if(currTemp >= TEMP_FLEVEL_MAX)
            {
                currTemp = TEMP_FLEVEL_MAX;
                setmTemp(TEMP_FLEVEL_MAX);
            }
            else if(currTemp < TEMP_FLEVEL_MIN)
            {
                currTemp = TEMP_FLEVEL_MIN;
                setmTemp(TEMP_FLEVEL_MIN);
            }
            else
            {
                if((currTemp) >= TEMP_FLEVEL_MAX)
                {
                    currTemp = TEMP_FLEVEL_MAX;
                    setmTemp(TEMP_FLEVEL_MAX);
                }
                else if((currTemp) <= TEMP_FLEVEL_MIN)
                {
                    currTemp = TEMP_FLEVEL_MIN;
                    setmTemp(TEMP_FLEVEL_MIN);
                }
                else
                {
                    setmTemp(currTemp);
                }
            }

            float _value = (float)(getmTemp()/10);
            // Log.d(TAG,"LevelUp(" + Float.toString(getmTemp()) + ")");
            Log.d(TAG,"LevelUp(" + Float.toString(_value) + ")");
            if(mlister != null)
            {
                mlister.onGUIUpdate(currTemp);  ///< callback for the result of adjusting temperature
            }
        }

    }

    @Override
    public void LevelOff(int value) {
        setmTempOff(true);
    }

    /**
     * @brief convert C to F
     * @param _CDegree
     * @return
     */
    public int ConvertC2F(int _CDegree)
    {
        float _val = (float) (_CDegree/10);
        int _FValue = 0;
        ///< F = C X 1.8 + 32
        _val = (float) ((_val * 1.8) + 32);
        _FValue = (int) (_val*10);
        Log.d(TAG,"ConvertC2F: C=" + _CDegree + " , F= " + Float.toString(_val) + "(" + _FValue + ")");
        return _FValue;


    }

    /**
     * @brief covert F to C
     * @param _FDegree
     * @return
     */
    public int ConvertF2C(int _FDegree)
    {
        float _val = (float) (_FDegree/10);
        int _CValue = 0;
        ///< C = ((F - 32 ) X 5) / 9
        _val = (float) (((_val - (float) 32)* 5)/9);
        _CValue = (int) (_val*10);
        Log.d(TAG,"ConvertF2C: F=" + _FDegree + " , C= " + Float.toString(_val) + "(" + _CValue + ")");
        return _CValue;
    }

}
