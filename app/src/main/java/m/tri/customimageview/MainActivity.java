package m.tri.customimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TouchImageView imageView;
    Button btnZoom, btnZoomArea, btnCancle;
    TextView txtX, txtY, txtZ, txtW, txtH, txtD, txtX_min, txtX_max, txtY_min, txtY_max,
            txtZ_min, txtZ_max, txtW_min, txtW_max, txtH_min, txtH_max, txtD_min, txtD_max;

    SeekBar seekBarX, seekBarY, seekBarZ, seekBarW, seekBarH, seekBarD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtX = (TextView) findViewById(R.id.txtX);
        txtY = (TextView) findViewById(R.id.txtY);
        txtZ = (TextView) findViewById(R.id.txtZ);
        txtW = (TextView) findViewById(R.id.txtW);
        txtH = (TextView) findViewById(R.id.txtH);
        txtD = (TextView) findViewById(R.id.txtD);
        txtX_min = (TextView) findViewById(R.id.txtX_min);
        txtX_max = (TextView) findViewById(R.id.txtX_max);
        txtY_min = (TextView) findViewById(R.id.txtY_min);
        txtY_max = (TextView) findViewById(R.id.txtY_max);
        txtZ_min = (TextView) findViewById(R.id.txtZ_min);
        txtZ_max = (TextView) findViewById(R.id.txtZ_max);
        txtW_min = (TextView) findViewById(R.id.txtW_min);
        txtW_max = (TextView) findViewById(R.id.txtW_max);
        txtH_min = (TextView) findViewById(R.id.txtH_min);
        txtH_max = (TextView) findViewById(R.id.txtH_max);
        txtD_min = (TextView) findViewById(R.id.txtD_min);
        txtD_max = (TextView) findViewById(R.id.txtD_max);

        seekBarX = (SeekBar) findViewById(R.id.seekBarX);
        seekBarY = (SeekBar) findViewById(R.id.seekBarY);
        seekBarZ = (SeekBar) findViewById(R.id.seekBarZ);
        seekBarW = (SeekBar) findViewById(R.id.seekBarW);
        seekBarH = (SeekBar) findViewById(R.id.seekBarH);
        seekBarD = (SeekBar) findViewById(R.id.seekBarD);


        imageView = (TouchImageView) findViewById(R.id.img);

        btnZoom = (Button) findViewById(R.id.btnZoom);
        btnZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.zooomBitmapXY((float) seekBarZ.getProgress() / 100,
                        seekBarX.getProgress(),
                        seekBarY.getProgress(),
                        seekBarD.getProgress(),
                        handler);
            }
        });

        btnZoomArea = (Button) findViewById(R.id.btnZoomArea);
        btnZoomArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final float focusX = seekBarX.getProgress() + (float)seekBarW.getProgress() / 2;
                final float focusY = seekBarY.getProgress() + (float)seekBarH.getProgress() / 2;

                if (imageView.zooomArea(focusX,
                        focusY,
                        seekBarW.getProgress(),
                        seekBarH.getProgress(),
                        0.7f,
                        seekBarD.getProgress(),
                        handler)) {

                    Toast.makeText(MainActivity.this, "start Zoom true", Toast.LENGTH_SHORT).show();
                } else {
                    // The are outside of Image (70% of image)
                    Toast.makeText(MainActivity.this, "start Zoom false", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.cancleAnimation();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.nature_1);

        float width = icon.getWidth();
        float height = icon.getHeight();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = width / metrics.scaledDensity;
        height = height / metrics.scaledDensity;

        txtX_max.setText((int) width + "");
        txtY_max.setText((int) height + "");
        txtZ_max.setText((int) imageView.getMaxZoom() + "");
        txtW_max.setText((int) width + "");
        txtH_max.setText((int) height + "");

        seekBarX.setMax((int) width);
        seekBarY.setMax((int) height);
        seekBarZ.setMax((int) imageView.getMaxZoom() * 100);
        seekBarW.setMax((int) width);
        seekBarH.setMax((int) height);
        seekBarD.setMax(10000);

        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtX.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtY.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarZ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtZ.setText("" + (float) progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarW.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtW.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarH.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtH.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarD.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtD.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarZ.setProgress(150);
        seekBarD.setProgress(2000);
    }

    // Define the Handler that receives messages from the thread and update the progress
    public final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            String aResponse = msg.getData().getString(TouchImageView.MESSAGE_KEY);

            if ((null != aResponse)) {
                //Zoom Completed, do something...
                if (aResponse.equals(TouchImageView.MESSAGE_OK))
                    Toast.makeText(MainActivity.this, "zoom completed", Toast.LENGTH_SHORT).show();

                //Zoom Cancled, do something...
                if (aResponse.equals(TouchImageView.MESSAGE_CANCLE))
                    Toast.makeText(MainActivity.this, "zoom cancled", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
