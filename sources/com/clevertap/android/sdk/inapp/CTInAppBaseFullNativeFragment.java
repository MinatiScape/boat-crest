package com.clevertap.android.sdk.inapp;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;
/* loaded from: classes2.dex */
public abstract class CTInAppBaseFullNativeFragment extends CTInAppBaseFullFragment {
    public int u() {
        WindowManager windowManager = (WindowManager) this.j.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            return this.j.getResources().getConfiguration().densityDpi;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public void v(Button button, CTInAppNotificationButton cTInAppNotificationButton, int i) {
        ShapeDrawable shapeDrawable;
        if (cTInAppNotificationButton != null) {
            button.setVisibility(0);
            button.setTag(Integer.valueOf(i));
            button.setText(cTInAppNotificationButton.getText());
            button.setTextColor(Color.parseColor(cTInAppNotificationButton.e()));
            button.setOnClickListener(new CTInAppBaseFragment.a());
            ShapeDrawable shapeDrawable2 = null;
            if (cTInAppNotificationButton.c().isEmpty()) {
                shapeDrawable = null;
            } else {
                float parseFloat = Float.parseFloat(cTInAppNotificationButton.c()) * (480.0f / u()) * 2.0f;
                shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat}, null, new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}));
                shapeDrawable.getPaint().setColor(Color.parseColor(cTInAppNotificationButton.a()));
                shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawable.getPaint().setAntiAlias(true);
                shapeDrawable2 = new ShapeDrawable(new RoundRectShape(new float[]{parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat}, null, new float[]{parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat, parseFloat}));
            }
            if (!cTInAppNotificationButton.b().isEmpty() && shapeDrawable2 != null) {
                shapeDrawable2.getPaint().setColor(Color.parseColor(cTInAppNotificationButton.b()));
                shapeDrawable2.setPadding(1, 1, 1, 1);
                shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
            }
            if (shapeDrawable != null) {
                button.setBackground(new LayerDrawable(new Drawable[]{shapeDrawable2, shapeDrawable}));
                return;
            }
            return;
        }
        button.setVisibility(8);
    }
}
