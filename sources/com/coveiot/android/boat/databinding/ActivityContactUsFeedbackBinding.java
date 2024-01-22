package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityContactUsFeedbackBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout ConstraintLayout3;
    @NonNull
    public final ConstraintLayout ConstraintLayoutEmail;
    @NonNull
    public final ConstraintLayout ConstraintLayoutMessage;
    @NonNull
    public final ConstraintLayout ConstraintLayoutOr;
    @NonNull
    public final ConstraintLayout ConstraintLayoutQuery;
    @NonNull
    public final ConstraintLayout ConstraintLayoutphn;
    @NonNull
    public final TextView Contact;
    @NonNull
    public final TextView Email;
    @NonNull
    public final TextView FillQueryFromId;
    @NonNull
    public final TextView Mailnumber;
    @NonNull
    public final TextView Message;
    @NonNull
    public final TextView OrId;
    @NonNull
    public final TextView Phn;
    @NonNull
    public final EditText PhnNumber;
    @NonNull
    public final ConstraintLayout PreferedContactLayout;
    @NonNull
    public final TextView Query;
    @NonNull
    public final RadioGroup RadioGroup;
    @NonNull
    public final TextView Reachus;
    @NonNull
    public final ConstraintLayout constraintLayout2;
    @NonNull
    public final WebView contactUsWebView;
    @NonNull
    public final EditText edittext;
    @NonNull
    public final ImageView emojiCove;
    @NonNull
    public final TextView fullName;
    @NonNull
    public final ImageView ivArrow;
    @NonNull
    public final EditText name;
    @NonNull
    public final Spinner selectQuerySpinner;
    @NonNull
    public final AppCompatButton submit;
    @NonNull
    public final TextView subtitle;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvEmailWarning;
    @NonNull
    public final EditText userEmail;
    @NonNull
    public final AppCompatRadioButton viaEmail;
    @NonNull
    public final AppCompatRadioButton viaPhoneNumber;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;

    public ActivityContactUsFeedbackBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, EditText editText, ConstraintLayout constraintLayout7, TextView textView8, RadioGroup radioGroup, TextView textView9, ConstraintLayout constraintLayout8, WebView webView, EditText editText2, ImageView imageView, TextView textView10, ImageView imageView2, EditText editText3, Spinner spinner, AppCompatButton appCompatButton, TextView textView11, View view2, TextView textView12, EditText editText4, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, View view3, View view4) {
        super(obj, view, i);
        this.ConstraintLayout3 = constraintLayout;
        this.ConstraintLayoutEmail = constraintLayout2;
        this.ConstraintLayoutMessage = constraintLayout3;
        this.ConstraintLayoutOr = constraintLayout4;
        this.ConstraintLayoutQuery = constraintLayout5;
        this.ConstraintLayoutphn = constraintLayout6;
        this.Contact = textView;
        this.Email = textView2;
        this.FillQueryFromId = textView3;
        this.Mailnumber = textView4;
        this.Message = textView5;
        this.OrId = textView6;
        this.Phn = textView7;
        this.PhnNumber = editText;
        this.PreferedContactLayout = constraintLayout7;
        this.Query = textView8;
        this.RadioGroup = radioGroup;
        this.Reachus = textView9;
        this.constraintLayout2 = constraintLayout8;
        this.contactUsWebView = webView;
        this.edittext = editText2;
        this.emojiCove = imageView;
        this.fullName = textView10;
        this.ivArrow = imageView2;
        this.name = editText3;
        this.selectQuerySpinner = spinner;
        this.submit = appCompatButton;
        this.subtitle = textView11;
        this.toolbar = view2;
        this.tvEmailWarning = textView12;
        this.userEmail = editText4;
        this.viaEmail = appCompatRadioButton;
        this.viaPhoneNumber = appCompatRadioButton2;
        this.view1 = view3;
        this.view2 = view4;
    }

    public static ActivityContactUsFeedbackBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityContactUsFeedbackBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityContactUsFeedbackBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityContactUsFeedbackBinding) ViewDataBinding.bind(obj, view, R.layout.activity_contact_us_feedback);
    }

    @NonNull
    @Deprecated
    public static ActivityContactUsFeedbackBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityContactUsFeedbackBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_contact_us_feedback, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityContactUsFeedbackBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityContactUsFeedbackBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityContactUsFeedbackBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_contact_us_feedback, null, false, obj);
    }
}
