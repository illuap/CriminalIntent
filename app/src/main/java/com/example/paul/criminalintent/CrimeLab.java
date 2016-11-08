package com.example.paul.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by paul on 18/08/16.
 */
public class CrimeLab {
    private  static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        /* generate 100 random crimes
        for(int i = 0; i <100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i%2==0);
            Log.d("CrimeLab","XXX"+i);
            mCrimes.add(crime);
        }
        */
    }
    public void addCrime(Crime c){
        mCrimes.add(c);
    }
    public void removeCrime(Crime c){
        mCrimes.remove(c);
    }
    public List<Crime> getCrimes(){
        return mCrimes;
    }
    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }


}