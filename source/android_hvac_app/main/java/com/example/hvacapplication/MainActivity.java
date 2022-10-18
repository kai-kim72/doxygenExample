package com.example.hvacapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @class MainActivity
 * @brief HVAC Application main thread
 */
public class MainActivity extends AppCompatActivity {

    public final String TAG = MainActivity.class.getSimpleName();

    private final boolean _DEBUG_MSG = true;    ///< debug message enable/disable

    public TextView mtextViewTitle; ///<  HVAC Panel Title

    public ImageButton mimageButtonLeftSeatHeatUp;   ///< left seat heat increase
    public ImageButton mimageButtonLeftSeatHeatDown;  ///< left seat heat decrease
    public ImageView mimageViewLeftSeatHeatOff; ///< left seat heat off

    public ImageButton mimageButtonRightSeatHeatUp;   ///< right seat heat increase
    public ImageButton mimageButtonRightSeatHeatDown;  ///< right seat heat decrease
    public ImageView mimageViewRightSeatHeatOff;    ///< right seat heat off

    public ImageView mimageViewLeftBlower; ///< decrease blower level
    public TextView mtextViewBlower;    ///< show blower level
    public ImageView mimageViewRightBlower;  ///< increase blower level
    public ImageView mimageViewLeftSeatCoolBlower; ///< driver blower level
    public ImageView mimageViewRightSeatCoolBlower; ///< passenger blower level
    public ImageView mimageViewLeftSeatCool;    ///< show left seat Cool level
    public ImageView mimageViewRightSeatCool;    ///< show right seat Cool level
    public ImageButton mbuttonAC;    ///< Air conditioner
    public ImageButton mbuttonSync;  ///< Update Sync

    public ImageButton mimageViewDrvTempUp; ///< driver Temperature Level up button
    public ImageButton mimageViewDrvTempDown;    ///< driver Temperature level Down
    public ImageButton mimageViewPassengerTempUp;   ///< Passenger Temperature Level Up
    public ImageButton mimageViewPassengerTempDown; ///< Passenger Temperature Level Down
    public ImageView mimageViewDrvTempDisplay;  ///< driver Temp display
    public ImageView mimageViewPassengerTempDisplay;    ///< Passenger Temp display

    // seat and heatable, coolable
    public PassengerSeat mPassengerHeatCoolSeat;    ///< Passenger Seat object
    public DriverSeat mDrvHeatCoolSeat;     ///< Driver Seat object
    public Heatable mDrvHeatable;   ///< Driver Seat heat control interface
    public Heatable mPassengerHeatable;  ///< Driver Seat heat control interface
    public Coolable mDrvCoolable;   ///< Driver Seat Cool control interface
    public Coolable mPassengerCoolable; ///< Passenger Seat Cool control interface
    //air conditioner
    public AirConditioner mAirConditioner;  ///< air conditioner object
    //steer wheel
    public SteerWheel mSteerWheel;  ///< SteerWheel object
    // setting Temperature
    public Temperaturable mSetDrvTemperature;  ///< Setting Driver Temperature
    public Temperaturable mSetPassengerTemperature;  ///< Setting Passenger Temperature

    public int mDrvSeatHeatLevel = 0;   ///< Driver Seat Heat Level
    public int mDrvSeatCoolLevel = 0;   ///< Driver Seat Cool Level

    public int mPassengerSeatHeatLevel = 0; ///< Passenger Seat Heat Level
    public int mPassengerSeatCoolLevel = 0; ///< Passenger Seat Cool Level

    public int mAirConditionLevel = 0;  ///< Air conditioner cool Level
    public boolean mAirConditionOff = true;
    public boolean mSyncOff = true;

    public final int[] mCTypeImg = {
            R.drawable.temp_17_0,
            R.drawable.temp_17_5,
            R.drawable.temp_18_0,
            R.drawable.temp_18_5,
            R.drawable.temp_19_0,
            R.drawable.temp_19_5,
            R.drawable.temp_20_0,
            R.drawable.temp_20_5,
            R.drawable.temp_21_0,
            R.drawable.temp_21_5,
            R.drawable.temp_22_0,
            R.drawable.temp_22_5,
            R.drawable.temp_23_0,
            R.drawable.temp_23_5,
            R.drawable.temp_24_0,
            R.drawable.temp_24_5,
            R.drawable.temp_25_0,
            R.drawable.temp_25_5,
            R.drawable.temp_26_0,
            R.drawable.temp_26_5,
            R.drawable.temp_27_0
    };

