package com.clevertap.android.sdk.inapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.clevertap.android.sdk.R;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class CTInAppNativeHeaderFragment extends CTInAppBasePartialNativeFragment {

    /* loaded from: classes2.dex */
    public class a implements View.OnTouchListener {
        public a() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            CTInAppNativeHeaderFragment.this.p.onTouchEvent(motionEvent);
            return true;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    @RequiresApi(api = 17)
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        View inflate = layoutInflater.inflate(R.layout.inapp_header, viewGroup, false);
        this.q = inflate;
        RelativeLayout relativeLayout = (RelativeLayout) ((FrameLayout) inflate.findViewById(R.id.header_frame_layout)).findViewById(R.id.header_relative_layout);
        relativeLayout.setBackgroundColor(Color.parseColor(this.l.c()));
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.header_linear_layout_2);
        LinearLayout linearLayout2 = (LinearLayout) relativeLayout.findViewById(R.id.header_linear_layout_3);
        Button button = (Button) linearLayout2.findViewById(R.id.header_button_1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout2.findViewById(R.id.header_button_2);
        arrayList.add(button2);
        ImageView imageView = (ImageView) ((LinearLayout) relativeLayout.findViewById(R.id.header_linear_layout_1)).findViewById(R.id.header_icon);
        if (!this.l.n().isEmpty()) {
            CTInAppNotification cTInAppNotification = this.l;
            Bitmap l = cTInAppNotification.l(cTInAppNotification.n().get(0));
            if (l != null) {
                imageView.setImageBitmap(l);
            } else {
                imageView.setVisibility(8);
            }
        } else {
            imageView.setVisibility(8);
        }
        TextView textView = (TextView) linearLayout.findViewById(R.id.header_title);
        textView.setText(this.l.getTitle());
        textView.setTextColor(Color.parseColor(this.l.q()));
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.header_message);
        textView2.setText(this.l.getMessage());
        textView2.setTextColor(Color.parseColor(this.l.o()));
        ArrayList<CTInAppNotificationButton> buttons = this.l.getButtons();
        if (buttons != null && !buttons.isEmpty()) {
            for (int i = 0; i < buttons.size(); i++) {
                if (i < 2) {
                    l((Button) arrayList.get(i), buttons.get(i), i);
                }
            }
        }
        if (this.l.e() == 1) {
            k(button, button2);
        }
        this.q.setOnTouchListener(new a());
        return this.q;
    }
}
