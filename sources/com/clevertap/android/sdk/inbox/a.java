package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import com.clevertap.android.sdk.R;
/* loaded from: classes2.dex */
public class a extends CTInboxBaseMessageViewHolder {
    public final TextView o;
    public final RelativeLayout p;
    public final CTCarouselViewPager q;
    public final LinearLayout r;

    /* renamed from: com.clevertap.android.sdk.inbox.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class C0230a implements ViewPager.OnPageChangeListener {
        public final Context h;
        public final ImageView[] i;

        public C0230a(a aVar, Context context, a aVar2, ImageView[] imageViewArr, CTInboxMessage cTInboxMessage) {
            this.h = context;
            this.i = imageViewArr;
            imageViewArr[0].setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ct_selected_dot, null));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            for (ImageView imageView : this.i) {
                imageView.setImageDrawable(ResourcesCompat.getDrawable(this.h.getResources(), R.drawable.ct_unselected_dot, null));
            }
            this.i[i].setImageDrawable(ResourcesCompat.getDrawable(this.h.getResources(), R.drawable.ct_selected_dot, null));
        }
    }

    public a(@NonNull View view) {
        super(view);
        this.q = (CTCarouselViewPager) view.findViewById(R.id.image_carousel_viewpager);
        this.r = (LinearLayout) view.findViewById(R.id.sliderDots);
        this.o = (TextView) view.findViewById(R.id.carousel_timestamp);
        this.p = (RelativeLayout) view.findViewById(R.id.body_linear_layout);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    public void c(CTInboxMessage cTInboxMessage, CTInboxListViewFragment cTInboxListViewFragment, int i) {
        super.c(cTInboxMessage, cTInboxListViewFragment, i);
        CTInboxListViewFragment f = f();
        Context applicationContext = cTInboxListViewFragment.getActivity().getApplicationContext();
        CTInboxMessageContent cTInboxMessageContent = cTInboxMessage.getInboxMessageContents().get(0);
        this.o.setVisibility(0);
        if (cTInboxMessage.isRead()) {
            this.readDot.setVisibility(8);
        } else {
            this.readDot.setVisibility(0);
        }
        this.o.setText(b(cTInboxMessage.getDate()));
        this.o.setTextColor(Color.parseColor(cTInboxMessageContent.getTitleColor()));
        this.p.setBackgroundColor(Color.parseColor(cTInboxMessage.getBgColor()));
        this.q.setAdapter(new CTCarouselViewPagerAdapter(applicationContext, cTInboxListViewFragment, cTInboxMessage, (LinearLayout.LayoutParams) this.q.getLayoutParams(), i));
        int size = cTInboxMessage.getInboxMessageContents().size();
        if (this.r.getChildCount() > 0) {
            this.r.removeAllViews();
        }
        ImageView[] imageViewArr = new ImageView[size];
        j(imageViewArr, size, applicationContext, this.r);
        imageViewArr[0].setImageDrawable(ResourcesCompat.getDrawable(applicationContext.getResources(), R.drawable.ct_selected_dot, null));
        this.q.addOnPageChangeListener(new C0230a(this, cTInboxListViewFragment.getActivity().getApplicationContext(), this, imageViewArr, cTInboxMessage));
        this.p.setOnClickListener(new e(i, cTInboxMessage, (String) null, f, (ViewPager) this.q, true, -1));
        markItemAsRead(cTInboxMessage, i);
    }
}
