package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 * Since the class is declared public, other classes can access it, such as the MainActivity.
 */
public class Word {

    // Both of the strings below are private, since they are only accessed within the class. Since they are private members, we add an 'm' to their variable name to indicate that. For an example, mDefaultTranslation. The 'm' is added to the start of each variable name.

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    // Now the constructor: takes in two Strings and set the translation words from above. The name has to match the class name exactly and has no return type.

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * Get the default translation of the word.
     * @return Default translation
     */
    public String getDefaulTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     * @return Miwok translation
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

}