    public final int[] mFTypeImg = {
            R.drawable.temp_62,
            R.drawable.temp_63,
            R.drawable.temp_64,
            R.drawable.temp_65,
            R.drawable.temp_66,
            R.drawable.temp_67,
            R.drawable.temp_68,
            R.drawable.temp_69,
            R.drawable.temp_70,
            R.drawable.temp_71,
            R.drawable.temp_72,
            R.drawable.temp_73,
            R.drawable.temp_74,
            R.drawable.temp_75,
            R.drawable.temp_76,
            R.drawable.temp_77,
            R.drawable.temp_78,
            R.drawable.temp_79,
            R.drawable.temp_80,
            R.drawable.temp_81,
            R.drawable.temp_82
    };

    /**
     * @fn void onCreate(Bundle savedInstanceState)
     * @brief create Activity function
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitContextView();

        // create seat instance with the heat & cool level
        mPassengerHeatCoolSeat = new PassengerSeat(0,0,0,0);
        mDrvHeatCoolSeat = new DriverSeat(0,0,0,0);

        // get the saved heat and cool level from preference
        mDrvHeatCoolSeat.setmHeat(mDrvSeatHeatLevel);
        mDrvHeatCoolSeat.setmCool(mDrvSeatCoolLevel);
        // set load heat & cool value into interfaces
        mDrvHeatable = new Heatable(mDrvHeatCoolSeat.getmHeat());
        //let's overriding LevelUp as like toggle the status 1/2/3/off
        mDrvCoolable = new Coolable(mDrvHeatCoolSeat.getmCool()){
            @Override
            public void LevelUp(int value) {
               // super.LevelUp(value);

                int currCool = getmCool() + value;

                if(currCool > COOL_LEVEL_MAX)
                {
                    setmCool(COOL_OFF);
                }
                else if(currCool <= COOL_OFF)
                {
                    setmCool(COOL_LEVEL_MIN);
                }
                else
                {
                    setmCool(currCool);
                }
            }
        };

        // get the saved heat and cool level from preference
        mPassengerHeatCoolSeat.setmHeat(mPassengerSeatHeatLevel);
        mPassengerHeatCoolSeat.setmCool(mPassengerSeatCoolLevel);
        // set load heat & cool value into interfaces
        mPassengerHeatable = new Heatable(mPassengerHeatCoolSeat.getmHeat());
        //let's overriding LevelUp to support level range toggling as like 1/2/3/off
        mPassengerCoolable = new Coolable(mPassengerHeatCoolSeat.getmCool()){
            @Override
            public void LevelUp(int value) {
               // super.LevelUp(value);
                int currCool = getmCool() + value;

                if(currCool > COOL_LEVEL_MAX)
                {
                    setmCool(COOL_OFF);
                }
                else if(currCool <= COOL_OFF)
                {
                    setmCool(COOL_LEVEL_MIN);
                }
                else
                {
                    setmCool(currCool);
                }
            }
        };

        //create air conditioner instance
        mAirConditioner = new AirConditioner(0,0,0,0);
        //default a/c off
        mAirConditionLevel = 0;
        mAirConditioner.LevelOff(mAirConditionLevel);

        mSteerWheel = new SteerWheel(0,0,0,0);
        //set default wheel lift level 0, wheel warm level 0, volume level 0
        mSteerWheel.setmWheelLiftLevel(0);
        mSteerWheel.setmWheelWarm(0);
        mSteerWheel.setmVolumeLevel(0);

        //setting Driver Passenger Temperature, default Type is C degree
        mSetDrvTemperature =  new Temperaturable();
        mSetDrvTemperature.setmScaleTypeC_degree(true);
        //implement gui display update interface callback
        mSetDrvTemperature.setmGUIUpdateEventListener(new IGUIUpdateEventListener() {
            @Override
            public void onGUIUpdate(int _value) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int tempValue = mSetDrvTemperature.getmTemp();

                        if(tempValue <= Temperaturable.TEMP_LEVEL_MAX)
                        {
                            if(tempValue <= Temperaturable.TEMP_LEVEL_MIN)
                            {
                                tempValue = Temperaturable.TEMP_LEVEL_MIN;
                            }

                            int Cindex = ((tempValue - Temperaturable.TEMP_LEVEL_MIN)/5);
                            Drawable img = getResources().getDrawable(mCTypeImg[Cindex]);
                            mimageViewDrvTempDisplay.setImageDrawable(img);

                        }
                        else if(tempValue >= Temperaturable.TEMP_FLEVEL_MIN)
                        {
                            if(tempValue >= Temperaturable.TEMP_FLEVEL_MAX)
                            {
                                tempValue = Temperaturable.TEMP_FLEVEL_MAX;
                            }

                            int Findex = ((tempValue - Temperaturable.TEMP_FLEVEL_MIN));
                            Drawable img = getResources().getDrawable(mFTypeImg[Findex]);
                            mimageViewDrvTempDisplay.setImageDrawable(img);

                        }

                    }
                });
            }

            @Override
            public void onEventUpdate(int _value) {

            }
        });
        mSetPassengerTemperature = new Temperaturable();
        mSetPassengerTemperature.setmScaleTypeC_degree(true);
        mSetPassengerTemperature.setmGUIUpdateEventListener(new IGUIUpdateEventListener() {
            @Override
            public void onGUIUpdate(int _value) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int tempValue = mSetPassengerTemperature.getmTemp();

                        if(tempValue <= Temperaturable.TEMP_LEVEL_MAX)
                        {
                            if(tempValue <= Temperaturable.TEMP_LEVEL_MIN)
                            {
                                tempValue = Temperaturable.TEMP_LEVEL_MIN;
                            }

                            int Cindex = ((tempValue - Temperaturable.TEMP_LEVEL_MIN)/5);
                            Drawable img = getResources().getDrawable(mCTypeImg[Cindex]);
                            mimageViewPassengerTempDisplay.setImageDrawable(img);

                        }
                        else if(tempValue >= Temperaturable.TEMP_FLEVEL_MIN)
                        {
                            if(tempValue >= Temperaturable.TEMP_FLEVEL_MAX)
                            {
                                tempValue = Temperaturable.TEMP_FLEVEL_MAX;
                            }

                            int Findex = ((tempValue - Temperaturable.TEMP_FLEVEL_MIN));
                            Drawable img = getResources().getDrawable(mFTypeImg[Findex]);
                            mimageViewPassengerTempDisplay.setImageDrawable(img);

                        }

                    }
                });
            }

            @Override
            public void onEventUpdate(int _value) {

            }
        });

        //register event listener
        RegisterEventListener();
    }

    /**
     * @fn void InitContextView()
     * @brief Initialize Hvac panel View and components
     */
    public void InitContextView(){

        mimageButtonLeftSeatHeatUp = (ImageButton) findViewById(R.id.imageButtonLeftUp);
        mimageButtonLeftSeatHeatDown = (ImageButton) findViewById(R.id.imageButtonLeftDown);
        mimageViewLeftSeatHeatOff = (ImageView) findViewById(R.id.imageViewLeftSeatOff);

        mimageButtonRightSeatHeatUp = (ImageButton) findViewById(R.id.imageButtonRightUp);
        mimageButtonRightSeatHeatDown = (ImageButton) findViewById(R.id.imageButtonRightDOwn);
        mimageViewRightSeatHeatOff = (ImageView) findViewById(R.id.imageViewRightSeatOff);

        mimageViewLeftBlower = (ImageView) findViewById(R.id.imageViewLeftBlower);
        mtextViewBlower = (TextView) findViewById(R.id.textViewBlower);
        mimageViewRightBlower = (ImageView) findViewById(R.id.imageViewRightBlower);

        mimageViewLeftSeatCool = (ImageView) findViewById(R.id.imageViewLeftSeatCool);
        mimageViewRightSeatCool = (ImageView) findViewById(R.id.imageViewRightSeatCool);

        mimageViewLeftSeatCoolBlower = (ImageView) findViewById(R.id.imageViewLeftSeatCoolBlower);
        mimageViewRightSeatCoolBlower = (ImageView) findViewById(R.id.imageViewRightSeatCoolBlower);

        //default is invisible
        mimageViewLeftSeatCoolBlower.setVisibility(View.INVISIBLE);
        mimageViewRightSeatCoolBlower.setVisibility(View.INVISIBLE);

        mbuttonAC = (ImageButton) findViewById(R.id.buttonAC);
        mbuttonSync = (ImageButton) findViewById(R.id.buttonSync);

        mtextViewTitle = (TextView) findViewById(R.id.textViewTitle);

        //temperature Level Up/Down for Driver and Passenger
        mimageViewDrvTempUp = (ImageButton) findViewById(R.id.imageViewDrvTempUp);
        mimageViewDrvTempDown = (ImageButton) findViewById(R.id.imageViewDrvTempDown);
        mimageViewPassengerTempUp = (ImageButton) findViewById(R.id.imageViewPassengerTempUp);
        mimageViewPassengerTempDown = (ImageButton) findViewById(R.id.imageViewPassengerTempDown);

        mimageViewDrvTempDisplay = (ImageView) findViewById(R.id.imageViewDrvTempDisplay);
        mimageViewPassengerTempDisplay = (ImageView) findViewById(R.id.imageViewPassengerTempDisplay);
    }

