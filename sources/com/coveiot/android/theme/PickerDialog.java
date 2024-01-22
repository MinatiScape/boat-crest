package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class PickerDialog {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes7.dex */
    public static final class Companion {

        /* loaded from: classes7.dex */
        public interface SelectionListener {
            void onValueSelected(@NotNull String str);
        }

        /* loaded from: classes7.dex */
        public interface SelectionPickerListener {
            void onPickerValueSelected(@NotNull String str, int i);
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final void E(Dialog dialog, View view) {
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            dialog.dismiss();
        }

        public static final void F(SelectionListener selectionListner, ArrayList heightListInCms, Ref.ObjectRef datanumberPicker, Dialog dialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(heightListInCms, "$heightListInCms");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            Object obj = heightListInCms.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "heightListInCms.get(data…cker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            dialog.dismiss();
        }

        public static final void G(ArrayList<String> arrayList, ArrayList<String> arrayList2, int i, int i2, Ref.ObjectRef<WheelPicker> objectRef, int i3) {
            arrayList.clear();
            arrayList2.clear();
            if (i <= i2) {
                int i4 = i;
                while (true) {
                    arrayList.add(String.valueOf(i4));
                    arrayList2.add(String.valueOf(i4));
                    if (i4 == i2) {
                        break;
                    }
                    i4++;
                }
            }
            objectRef.element.setData(arrayList2);
            boolean z = true;
            objectRef.element.setAtmospheric(true);
            objectRef.element.setVisibleItemCount(3);
            objectRef.element.setItemSpace(60);
            if (i > i3 || i3 > i2) {
                z = false;
            }
            if (z) {
                objectRef.element.setSelectedItemPosition(arrayList2.indexOf(String.valueOf(i3).toString()));
            }
        }

        public static final void H(String header_label, Context context, Dialog dialog, View view) {
            Intrinsics.checkNotNullParameter(header_label, "$header_label");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            if (kotlin.text.m.equals(header_label, context.getResources().getString(R.string.height), true)) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setParentScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEIGHT_POPUP.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            } else if (kotlin.text.m.equals(header_label, context.getResources().getString(R.string.weight), true)) {
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog2.setParentScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.WEIGHT_POPUP.getValue());
                analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            }
            dialog.dismiss();
        }

        public static final void I(String header_label, Context context, SelectionListener selectionListner, ArrayList heightListInCms, Ref.ObjectRef datanumberPicker, Dialog dialog, View view) {
            Intrinsics.checkNotNullParameter(header_label, "$header_label");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(heightListInCms, "$heightListInCms");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            if (kotlin.text.m.equals(header_label, context.getResources().getString(R.string.height), true)) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setParentScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEIGHT_POPUP.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.OK_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            } else if (kotlin.text.m.equals(header_label, context.getResources().getString(R.string.weight), true)) {
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog2.setParentScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.WEIGHT_POPUP.getValue());
                analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_WEIGHT_SCREEN.getValue());
                analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.OK_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            }
            Object obj = heightListInCms.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "heightListInCms.get(data…cker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            dialog.dismiss();
        }

        public static final void J(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void K(SelectionListener selectionListner, ArrayList handList, Ref.ObjectRef datanumberPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(handList, "$handList");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = handList.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "handList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void L(Dialog dialog, View view) {
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            dialog.dismiss();
        }

        public static final void M(SelectionListener selectionListener, ArrayList goalList, Ref.ObjectRef dataNumberPicker, Dialog dialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListener, "$selectionListener");
            Intrinsics.checkNotNullParameter(goalList, "$goalList");
            Intrinsics.checkNotNullParameter(dataNumberPicker, "$dataNumberPicker");
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            Object obj = goalList.get(((WheelPicker) dataNumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "goalList[dataNumberPicker.currentItemPosition]");
            selectionListener.onValueSelected((String) obj);
            dialog.dismiss();
        }

        public static final void N(ArrayList<String> arrayList, int i, int i2, int i3, Ref.ObjectRef<WheelPicker> objectRef, int i4) {
            arrayList.clear();
            if (i > 0) {
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(i2, i3, i);
                if (i2 <= progressionLastElement) {
                    int i5 = i2;
                    while (true) {
                        arrayList.add(String.valueOf(i5));
                        if (i5 == progressionLastElement) {
                            break;
                        }
                        i5 += i;
                    }
                }
                objectRef.element.setData(arrayList);
                boolean z = true;
                objectRef.element.setAtmospheric(true);
                objectRef.element.setVisibleItemCount(3);
                objectRef.element.setItemSpace(60);
                if (i2 > i4 || i4 > i3) {
                    z = false;
                }
                if (z) {
                    objectRef.element.setSelectedItemPosition(arrayList.indexOf(String.valueOf(i4).toString()));
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Step must be positive, was: " + i + '.');
        }

        public static final void O(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void P(SelectionListener selectionListner, ArrayList genderList, Ref.ObjectRef datanumberPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(genderList, "$genderList");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = genderList.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "genderList.get(datanumbe…cker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void Q(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void R(SelectionListener selectionListner, ArrayList genderList, Ref.ObjectRef datanumberPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(genderList, "$genderList");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = genderList.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "genderList.get(datanumbe…cker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void S(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void T(SelectionListener selectionListner, ArrayList handList, Ref.ObjectRef datanumberPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(handList, "$handList");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = handList.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "handList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void U(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void V(SelectionListener selectionListner, ArrayList handList, Ref.ObjectRef datanumberPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(handList, "$handList");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = handList.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "handList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void W(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void X(SelectionListener selectionListner, ArrayList minList, WheelPicker wheelPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(minList, "$minList");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = minList.get(wheelPicker.getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "minList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void Y(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void Z(SelectionListener selectionListner, ArrayList minList, WheelPicker wheelPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(minList, "$minList");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = minList.get(wheelPicker.getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "minList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void a0(Dialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void b0(Ref.ObjectRef datanumberPicker, SelectionListener selectionListner, Dialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            int currentItemPosition = ((WheelPicker) datanumberPicker.element).getCurrentItemPosition();
            int i = 60;
            if (currentItemPosition != 0) {
                if (currentItemPosition == 1) {
                    i = 120;
                } else if (currentItemPosition == 2) {
                    i = 180;
                } else if (currentItemPosition == 3) {
                    i = 240;
                }
            }
            selectionListner.onValueSelected(String.valueOf(i));
            alertDialog.dismiss();
        }

        public static final void c0(Dialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void d0(Ref.ObjectRef datanumberPicker, SelectionListener selectionListner, Dialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            int currentItemPosition = ((WheelPicker) datanumberPicker.element).getCurrentItemPosition();
            int i = 5;
            if (currentItemPosition == 0) {
                i = 0;
            } else if (currentItemPosition != 1) {
                if (currentItemPosition == 2) {
                    i = 10;
                } else if (currentItemPosition == 3) {
                    i = 15;
                } else if (currentItemPosition == 4) {
                    i = 30;
                }
            }
            selectionListner.onValueSelected(String.valueOf(i));
            alertDialog.dismiss();
        }

        public static final void e0(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void f0(SelectionListener selectionListner, ArrayList minList, WheelPicker wheelPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(minList, "$minList");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = minList.get(wheelPicker.getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "minList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void g0(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void h0(SelectionListener selectionListner, ArrayList handList, Ref.ObjectRef datanumberPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(handList, "$handList");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = handList.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "handList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onValueSelected((String) obj);
            alertDialog.dismiss();
        }

        public static final void i0(BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            alertDialog.dismiss();
        }

        public static final void j0(SelectionPickerListener selectionListner, ArrayList handList, Ref.ObjectRef datanumberPicker, BottomSheetDialog alertDialog, View view) {
            Intrinsics.checkNotNullParameter(selectionListner, "$selectionListner");
            Intrinsics.checkNotNullParameter(handList, "$handList");
            Intrinsics.checkNotNullParameter(datanumberPicker, "$datanumberPicker");
            Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
            Object obj = handList.get(((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "handList.get(datanumberPicker.currentItemPosition)");
            selectionListner.onPickerValueSelected((String) obj, ((WheelPicker) datanumberPicker.element).getCurrentItemPosition());
            LogHelper.d("pickerDialog watchface value", String.valueOf(((WheelPicker) datanumberPicker.element).getCurrentItemPosition()));
            alertDialog.dismiss();
        }

        /* JADX WARN: Type inference failed for: r1v5, types: [T, android.view.View] */
        public final void dataPickerStrideLength(@NotNull Context context, @NotNull String header_label, int i, int i2, int i3, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final Dialog dialog = new Dialog(context, R.style.GenericDialog);
            dialog.setContentView(R.layout.picker_dialog);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            ((TextView) dialog.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = dialog.findViewById(R.id.number_picker);
            final ArrayList arrayList = new ArrayList();
            G(arrayList, new ArrayList(), i, i2, objectRef, i3);
            ((WheelPicker) objectRef.element).setCyclic(true);
            ((TextView) dialog.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.u0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.E(dialog, view);
                }
            });
            ((TextView) dialog.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.z0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.F(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, dialog, view);
                }
            });
            dialog.show();
        }

        /* JADX WARN: Type inference failed for: r10v5, types: [T, android.view.View] */
        public final void dataPickerWeight(@NotNull final Context context, @NotNull final String header_label, @NotNull String unit, int i, int i2, int i3, int i4, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(unit, "unit");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final Dialog dialog = new Dialog(context, R.style.DialogTheme);
            dialog.setContentView(R.layout.picker_dialog);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            ((TextView) dialog.findViewById(R.id.unit_name)).setText(unit);
            ((TextView) dialog.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = dialog.findViewById(R.id.number_picker);
            final ArrayList arrayList = new ArrayList();
            if (i <= i2) {
                int i5 = i;
                while (true) {
                    arrayList.add(String.valueOf(i5));
                    if (i5 == i2) {
                        break;
                    }
                    i5++;
                }
            }
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            ((WheelPicker) objectRef.element).setVisibleItemCount(3);
            ((WheelPicker) objectRef.element).setItemSpace(60);
            boolean z = false;
            if (i <= i4 && i4 <= i2) {
                z = true;
            }
            if (z) {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(arrayList.indexOf(String.valueOf(i4)));
            }
            ((WheelPicker) objectRef.element).setCyclic(true);
            ((TextView) dialog.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.p0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.H(header_label, context, dialog, view);
                }
            });
            ((TextView) dialog.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.q0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.I(header_label, context, selectionListner, arrayList, objectRef, dialog, view);
                }
            });
            dialog.show();
        }

        /* JADX WARN: Type inference failed for: r2v8, types: [T, android.view.View] */
        public final void distanceUnitPicker(@NotNull Context context, boolean z, @NotNull String header_label, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_settings, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            ((ImageView) inflate.findViewById(R.id.arrow_image)).setVisibility(8);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(context.getString(R.string.dist_km));
            arrayList.add(context.getString(R.string.dist_mile));
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            if (z) {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(1);
            } else {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(0);
            }
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.h0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.J(BottomSheetDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.y
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.K(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, bottomSheetDialog, view);
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX WARN: Type inference failed for: r5v5, types: [T, android.view.View] */
        public final void fitnessChallengeGoalPicker(@NotNull Context context, @NotNull String header_label, int i, int i2, int i3, @NotNull String unit_value, int i4, @NotNull final SelectionListener selectionListener) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(unit_value, "unit_value");
            Intrinsics.checkNotNullParameter(selectionListener, "selectionListener");
            final Dialog dialog = new Dialog(context, R.style.GenericDialog);
            dialog.setContentView(R.layout.picker_dialog);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            ((TextView) dialog.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = dialog.findViewById(R.id.number_picker);
            ((TextView) dialog.findViewById(R.id.unit_name)).setText(unit_value);
            final ArrayList arrayList = new ArrayList();
            N(arrayList, i4, i, i2, objectRef, i3);
            ((WheelPicker) objectRef.element).setCyclic(true);
            ((TextView) dialog.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.i0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.L(dialog, view);
                }
            });
            ((TextView) dialog.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.y0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.M(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, dialog, view);
                }
            });
            dialog.show();
        }

        /* JADX WARN: Type inference failed for: r3v5, types: [T, android.view.View] */
        public final void genderPicker(@NotNull Context context, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_settings, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(context.getString(R.string.gender_selection));
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(context.getString(R.string.gender_male));
            arrayList.add(context.getString(R.string.gender_female));
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            ((WheelPicker) objectRef.element).setSelectedItemPosition(0);
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.g0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.O(BottomSheetDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.a1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.P(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, bottomSheetDialog, view);
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX WARN: Type inference failed for: r2v8, types: [T, android.view.View] */
        public final void handPicker(@NotNull Context context, boolean z, @NotNull String header_label, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_settings, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            ((ImageView) inflate.findViewById(R.id.arrow_image)).setVisibility(8);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(context.getString(R.string.right_hand));
            arrayList.add(context.getString(R.string.left_hand));
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            if (z) {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(0);
            } else {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(1);
            }
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.o0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.S(BottomSheetDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.b0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.T(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, bottomSheetDialog, view);
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX WARN: Type inference failed for: r2v8, types: [T, android.view.View] */
        public final void hourFormatPicker(@NotNull Context context, boolean z, @NotNull String header_label, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_settings, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            ((ImageView) inflate.findViewById(R.id.arrow_image)).setVisibility(8);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(context.getString(R.string.hour_12));
            arrayList.add(context.getString(R.string.hour_24));
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            if (z) {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(0);
            } else {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(1);
            }
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.m0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.U(BottomSheetDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.c0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.V(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, bottomSheetDialog, view);
                }
            });
            bottomSheetDialog.show();
        }

        public final void minutesPicker(@NotNull Context context, int i, int i2, int i3, int i4, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_sedentary, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setText(context.getString(R.string.min));
            ((TextView) inflate.findViewById(R.id.pouup_label)).setVisibility(8);
            final WheelPicker wheelPicker = (WheelPicker) inflate.findViewById(R.id.number_picker);
            final ArrayList arrayList = new ArrayList();
            if (i4 > 0) {
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(i2, i3, i4);
                if (i2 <= progressionLastElement) {
                    while (true) {
                        arrayList.add(String.valueOf(i2));
                        if (i2 == progressionLastElement) {
                            break;
                        }
                        i2 += i4;
                    }
                }
                int indexOf = arrayList.indexOf(String.valueOf(i));
                wheelPicker.setData(arrayList);
                wheelPicker.setAtmospheric(true);
                wheelPicker.setSelectedItemPosition(indexOf);
                wheelPicker.setCyclic(false);
                ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.l0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PickerDialog.Companion.W(BottomSheetDialog.this, view);
                    }
                });
                ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.w0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PickerDialog.Companion.X(PickerDialog.Companion.SelectionListener.this, arrayList, wheelPicker, bottomSheetDialog, view);
                    }
                });
                bottomSheetDialog.show();
                return;
            }
            throw new IllegalArgumentException("Step must be positive, was: " + i4 + '.');
        }

        public final void minutesPickerAutoStress(@NotNull Context context, int i, int i2, int i3, int i4, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_sedentary, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setText(context.getString(R.string.min));
            ((TextView) inflate.findViewById(R.id.pouup_label)).setVisibility(8);
            final WheelPicker wheelPicker = (WheelPicker) inflate.findViewById(R.id.number_picker);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(0, BleConst.GetDeviceName);
            if (i4 > 0) {
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(i2, i3, i4);
                if (i2 <= progressionLastElement) {
                    while (true) {
                        arrayList.add(String.valueOf(i2));
                        if (i2 == progressionLastElement) {
                            break;
                        }
                        i2 += i4;
                    }
                }
                int indexOf = arrayList.indexOf(String.valueOf(i));
                wheelPicker.setData(arrayList);
                wheelPicker.setAtmospheric(true);
                wheelPicker.setSelectedItemPosition(indexOf);
                wheelPicker.setCyclic(false);
                ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.f0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PickerDialog.Companion.Y(BottomSheetDialog.this, view);
                    }
                });
                ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.x0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PickerDialog.Companion.Z(PickerDialog.Companion.SelectionListener.this, arrayList, wheelPicker, bottomSheetDialog, view);
                    }
                });
                bottomSheetDialog.show();
                return;
            }
            throw new IllegalArgumentException("Step must be positive, was: " + i4 + '.');
        }

        /* JADX WARN: Type inference failed for: r1v6, types: [T, android.view.View] */
        public final void remindFrequencyPicker(@NotNull Context context, int i, @NotNull String header_label, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final Dialog dialog = new Dialog(context, R.style.DialogTheme);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_remind_in, (ViewGroup) null);
            dialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            ((ImageView) inflate.findViewById(R.id.arrow_image)).setVisibility(8);
            ArrayList arrayList = new ArrayList();
            arrayList.add("1 hr");
            arrayList.add("2 hr");
            arrayList.add("3 hr");
            arrayList.add("4 hr");
            ((WheelPicker) objectRef.element).setSelectedItemPosition(i != 60 ? i != 120 ? i != 180 ? i != 240 ? -1 : 3 : 2 : 1 : 0);
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.t0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.a0(dialog, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.s0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.b0(Ref.ObjectRef.this, selectionListner, dialog, view);
                }
            });
            dialog.show();
        }

        /* JADX WARN: Type inference failed for: r2v5, types: [T, android.view.View] */
        public final void remindInPicker(@NotNull Context context, int i, @NotNull String header_label, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final Dialog dialog = new Dialog(context, R.style.DialogTheme);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_remind_in, (ViewGroup) null);
            dialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            ((ImageView) inflate.findViewById(R.id.arrow_image)).setVisibility(8);
            ArrayList arrayList = new ArrayList();
            arrayList.add(context.getString(R.string.ontime));
            arrayList.add("5 min");
            arrayList.add("10 min");
            arrayList.add("15 min");
            arrayList.add("30 min");
            ((WheelPicker) objectRef.element).setSelectedItemPosition(i != 0 ? i != 5 ? i != 10 ? i != 15 ? i != 30 ? -1 : 4 : 3 : 2 : 1 : 0);
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.x
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.c0(dialog, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.r0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.d0(Ref.ObjectRef.this, selectionListner, dialog, view);
                }
            });
            dialog.show();
        }

        public final void repeatReminderPicker(@NotNull Context context, @NotNull String repeatCount, int i, int i2, int i3, @NotNull final SelectionListener selectionListner) {
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(repeatCount, "repeatCount");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogTheme);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_sedentary, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setVisibility(8);
            final WheelPicker wheelPicker = (WheelPicker) inflate.findViewById(R.id.number_picker);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(0, context.getResources().getString(R.string.donot_repeat));
            if (i3 > 0) {
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(i, i2, i3);
                if (i <= progressionLastElement) {
                    while (true) {
                        arrayList.add(i + " count");
                        if (i == progressionLastElement) {
                            break;
                        }
                        i += i3;
                    }
                }
                if (kotlin.text.m.equals(repeatCount, "DonotRepeat", true)) {
                    str = context.getResources().getString(R.string.donot_repeat);
                } else {
                    str = repeatCount + " count";
                }
                Intrinsics.checkNotNullExpressionValue(str, "if (repeatCount.equals(\"…ount count\"\n            }");
                int indexOf = arrayList.indexOf(str);
                wheelPicker.setData(arrayList);
                wheelPicker.setAtmospheric(true);
                wheelPicker.setSelectedItemPosition(indexOf);
                wheelPicker.setCyclic(false);
                ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.e0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PickerDialog.Companion.e0(BottomSheetDialog.this, view);
                    }
                });
                ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.v0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PickerDialog.Companion.f0(PickerDialog.Companion.SelectionListener.this, arrayList, wheelPicker, bottomSheetDialog, view);
                    }
                });
                bottomSheetDialog.show();
                return;
            }
            throw new IllegalArgumentException("Step must be positive, was: " + i3 + '.');
        }

        /* JADX WARN: Type inference failed for: r2v8, types: [T, android.view.View] */
        public final void temperatureUnitPicker(@NotNull Context context, boolean z, @NotNull String header_label, @NotNull final SelectionListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(header_label, "header_label");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_settings, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(header_label);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            ((ImageView) inflate.findViewById(R.id.arrow_image)).setVisibility(8);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(context.getString(R.string.fahrenheit));
            arrayList.add(context.getString(R.string.celsius));
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            if (z) {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(0);
            } else {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(1);
            }
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.n0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.g0(BottomSheetDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.a0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.h0(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, bottomSheetDialog, view);
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [T, android.view.View] */
        public final void watchFaceWebViewPicker(@NotNull Context context, @NotNull ArrayList<String> array, int i, @NotNull String option, @NotNull final SelectionPickerListener selectionListner) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(option, "option");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_watchface, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            ((ImageView) inflate.findViewById(R.id.arrow_image)).setVisibility(8);
            final ArrayList arrayList = new ArrayList();
            int size = array.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(array.get(i2));
            }
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            ((WheelPicker) objectRef.element).setCyclic(false);
            if (i > 0) {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(i);
            } else {
                ((WheelPicker) objectRef.element).setSelectedItemPosition(0);
            }
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.k0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.i0(BottomSheetDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.d0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.j0(PickerDialog.Companion.SelectionPickerListener.this, arrayList, objectRef, bottomSheetDialog, view);
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX WARN: Type inference failed for: r3v5, types: [T, android.view.View] */
        public final void genderPicker(@NotNull Context context, @NotNull final SelectionListener selectionListner, @NotNull String defaultGender) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(selectionListner, "selectionListner");
            Intrinsics.checkNotNullParameter(defaultGender, "defaultGender");
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
            View inflate = LayoutInflater.from(context).inflate(R.layout.picker_dialog_settings, (ViewGroup) null);
            bottomSheetDialog.setContentView(inflate);
            ((TextView) inflate.findViewById(R.id.unit_name)).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.pouup_label)).setText(context.getString(R.string.gender_selection));
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = inflate.findViewById(R.id.number_picker);
            final ArrayList arrayList = new ArrayList();
            arrayList.add(context.getString(R.string.gender_male));
            arrayList.add(context.getString(R.string.gender_female));
            ((WheelPicker) objectRef.element).setData(arrayList);
            ((WheelPicker) objectRef.element).setAtmospheric(true);
            ((WheelPicker) objectRef.element).setSelectedItemPosition(arrayList.indexOf(defaultGender));
            ((WheelPicker) objectRef.element).setCyclic(false);
            ((TextView) inflate.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.j0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.Q(BottomSheetDialog.this, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.z
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PickerDialog.Companion.R(PickerDialog.Companion.SelectionListener.this, arrayList, objectRef, bottomSheetDialog, view);
                }
            });
            bottomSheetDialog.show();
        }
    }
}
