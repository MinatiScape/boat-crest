package com.mappls.sdk.maps.widgets.indoor;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.mappls.sdk.maps.R;
/* loaded from: classes11.dex */
public class FloorView extends FrameLayout {
    public Floor h;
    public TextView i;
    public View j;
    public ObjectAnimator k;
    public ColorStateList l;
    public ObjectAnimator m;

    public FloorView(@NonNull Context context, @NonNull Floor floor) {
        super(context);
        this.h = floor;
        a(context);
    }

    public final void a(@NonNull Context context) {
        View view = new View(context);
        this.j = view;
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(ContextCompat.getColor(context, R.color.mappls_maps_blue_indoor));
        if (Build.VERSION.SDK_INT >= 16) {
            this.j.setBackground(gradientDrawable);
        }
        this.j.setVisibility(4);
        addView(this.j);
        TextView textView = new TextView(context);
        this.i = textView;
        textView.setTextAppearance(context, R.style.mappls_maps_layer_radio_button);
        this.l = this.i.getTextColors();
        this.i.setText(this.h.getName());
        this.i.setGravity(17);
        this.i.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.i);
    }

    public Floor getFloor() {
        return this.h;
    }

    public void setLoading() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.j, PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f));
        this.k = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setDuration(500L);
        this.k.start();
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this.i, "textColor", this.l.getDefaultColor(), -1);
        this.m = ofInt;
        ofInt.setEvaluator(new ArgbEvaluator());
        this.m.setDuration(500L);
        this.m.start();
        this.j.setVisibility(0);
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        ObjectAnimator objectAnimator = this.m;
        if (objectAnimator != null) {
            objectAnimator.end();
        }
        if (z) {
            this.j.setVisibility(0);
            this.i.setTextColor(Color.parseColor("#4A90E2"));
        } else {
            this.j.setVisibility(4);
            this.i.setTextColor(this.l);
        }
        this.k = null;
        this.m = null;
    }
}
