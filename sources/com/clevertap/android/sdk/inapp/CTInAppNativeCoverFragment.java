package com.clevertap.android.sdk.inapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class CTInAppNativeCoverFragment extends CTInAppBaseFullNativeFragment {

    /* loaded from: classes2.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppNativeCoverFragment.this.didDismiss(null);
            CTInAppNativeCoverFragment.this.getActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        View inflate = layoutInflater.inflate(R.layout.inapp_cover, viewGroup, false);
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.inapp_cover_frame_layout);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.cover_relative_layout);
        relativeLayout.setBackgroundColor(Color.parseColor(this.l.c()));
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.cover_linear_layout);
        Button button = (Button) linearLayout.findViewById(R.id.cover_button1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout.findViewById(R.id.cover_button2);
        arrayList.add(button2);
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.backgroundImage);
        if (this.l.m(this.k) != null) {
            CTInAppNotification cTInAppNotification = this.l;
            if (cTInAppNotification.l(cTInAppNotification.m(this.k)) != null) {
                CTInAppNotification cTInAppNotification2 = this.l;
                imageView.setImageBitmap(cTInAppNotification2.l(cTInAppNotification2.m(this.k)));
                imageView.setTag(0);
                imageView.setOnClickListener(new CTInAppBaseFragment.a());
            }
        }
        TextView textView = (TextView) relativeLayout.findViewById(R.id.cover_title);
        textView.setText(this.l.getTitle());
        textView.setTextColor(Color.parseColor(this.l.q()));
        TextView textView2 = (TextView) relativeLayout.findViewById(R.id.cover_message);
        textView2.setText(this.l.getMessage());
        textView2.setTextColor(Color.parseColor(this.l.o()));
        ArrayList<CTInAppNotificationButton> buttons = this.l.getButtons();
        if (buttons.size() == 1) {
            int i = this.k;
            if (i == 2) {
                button.setVisibility(8);
            } else if (i == 1) {
                button.setVisibility(4);
            }
            v(button2, buttons.get(0), 0);
        } else if (!buttons.isEmpty()) {
            for (int i2 = 0; i2 < buttons.size(); i2++) {
                if (i2 < 2) {
                    v((Button) arrayList.get(i2), buttons.get(i2), i2);
                }
            }
        }
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        closeImageView.setOnClickListener(new a());
        if (!this.l.u()) {
            closeImageView.setVisibility(8);
        } else {
            closeImageView.setVisibility(0);
        }
        return inflate;
    }
}
