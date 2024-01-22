package com.coveiot.android.activitymodes.swipebutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.content.ContextCompat;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public class SlideButton extends FrameLayout {
    public TextView h;
    public SlideBar i;
    public SlideButtonListener j;
    public OnSlideChangeListener k;
    public int l;

    /* loaded from: classes2.dex */
    public interface OnSlideChangeListener {
        void onSlideChange(float f);

        void onStart();

        void onStop();
    }

    /* loaded from: classes2.dex */
    public interface SlideButtonListener {
        void onSlide();
    }

    public SlideButton(Context context) {
        super(context);
        init(null);
    }

    public int dpToPixels(int i) {
        return (int) (i * getContext().getResources().getDisplayMetrics().density);
    }

    public TextView getTexView() {
        return this.h;
    }

    public void init(@Nullable AttributeSet attributeSet) {
        FrameLayout.inflate(getContext(), R.layout.slider_item, this);
        this.l = dpToPixels(16);
        this.i = new SlideBar(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        this.i.setLayoutParams(layoutParams);
        this.i.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.back_slide_layer));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.slider_button, 0, 0);
            int i = R.styleable.slider_button_text;
            if (obtainStyledAttributes.hasValue(i)) {
                setText(obtainStyledAttributes.getString(i));
            }
            int i2 = R.styleable.slider_button_thumb;
            if (obtainStyledAttributes.hasValue(i2)) {
                this.i.setThumb(obtainStyledAttributes.getDrawable(i2));
            } else {
                this.i.setThumb(ContextCompat.getDrawable(getContext(), R.drawable.swipe_circle_button));
            }
            int i3 = R.styleable.slider_button_thumbOffset;
            if (obtainStyledAttributes.hasValue(i3)) {
                this.l += obtainStyledAttributes.getDimensionPixelSize(i3, dpToPixels(10));
            }
            int i4 = R.styleable.slider_button_sliderBackground;
            if (obtainStyledAttributes.hasValue(i4)) {
                setBackgroundDrawable(obtainStyledAttributes.getDrawable(i4));
            } else {
                setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.back_slide_button));
            }
            obtainStyledAttributes.getDimensionPixelSize(R.styleable.slider_button_textSize, dpToPixels(14));
            obtainStyledAttributes.getColor(R.styleable.slider_button_textColor, -1);
            obtainStyledAttributes.recycle();
        }
        setThumbOffset(this.l);
        addView(this.i);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.i.setEnabled(z);
        this.i.getThumb().setColorFilter(!z ? ContextCompat.getColor(getContext(), R.color.disabled_filter) : 0, PorterDuff.Mode.XOR);
    }

    public void setOnSlideChangeListener(OnSlideChangeListener onSlideChangeListener) {
        this.k = onSlideChangeListener;
    }

    public void setProgress(int i) {
        SlideBar slideBar = this.i;
        if (slideBar != null) {
            slideBar.setProgress(i);
        }
    }

    public void setSlideButtonListener(SlideButtonListener slideButtonListener) {
        this.j = slideButtonListener;
    }

    public void setText(@StringRes int i) {
    }

    public void setText(CharSequence charSequence) {
    }

    public void setThumb(Drawable drawable) {
        this.i.setThumb(drawable);
    }

    public void setThumbOffset(int i) {
        this.i.setThumbOffset(i);
    }

    public SlideButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    /* loaded from: classes2.dex */
    public class SlideBar extends AppCompatSeekBar {
        public Drawable i;
        public SeekBar.OnSeekBarChangeListener j;

        /* loaded from: classes2.dex */
        public class a implements SeekBar.OnSeekBarChangeListener {
            public a() {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (i > 80) {
                    SlideBar slideBar = SlideBar.this;
                    slideBar.c(i / slideBar.getMax());
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        }

        public SlideBar(Context context) {
            super(context);
            this.j = new a();
            init();
        }

        public final void b() {
            if (SlideButton.this.j != null) {
                SlideButton.this.j.onSlide();
            }
        }

        public final void c(float f) {
            if (SlideButton.this.k != null) {
                SlideButton.this.k.onSlideChange(f);
            }
        }

        @Override // android.widget.AbsSeekBar
        public Drawable getThumb() {
            return this.i;
        }

        public void init() {
            setMax(100);
            setOnSeekBarChangeListener(this.j);
        }

        @Override // android.widget.AbsSeekBar, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                if (!this.i.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    return false;
                }
                super.onTouchEvent(motionEvent);
            } else if (motionEvent.getAction() == 1) {
                if (getProgress() > 50) {
                    b();
                }
                setProgress(0);
            } else {
                super.onTouchEvent(motionEvent);
            }
            return true;
        }

        @Override // android.widget.AbsSeekBar
        public void setThumb(Drawable drawable) {
            super.setThumb(drawable);
            this.i = drawable;
        }

        public SlideBar(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.j = new a();
            init();
        }

        public SlideBar(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.j = new a();
            init();
        }
    }

    public SlideButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    @TargetApi(21)
    public SlideButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }
}
