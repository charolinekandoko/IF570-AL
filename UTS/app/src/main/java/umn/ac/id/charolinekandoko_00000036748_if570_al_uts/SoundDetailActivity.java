package umn.ac.id.charolinekandoko_00000036748_if570_al_uts;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SoundDetailActivity extends AppCompatActivity {

    private Button btnPlay;
    private TextView tvJudul, tvKeterangan;
    private ImageView soundDetail;

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_detail);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        tvJudul = (TextView) findViewById(R.id.etJudul);
        tvKeterangan = (TextView) findViewById(R.id.etKeterangan);
        soundDetail = (ImageView) findViewById(R.id.soundDetail);

        Intent detailIntent = getIntent();
        Bundle bundle = detailIntent.getExtras();
        SumberSound ss =(SumberSound) bundle.getSerializable("SoundDetail");

        String judul = detailIntent.getStringExtra("NamaSound");
        getSupportActionBar(). setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(judul);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvJudul.setText(ss.getJudul());
        tvKeterangan.setText(ss.getKeterangan());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mp = MediaPlayer.create(SoundDetailActivity.this, ss.getSoundURI());
                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                } catch (Exception e){
                    e.printStackTrace();
                    if(mp != null){
                        mp.release();
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        mp.stop();
        mp.release();
        return true;
    }
}
