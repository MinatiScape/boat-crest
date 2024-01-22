package com.coveiot.android.femalewellness.wellnesscalendar.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TimePicker;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.CustomTextView;
import java.util.Calendar;
/* loaded from: classes4.dex */
public class TimePickerDialog extends Dialog {
    public CustomTextView h;
    public CustomTextView i;
    public CustomTextView j;
    public String k;
    public int l;
    public int m;
    public TimePicker n;
    public TimePickerCallback o;

    /* loaded from: classes4.dex */
    public interface TimePickerCallback {
        void onCancel();

        void onTimeSelected(int i, int i2);
    }

    /* loaded from: classes4.dex */
    public class a implements TimePicker.OnTimeChangedListener {
        public a() {
        }

        @Override // android.widget.TimePicker.OnTimeChangedListener
        public void onTimeChanged(TimePicker timePicker, int i, int i2) {
            TimePickerDialog.this.l = i;
            TimePickerDialog.this.m = i2;
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TimePickerDialog.this.o != null) {
                TimePickerDialog.this.o.onCancel();
            }
            TimePickerDialog.this.dismiss();
        }
    }

    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TimePickerDialog.this.o != null) {
                TimePickerDialog.this.o.onTimeSelected(TimePickerDialog.this.l, TimePickerDialog.this.m);
            }
            TimePickerDialog.this.dismiss();
        }
    }

    public TimePickerDialog(Context context, String str, TimePickerCallback timePickerCallback) {
        super(context);
        this.k = str;
        this.o = timePickerCallback;
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setGravity(80);
        setCanceledOnTouchOutside(false);
        f();
        g();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        window.setAttributes(layoutParams);
    }

    public final void f() {
        setContentView(R.layout.dialog_time_picker_female_wellness);
        this.h = (CustomTextView) findViewById(R.id.tvHeaderTitle);
        this.i = (CustomTextView) findViewById(R.id.tvHeaderDone);
        this.j = (CustomTextView) findViewById(R.id.tvHeaderCancel);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        this.n = timePicker;
        timePicker.setOnTimeChangedListener(new a());
        this.h.setText(this.k);
    }

    public final void g() {
        this.j.setOnClickListener(new b());
        this.i.setOnClickListener(new c());
    }

    public void showDialog() {
        this.l = Calendar.getInstance().get(11);
        this.m = Calendar.getInstance().get(12);
        show();
    }
}
