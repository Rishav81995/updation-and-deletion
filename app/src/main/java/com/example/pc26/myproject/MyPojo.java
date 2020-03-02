package com.example.pc26.myproject;


import com.google.gson.JsonParser;

import java.io.Serializable;

/**
 * Created by pc26 on 2/17/2020.
 */


public class MyPojo implements Serializable
{
    private Data[] data;

    private String status;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
}

