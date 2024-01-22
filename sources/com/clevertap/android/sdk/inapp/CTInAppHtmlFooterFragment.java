package com.clevertap.android.sdk.inapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.clevertap.android.sdk.R;
/* loaded from: classes2.dex */
public class CTInAppHtmlFooterFragment extends CTInAppBasePartialHtmlFragment {
    @Override // com.clevertap.android.sdk.inapp.CTInAppBasePartialHtmlFragment
    public ViewGroup m(View view) {
        return (ViewGroup) view.findViewById(R.id.inapp_html_footer_frame_layout);
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBasePartialHtmlFragment
    public View n(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.inapp_html_footer, viewGroup, false);
    }
}
