package si.majcn.pritrkavanje;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {

    private SoundPool[] bells;

    private int counter = 0;

    private int speed = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bells = new SoundPool[4];
        for (int i = 0; i < bells.length; i++) {
            bells[i] = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        final int z1 = bells[0].load(this, R.raw.zvon_1, 1);
        final int z2 = bells[1].load(this, R.raw.zvon_2, 1);
        final int z3 = bells[2].load(this, R.raw.zvon_3, 1);
        final int z4 = bells[3].load(this, R.raw.zvon_4, 1);

        SeekBar sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speed = progress + 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (counter++ % 3 == 0) {
                    bells[3].play(z4, 0.2f, 0.2f, 1, 0, 1f);
                } else {
                    bells[1].play(z2, 0.2f, 0.2f, 1, 0, 1f);
                }
                handler.postDelayed(this, speed);
            }
        };
        handler.post(runnable);

        findViewById(R.id.global).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bells[0].play(z1, 1, 1, 1, 0, 1f);
            }
        });
    }
}
