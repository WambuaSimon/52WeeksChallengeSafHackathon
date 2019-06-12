package com.wizag.a52weekssavingchallenge.utils;

import android.widget.EditText;

public class EditTextValidator {

    public static void setError(EditText editText, String errorString) {

        editText.setError(errorString);

    }

    public static void clearError(EditText editText) {

        editText.setError(null);

    }


    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }

    public static boolean inRange(EditText editText) {
        if (editText.getText().toString().trim().length() >= 0) {
            if (Integer.parseInt(editText.getText().toString()) <= 50000000) {
                return true;
            }
        }
        return true;
    }


}
