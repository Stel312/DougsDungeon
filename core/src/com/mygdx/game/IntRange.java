package com.mygdx.game;
public class IntRange {
    private final int min, max;
    public IntRange (int num1, int num2)
    {
        if (num1 > num2)
        {
            min = num2;
            max = num1;
        }
        else
        {
            min = num1;
            max = num2;
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    public int Random()
    {
        int rand = (int)(Math.random() * max) + min;
        return rand;
    }
}
