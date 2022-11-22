package com.example.housemap;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class CapteursActivity extends AppCompatActivity {
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        private Switch switchCapt;
        private SensorManager sensorManager;
        private Sensor mAccelerometer;
        private Sensor mMagnetometer;
        private boolean activation;
        private ExecutorService executorService;
        protected float xA, yA, zA,xM,yM,zM;

        private float[] valuesAccelerometer;
        private float[] valuesMagneticField;

        private float[] matrixR;
        private float[] matrixI;
        private float[] matrixValues;
        private Compass myCompass;

        @Override
        protected void onResume() {
            super.onResume();
            sensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(mSensorEventListener, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected void onPause() {
            super.onPause();
            sensorManager.unregisterListener(mSensorEventListener,mAccelerometer);
            sensorManager.unregisterListener(mSensorEventListener,mMagnetometer);
        }

        @SuppressLint("SourceLockedOrientationActivity")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_capteurs);
            this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
            executorService = Executors.newFixedThreadPool(4);
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            mMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            activation = false;
            valuesAccelerometer = new float[3];
            valuesMagneticField = new float[3];

            matrixR = new float[9];
            matrixI = new float[9];
            matrixValues = new float[3];
            myCompass = new Compass(this);
            activation=false;
        }

        public void clickCapteur(View view) {
            switchCapt = findViewById(R.id.switchCapt);
            mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(!activation){
                activation = true;
                Toast.makeText(this, "Capteur activé !", Toast.LENGTH_SHORT).show();
                onResume();
                switchCapt.setText("Capteur Activé");
            }
            else {
                Toast.makeText(this, "Capteur desactivé !", Toast.LENGTH_SHORT).show();
                onPause();
                switchCapt.setText("Capteur Désactivé");
                activation = false;

            }
        }
        /**
         * Listener that handles sensor events
         */
        private final SensorEventListener mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    xA = event.values[0];
                    yA = event.values[1];
                    zA = event.values[2];
                    System.arraycopy(event.values, 0, valuesAccelerometer, 0, 3);

                } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    xM = event.values[0];
                    yM = event.values[1];
                    zM = event.values[2];
                    System.arraycopy(event.values, 0, valuesMagneticField, 0, 3);
                }
                SensorManager.getRotationMatrix(
                        matrixR,
                        matrixI,
                        valuesAccelerometer,
                        valuesMagneticField);
                SensorManager.getOrientation(matrixR, matrixValues);
                myCompass.update(matrixValues[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

}