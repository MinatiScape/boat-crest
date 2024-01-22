package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.callback.CropBoundsChangeListener;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
/* loaded from: classes12.dex */
public class UCropView extends FrameLayout {
    public GestureCropImageView h;
    public final OverlayView i;

    /* loaded from: classes12.dex */
    public class a implements CropBoundsChangeListener {
        public a() {
        }

        @Override // com.yalantis.ucrop.callback.CropBoundsChangeListener
        public void onCropAspectRatioChanged(float f) {
            UCropView.this.i.setTargetAspectRatio(f);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements OverlayViewChangeListener {
        public b() {
        }

        @Override // com.yalantis.ucrop.callback.OverlayViewChangeListener
        public void onCropRectUpdated(RectF rectF) {
            UCropView.this.h.setCropRect(rectF);
        }
    }

    public UCropView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void c() {
        this.h.setCropBoundsChangeListener(new a());
        this.i.setOverlayViewChangeListener(new b());
    }

    @NonNull
    public GestureCropImageView getCropImageView() {
        return this.h;
    }

    @NonNull
    public OverlayView getOverlayView() {
        return this.i;
    }

    public void resetCropImageView() {
        removeView(this.h);
        this.h = new GestureCropImageView(getContext());
        c();
        this.h.setCropRect(getOverlayView().getCropViewRect());
        addView(this.h, 0);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public UCropView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.ucrop_view, (ViewGroup) this, true);
        this.h = (GestureCropImageView) findViewById(R.id.image_view_crop);
        OverlayView overlayView = (OverlayView) findViewById(R.id.view_overlay);
        this.i = overlayView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ucrop_UCropView);
        overlayView.processStyledAttributes(obtainStyledAttributes);
        this.h.processStyledAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        c();
    }
}
