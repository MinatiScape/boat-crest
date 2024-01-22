package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivityContactUsFeedbackBindingImpl extends ActivityContactUsFeedbackBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.constraint_layout2, 2);
        sparseIntArray.put(R.id.FillQueryFromId, 3);
        sparseIntArray.put(R.id.subtitle, 4);
        sparseIntArray.put(R.id.fullName, 5);
        sparseIntArray.put(R.id.Constraint_layout3, 6);
        sparseIntArray.put(R.id.name, 7);
        sparseIntArray.put(R.id.Phn, 8);
        sparseIntArray.put(R.id.Constraint_layoutphn, 9);
        sparseIntArray.put(R.id.Phn_Number, 10);
        sparseIntArray.put(R.id.Email, 11);
        sparseIntArray.put(R.id.Constraint_layoutEmail, 12);
        sparseIntArray.put(R.id.userEmail, 13);
        sparseIntArray.put(R.id.Query, 14);
        sparseIntArray.put(R.id.Constraint_layoutQuery, 15);
        sparseIntArray.put(R.id.selectQuerySpinner, 16);
        sparseIntArray.put(R.id.ivArrow, 17);
        sparseIntArray.put(R.id.Message, 18);
        sparseIntArray.put(R.id.Constraint_layoutMessage, 19);
        sparseIntArray.put(R.id.edittext, 20);
        sparseIntArray.put(R.id.Mailnumber, 21);
        sparseIntArray.put(R.id.PreferedContact_layout, 22);
        sparseIntArray.put(R.id.Contact, 23);
        sparseIntArray.put(R.id.RadioGroup, 24);
        sparseIntArray.put(R.id.viaPhoneNumber, 25);
        sparseIntArray.put(R.id.viaEmail, 26);
        sparseIntArray.put(R.id.tv_email_warning, 27);
        sparseIntArray.put(R.id.submit, 28);
        sparseIntArray.put(R.id.Constraint_layoutOr, 29);
        sparseIntArray.put(R.id.view1, 30);
        sparseIntArray.put(R.id.Or_id, 31);
        sparseIntArray.put(R.id.view2, 32);
        sparseIntArray.put(R.id.Reachus, 33);
        sparseIntArray.put(R.id.contact_us_webView, 34);
        sparseIntArray.put(R.id.emojiCove, 35);
    }

    public ActivityContactUsFeedbackBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 36, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 1L;
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

    public ActivityContactUsFeedbackBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[29], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[9], (TextView) objArr[23], (TextView) objArr[11], (TextView) objArr[3], (TextView) objArr[21], (TextView) objArr[18], (TextView) objArr[31], (TextView) objArr[8], (EditText) objArr[10], (ConstraintLayout) objArr[22], (TextView) objArr[14], (RadioGroup) objArr[24], (TextView) objArr[33], (ConstraintLayout) objArr[2], (WebView) objArr[34], (EditText) objArr[20], (ImageView) objArr[35], (TextView) objArr[5], (ImageView) objArr[17], (EditText) objArr[7], (Spinner) objArr[16], (AppCompatButton) objArr[28], (TextView) objArr[4], (View) objArr[1], (TextView) objArr[27], (EditText) objArr[13], (AppCompatRadioButton) objArr[26], (AppCompatRadioButton) objArr[25], (View) objArr[30], (View) objArr[32]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
