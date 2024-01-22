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
public class b extends CTInboxBaseMessageViewHolder {
    public final RelativeLayout o;
    public final CTCarouselViewPager p;
    public final LinearLayout q;
    public final TextView r;
    public final TextView s;
    public final TextView t;

    /* loaded from: classes2.dex */
    public class a implements ViewPager.OnPageChangeListener {
        public final Context h;
        public final ImageView[] i;
        public final CTInboxMessage j;
        public final b k;

        public a(b bVar, Context context, b bVar2, ImageView[] imageViewArr, CTInboxMessage cTInboxMessage) {
            this.h = context;
            this.k = bVar2;
            this.i = imageViewArr;
            this.j = cTInboxMessage;
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
            this.k.r.setText(this.j.getInboxMessageContents().get(i).getTitle());
            this.k.r.setTextColor(Color.parseColor(this.j.getInboxMessageContents().get(i).getTitleColor()));
            this.k.s.setText(this.j.getInboxMessageContents().get(i).getMessage());
            this.k.s.setTextColor(Color.parseColor(this.j.getInboxMessageContents().get(i).getMessageColor()));
        }
    }

    public b(@NonNull View view) {
        super(view);
        this.p = (CTCarouselViewPager) view.findViewById(R.id.image_carousel_viewpager);
        this.q = (LinearLayout) view.findViewById(R.id.sliderDots);
        this.r = (TextView) view.findViewById(R.id.messageTitle);
        this.s = (TextView) view.findViewById(R.id.messageText);
        this.t = (TextView) view.findViewById(R.id.timestamp);
        this.o = (RelativeLayout) view.findViewById(R.id.body_linear_layout);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    public void c(CTInboxMessage cTInboxMessage, CTInboxListViewFragment cTInboxListViewFragment, int i) {
        super.c(cTInboxMessage, cTInboxListViewFragment, i);
        CTInboxListViewFragment f = f();
        Context applicationContext = cTInboxListViewFragment.getActivity().getApplicationContext();
        CTInboxMessageContent cTInboxMessageContent = cTInboxMessage.getInboxMessageContents().get(0);
        this.r.setVisibility(0);
        this.s.setVisibility(0);
        this.r.setText(cTInboxMessageContent.getTitle());
        this.r.setTextColor(Color.parseColor(cTInboxMessageContent.getTitleColor()));
        this.s.setText(cTInboxMessageContent.getMessage());
        this.s.setTextColor(Color.parseColor(cTInboxMessageContent.getMessageColor()));
        if (cTInboxMessage.isRead()) {
            this.readDot.setVisibility(8);
        } else {
            this.readDot.setVisibility(0);
        }
        this.t.setVisibility(0);
        this.t.setText(b(cTInboxMessage.getDate()));
        this.t.setTextColor(Color.parseColor(cTInboxMessageContent.getTitleColor()));
        this.o.setBackgroundColor(Color.parseColor(cTInboxMessage.getBgColor()));
        this.p.setAdapter(new CTCarouselViewPagerAdapter(applicationContext, cTInboxListViewFragment, cTInboxMessage, (LinearLayout.LayoutParams) this.p.getLayoutParams(), i));
        int size = cTInboxMessage.getInboxMessageContents().size();
        if (this.q.getChildCount() > 0) {
            this.q.removeAllViews();
        }
        ImageView[] imageViewArr = new ImageView[size];
        j(imageViewArr, size, applicationContext, this.q);
        imageViewArr[0].setImageDrawable(ResourcesCompat.getDrawable(applicationContext.getResources(), R.drawable.ct_selected_dot, null));
        this.p.addOnPageChangeListener(new a(this, cTInboxListViewFragment.getActivity().getApplicationContext(), this, imageViewArr, cTInboxMessage));
        this.o.setOnClickListener(new e(i, cTInboxMessage, (String) null, f, (ViewPager) this.p, true, -1));
        markItemAsRead(cTInboxMessage, i);
    }
}
