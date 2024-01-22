package com.clevertap.android.sdk.inapp;

import android.widget.RelativeLayout;
/* loaded from: classes2.dex */
public class CTInAppHtmlCoverFragment extends CTInAppBaseFullHtmlFragment {
    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFullHtmlFragment
    public RelativeLayout.LayoutParams getLayoutParamsForCloseButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(11, this.webView.getId());
        layoutParams.addRule(10, this.webView.getId());
        int h = h(40) / 4;
        layoutParams.setMargins(0, h, h, 0);
        return layoutParams;
    }
}
