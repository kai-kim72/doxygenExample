package com.example.hvacapplication;

/**
 * @class IGUIUpdateEventListener
 * @brief GUI Display Update callback and event listener interface
 * @details use to register an event listener which listens the result of adjusting Temperature
 */
public interface IGUIUpdateEventListener {
    /**
     * @brief implement this callback handle the result of adjusting temperature
     * @param _value
     */
    void onGUIUpdate(int _value);

    /**
     * @brief implement this callback handle the result event of adjusting temperature
     * @param _value
     */
    void onEventUpdate(int _value);
}
