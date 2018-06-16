package com.example.android.miwok;

import android.media.Image;

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

    /** Resource ID for the image */
    private int mImageResourceID = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    /** Resource ID for the audio file */
    private int mRawAudioID;

    // Now the constructor: takes in two Strings and set the translation words from above. The name has to match the class name exactly and has no return type.

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation, int rawID) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mRawAudioID = rawID;

    }

    /**
     * Create a new Word object with resource ID
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with (such as English)
     * @param miwokTranslation is the word in the Miwok language
     * @param resourceID is the resource ID for the image to be displayed
     */
    public Word(String defaultTranslation, String miwokTranslation, int resourceID, int rawID) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = resourceID;
        mRawAudioID = rawID;
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

    /**
     * Get the image resource ID of the icon associated with the word
     * @return Image Resource ID
     */
    public int getImageResourceID() { return mImageResourceID; }

    /**
     * Returns whether or not there is an image for this word
     * @return true or false depending on image presence
     */
    public boolean hasImage() {
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }

    public int getRawResourceID() { return mRawAudioID; }

    /**
     * Returns a string containing a concise, human-readable description of this
     * object. Subclasses are encouraged to override this method and provide an
     * implementation that takes into account the object's type and data. The
     * default implementation is equivalent to the following expression:
     * <pre>
     *   getClass().getName() + '@' + Integer.toHexString(hashCode())</pre>
     * <p>See <a href="{@docRoot}reference/java/lang/Object.html#writing_toString">Writing a useful
     * {@code toString} method</a>
     * if you intend implementing your own {@code toString} method.
     *
     * @return a printable representation of this object.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
