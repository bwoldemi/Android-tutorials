package com.example.listtut;

import android.os.Bundle;
import android.view.Menu;
import android.app.ListActivity;
public class ListViewTutAct extends ListActivity {
	
	
	static final String[] MOBILE_OS = 
            new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("hellow ");
        setListAdapter(new MobAddapter(this, MOBILE_OS,MOBILE_OS));
        
    }


   
}
