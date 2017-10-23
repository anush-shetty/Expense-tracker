package com.example.add;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeUti extends Activity {

	EditText amount1;
	  EditText amount2;
	  EditText amount3;
	  TextView tt;
	  Button calculate;
	  Button next;
	  Button newact;
	  int x=0;
	  int y=0;
	  int z=0;
	  int q=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_uti);
		newact=(Button)findViewById(R.id.newact);
	      newact.setOnClickListener(new Button.OnClickListener()
	      {public void onClick
	      (View  v) { 
	    	  Intent i = new Intent(HomeUti.this,Education.class);
	    	 
	    	 startActivity(i);
	      }
	      }
	      );
initControls();
	}

	  private void initControls()
	  {
	      amount1=(EditText)findViewById(R.id.amount1);
	      amount2=(EditText)findViewById(R.id.amount2);
	      amount3=(EditText)findViewById(R.id.amount3);
	      tt=(TextView)findViewById(R.id.tt);
	      calculate=(Button)findViewById(R.id.calculate);
	      calculate.setOnClickListener(new Button.OnClickListener()
	      {public void onClick
	      (View  v) { calculate();}});
	      next=(Button)findViewById(R.id.button1);
	      next.setOnClickListener(new Button.OnClickListener()
	      {public void onClick
	      (View  v) { 
	    	  Intent i = new Intent(HomeUti.this,DisplayResult.class);
	    	  String qq = String.valueOf(z);
	    	 i.putExtra("Two",qq);
	    	  startActivity(i);
	      }
	      }
	      );
	  }
	  private void calculate()
	  {
	      x=Integer.parseInt(amount1.getText().toString());
	      y=Integer.parseInt(amount2.getText().toString());
	      q=Integer.parseInt(amount3.getText().toString());
	      z=x+y+q;
	      tt.setText(Double.toString(z));
	      SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	      Editor editor = myPrefs.edit();
	      editor.putString("home", tt.getText().toString()); 
	      editor.commit();
	  }
}
