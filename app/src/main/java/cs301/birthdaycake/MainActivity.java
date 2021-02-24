package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        CakeView theCake = findViewById(R.id.cakeview);
        cakeController theController = new cakeController(theCake);
        Button blowOut = (Button)findViewById(R.id.button);
        blowOut.setOnClickListener(theController);
        CompoundButton drawCandles = findViewById(R.id.Candles);
        drawCandles.setOnCheckedChangeListener(theController);
        SeekBar mySeekBar = findViewById(R.id.seekBar3);
        mySeekBar.setOnSeekBarChangeListener(theController);
    }
    public void goodbye(View button) {
        System.out.println("Goodbye");
    }

}
