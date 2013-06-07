package com.sensorcon.sdsimpleserial;

import java.util.EventObject;

import com.sensorcon.sensordrone.Drone;
import com.sensorcon.sensordrone.Drone.DroneEventListener;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class DroneTextView extends TextView implements DroneEventListener{

	private Drone tvDrone;
	private Handler tvHandler = new Handler();

	public DroneTextView(Context context) {
		super(context);
	}

	public DroneTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public DroneTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void setTvDrone(Drone drone) {
		tvDrone = drone;
	}

	@Override
	public void adcMeasured(EventObject arg0) {
		

	}

	@Override
	public void altitudeMeasured(EventObject arg0) {
		

	}

	@Override
	public void capacitanceMeasured(EventObject arg0) {
		

	}

	@Override
	public void connectEvent(EventObject arg0) {
		update("Connected");
	}

	@Override
	public void connectionLostEvent(EventObject arg0) {
		
		update("Connection Lost");
	}

	@Override
	public void customEvent(EventObject arg0) {
		

	}

	@Override
	public void disconnectEvent(EventObject arg0) {
		
		update("Diconnected");
	}

	@Override
	public void humidityMeasured(EventObject arg0) {
		

	}

	@Override
	public void i2cRead(EventObject arg0) {
		

	}

	@Override
	public void irTemperatureMeasured(EventObject arg0) {
		

	}

	@Override
	public void oxidizingGasMeasured(EventObject arg0) {
		

	}

	@Override
	public void precisionGasMeasured(EventObject arg0) {
		

	}

	@Override
	public void pressureMeasured(EventObject arg0) {
		

	}

	@Override
	public void reducingGasMeasured(EventObject arg0) {
		

	}

	@Override
	public void rgbcMeasured(EventObject arg0) {
		

	}

	@Override
	public void temperatureMeasured(EventObject arg0) {
		

	}

	@Override
	public void uartRead(EventObject arg0) {
		// Get the data
		byte[] readData = tvDrone.uartReadBuffer.array();
		// Set up two Strings: one of hex data, one of ascii data
		String hexData = "HEX:\n";
		for (int i=0; i < readData.length; i++) {
			hexData += Integer.toHexString(readData[i] & 0xff) + " ";
		}
		String asciiData = new String(readData);
		if (asciiData.equals(null)) {
			asciiData = "(parsed as Null)";
		}
		// Put it all together
		String displayString = "";
		displayString += hexData
				+ "\n\n"
				+ "Ascii: \n"
				+ asciiData;
		update(displayString);
	}

	@Override
	public void unknown(EventObject arg0) {
		

	}

	@Override
	public void usbUartRead(EventObject arg0) {
		

	}

	/*
	 * We use a handler to post data to the UI thread
	 */
	public void update(final String msg) {
		tvHandler.post(new Runnable() {
			@Override
			public void run() {
				
				DroneTextView.this.setText(msg);
			}
		});
	}

}
