package com.coveiot.android.theme.compundview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class MovingFloatingButton extends FloatingActionButton implements View.OnTouchListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public float A;
    public float B;
    public float C;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public float z;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovingFloatingButton(@Nullable Context context) {
        super(context);
        Intrinsics.checkNotNull(context);
        k();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void k() {
        setOnTouchListener(this);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(@NotNull View view, @NotNull MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.z = motionEvent.getRawX();
            this.A = motionEvent.getRawY();
            this.B = view.getX() - this.z;
            this.C = view.getY() - this.A;
            return true;
        } else if (action == 1) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            float f = rawX - this.z;
            float f2 = rawY - this.A;
            if (Math.abs(f) >= 10.0f || Math.abs(f2) >= 10.0f) {
                return true;
            }
            return performClick();
        } else if (action != 2) {
            return super.onTouchEvent(motionEvent);
        } else {
            int width = view.getWidth();
            int height = view.getHeight();
            ViewParent parent = view.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            View view2 = (View) parent;
            int width2 = view2.getWidth();
            int height2 = view2.getHeight();
            float rawX2 = motionEvent.getRawX() + this.B;
            float rawY2 = motionEvent.getRawY() + this.C;
            float min = Math.min((width2 - width) - marginLayoutParams.rightMargin, Math.max(marginLayoutParams.leftMargin, rawX2));
            view.animate().x(min).y(Math.min((height2 - height) - marginLayoutParams.bottomMargin, Math.max(marginLayoutParams.topMargin, rawY2))).setDuration(0L).start();
            return true;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovingFloatingButton(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNull(context);
        k();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovingFloatingButton(@Nullable Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNull(context);
        k();
    }
}
