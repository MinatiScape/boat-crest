package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class CheerBuddyDialogBinding extends ViewDataBinding {
    @NonNull
    public final Button btnCancel;
    @NonNull
    public final Button btnSend;
    @NonNull
    public final ConstraintLayout clLabelBuddy;
    @NonNull
    public final ConstraintLayout clUpdateMsg;
    @NonNull
    public final ConstraintLayout editTextMsgConstraint;
    @NonNull
    public final TextView tvBuddyLabel;
    @NonNull
    public final TextView tvTextNum;
    @NonNull
    public final EditText updateMsgEditText;

    public CheerBuddyDialogBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView, TextView textView2, EditText editText) {
        super(obj, view, i);
        this.btnCancel = button;
        this.btnSend = button2;
        this.clLabelBuddy = constraintLayout;
        this.clUpdateMsg = constraintLayout2;
        this.editTextMsgConstraint = constraintLayout3;
        this.tvBuddyLabel = textView;
        this.tvTextNum = textView2;
        this.updateMsgEditText = editText;
    }

    public static CheerBuddyDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CheerBuddyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CheerBuddyDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CheerBuddyDialogBinding) ViewDataBinding.bind(obj, view, R.layout.cheer_buddy_dialog);
    }

    @NonNull
    @Deprecated
    public static CheerBuddyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CheerBuddyDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.cheer_buddy_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static CheerBuddyDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CheerBuddyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CheerBuddyDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.cheer_buddy_dialog, null, false, obj);
    }
}
