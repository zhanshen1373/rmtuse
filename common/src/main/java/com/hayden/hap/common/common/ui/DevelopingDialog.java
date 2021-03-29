package com.hayden.hap.common.common.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import test.demo.mylibrary.R;


/**
 * “开发中” 提示框
 * Created by liuyang on 2017/2/24.
 */

public class DevelopingDialog extends Dialog {
    public DevelopingDialog(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_developing, null);
        TextView okBtn = (TextView) layout.findViewById(R.id.btnOk);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout);
    }
}
