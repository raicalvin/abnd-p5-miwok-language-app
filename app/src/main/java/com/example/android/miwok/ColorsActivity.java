package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class ColorsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Now create an ArrayList of Word objects. Each element is a Word object
        final ArrayList<Word> words = new ArrayList<Word>();

        // Add every word you want to put into the list as a Word object using the custom-created Word class
        words.add(new Word("red", "weretti", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "takaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("dusty yellow", "topiise", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiite", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int resource = words.get(position).getRawResourceID();

                // Release the media player if it currently exists because we are about to play a different sound file.
                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(getApplicationContext(), resource);

                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT) {
                    // pause playback
                    mMediaPlayer.pause();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // stop playback
                    mMediaPlayer.stop();
                    releaseMediaPlayer();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // lower volume
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // resume playback
                }
            }
        };
    }

    /**
     * Clean up the media player by releasing its resources
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a song
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources because we no longer need them
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that setting the media player to null is an easy wayt o tell that the media player is not configured to play an audio file at the moment
            mMediaPlayer = null;

            // regardless of whether or not we were granted audio focus, abandon it. This also unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        releaseMediaPlayer();
        super.onStop();
    }
}
