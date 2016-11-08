package com.example.paul.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by paul on 18/08/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
