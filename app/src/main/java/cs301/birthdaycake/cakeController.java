package cs301.birthdaycake;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class cakeController implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private CakeView myCakeView;
    private cakeModel model;

    public cakeController(CakeView view) {
        myCakeView = view;
        model = view.getCakeModel();

    }

    public void onClick(View view) {
        model.lit = false;
        myCakeView.invalidate();
        Log.d("mytag","CLICKED");
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        model.candles = b;
        myCakeView.invalidate();


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(b) {
            model.candleNum = i;
            myCakeView.invalidate();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public boolean onTouch(View view, MotionEvent motionEvent) {

        return true;
    }
}