    /**
     * @fn void RegisterEventListener()
     * @brief register event listener for user action
     */
    public void RegisterEventListener(){

        mimageButtonLeftSeatHeatUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrvHeatCoolSeat.Increase(mDrvHeatable);
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Driver Seat heat up level : " + Integer.toString(mDrvHeatable.getmHeat()));
                }

                //update image
                int level = mDrvHeatable.getmHeat();
                if(level == Heatable.HEAT_LEVEL_MAX)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_hot_03);
                    mimageViewLeftSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MED)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_hot_02);
                    mimageViewLeftSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MIN)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_hot_01);
                    mimageViewLeftSeatHeatOff.setImageDrawable(img);
                }
            }
        });

        mimageButtonLeftSeatHeatDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrvHeatCoolSeat.Decrease(mDrvHeatable);
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Driver Seat heat down level : " + Integer.toString(mDrvHeatable.getmHeat()));
                }

                //update image
                int level = mDrvHeatable.getmHeat();
                if(level == Heatable.HEAT_LEVEL_MAX)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_hot_03);
                    mimageViewLeftSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MED)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_hot_02);
                    mimageViewLeftSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MIN)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_hot_01);
                    mimageViewLeftSeatHeatOff.setImageDrawable(img);
                }

            }
        });

        mimageViewLeftSeatHeatOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrvHeatCoolSeat.Off(mDrvHeatable);
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Off Driver Seat heat level : " + Integer.toString(mDrvHeatable.getmHeat()));
                }

                Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_off);
                mimageViewLeftSeatHeatOff.setImageDrawable(img);
            }
        });

        mimageViewLeftSeatCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrvHeatCoolSeat.Increase(mDrvCoolable);
                mDrvSeatCoolLevel = mDrvCoolable.getmCool();
                //update text field for air conditioner status display
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Driver Seat Cool level : " + Integer.toString(mDrvSeatCoolLevel));
                }

                //image update
                if(mDrvSeatCoolLevel == Coolable.COOL_LEVEL_MAX)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_cool_03);
                    mimageViewLeftSeatCool.setImageDrawable(img);
                }
                else if(mDrvSeatCoolLevel == Coolable.COOL_LEVEL_MED)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_cool_02);
                    mimageViewLeftSeatCool.setImageDrawable(img);
                }
                else if(mDrvSeatCoolLevel == Coolable.COOL_LEVEL_MIN)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_cool_01);
                    mimageViewLeftSeatCool.setImageDrawable(img);
                }
                else if(mDrvSeatCoolLevel == Coolable.COOL_OFF)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_driver_off);
                    mimageViewLeftSeatCool.setImageDrawable(img);
                }

            }
        });

        mimageButtonRightSeatHeatUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPassengerHeatCoolSeat.Increase(mPassengerHeatable);
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Passenger Seat heat up level : " + Integer.toString(mPassengerHeatable.getmHeat()));
                }

                //update image
                int level = mPassengerHeatable.getmHeat();
                if(level == Heatable.HEAT_LEVEL_MAX)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_hot_03);
                    mimageViewRightSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MED)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_hot_02);
                    mimageViewRightSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MIN)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_hot_01);
                    mimageViewRightSeatHeatOff.setImageDrawable(img);
                }
            }
        });

        mimageButtonRightSeatHeatDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrvHeatCoolSeat.Decrease(mPassengerHeatable);
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Passenger Seat heat down level : " + Integer.toString(mPassengerHeatable.getmHeat()));
                }

                //update image
                int level = mPassengerHeatable.getmHeat();
                if(level == Heatable.HEAT_LEVEL_MAX)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_hot_03);
                    mimageViewRightSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MED)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_hot_02);
                    mimageViewRightSeatHeatOff.setImageDrawable(img);
                }
                else if(level == Heatable.HEAT_LEVEL_MIN)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_hot_01);
                    mimageViewRightSeatHeatOff.setImageDrawable(img);
                }

            }
        });

        mimageViewRightSeatHeatOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrvHeatCoolSeat.Off(mPassengerHeatable);
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Off Passenger Seat heat level : " + Integer.toString(mPassengerHeatable.getmHeat()));
                }

                //update image
                Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_off);
                mimageViewRightSeatHeatOff.setImageDrawable(img);

            }
        });

        mimageViewRightSeatCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPassengerHeatCoolSeat.Increase(mPassengerCoolable);
                mPassengerSeatCoolLevel = mPassengerCoolable.getmCool();

                //update text field for air conditioner status display
                if(_DEBUG_MSG == true)
                {
                    mtextViewTitle.setText("Passenger Seat Cool up  level : " + Integer.toString(mPassengerSeatCoolLevel));
                }

                //image update
                if(mPassengerSeatCoolLevel == Coolable.COOL_LEVEL_MAX)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_cool_03);
                    mimageViewRightSeatCool.setImageDrawable(img);
                }
                else if(mPassengerSeatCoolLevel == Coolable.COOL_LEVEL_MED)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_cool_02);
                    mimageViewRightSeatCool.setImageDrawable(img);
                }
                else if(mPassengerSeatCoolLevel == Coolable.COOL_LEVEL_MIN)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_cool_01);
                    mimageViewRightSeatCool.setImageDrawable(img);
                }
                else if(mPassengerSeatCoolLevel == Coolable.COOL_OFF)
                {
                    Drawable img = getResources().getDrawable(R.drawable.seat_status_passenger_off);
                    mimageViewRightSeatCool.setImageDrawable(img);
                }

            }

        });


        mbuttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //off status
                if(mAirConditionOff == true)
                {
                    mAirConditionOff = false;
                    mtextViewBlower.setText("ON");

                    Drawable img = getResources().getDrawable(R.drawable.btn_ac_on_normal);
                    mbuttonAC.setImageDrawable(img);
                }
                else if(mAirConditionOff == false)
                {
                    mAirConditionOff = true;
                    mtextViewBlower.setText("OFF");
                    Drawable img = getResources().getDrawable(R.drawable.btn_ac_off_normal);
                    mbuttonAC.setImageDrawable(img);
                }

            }
        });

        mimageViewLeftBlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAirConditionLevel <= AirConditioner._LEVEL_OFF)
                {
                    //then turn on
                    mAirConditionLevel = AirConditioner._LEVEL_OFF;
                    mAirConditioner.LevelDown(mAirConditionLevel);
                    if(mAirConditionOff == true)
                    {
                        mtextViewTitle.setText("Blower : " + Integer.toString(mAirConditionLevel));
                    }
                    else
                    {
                        mtextViewTitle.setText("A/C Blower : " + Integer.toString(mAirConditionLevel));
                    }
                }
                else if (mAirConditionLevel < AirConditioner._LEVEL_MAX)
                {
                    mAirConditionLevel -= AirConditioner._LEVEL_OFFSET;
                    mAirConditioner.LevelDown(mAirConditionLevel);
                    if(mAirConditionOff == true)
                    {
                        mtextViewTitle.setText("Blower : " + Integer.toString(mAirConditionLevel));
                    }
                    else
                    {
                        mtextViewTitle.setText("A/C Blower : " + Integer.toString(mAirConditionLevel));
                    }
                }
                else if(mAirConditionLevel >= AirConditioner._LEVEL_MAX)
                {
                    mAirConditionLevel -= AirConditioner._LEVEL_OFFSET;
                    mAirConditioner.LevelDown(mAirConditionLevel);
                    if(mAirConditionOff == true)
                    {
                        mtextViewTitle.setText("Blower : " + Integer.toString(mAirConditionLevel));
                    }
                    else
                    {
                        mtextViewTitle.setText("A/C Blower : " + Integer.toString(mAirConditionLevel));
                    }
                }

                //let check sync is on
                // if sync is on then adjusting left and right blower image level simultaneously
                if(mSyncOff == true)
                {
                    if(mAirConditionLevel == AirConditioner._LEVEL_MAX)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_high_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_driver_high_on_normal_rhd);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MED)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_mid_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_mid_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MIN)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_off_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.INVISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_off_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.INVISIBLE);
                    }
                    /*
                    if(mAirConditionLevel == AirConditioner._LEVEL_MAX)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_high_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MED)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_mid_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MIN)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_off_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.INVISIBLE);
                    }
                    */
                }
                else
                {
                    if(mAirConditionLevel == AirConditioner._LEVEL_MAX)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_high_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_driver_high_on_normal_rhd);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MED)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_mid_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_mid_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MIN)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_off_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.INVISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_off_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        mimageViewRightBlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAirConditionLevel == AirConditioner._LEVEL_OFF)
                {
                    //then turn on
                    mAirConditionLevel += AirConditioner._LEVEL_OFFSET;
                    mAirConditioner.LevelUp(mAirConditionLevel);
                    if(mAirConditionOff == true)
                    {
                        mtextViewTitle.setText("Blower : " + Integer.toString(mAirConditionLevel));
                    }
                    else
                    {
                        mtextViewTitle.setText("A/C Blower : " + Integer.toString(mAirConditionLevel));
                    }
                }
                else if (mAirConditionLevel < AirConditioner._LEVEL_MAX)
                {
                    mAirConditionLevel += AirConditioner._LEVEL_OFFSET;
                    mAirConditioner.LevelUp(mAirConditionLevel);
                    if(mAirConditionOff == true)
                    {
                        mtextViewTitle.setText("Blower : " + Integer.toString(mAirConditionLevel));
                    }
                    else
                    {
                        mtextViewTitle.setText("A/C Blower : " + Integer.toString(mAirConditionLevel));
                    }
                }
                else if(mAirConditionLevel >= AirConditioner._LEVEL_MAX)
                {
                    mAirConditionLevel = AirConditioner._LEVEL_MAX;
                    mAirConditioner.LevelUp(mAirConditionLevel);
                    if(mAirConditionOff == true)
                    {
                        mtextViewTitle.setText("Blower : " + Integer.toString(mAirConditionLevel));
                    }
                    else
                    {
                        mtextViewTitle.setText("A/C Blower : " + Integer.toString(mAirConditionLevel));
                    }
                }

                //let check sync is on
                // if sync is on then adjusting left and right blower image level simultaneously
                if(mSyncOff == true)
                {
                    if(mAirConditionLevel == AirConditioner._LEVEL_MAX)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_high_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_driver_high_on_normal_rhd);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MED)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_mid_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_mid_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MIN)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_off_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.INVISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_off_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.INVISIBLE);
                    }
                    /*
                    if(mAirConditionLevel == AirConditioner._LEVEL_MAX)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_high_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(img);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MED)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_mid_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(img);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MIN)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(img);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_off_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(img);
                        mimageViewRightSeatCoolBlower.setVisibility(View.INVISIBLE);
                    }
                    */

                }
                else
                {
                    if(mAirConditionLevel == AirConditioner._LEVEL_MAX)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_high_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_driver_high_on_normal_rhd);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MED)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_mid_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_mid_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else if(mAirConditionLevel == AirConditioner._LEVEL_MIN)
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_on_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.VISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_on_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Drawable img = getResources().getDrawable(R.drawable.wind_driver_low_off_normal);
                        mimageViewLeftSeatCoolBlower.setImageDrawable(img);
                        mimageViewLeftSeatCoolBlower.setVisibility(View.INVISIBLE);

                        Drawable imgP = getResources().getDrawable(R.drawable.wind_passenger_low_off_normal);
                        mimageViewRightSeatCoolBlower.setImageDrawable(imgP);
                        mimageViewRightSeatCoolBlower.setVisibility(View.INVISIBLE);
                    }
                }


            }
        });

        mbuttonSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //off status
                if(mSyncOff == true)
                {
                    mSyncOff = false;

                    Drawable img = getResources().getDrawable(R.drawable.btn_sync_on_normal);
                    mbuttonSync.setImageDrawable(img);
                }
                else if(mSyncOff == false)
                {
                    mSyncOff = true;

                    Drawable img = getResources().getDrawable(R.drawable.btn_sync_off_normal);
                    mbuttonSync.setImageDrawable(img);
                }
            }
        });


        //temperature level adjusting
        mimageViewDrvTempUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean CType = mSetDrvTemperature.ismScaleTypeC_degree();
                if(mSyncOff == false)
                {
                    // let's sync passenger temperature up
                    mSetPassengerTemperature.setmTemp(mSetDrvTemperature.getmTemp());
                }
                if(CType)
                {
                    mSetDrvTemperature.LevelUp(Temperaturable._TEMP_LEVEL_OFFSET);
                }
                else
                {
                    mSetDrvTemperature.LevelUp(Temperaturable._TEMP_FLEVEL_OFFSET);
                }

                if(mSyncOff == false)
                {
                    boolean CType1 = mSetPassengerTemperature.ismScaleTypeC_degree();
                    if(CType1)
                    {
                        mSetPassengerTemperature.LevelUp(Temperaturable._TEMP_LEVEL_OFFSET);
                    }
                    else
                    {
                        mSetPassengerTemperature.LevelUp(Temperaturable._TEMP_FLEVEL_OFFSET);
                    }
                }

            }
        });

        mimageViewDrvTempDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean CType = mSetDrvTemperature.ismScaleTypeC_degree();
                if(mSyncOff == false)
                {
                    // let's sync passenger temperature up
                    mSetPassengerTemperature.setmTemp(mSetDrvTemperature.getmTemp());
                }
                if(CType)
                {
                    mSetDrvTemperature.LevelDown(Temperaturable._TEMP_LEVEL_OFFSET);
                }
                else
                {
                    mSetDrvTemperature.LevelDown(Temperaturable._TEMP_FLEVEL_OFFSET);
                }

                if(mSyncOff == false)
                {
                    boolean CType1 = mSetPassengerTemperature.ismScaleTypeC_degree();
                    if(CType1)
                    {
                        mSetPassengerTemperature.LevelDown(Temperaturable._TEMP_LEVEL_OFFSET);
                    }
                    else
                    {
                        mSetPassengerTemperature.LevelDown(Temperaturable._TEMP_FLEVEL_OFFSET);
                    }
                }
            }
        });

        mimageViewPassengerTempUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean CType = mSetPassengerTemperature.ismScaleTypeC_degree();

                if(mSyncOff == false)
                {
                    // let's sync driver temperature up
                    mSetDrvTemperature.setmTemp(mSetPassengerTemperature.getmTemp());
                }

                if(CType)
                {
                    mSetPassengerTemperature.LevelUp(Temperaturable._TEMP_LEVEL_OFFSET);
                }
                else
                {
                    mSetPassengerTemperature.LevelUp(Temperaturable._TEMP_FLEVEL_OFFSET);
                }

                if(mSyncOff == false)
                {
                    boolean CType1 = mSetDrvTemperature.ismScaleTypeC_degree();
                    if(CType1)
                    {
                        mSetDrvTemperature.LevelUp(Temperaturable._TEMP_LEVEL_OFFSET);
                    }
                    else
                    {
                        mSetDrvTemperature.LevelUp(Temperaturable._TEMP_FLEVEL_OFFSET);
                    }
                }
            }
        });

        mimageViewPassengerTempDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean CType = mSetPassengerTemperature.ismScaleTypeC_degree();
                if(mSyncOff == false)
                {
                    // let's sync driver temperature up
                    mSetDrvTemperature.setmTemp(mSetPassengerTemperature.getmTemp());
                }

                if(CType)
                {
                    mSetPassengerTemperature.LevelDown(Temperaturable._TEMP_LEVEL_OFFSET);
                }
                else
                {
                    mSetPassengerTemperature.LevelDown(Temperaturable._TEMP_FLEVEL_OFFSET);
                }

                if(mSyncOff == false)
                {
                    boolean CType1 = mSetDrvTemperature.ismScaleTypeC_degree();
                    if(CType1)
                    {
                        mSetDrvTemperature.LevelDown(Temperaturable._TEMP_LEVEL_OFFSET);
                    }
                    else
                    {
                        mSetDrvTemperature.LevelDown(Temperaturable._TEMP_FLEVEL_OFFSET);
                    }
                }
            }
        });

    }

}