package com.coveiot.android.leonardo.dashboard.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public final class PagerViewFragment extends Fragment {
    public String h = "";
    public int i;

    public static PagerViewFragment newInstance(String str, int i, Context context) {
        PagerViewFragment pagerViewFragment = new PagerViewFragment();
        pagerViewFragment.h = str;
        pagerViewFragment.i = i;
        return pagerViewFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.fragment_pager, viewGroup, false);
        CardView cardView = (CardView) viewGroup2.findViewById(R.id.cv_with_image);
        CardView cardView2 = (CardView) viewGroup2.findViewById(R.id.cv_without_image);
        if (this.i != 0) {
            cardView.setVisibility(0);
            cardView2.setVisibility(8);
            ((TextView) viewGroup2.findViewById(R.id.desc_text)).setText("" + this.h);
            ((ImageView) viewGroup2.findViewById(R.id.hand_image)).setBackgroundResource(this.i);
        } else {
            cardView.setVisibility(8);
            cardView2.setVisibility(0);
            ((TextView) viewGroup2.findViewById(R.id.desc_text_without_img)).setText("" + this.h);
        }
        return viewGroup2;
    }

    public static PagerViewFragment newInstance(String str) {
        PagerViewFragment pagerViewFragment = new PagerViewFragment();
        pagerViewFragment.h = str;
        return pagerViewFragment;
    }
}
