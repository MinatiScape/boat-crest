package com.coveiot.android.theme.compundview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.coveiot.android.theme.R;
@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes7.dex */
public class OtpEditText extends EditText {
    public static final String XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    public float h;
    public float i;
    public float j;
    public float k;
    public int l;
    public View.OnClickListener m;
    public float n;
    public float o;
    public Paint p;
    public int[][] q;
    public int[] r;

    /* loaded from: classes7.dex */
    public class a implements ActionMode.Callback {
        public a(OtpEditText otpEditText) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
    }

    /* loaded from: classes7.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OtpEditText otpEditText = OtpEditText.this;
            otpEditText.setSelection(otpEditText.getText().length());
            if (OtpEditText.this.m != null) {
                OtpEditText.this.m.onClick(view);
            }
        }
    }

    public OtpEditText(Context context) {
        super(context);
        this.h = 24.0f;
        this.j = 4.0f;
        this.k = 8.0f;
        this.l = 4;
        this.n = 1.0f;
        this.o = 2.0f;
        this.q = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.r = new int[]{-16711936, -1, -7829368};
        new ColorStateList(this.q, this.r);
    }

    public final void b(Context context, AttributeSet attributeSet) {
        float f = context.getResources().getDisplayMetrics().density;
        this.n *= f;
        this.o *= f;
        Paint paint = new Paint(getPaint());
        this.p = paint;
        paint.setStrokeWidth(this.n);
        if (!isInEditMode()) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorControlActivated, typedValue, true);
            this.r[0] = typedValue.data;
            context.getTheme().resolveAttribute(R.color.main_text_color, typedValue, true);
            this.r[1] = typedValue.data;
            context.getTheme().resolveAttribute(R.attr.colorControlHighlight, typedValue, true);
            this.r[2] = typedValue.data;
        }
        setBackgroundResource(0);
        this.h *= f;
        this.k = f * this.k;
        int attributeIntValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLength", 4);
        this.l = attributeIntValue;
        this.j = attributeIntValue;
        super.setCustomSelectionActionModeCallback(new a(this));
        super.setOnClickListener(new b());
    }

    public final void c(boolean z, int i) {
        if (getText().length() != this.l) {
            if (z) {
                this.p.setColor(getResources().getColor(R.color.main_text_color));
            } else if (i < getText().length()) {
                this.p.setColor(getResources().getColor(R.color.main_text_color));
            } else {
                this.p.setColor(getResources().getColor(R.color.color_e2e2e2));
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        float f;
        int width = (getWidth() - getPaddingRight()) - getPaddingLeft();
        float f2 = this.h;
        if (f2 < 0.0f) {
            this.i = width / ((this.j * 2.0f) - 1.0f);
        } else {
            float f3 = this.j;
            this.i = (width - (f2 * (f3 - 1.0f))) / f3;
        }
        int paddingLeft = getPaddingLeft();
        int height = getHeight() - getPaddingBottom();
        Editable text = getText();
        int length = text.length();
        float[] fArr = new float[length];
        getPaint().getTextWidths(getText(), 0, length, fArr);
        getPaint().setColor(Color.parseColor("#DEFFFFFF"));
        int i2 = 0;
        while (i2 < this.j) {
            c(i2 == length, i2);
            float f4 = paddingLeft;
            float f5 = height;
            canvas.drawLine(f4, f5, f4 + this.i, f5, this.p);
            if (getText().length() > i2) {
                i = i2;
                canvas.drawText(text, i2, i2 + 1, ((this.i / 2.0f) + f4) - (fArr[0] / 2.0f), f5 - this.k, getPaint());
            } else {
                i = i2;
            }
            float f6 = this.h;
            if (f6 < 0.0f) {
                f = f4 + (this.i * 2.0f);
            } else {
                f = f4 + this.i + f6;
            }
            paddingLeft = (int) f;
            i2 = i + 1;
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        throw new RuntimeException("setCustomSelectionActionModeCallback() not supported.");
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.m = onClickListener;
    }

    public void updateUnderLineColor(boolean z) {
        if (!z) {
            this.p.setStrokeWidth(this.o);
            this.p.setColor(getResources().getColor(R.color.main_text_color));
        } else {
            this.p.setColor(getResources().getColor(R.color.main_text_color));
        }
        invalidate();
    }

    public OtpEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 24.0f;
        this.j = 4.0f;
        this.k = 8.0f;
        this.l = 4;
        this.n = 1.0f;
        this.o = 2.0f;
        this.q = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.r = new int[]{-16711936, -1, -7829368};
        new ColorStateList(this.q, this.r);
        b(context, attributeSet);
    }

    public OtpEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 24.0f;
        this.j = 4.0f;
        this.k = 8.0f;
        this.l = 4;
        this.n = 1.0f;
        this.o = 2.0f;
        this.q = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.r = new int[]{-16711936, -1, -7829368};
        new ColorStateList(this.q, this.r);
        b(context, attributeSet);
    }

    @TargetApi(21)
    public OtpEditText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.h = 24.0f;
        this.j = 4.0f;
        this.k = 8.0f;
        this.l = 4;
        this.n = 1.0f;
        this.o = 2.0f;
        this.q = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.r = new int[]{-16711936, -1, -7829368};
        new ColorStateList(this.q, this.r);
        b(context, attributeSet);
    }
}
