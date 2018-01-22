
package com.cql.customerview;

import com.cql.customerview.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    
    private boolean sureBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        getActionBar().hide();
        
    }
    
    @Override
    public void onBackPressed() {
        if(!sureBack){
            sureBack = true;
            Toast.makeText(this, "please press back again", Toast.LENGTH_SHORT).show();
        }else{
            super.onBackPressed();
        }
    }
}
