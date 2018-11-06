package com.erdem.motiondetector.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.erdem.motiondetector.R;
import com.erdem.motiondetector.activities.motiondetector.MotionDetector;
import com.erdem.motiondetector.activities.motiondetector.MotionDetectorCallback;

public class MainActivity extends AppCompatActivity {

    private MotionDetector mMotionDetector;
    private Button mRotateButton;
    private Button mChangeCmareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findControls();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMotionDetector.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMotionDetector.onPause();
    }

    private void findControls()
    {
        mRotateButton      = (Button)findViewById(R.id.btnRotate);
        mChangeCmareButton = (Button)findViewById(R.id.btnChangeCamera);
        mRotateButton.setOnClickListener(mRotateButtonOnClicked);
        mChangeCmareButton.setOnClickListener(mChangeCameraButtonOnClicked);
        mMotionDetector = new MotionDetector(this, (SurfaceView) findViewById(R.id.surfaceView));
        mMotionDetector.setMotionDetectorCallback(new MotionDetectorCallback() {
            @Override
            public void onMotionDetected() {
                Toast.makeText(getApplicationContext(), "MotionDetected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTooDark() {
                Toast.makeText(getApplicationContext(), "Too Dark", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View.OnClickListener mRotateButtonOnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                mMotionDetector.rotateCamera();
        }
    };

    private View.OnClickListener mChangeCameraButtonOnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMotionDetector.changeCamera();
        }
    };

}
