package com.coveiot.android.theme.compundview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class PinEntryEditText extends AppCompatEditText {
    public static final String XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    public int[][] n;
    public int[] o;
    public float p;
    public float q;
    public float r;
    public float s;
    public float t;
    public int u;
    public View.OnClickListener v;
    public float w;
    public float x;
    public Paint y;

    /* loaded from: classes7.dex */
    public class a implements ActionMode.Callback {
        public a(PinEntryEditText pinEntryEditText) {
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
            PinEntryEditText pinEntryEditText = PinEntryEditText.this;
            pinEntryEditText.setSelection(pinEntryEditText.getText().length());
            if (PinEntryEditText.this.v != null) {
                PinEntryEditText.this.v.onClick(view);
            }
        }
    }

    public PinEntryEditText(Context context) {
        super(context);
        this.n = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.o = new int[]{-16711936, ViewCompat.MEASURED_STATE_MASK, -7829368};
        new ColorStateList(this.n, this.o);
        this.q = 24.0f;
        this.s = 6.0f;
        this.t = 8.0f;
        this.u = 6;
        this.w = 1.0f;
        this.x = 2.0f;
    }

    public final void e(Context context, AttributeSet attributeSet) {
        float f = context.getResources().getDisplayMetrics().density;
        this.w *= f;
        this.x *= f;
        Paint paint = new Paint(getPaint());
        this.y = paint;
        paint.setStrokeWidth(this.w);
        if (!isInEditMode()) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorControlActivated, typedValue, true);
            this.o[0] = typedValue.data;
            context.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
            this.o[1] = typedValue.data;
            context.getTheme().resolveAttribute(R.attr.colorControlHighlight, typedValue, true);
            this.o[2] = typedValue.data;
        }
        TypedValue typedValue2 = new TypedValue();
        context.getTheme().resolveAttribute(16842964, typedValue2, true);
        setBackgroundResource(typedValue2.resourceId);
        this.q *= f;
        this.t = f * this.t;
        int attributeIntValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLength", 4);
        this.u = attributeIntValue;
        this.s = attributeIntValue;
        super.setCustomSelectionActionModeCallback(new a(this));
        super.setOnClickListener(new b());
    }

    public final void f(boolean z) {
        if (isFocused()) {
            this.y.setStrokeWidth(this.x);
            this.y.setColor(Color.parseColor("#222B37"));
            if (z) {
                this.y.setColor(Color.parseColor("#222B37"));
                return;
            }
            return;
        }
        this.y.setStrokeWidth(this.w);
        this.y.setColor(Color.parseColor("#222B37"));
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        RectF rectF;
        float f;
        float f2;
        int i;
        float f3;
        float f4;
        float f5;
        int width = (getWidth() - getPaddingRight()) - getPaddingLeft();
        float f6 = this.q;
        if (f6 < 0.0f) {
            this.r = width / ((this.s * 2.0f) - 1.0f);
        } else {
            float f7 = width;
            float f8 = this.s;
            this.r = (f7 - ((f8 - 1.0f) * f6)) / f8;
            if (this.p == 0.0f) {
                this.p = (f7 - (f6 * (f8 - 1.0f))) / f8;
            }
        }
        int paddingLeft = getPaddingLeft();
        int height = getHeight() - getPaddingBottom();
        Editable text = getText();
        int length = text.length();
        float[] fArr = new float[length];
        getPaint().getTextWidths(getText(), 0, length, fArr);
        int i2 = 0;
        while (i2 < this.s) {
            f(i2 == length);
            if (i2 == 0) {
                float f9 = height;
                rectF = new RectF(paddingLeft, (f9 - this.r) - 24.0f, (int) (f5 + f4 + 24.0f), f9);
            } else {
                float f10 = height;
                rectF = new RectF(paddingLeft, (f10 - this.r) - 24.0f, (int) (f2 + f + 24.0f), f10);
            }
            canvas.drawRoundRect(rectF, 12.0f, 12.0f, this.y);
            if (getText().length() > i2) {
                float f11 = paddingLeft + (this.r / 2.0f);
                getPaint().setColor(Color.parseColor(Constants.WHITE));
                i = i2;
                canvas.drawText(text, i2, i2 + 1, f11 - (fArr[0] / 8.0f), (height - this.t) - 6.0f, getPaint());
            } else {
                i = i2;
            }
            float f12 = this.q;
            if (f12 < 0.0f) {
                f3 = paddingLeft + (this.r * 2.0f);
            } else {
                f3 = paddingLeft + this.r + f12;
            }
            paddingLeft = (int) f3;
            i2 = i + 1;
        }
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        throw new RuntimeException("setCustomSelectionActionModeCallback() not supported.");
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.v = onClickListener;
    }

    public PinEntryEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.o = new int[]{-16711936, ViewCompat.MEASURED_STATE_MASK, -7829368};
        new ColorStateList(this.n, this.o);
        this.q = 24.0f;
        this.s = 6.0f;
        this.t = 8.0f;
        this.u = 6;
        this.w = 1.0f;
        this.x = 2.0f;
        e(context, attributeSet);
    }

    public PinEntryEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.o = new int[]{-16711936, ViewCompat.MEASURED_STATE_MASK, -7829368};
        new ColorStateList(this.n, this.o);
        this.q = 24.0f;
        this.s = 6.0f;
        this.t = 8.0f;
        this.u = 6;
        this.w = 1.0f;
        this.x = 2.0f;
        e(context, attributeSet);
    }
}
