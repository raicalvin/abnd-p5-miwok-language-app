package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class WordAdapter extends ArrayAdapter<Word> {

    // This is the constructor for the WordAdapter which takes in two arguments: the context (the activity) and the ArrayList of word objects, in this case words.
    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
        Log.i(TAG, "OMGGGGGGGGGG the CONTEXT returned " + context);

    }



    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Get the position for this data item
        Word word = getItem(position);

        // First check to make sure the existing view is being reused, otherwise we need to inflate the view
        if (convertView == null) {
            // If there are no more scrap views in the scrap pile to be reused, we'll just create one by inflating from the XML layout file that we used.
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Now we need to get the information that will be populated in the two TextViews in our layout.
        // These are the two TextViews that we are referencing from the custom XML layout
        TextView miwokTranslationTV = (TextView) convertView.findViewById(R.id.miwok_text_view);
        TextView defaultTranslationTV = (TextView) convertView.findViewById(R.id.default_text_view);

        // Now populate the two above views using the data from the word object
        miwokTranslationTV.setText(word.getMiwokTranslation());
        defaultTranslationTV.setText(word.getDefaulTranslation());

        Log.i(TAG, "OMGGGGGGGGGG the view returned " + word.getImageResourceID());

        ImageView iconImageIV = (ImageView) convertView.findViewById(R.id.image);

        if (word.hasImage()) {
            // Set the ImageView to the image resource ID specified in the current word
            iconImageIV.setImageResource(word.getImageResourceID());

            // Make sure the View is visible (since views get reused)
            iconImageIV.setVisibility(View.VISIBLE);
        } else {
            iconImageIV.setVisibility(View.GONE);
        }

        // Now return the completed and populated View that we want to render on the screen
        return convertView;

    }
}
