package com.example.paul.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by paul on 17/08/16.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }


    public Crime(){

        this(UUID.randomUUID());
        //mId = UUID.randomUUID();
        //mDate = new Date();
    }
    public Crime(UUID id){
        mId = id;
        mDate = new Date();
    }

}
