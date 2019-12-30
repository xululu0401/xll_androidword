package com.xululu.alertdlgleakdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.ViewTreeObserver;

/**
 * Author: pipilu
 * Time: 2019-12-30 11:31
 */
public class MyListener implements DialogInterface.OnClickListener {

    private DialogInterface.OnClickListener mListener;

    public static MyListener wrap(DialogInterface.OnClickListener listener) {
        return new MyListener(listener);
    }

    private MyListener(DialogInterface.OnClickListener listener) {
        this.mListener = listener;
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (null != mListener) {
            mListener.onClick(dialog, which);
        }
    }

    public void clearOnDetach(Dialog dialog) {
        dialog.getWindow().getDecorView()
                .getViewTreeObserver()
                .addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
                    @Override
                    public void onWindowAttached() {

                    }

                    @Override
                    public void onWindowDetached() {
                        mListener = null;
                    }
                });
    }
}
