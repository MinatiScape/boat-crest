package com.coveiot.android.leonardo.more.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityLanguageSettings;
import com.coveiot.covepreferences.SessionManager;
import com.szabh.smable3.entity.Languages;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class LanguageAdapter extends ArrayAdapter<String> {
    @NotNull
    public final Activity h;
    @NotNull
    public final ArrayList<String> i;
    public int j;
    public String k;
    public SubmitButtonEnableListner submitButtonEnableListner;

    /* loaded from: classes5.dex */
    public interface SubmitButtonEnableListner {
        void onLanguageSelected(@NotNull String str, int i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LanguageAdapter(@NotNull Activity context, @NotNull ArrayList<String> lang_array, int i) {
        super(context, (int) R.layout.language_item, lang_array);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(lang_array, "lang_array");
        this.h = context;
        this.i = lang_array;
        this.j = i;
        this.k = SessionManager.getInstance(context).getSelectedLanguage();
    }

    public static final void b(LanguageAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object tag = view.getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.Int");
        int intValue = ((Integer) tag).intValue();
        this$0.j = intValue;
        if (intValue == 0) {
            this$0.k = Languages.DEFAULT_LANGUAGE;
        } else if (intValue == 1) {
            this$0.k = "bn";
        } else if (intValue == 2) {
            this$0.k = "ta";
        } else if (intValue == 3) {
            this$0.k = "zh";
        } else if (intValue == 4) {
            this$0.k = "kn";
        } else if (intValue != 5) {
            this$0.k = SessionManager.getInstance(this$0.h).getSelectedLanguage();
        } else {
            this$0.k = "te";
        }
        SubmitButtonEnableListner submitButtonEnableListner = this$0.getSubmitButtonEnableListner();
        String selectedLanguage = this$0.k;
        Intrinsics.checkNotNullExpressionValue(selectedLanguage, "selectedLanguage");
        submitButtonEnableListner.onLanguageSelected(selectedLanguage, this$0.j);
        this$0.notifyDataSetChanged();
    }

    public final String getSelectedLanguage() {
        return this.k;
    }

    public final int getSelectedPosition() {
        return this.j;
    }

    @NotNull
    public final SubmitButtonEnableListner getSubmitButtonEnableListner() {
        SubmitButtonEnableListner submitButtonEnableListner = this.submitButtonEnableListner;
        if (submitButtonEnableListner != null) {
            return submitButtonEnableListner;
        }
        Intrinsics.throwUninitializedPropertyAccessException("submitButtonEnableListner");
        return null;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @NotNull
    public View getView(int i, @Nullable View view, @NotNull ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.h.getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "context.layoutInflater");
        View rowView = layoutInflater.inflate(R.layout.language_item, (ViewGroup) null, true);
        Activity activity = this.h;
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.activities.ActivityLanguageSettings");
        setSubmitButtonEnableListner((ActivityLanguageSettings) activity);
        View findViewById = rowView.findViewById(R.id.language_name);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        View findViewById2 = rowView.findViewById(R.id.language_radio);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.RadioButton");
        RadioButton radioButton = (RadioButton) findViewById2;
        radioButton.setChecked(i == this.j);
        radioButton.setTag(Integer.valueOf(i));
        radioButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LanguageAdapter.b(LanguageAdapter.this, view2);
            }
        });
        textView.setText(this.i.get(i));
        Intrinsics.checkNotNullExpressionValue(rowView, "rowView");
        return rowView;
    }

    public final void setSelectedLanguage(String str) {
        this.k = str;
    }

    public final void setSelectedPosition(int i) {
        this.j = i;
    }

    public final void setSubmitButtonEnableListner(@NotNull SubmitButtonEnableListner submitButtonEnableListner) {
        Intrinsics.checkNotNullParameter(submitButtonEnableListner, "<set-?>");
        this.submitButtonEnableListner = submitButtonEnableListner;
    }
}
