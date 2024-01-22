package com.coveiot.android.fitnesschallenges;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.theme.databinding.PickerDialogBinding;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GoalPickerDialog extends DialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    public final String h;
    @NotNull
    public final String i;
    public final long j;
    public final long k;
    @NotNull
    public final OnRangeSelector l;
    public PickerDialogBinding m;
    public WheelPicker n;

    /* loaded from: classes2.dex */
    public interface OnRangeSelector {
        void onRangeSelected(@NotNull String str);
    }

    public GoalPickerDialog(@NotNull String title, @NotNull String unit, long j, long j2, @NotNull OnRangeSelector onRangeSelector) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(onRangeSelector, "onRangeSelector");
        this._$_findViewCache = new LinkedHashMap();
        this.h = title;
        this.i = unit;
        this.j = j;
        this.k = j2;
        this.l = onRangeSelector;
    }

    public static final void d(GoalPickerDialog this$0, ArrayList heightListInCms, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(heightListInCms, "$heightListInCms");
        OnRangeSelector onRangeSelector = this$0.l;
        WheelPicker wheelPicker = this$0.n;
        if (wheelPicker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangePicker");
            wheelPicker = null;
        }
        Object obj = heightListInCms.get(wheelPicker.getCurrentItemPosition());
        Intrinsics.checkNotNullExpressionValue(obj, "heightListInCms[rangePicker.currentItemPosition]");
        onRangeSelector.onRangeSelected((String) obj);
        this$0.dismiss();
    }

    public static final void e(GoalPickerDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final PickerDialogBinding c() {
        PickerDialogBinding pickerDialogBinding = this.m;
        if (pickerDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return pickerDialogBinding;
    }

    public final long getEndRange() {
        return this.k;
    }

    @NotNull
    public final OnRangeSelector getOnRangeSelector() {
        return this.l;
    }

    public final long getStartRange() {
        return this.j;
    }

    @NotNull
    public final String getTitle() {
        return this.h;
    }

    @NotNull
    public final String getUnit() {
        return this.i;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(@NotNull DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onCancel(dialog);
        setStyle(1, com.coveiot.android.theme.R.style.dialogBackgroundStyle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Window window;
        Window window2;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            if ((dialog != null ? dialog.getWindow() : null) != null) {
                Dialog dialog2 = getDialog();
                if (dialog2 != null && (window2 = dialog2.getWindow()) != null) {
                    window2.setBackgroundDrawable(new ColorDrawable(0));
                }
                Dialog dialog3 = getDialog();
                if (dialog3 != null && (window = dialog3.getWindow()) != null) {
                    window.requestFeature(1);
                }
            }
        }
        PickerDialogBinding inflate = PickerDialogBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        return c().getRoot();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setLayout(-1, -2);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setCancelable(false);
        }
        Dialog dialog3 = getDialog();
        if (dialog3 != null) {
            dialog3.setCanceledOnTouchOutside(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        c().unitName.setText(this.i);
        c().pouupLabel.setText(this.h);
        WheelPicker wheelPicker = c().numberPicker;
        Intrinsics.checkNotNullExpressionValue(wheelPicker, "binding.numberPicker");
        this.n = wheelPicker;
        final ArrayList arrayList = new ArrayList();
        long j = this.j;
        long j2 = this.k;
        if (j <= j2) {
            while (true) {
                arrayList.add(String.valueOf(j));
                if (j == j2) {
                    break;
                }
                j++;
            }
        }
        WheelPicker wheelPicker2 = this.n;
        WheelPicker wheelPicker3 = null;
        if (wheelPicker2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangePicker");
            wheelPicker2 = null;
        }
        wheelPicker2.setData(arrayList);
        WheelPicker wheelPicker4 = this.n;
        if (wheelPicker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangePicker");
            wheelPicker4 = null;
        }
        wheelPicker4.setAtmospheric(true);
        WheelPicker wheelPicker5 = this.n;
        if (wheelPicker5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangePicker");
            wheelPicker5 = null;
        }
        wheelPicker5.setVisibleItemCount(3);
        WheelPicker wheelPicker6 = this.n;
        if (wheelPicker6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangePicker");
            wheelPicker6 = null;
        }
        wheelPicker6.setItemSpace(60);
        WheelPicker wheelPicker7 = this.n;
        if (wheelPicker7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangePicker");
        } else {
            wheelPicker3 = wheelPicker7;
        }
        wheelPicker3.setCyclic(true);
        c().okPopup.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.h1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GoalPickerDialog.d(GoalPickerDialog.this, arrayList, view2);
            }
        });
        c().cancelPopup.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GoalPickerDialog.e(GoalPickerDialog.this, view2);
            }
        });
    }
}
