package com.example.hvacapplication;

/**
 * @class ISeatBackMovable
 * @brief Adjusting Seat Back location by using this abstract interface's implementation
 */
public interface ISeatBackMovable {
    /** Seat back forward Interface
     * _leveldegree 0, 10, 20 ,30,40,50,60,70,80, 90
     */
    abstract void moveSeatBackForward(int _leveldegree);
    /** Seat back backward Interface
     * _leveldegree 0, -10, -20 ,-30,-40,-50,-60,-70,-80, -90
     */
    abstract void moveSeatBackBackward(int _leveldegree);
}
