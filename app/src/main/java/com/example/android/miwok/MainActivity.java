/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view that shows the numbers category
        final TextView numbers = (TextView) findViewById(R.id.numbers);

        // Find the view that shows the family category
        TextView family = (TextView) findViewById(R.id.family);

        // Find the view that shows the colors category
        TextView colors = (TextView) findViewById(R.id.colors);

        // Find the view that shows the phrases category
        TextView phrases = (TextView) findViewById(R.id.phrases);

        // Set a clickListener on the numbers view
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent for open the NumbersActivity
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                // Start the NumbersActivity
                startActivity(numbersIntent);
            }
        });

        // Set a clickListener on the family view
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent for open the FamilyActivity
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                // Start the FamilyActivity
                startActivity(familyIntent);
            }
        });

        // Set a clickListener on the colors view
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent for open the ColorsActivity
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                // Start the ColorsActivity
                startActivity(colorsIntent);
            }
        });

        // Set a clickListener on the phrases view
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent for open the PhrasesActivity
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                // Start the PhrasesActivity
                startActivity(phrasesIntent);
            }
        });



    }

    public void openNumbersList(View view) {
        Intent i = new Intent(this, NumbersActivity.class);
        startActivity(i);
    }


}
