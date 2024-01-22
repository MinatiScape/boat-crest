package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.jose4j.jwk.RsaJsonWebKey;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTCarouselViewPagerAdapter extends PagerAdapter {
    public final ArrayList<String> c;
    public final Context d;
    public final CTInboxMessage e;
    public LayoutInflater f;
    public final LinearLayout.LayoutParams g;
    public final WeakReference<CTInboxListViewFragment> h;
    public final int i;
    public View j;

    /* loaded from: classes2.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ int h;

        public a(int i) {
            this.h = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInboxListViewFragment d = CTCarouselViewPagerAdapter.this.d();
            if (d != null) {
                d.h(CTCarouselViewPagerAdapter.this.i, this.h);
            }
        }
    }

    public CTCarouselViewPagerAdapter(Context context, CTInboxListViewFragment cTInboxListViewFragment, CTInboxMessage cTInboxMessage, LinearLayout.LayoutParams layoutParams, int i) {
        this.d = context;
        this.h = new WeakReference<>(cTInboxListViewFragment);
        this.c = cTInboxMessage.getCarouselImages();
        this.g = layoutParams;
        this.e = cTInboxMessage;
        this.i = i;
    }

    public void c(ImageView imageView, View view, int i, ViewGroup viewGroup) {
        imageView.setVisibility(0);
        try {
            Glide.with(imageView.getContext()).m30load(this.c.get(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(Utils.getThumbnailImage(this.d, Constants.IMAGE_PLACEHOLDER)).error(Utils.getThumbnailImage(this.d, Constants.IMAGE_PLACEHOLDER))).into(imageView);
        } catch (NoSuchMethodError unused) {
            Logger.d("CleverTap SDK requires Glide v4.9.0 or above. Please refer CleverTap Documentation for more info");
            Glide.with(imageView.getContext()).m30load(this.c.get(i)).into(imageView);
        }
        viewGroup.addView(view, this.g);
        view.setOnClickListener(new a(i));
    }

    public CTInboxListViewFragment d() {
        return this.h.get();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.c.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.d.getSystemService("layout_inflater");
        this.f = layoutInflater;
        this.j = layoutInflater.inflate(R.layout.inbox_carousel_image_layout, viewGroup, false);
        try {
            if (this.e.getOrientation().equalsIgnoreCase("l")) {
                c((ImageView) this.j.findViewById(R.id.imageView), this.j, i, viewGroup);
            } else if (this.e.getOrientation().equalsIgnoreCase(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME)) {
                c((ImageView) this.j.findViewById(R.id.squareImageView), this.j, i, viewGroup);
            }
        } catch (NoClassDefFoundError unused) {
            Logger.d("CleverTap SDK requires Glide dependency. Please refer CleverTap Documentation for more info");
        }
        return this.j;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }
}
