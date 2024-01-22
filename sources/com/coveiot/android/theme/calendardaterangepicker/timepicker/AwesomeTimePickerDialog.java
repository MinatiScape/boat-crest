package com.coveiot.android.theme.calendardaterangepicker.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TimePicker;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarCustomTextView;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class AwesomeTimePickerDialog extends Dialog {
    @NotNull
    public final String h;
    @NotNull
    public final TimePickerCallback i;
    public CalendarCustomTextView j;
    public CalendarCustomTextView k;
    public int l;
    public int m;

    /* loaded from: classes7.dex */
    public interface TimePickerCallback {
        void onCancel();

        void onTimeSelected(int i, int i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AwesomeTimePickerDialog(@NotNull Context context, @NotNull String mTitle, @NotNull TimePickerCallback onTimeChangedListener) {
        super(context, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(onTimeChangedListener, "onTimeChangedListener");
        this.h = mTitle;
        this.i = onTimeChangedListener;
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setGravity(80);
        }
        setCanceledOnTouchOutside(false);
        d();
        f();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window3 = getWindow();
        layoutParams.copyFrom(window3 != null ? window3.getAttributes() : null);
        layoutParams.width = -1;
        layoutParams.height = -2;
        if (window3 == null) {
            return;
        }
        window3.setAttributes(layoutParams);
    }

    public static final void e(AwesomeTimePickerDialog this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.l = i;
        this$0.m = i2;
    }

    public static final void g(AwesomeTimePickerDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.i.onCancel();
        this$0.dismiss();
    }

    public static final void h(AwesomeTimePickerDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.i.onTimeSelected(this$0.l, this$0.m);
        this$0.dismiss();
    }

    public final void d() {
        setContentView(R.layout.dialog_time_picker);
        View findViewById = findViewById(R.id.tvHeaderTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.tvHeaderTitle)");
        View findViewById2 = findViewById(R.id.tvHeaderDone);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.tvHeaderDone)");
        this.j = (CalendarCustomTextView) findViewById2;
        View findViewById3 = findViewById(R.id.tvHeaderCancel);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(R.id.tvHeaderCancel)");
        this.k = (CalendarCustomTextView) findViewById3;
        ((TimePicker) findViewById(R.id.timePicker)).setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.timepicker.c
            @Override // android.widget.TimePicker.OnTimeChangedListener
            public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
                AwesomeTimePickerDialog.e(AwesomeTimePickerDialog.this, timePicker, i, i2);
            }
        });
        ((CalendarCustomTextView) findViewById).setText(this.h);
    }

    public final void f() {
        CalendarCustomTextView calendarCustomTextView = this.k;
        CalendarCustomTextView calendarCustomTextView2 = null;
        if (calendarCustomTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDialogCancel");
            calendarCustomTextView = null;
        }
        calendarCustomTextView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.timepicker.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AwesomeTimePickerDialog.g(AwesomeTimePickerDialog.this, view);
            }
        });
        CalendarCustomTextView calendarCustomTextView3 = this.j;
        if (calendarCustomTextView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDialogDone");
        } else {
            calendarCustomTextView2 = calendarCustomTextView3;
        }
        calendarCustomTextView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.timepicker.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AwesomeTimePickerDialog.h(AwesomeTimePickerDialog.this, view);
            }
        });
    }

    public final void showDialog() {
        this.l = Calendar.getInstance().get(11);
        this.m = Calendar.getInstance().get(12);
        show();
    }
}
