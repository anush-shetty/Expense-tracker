package com.example.add;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayResult extends Activity {
	double result, shopres, homeres, edures, entres;
	private Button button1;
	private Button button2;
	private AlarmManager mAlarmManager;
	private long INITIAL_ALARM_DELAY = 2 * 1000;
	private Intent mARIntent;
	private PendingIntent mNRPIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_result);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		mARIntent = new Intent(this, AlarmNotification.class);
		mNRPIntent = PendingIntent.getBroadcast(DisplayResult.this, 0,
				mARIntent, 0);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mAlarmManager.set(AlarmManager.RTC_WAKEUP,
						System.currentTimeMillis() + INITIAL_ALARM_DELAY,
						mNRPIntent);
				Toast.makeText(getApplicationContext(),
						"Alarm will go off in 2 seconds", Toast.LENGTH_SHORT)
						.show();
			}
		});
		SharedPreferences myPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String newshop = myPrefs.getString("shop", "nothing");

		SharedPreferences income = PreferenceManager
				.getDefaultSharedPreferences(this);
		String newinc = income.getString("inc", "nothing");

		SharedPreferences home = PreferenceManager
				.getDefaultSharedPreferences(this);
		String newhome = home.getString("home", "nothing");

		SharedPreferences Edu = PreferenceManager
				.getDefaultSharedPreferences(this);
		String newedu = Edu.getString("Edu", "nothing");

		SharedPreferences Ent = PreferenceManager
				.getDefaultSharedPreferences(this);
		String newent = Ent.getString("Ent", "nothing");

		// Intent intent=getIntent();
		// String One=intent.getStringExtra("One");
		// String Two=intent.getStringExtra("Two");

		TextView newres = (TextView) findViewById(R.id.result);
		TextView newres2 = (TextView) findViewById(R.id.shopres);
		TextView newres3 = (TextView) findViewById(R.id.homeres);
		TextView newres4 = (TextView) findViewById(R.id.edures);
		TextView newres5 = (TextView) findViewById(R.id.entres);

		result = Double.parseDouble(newinc);
		shopres = Double.parseDouble(newshop);
		homeres = Double.parseDouble(newhome);
		edures = Double.parseDouble(newedu);
		entres = Double.parseDouble(newent);

		newres.setText(newinc);
		newres2.setText(newshop);
		newres3.setText(newhome);
		newres4.setText(newedu);
		newres5.setText(newent);

		// subtraction:
		Double in1 = Double.parseDouble(newinc);
		Double in2 = Double.parseDouble(newshop);
		Double in3 = Double.parseDouble(newhome);
		Double in4 = Double.parseDouble(newedu);
		Double in5 = Double.parseDouble(newent);
		in1 = in1 - in2 - in3 - in4 - in5;
		String sub = in1.toString();
		TextView newres6 = (TextView) findViewById(R.id.sub);
		newres6.setText(sub);
		openChart();
	}

	private void openChart() {

		// Pie Chart Section Names
		String[] code = new String[] { "Shopping", "Home/Utilities",
				"Education/Health", "Entertainment" };

		// Pie Chart Section Value
		double[] distribution = { shopres, homeres, edures, entres };

		// Color of each Pie Chart Sections
		int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN,
				Color.RED, Color.YELLOW };

		// Instantiating CategorySeries to plot Pie Chart
		CategorySeries distributionSeries = new CategorySeries(
				" Android version distribution as on October 1, 2012");
		for (int i = 0; i < distribution.length; i++) {
			// Adding a slice with its values and name to the Pie Chart
			distributionSeries.add(code[i], distribution[i]);
		}

		// Instantiating a renderer for the Pie Chart
		DefaultRenderer defaultRenderer = new DefaultRenderer();
		for (int i = 0; i < distribution.length; i++) {
			SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
			seriesRenderer.setColor(colors[i]);
			seriesRenderer.setDisplayChartValues(true);
			// Adding a renderer for a slice
			defaultRenderer.addSeriesRenderer(seriesRenderer);

		}

		defaultRenderer.setLabelsTextSize(30);
		defaultRenderer.setChartTitle("Your Daily expense is as below ");
		defaultRenderer.setChartTitleTextSize(20);
		defaultRenderer.setZoomButtonsVisible(true);

		// Creating an intent to plot bar chart using dataset and
		// multipleRenderer
		Intent intent = ChartFactory
				.getPieChartIntent(getBaseContext(), distributionSeries,
						defaultRenderer, "AChartEnginePieChartDemo");

		// Start Activity
		startActivity(intent);

	}
}
