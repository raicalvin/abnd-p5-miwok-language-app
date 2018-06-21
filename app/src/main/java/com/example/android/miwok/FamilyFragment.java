package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    /** handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // pause playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // audiofocus_gain case means we have regained focus and can resume playback
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // we lost audio focus and stop playback and cleanup resources
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Now create an ArrayList of Word objects. Each element is a Word object
        final ArrayList<Word> words = new ArrayList<Word>();

        // Add every word you want to put into the list as a Word object using the custom-created Word class
        words.add(new Word("father", "epe", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "eta", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalittie", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "tete", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int resource = words.get(position).getRawResourceID();

                // Release the media player if it currently exists because we are about to play a different sound file.
                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(getActivity(), resource);

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

        return rootView;

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
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
