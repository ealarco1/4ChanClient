package com.ealarcon.app.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.ealarcon.app.R;

/**
 * Helper class that encapsulates Snackbar actions
 * Created by ealarcon on 6/22/17.
 */

public class FeedbackHelper {

    public static Snackbar buildSnackBar(View view, String message) {
        return buildSnackBar(view, message, R.string.dismiss_action, view1 -> {
        });
    }

    public static Snackbar buildSnackBar(View view, int messageResId) {
        return buildSnackBar(view, messageResId, R.string.dismiss_action, view1 -> {
        });
    }

    public static Snackbar buildSnackBar(View view, int messageResId, int actionResId,
                                         View.OnClickListener actionListener) {
        return Snackbar.make(view, messageResId, Snackbar.LENGTH_LONG)
                .setAction(actionResId, actionListener);
    }

    public static Snackbar buildSnackBar(View view, String message, int actionResId,
                                         View.OnClickListener actionListener) {
        return Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(actionResId, actionListener);
    }
}
