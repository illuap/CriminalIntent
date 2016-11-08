package com.example.paul.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.paul.criminalintent.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by paul on 18/08/16.
 */
public class CrimeLab {
    private  static CrimeLab sCrimeLab;

    //private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext(); //find out later
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();


        //mCrimes = new ArrayList<>();
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
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeTable.NAME, null, values);
        //mCrimes.add(c);
    }
    public void removeCrime(Crime c){
        //mCrimes.remove(c);
    }
    public List<Crime> getCrimes(){
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor  = queryCrimes(null,null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
        //return mCrimes;
    }
    public Crime getCrime(UUID id){
        /*for(Crime crime : mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }*/
        return null;
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?",
                    new String[]{uuidString});
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED,crime.isSolved() ? 1: 0);

        return values;
    }
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
          CrimeTable.NAME,
                null,  // Columns
                whereClause,
                whereArgs,
                null, //group by
                null, //having
                null  //orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }
}
