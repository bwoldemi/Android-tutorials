package fi.oulu.tol.simplecalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class SimpleCalculatorActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        Button one_button= (Button)findViewById(R.id.one);
        Button two_button= (Button)findViewById(R.id.two);
        Button three_button= (Button)findViewById(R.id.three);
        Button four_button= (Button)findViewById(R.id.four);
        Button five_button= (Button)findViewById(R.id.five);
        Button six_button= (Button)findViewById(R.id.six);
        Button seven_button= (Button)findViewById(R.id.seven);
        Button eight_button= (Button)findViewById(R.id.eight);
        Button nine_button= (Button)findViewById(R.id.nine);
        Button zero_button= (Button)findViewById(R.id.zero);
        Button plus_button= (Button)findViewById(R.id.plus);
        EditText result_editText = (EditText)findViewById(R.id.result);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simple_calculator, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		
	}
    
}
