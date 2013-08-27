package com.sensorcon.sdsimpleserial;

import com.sensorcon.sdhelper.SDHelper;
import com.sensorcon.sensordrone.Drone;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SDSimpleSerial extends Activity {

	// Drone Stuff
	Drone myDrone;
	SDHelper droneHelper; // Much of this will be added into the next version of the main library

	// UI elements
	DroneTextView tvReceive;
	EditText etSend;
	Button btnSend;
	Button btnReceive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdsimple_serial);


		// Drone stuff
		myDrone = new Drone();
		droneHelper = new SDHelper();

		// Hook up our UI
		tvReceive = (DroneTextView)findViewById(R.id.tvReceiveData);
		tvReceive.setTvDrone(myDrone);
		etSend = (EditText)findViewById(R.id.etSendData);

		// Our custom TextView also handles
		myDrone.registerDroneEventListener(tvReceive);

		btnSend = (Button)findViewById(R.id.btnSend);
		btnReceive = (Button)findViewById(R.id.btnReceive);

		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String stringToSend = etSend.getText().toString();
				if (stringToSend != null && stringToSend.length() >0) {
					// There is a 32 byte limit. If longer than 32 bytes,
					// usartWrite won't send the data
                    stringToSend += "\r\n";
					byte[] dataToSend = stringToSend.getBytes();
					myDrone.uartWrite(dataToSend);
				}

			}
		});

		btnReceive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myDrone.uartRead();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sdsimple_serial, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.connect:
			if (!myDrone.isConnected) {
				droneHelper.scanToConnect(myDrone, this, this, false);
			}
			break;
		case R.id.Reconnect:
			if (!myDrone.isConnected && !myDrone.lastMAC.equals("")) {
				myDrone.btConnect(myDrone.lastMAC);
			}

			break;
		case R.id.disconnect:
			if (myDrone.isConnected) {
				myDrone.disconnect();
			}

			break;
		case R.id.br2400:
			if (myDrone.setBaudRate_2400()) {
				tvReceive.setText("Baud 2400");
			}
			break;			
		case R.id.br9600:
			if (myDrone.setBaudRate_9600()) {
				tvReceive.setText("Baud 9600");
			}
			break;
		case R.id.br19200:
			if (myDrone.setBaudRate_19200()) {
				tvReceive.setText("Baud 19200");
			}
			break;
		case R.id.br38400:
			if (myDrone.setBaudRate_38400()) {
				tvReceive.setText("Baud 38400");
			}
			break;
		case R.id.br115200:
			if (myDrone.setBaudRate_115200()) {
				tvReceive.setText("Baud 115200");
			}
			break;

		}
		return true;
	}

}
