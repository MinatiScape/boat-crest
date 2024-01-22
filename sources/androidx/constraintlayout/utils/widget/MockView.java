package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class MockView extends View {
    public Paint h;
    public Paint i;
    public Paint j;
    public boolean k;
    public boolean l;
    public Rect m;
    public String mText;
    public int n;
    public int o;
    public int p;
    public int q;

    public MockView(Context context) {
        super(context);
        this.h = new Paint();
        this.i = new Paint();
        this.j = new Paint();
        this.k = true;
        this.l = true;
        this.mText = null;
        this.m = new Rect();
        this.n = Color.argb(255, 0, 0, 0);
        this.o = Color.argb(255, 200, 200, 200);
        this.p = Color.argb(255, 50, 50, 50);
        this.q = 4;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MockView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.MockView_mock_label) {
                    this.mText = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.MockView_mock_showDiagonals) {
                    this.k = obtainStyledAttributes.getBoolean(index, this.k);
                } else if (index == R.styleable.MockView_mock_diagonalsColor) {
                    this.n = obtainStyledAttributes.getColor(index, this.n);
                } else if (index == R.styleable.MockView_mock_labelBackgroundColor) {
                    this.p = obtainStyledAttributes.getColor(index, this.p);
                } else if (index == R.styleable.MockView_mock_labelColor) {
                    this.o = obtainStyledAttributes.getColor(index, this.o);
                } else if (index == R.styleable.MockView_mock_showLabel) {
                    this.l = obtainStyledAttributes.getBoolean(index, this.l);
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (this.mText == null) {
            try {
                this.mText = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.h.setColor(this.n);
        this.h.setAntiAlias(true);
        this.i.setColor(this.o);
        this.i.setAntiAlias(true);
        this.j.setColor(this.p);
        this.q = Math.round(this.q * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.k) {
            width--;
            height--;
            float f = width;
            float f2 = height;
            canvas.drawLine(0.0f, 0.0f, f, f2, this.h);
            canvas.drawLine(0.0f, f2, f, 0.0f, this.h);
            canvas.drawLine(0.0f, 0.0f, f, 0.0f, this.h);
            canvas.drawLine(f, 0.0f, f, f2, this.h);
            canvas.drawLine(f, f2, 0.0f, f2, this.h);
            canvas.drawLine(0.0f, f2, 0.0f, 0.0f, this.h);
        }
        String str = this.mText;
        if (str == null || !this.l) {
            return;
        }
        this.i.getTextBounds(str, 0, str.length(), this.m);
        float width2 = (width - this.m.width()) / 2.0f;
        float height2 = ((height - this.m.height()) / 2.0f) + this.m.height();
        this.m.offset((int) width2, (int) height2);
        Rect rect = this.m;
        int i = rect.left;
        int i2 = this.q;
        rect.set(i - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
        canvas.drawRect(this.m, this.j);
        canvas.drawText(this.mText, width2, height2, this.i);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new Paint();
        this.i = new Paint();
        this.j = new Paint();
        this.k = true;
        this.l = true;
        this.mText = null;
        this.m = new Rect();
        this.n = Color.argb(255, 0, 0, 0);
        this.o = Color.argb(255, 200, 200, 200);
        this.p = Color.argb(255, 50, 50, 50);
        this.q = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new Paint();
        this.i = new Paint();
        this.j = new Paint();
        this.k = true;
        this.l = true;
        this.mText = null;
        this.m = new Rect();
        this.n = Color.argb(255, 0, 0, 0);
        this.o = Color.argb(255, 200, 200, 200);
        this.p = Color.argb(255, 50, 50, 50);
        this.q = 4;
        a(context, attributeSet);
    }
}
