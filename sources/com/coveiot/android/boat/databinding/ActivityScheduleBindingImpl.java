package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivityScheduleBindingImpl extends ActivityScheduleBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts m = null;
    @Nullable
    public static final SparseIntArray n;
    @NonNull
    public final ConstraintLayout h;
    public InverseBindingListener i;
    public InverseBindingListener j;
    public InverseBindingListener k;
    public long l;

    /* loaded from: classes3.dex */
    public class a implements InverseBindingListener {
        public a() {
        }

        @Override // androidx.databinding.InverseBindingListener
        public void onChange() {
            synchronized (ActivityScheduleBindingImpl.this) {
                ActivityScheduleBindingImpl.a(ActivityScheduleBindingImpl.this, 2L);
            }
            ActivityScheduleBindingImpl.this.requestRebind();
        }
    }

    /* loaded from: classes3.dex */
    public class b implements InverseBindingListener {
        public b() {
        }

        @Override // androidx.databinding.InverseBindingListener
        public void onChange() {
            synchronized (ActivityScheduleBindingImpl.this) {
                ActivityScheduleBindingImpl.a(ActivityScheduleBindingImpl.this, 4L);
            }
            ActivityScheduleBindingImpl.this.requestRebind();
        }
    }

    /* loaded from: classes3.dex */
    public class c implements InverseBindingListener {
        public c() {
        }

        @Override // androidx.databinding.InverseBindingListener
        public void onChange() {
            synchronized (ActivityScheduleBindingImpl.this) {
                ActivityScheduleBindingImpl.a(ActivityScheduleBindingImpl.this, 1L);
            }
            ActivityScheduleBindingImpl.this.requestRebind();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        n = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.clReminders, 3);
        sparseIntArray.put(R.id.rvScheduleList, 4);
        sparseIntArray.put(R.id.emptyCl, 5);
        sparseIntArray.put(R.id.noScheduleImgV, 6);
        sparseIntArray.put(R.id.noScheduleTv, 7);
        sparseIntArray.put(R.id.clAddReminder, 8);
        sparseIntArray.put(R.id.tvEventName, 9);
        sparseIntArray.put(R.id.etTitle, 10);
        sparseIntArray.put(R.id.tvTitleCharacterCount, 11);
        sparseIntArray.put(R.id.dateTime, 12);
        sparseIntArray.put(R.id.etScheduleDateTime, 13);
        sparseIntArray.put(R.id.scheduleDateTimeView, 14);
        sparseIntArray.put(R.id.remind, 15);
        sparseIntArray.put(R.id.clSpinner, 16);
        sparseIntArray.put(R.id.scheduleReminderSpinner, 17);
        sparseIntArray.put(R.id.ivDropDown, 18);
        sparseIntArray.put(R.id.tvDescription, 19);
        sparseIntArray.put(R.id.etDescription, 20);
        sparseIntArray.put(R.id.tvDescCharacterCount, 21);
        sparseIntArray.put(R.id.clBottomButtons, 22);
        sparseIntArray.put(R.id.btnAddScheduleReminder, 23);
        sparseIntArray.put(R.id.btnSave, 24);
    }

    public ActivityScheduleBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 25, m, n));
    }

    public static /* synthetic */ long a(ActivityScheduleBindingImpl activityScheduleBindingImpl, long j) {
        long j2 = j | activityScheduleBindingImpl.l;
        activityScheduleBindingImpl.l = j2;
        return j2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0076, code lost:
        if ((r7 != null ? r7.length() : 0) > 0) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r14 = this;
            monitor-enter(r14)
            long r0 = r14.l     // Catch: java.lang.Throwable -> La7
            r2 = 0
            r14.l = r2     // Catch: java.lang.Throwable -> La7
            monitor-exit(r14)     // Catch: java.lang.Throwable -> La7
            r4 = 15
            long r6 = r0 & r4
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            r7 = 32
            r9 = 1
            r10 = 0
            if (r6 == 0) goto L31
            android.widget.EditText r11 = r14.etTitle
            android.text.Editable r11 = r11.getText()
            if (r11 == 0) goto L21
            int r11 = r11.length()
            goto L22
        L21:
            r11 = r10
        L22:
            if (r11 <= 0) goto L26
            r11 = r9
            goto L27
        L26:
            r11 = r10
        L27:
            if (r6 == 0) goto L32
            if (r11 == 0) goto L2d
            long r0 = r0 | r7
            goto L32
        L2d:
            r12 = 16
            long r0 = r0 | r12
            goto L32
        L31:
            r11 = r10
        L32:
            long r6 = r0 & r7
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L4a
            android.widget.EditText r6 = r14.etScheduleDateTime
            android.text.Editable r6 = r6.getText()
            if (r6 == 0) goto L45
            int r6 = r6.length()
            goto L46
        L45:
            r6 = r10
        L46:
            if (r6 <= 0) goto L4a
            r6 = r9
            goto L4b
        L4a:
            r6 = r10
        L4b:
            long r7 = r0 & r4
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            r12 = 128(0x80, double:6.32E-322)
            if (r7 == 0) goto L61
            if (r11 == 0) goto L56
            goto L57
        L56:
            r6 = r10
        L57:
            if (r7 == 0) goto L62
            if (r6 == 0) goto L5d
            long r0 = r0 | r12
            goto L62
        L5d:
            r7 = 64
            long r0 = r0 | r7
            goto L62
        L61:
            r6 = r10
        L62:
            long r7 = r0 & r12
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L79
            android.widget.EditText r7 = r14.etDescription
            android.text.Editable r7 = r7.getText()
            if (r7 == 0) goto L75
            int r7 = r7.length()
            goto L76
        L75:
            r7 = r10
        L76:
            if (r7 <= 0) goto L79
            goto L7a
        L79:
            r9 = r10
        L7a:
            long r4 = r4 & r0
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L82
            if (r6 == 0) goto L82
            r10 = r9
        L82:
            if (r4 == 0) goto L89
            android.widget.Button r4 = r14.btnSetScheduleReminder
            r4.setEnabled(r10)
        L89:
            r4 = 8
            long r0 = r0 & r4
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto La6
            android.widget.EditText r0 = r14.etDescription
            androidx.databinding.InverseBindingListener r1 = r14.i
            r2 = 0
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r2, r2, r2, r1)
            android.widget.EditText r0 = r14.etScheduleDateTime
            androidx.databinding.InverseBindingListener r1 = r14.j
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r2, r2, r2, r1)
            android.widget.EditText r0 = r14.etTitle
            androidx.databinding.InverseBindingListener r1 = r14.k
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r2, r2, r2, r1)
        La6:
            return
        La7:
            r0 = move-exception
            monitor-exit(r14)     // Catch: java.lang.Throwable -> La7
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.boat.databinding.ActivityScheduleBindingImpl.executeBindings():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.l != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.l = 8L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivityScheduleBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[23], (TextView) objArr[24], (Button) objArr[1], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[16], (TextView) objArr[12], (ConstraintLayout) objArr[5], (EditText) objArr[20], (EditText) objArr[13], (EditText) objArr[10], (ImageView) objArr[18], (ImageView) objArr[6], (TextView) objArr[7], (TextView) objArr[15], (RecyclerView) objArr[4], (View) objArr[14], (Spinner) objArr[17], (View) objArr[2], (TextView) objArr[21], (TextView) objArr[19], (TextView) objArr[9], (TextView) objArr[11]);
        this.i = new a();
        this.j = new b();
        this.k = new c();
        this.l = -1L;
        this.btnSetScheduleReminder.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
