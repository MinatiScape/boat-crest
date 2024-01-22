package com.coveiot.android.theme.compundview;

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
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class EntryEditText extends AppCompatEditText {
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
        public a(EntryEditText entryEditText) {
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
            EntryEditText entryEditText = EntryEditText.this;
            entryEditText.setSelection(entryEditText.getText().length());
            if (EntryEditText.this.v != null) {
                EntryEditText.this.v.onClick(view);
            }
        }
    }

    public EntryEditText(Context context) {
        super(context);
        this.n = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.o = new int[]{-16711936, ViewCompat.MEASURED_STATE_MASK, -7829368};
        new ColorStateList(this.n, this.o);
        this.q = 24.0f;
        this.s = 4.0f;
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
        int i;
        float f;
        super.onDraw(canvas);
        int width = (getWidth() - getPaddingRight()) - getPaddingLeft();
        float f2 = this.q;
        if (f2 < 0.0f) {
            this.r = width / ((this.s * 2.0f) - 1.0f);
        } else {
            float f3 = width;
            float f4 = this.s;
            this.r = f3 - ((f4 - 1.0f) * f2);
            if (this.p == 0.0f) {
                this.p = f3 - (f2 * (f4 - 1.0f));
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
            float f5 = paddingLeft;
            float f6 = height;
            canvas.drawLine(f5, f6, f5 + this.r, f6, this.y);
            if (getText().length() > i2) {
                getPaint().setColor(Color.parseColor(Constants.WHITE));
                i = i2;
                canvas.drawText(text, i2, i2 + 1, ((this.r / 2.0f) + f5) - (fArr[0] / 8.0f), (f6 - this.t) - 6.0f, getPaint());
            } else {
                i = i2;
            }
            float f7 = this.q;
            if (f7 < 0.0f) {
                f = f5 + (this.r * 2.0f);
            } else {
                f = f5 + this.r + f7;
            }
            paddingLeft = (int) f;
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

    public EntryEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.o = new int[]{-16711936, ViewCompat.MEASURED_STATE_MASK, -7829368};
        new ColorStateList(this.n, this.o);
        this.q = 24.0f;
        this.s = 4.0f;
        this.t = 8.0f;
        this.u = 6;
        this.w = 1.0f;
        this.x = 2.0f;
        e(context, attributeSet);
    }

    public EntryEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = new int[][]{new int[]{16842913}, new int[]{16842908}, new int[]{-16842908}};
        this.o = new int[]{-16711936, ViewCompat.MEASURED_STATE_MASK, -7829368};
        new ColorStateList(this.n, this.o);
        this.q = 24.0f;
        this.s = 4.0f;
        this.t = 8.0f;
        this.u = 6;
        this.w = 1.0f;
        this.x = 2.0f;
        e(context, attributeSet);
    }
}
