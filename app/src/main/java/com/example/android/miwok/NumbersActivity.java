package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create array to store number words
        /*
        String[] words = new String[10];

        words[0] = "one";
        words[1] = "two";
        words[2] = "three";
        words[3] = "four";
        words[4] = "five";
        words[5] = "six";
        words[6] = "seven";
        words[7] = "eight";
        words[8] = "nine";
        words[9] = "ten";

        Log.v("NumbersActivity", "Word at index 0: " + words[0]);
        Log.v("NumbersActivity", "Word at index 1: " + words[1]);
        Log.v("NumbersActivity", "Word at index 2: " + words[2]);
        Log.v("NumbersActivity", "Word at index 3: " + words[3]);
        Log.v("NumbersActivity", "Word at index 4: " + words[4]);
        Log.v("NumbersActivity", "Word at index 5: " + words[5]);
        Log.v("NumbersActivity", "Word at index 6: " + words[6]);
        Log.v("NumbersActivity", "Word at index 7: " + words[7]);
        Log.v("NumbersActivity", "Word at index 0: " + words[8]);
        Log.v("NumbersActivity", "Word at index 0: " + words[9]);
        */

        // Now create an ArrayList to store the words
        // ArrayList<String> words = new ArrayList<String>();

        // Now create an ArrayList of Word objects. Each element is a Word object
        final ArrayList<Word> words = new ArrayList<Word>();

        // Add every word you want to put into the list as a Word object using the custom-created Word class
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        /*
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("size");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
        */

        // Use the LogCat to determine the object at each index
        /*
        Log.v("NumbersActivity", "Word at index 0: " + words.get(0));
        Log.v("NumbersActivity", "Word at index 1: " + words.get(1));
        Log.v("NumbersActivity", "Word at index 2: " + words.get(2));
        Log.v("NumbersActivity", "Word at index 3: " + words.get(3));
        Log.v("NumbersActivity", "Word at index 4: " + words.get(4));
        Log.v("NumbersActivity", "Word at index 5: " + words.get(5));
        Log.v("NumbersActivity", "Word at index 6: " + words.get(6));
        Log.v("NumbersActivity", "Word at index 7: " + words.get(7));
        Log.v("NumbersActivity", "Word at index 8: " + words.get(8));
        Log.v("NumbersActivity", "Word at index 9: " + words.get(9));
        */

        // Create a variable to reference LinearLayout in numbers_activity.xml
        // LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        // Create a while loop to add in the TextViews
        /*
        int i = 0;
        while (i < words.size()) {
            TextView textViewToAdd = new TextView(this);
            textViewToAdd.setText(words.get(i));
            rootView.addView(textViewToAdd);
            i++;
        }
        */

        /*
        for (int i = 0; i < words.size(); i++) {
            TextView textViewToAdd = new TextView(this);
            textViewToAdd.setText(words.get(i));
            rootView.addView(textViewToAdd);
        }
        */

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        // ArrayAdapter<String> itemsAdapter =
        //         new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        // ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, R.layout.list_item, words);
        // Need to update the ArrayAdapter to take in Word objects
        // ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);
        // Instead of the ArrayAdapter, we'll be using the WordAdapter class we just created
        // The WordAdapter class will take in two inputs: the context and the list of words since we'll be inflating the layout within the class instead of passing it as an argument here:
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);
        // GridView gridview = (GridView) findViewById(R.id.gridview);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int resource = words.get(position).getRawResourceID();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), resource);
                mediaPlayer.start();
            }
        });


    }
}
