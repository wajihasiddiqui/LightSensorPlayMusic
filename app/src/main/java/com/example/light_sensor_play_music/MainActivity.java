package com.example.light_sensor_play_music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor sensor;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    MediaPlayer mp;
    boolean isrunning = false;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0]>40 && isrunning == false){
            isrunning = true;
            mp = new MediaPlayer();
            try{
                mp.setDataSource("http://server6.mp3quran.net/kanakeri/011.mp3");
                mp.prepare();
                mp.start();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

    }
}