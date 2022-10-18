package com.example.hvacapplication;

/**
 * @class  ILevelAdjustable interface
 * @brief Adjusting a destination level as like heat, cool, temperature, etc... ,
 *        by using this abstract interface's implementation
 */
public interface ILevelAdjustable {
    abstract void LevelUp(int value);
    abstract void LevelDown(int value);
    abstract void LevelOff(int value);
}
