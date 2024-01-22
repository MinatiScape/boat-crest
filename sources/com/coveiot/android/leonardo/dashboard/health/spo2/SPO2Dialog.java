package com.coveiot.android.leonardo.dashboard.health.spo2;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.SPO2Dialog;
import com.coveiot.android.leonardo.utils.AppConstants;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SPO2Dialog {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes3.dex */
    public static final class Companion {

        /* loaded from: classes3.dex */
        public interface SelectionListener {
            void onValueSelected(double d);
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final void c(Dialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void d(Ref.DoubleRef value, SelectionListener selectionListner, Dialog alertDialog, Context context, View view) {
            Intrinsics.checkNotNullParameter(value, "$value");
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Intrinsics.checkNotNullParameter(context, "$context");
            double d = value.element;
            if (d > 0.0d) {
                selectionListner.onValueSelected(d);
                alertDialog.dismiss();
                return;
            }
            Toast.makeText(context, context.getString(R.string.please_enter_spo2), 1).show();
        }

        public final void showSPO2Dialog(@NotNull final Context context, @NotNull String header_label, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final Dialog dialog = new Dialog(context, R.style.DialogTheme);
            View inflate = LayoutInflater.from(context).inflate(R.layout.spo2_dialog, (ViewGroup) null);
            dialog.setContentView(inflate);
            final TextView textView = (TextView) inflate.findViewById(R.id.invalid_spo2);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.DoubleRef doubleRef = new Ref.DoubleRef();
            final EditText editText = (EditText) inflate.findViewById(R.id.updateSPO2EditText);
            editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3, 1)});
            editText.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.SPO2Dialog$Companion$showSPO2Dialog$1
                @Override // android.text.TextWatcher
                public void afterTextChanged(@Nullable Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                    if (editText.getText().toString().length() > 0) {
                        try {
                            double parseDouble = Double.parseDouble(editText.getText().toString());
                            if (parseDouble > 100.0d) {
                                editText.setText(AppConstants.HUNDRED.getValue());
                                doubleRef.element = 100.0d;
                                return;
                            }
                            if (parseDouble < 90.0d && parseDouble > 0.0d) {
                                TextView textView2 = textView;
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                Locale locale = Locale.ENGLISH;
                                String string = context.getString(R.string.spo2_range);
                                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spo2_range)");
                                String format = String.format(locale, string, Arrays.copyOf(new Object[]{Double.valueOf(parseDouble)}, 1));
                                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                                textView2.setText(format);
                                textView.setVisibility(0);
                            } else {
                                textView.setVisibility(8);
                            }
                            doubleRef.element = parseDouble;
                            return;
                        } catch (NumberFormatException unused) {
                            return;
                        }
                    }
                    doubleRef.element = 0.0d;
                    textView.setVisibility(8);
                }
            });
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SPO2Dialog.Companion.c(dialog, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SPO2Dialog.Companion.d(Ref.DoubleRef.this, selectionListner, dialog, context, view);
                }
            });
            Window window = dialog.getWindow();
            Intrinsics.checkNotNull(window);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            attributes.gravity = 17;
            window.setAttributes(attributes);
            dialog.show();
        }
    }

    /* loaded from: classes3.dex */
    public static final class DecimalDigitsInputFilter implements InputFilter {
        public Pattern mPattern;

        public DecimalDigitsInputFilter(int i, int i2) {
            Pattern compile = Pattern.compile("[0-9]{0," + i + "}+((\\.[0-9]{0," + i2 + "})?)||(\\.)?");
            Intrinsics.checkNotNullExpressionValue(compile, "compile(\"[0-9]{0,\" + (di…erZero) + \"})?)||(\\\\.)?\")");
            setMPattern(compile);
        }

        @Override // android.text.InputFilter
        @Nullable
        public CharSequence filter(@Nullable CharSequence charSequence, int i, int i2, @Nullable Spanned spanned, int i3, int i4) {
            Pattern mPattern = getMPattern();
            StringBuilder sb = new StringBuilder();
            sb.append((Object) (spanned != null ? spanned.subSequence(0, i3) : null));
            sb.append((Object) (charSequence != null ? charSequence.subSequence(i, i2) : null));
            sb.append((Object) (spanned != null ? spanned.subSequence(i4, spanned.length()) : null));
            Matcher matcher = mPattern.matcher(sb.toString());
            Intrinsics.checkNotNullExpressionValue(matcher, "mPattern.matcher(\n      ….toString()\n            )");
            if (matcher.matches()) {
                return null;
            }
            return "";
        }

        @NotNull
        public final Pattern getMPattern() {
            Pattern pattern = this.mPattern;
            if (pattern != null) {
                return pattern;
            }
            Intrinsics.throwUninitializedPropertyAccessException("mPattern");
            return null;
        }

        public final void setMPattern(@NotNull Pattern pattern) {
            Intrinsics.checkNotNullParameter(pattern, "<set-?>");
            this.mPattern = pattern;
        }
    }
}
