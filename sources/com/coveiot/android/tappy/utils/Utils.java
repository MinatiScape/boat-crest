package com.coveiot.android.tappy.utils;

import android.content.Context;
import android.os.Build;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.DeviceInfo;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class Utils {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f6016a = "Utils";

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DeviceInfo a(Context context) {
            String name;
            List split$default;
            DeviceInfo deviceInfo = new DeviceInfo();
            ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
            deviceInfo.setOs("Android");
            deviceInfo.setOsVersion(String.valueOf(Build.VERSION.SDK_INT));
            deviceInfo.setModel(Build.MODEL);
            boolean z = false;
            deviceInfo.setFriendlyName((userDetails == null || (name = userDetails.getName()) == null || (split$default = StringsKt__StringsKt.split$default((CharSequence) name, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null)) == null) ? null : (String) split$default.get(0));
            deviceInfo.setFormFactor("Smartphone");
            String str = Build.BRAND;
            deviceInfo.setMake((str == null || str.length() == 0) ? true : true ? "Android" : str);
            return deviceInfo;
        }

        public final String b(Context context) {
            String name;
            List split$default;
            ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
            StringBuilder sb = new StringBuilder();
            sb.append("Android");
            sb.append(Build.VERSION.SDK_INT);
            sb.append(Build.MODEL);
            sb.append((userDetails == null || (name = userDetails.getName()) == null || (split$default = StringsKt__StringsKt.split$default((CharSequence) name, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null)) == null) ? null : (String) split$default.get(0));
            sb.append("'s Android");
            return sb.toString();
        }

        @NotNull
        public final String convertToAtleastTwoDigits(int i) {
            if (i < 10) {
                StringBuilder sb = new StringBuilder();
                sb.append('0');
                sb.append(i);
                return sb.toString();
            }
            return String.valueOf(i);
        }

        @NotNull
        public final String formatTimestamp(@NotNull Context context, long j, boolean z, boolean z2) {
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            long abs = Math.abs(System.currentTimeMillis() - j);
            Date date = new Date();
            date.setTime(j);
            if (abs < 86400000) {
                if (z) {
                    String sdf = AppUtils.formatDate(date, z2 ? "hh:mm a" : "HH:mm a");
                    Intrinsics.checkNotNullExpressionValue(sdf, "sdf");
                    str = context.getString(R.string.paid_today, m.replace$default(m.replace$default(sdf, "AM", "am", false, 4, (Object) null), "PM", "pm", false, 4, (Object) null));
                } else {
                    str = context.getString(R.string.today);
                }
                Intrinsics.checkNotNullExpressionValue(str, "{\n                    if…      }\n                }");
            } else if (abs < 172800000) {
                if (z) {
                    String sdf2 = AppUtils.formatDate(date, z2 ? "hh:mm a" : "HH:mm a");
                    Intrinsics.checkNotNullExpressionValue(sdf2, "sdf");
                    str = context.getString(R.string.paid_yesterday, m.replace$default(m.replace$default(sdf2, "AM", "am", false, 4, (Object) null), "PM", "pm", false, 4, (Object) null));
                } else {
                    str = context.getString(R.string.yesterday);
                }
                Intrinsics.checkNotNullExpressionValue(str, "{\n                    if…      }\n                }");
            } else if (abs < 604800000) {
                if (z) {
                    String sdf3 = AppUtils.formatDate(date, z2 ? " EEEE hh:mm a" : "EEEE HH:mm a");
                    Intrinsics.checkNotNullExpressionValue(sdf3, "sdf");
                    str = context.getString(R.string.paid_time, m.replace$default(m.replace$default(sdf3, "AM", "am", false, 4, (Object) null), "PM", "pm", false, 4, (Object) null));
                } else {
                    str = AppUtils.formatDate(date, "EEEE");
                }
                Intrinsics.checkNotNullExpressionValue(str, "{ // Within the last wee…      }\n                }");
            } else {
                if (z) {
                    String sdf4 = AppUtils.formatDate(date, z2 ? " EEEE dd MMMM yyyy, hh:mm a" : "EEEE dd MMMM yyyy, HH:mm a");
                    String dayOfMonth = AppUtils.formatDate(date, "dd");
                    Intrinsics.checkNotNullExpressionValue(sdf4, "sdf");
                    String replace$default = m.replace$default(m.replace$default(sdf4, "AM", "am", false, 4, (Object) null), "PM", "pm", false, 4, (Object) null);
                    Intrinsics.checkNotNullExpressionValue(dayOfMonth, "dayOfMonth");
                    str = context.getString(R.string.paid_time, m.replaceFirst$default(replace$default, dayOfMonth, getDayWithSuffix(Integer.parseInt(dayOfMonth)), false, 4, (Object) null));
                } else {
                    String sdf5 = AppUtils.formatDate(date, "EEEE, dd MMMM yyyy");
                    String dayOfMonth2 = AppUtils.formatDate(date, "dd");
                    Intrinsics.checkNotNullExpressionValue(dayOfMonth2, "dayOfMonth");
                    String dayWithSuffix = getDayWithSuffix(Integer.parseInt(dayOfMonth2));
                    Intrinsics.checkNotNullExpressionValue(sdf5, "sdf");
                    str = m.replaceFirst$default(sdf5, dayOfMonth2, dayWithSuffix, false, 4, (Object) null).toString();
                }
                Intrinsics.checkNotNullExpressionValue(str, "{\n                    if…      }\n                }");
            }
            return str;
        }

        @NotNull
        public final String get4DigitYear(@NotNull String twoDigitYear) {
            Intrinsics.checkNotNullParameter(twoDigitYear, "twoDigitYear");
            return BleConst.SetAlarmClockWithAllClock + twoDigitYear;
        }

        public final int getCardType(@NotNull String cardNumber) {
            Intrinsics.checkNotNullParameter(cardNumber, "cardNumber");
            int i = LocalAPDUCommands.PAYMENTNETWORK_MASTERCARD;
            if (cardNumber.length() == 0) {
                return i;
            }
            Regex regex = new Regex("^4[0-9]{2,12}(?:[0-9]{3})?$");
            Regex regex2 = new Regex("^5[1-5][0-9]{1,14}$");
            if (regex.matches(cardNumber)) {
                return LocalAPDUCommands.PAYMENTNETWORK_VISA;
            }
            return regex2.matches(cardNumber) ? LocalAPDUCommands.PAYMENTNETWORK_MASTERCARD : i;
        }

        @Nullable
        public final String getCurrencySymbol(@NotNull String currencyCode) {
            Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
            return Currency.getInstance(currencyCode).getSymbol();
        }

        @NotNull
        public final String getDayWithSuffix(int i) {
            boolean z = false;
            if (11 <= i && i < 14) {
                z = true;
            }
            if (z) {
                return i + "th";
            }
            int i2 = i % 10;
            if (i2 == 1) {
                return i + "st";
            } else if (i2 == 2) {
                return i + "nd";
            } else if (i2 != 3) {
                return i + "th";
            } else {
                return i + "rd";
            }
        }

        @NotNull
        public final DeviceInfo getDeviceInfo(@Nullable List<DeviceInfo> list, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceInfo deviceInfo = new DeviceInfo();
            boolean z = false;
            if (list == null || list.isEmpty()) {
                return a(context);
            }
            String b = b(context);
            Iterator<DeviceInfo> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (Intrinsics.areEqual(next.getOs() + next.getOsVersion() + next.getModel() + next.getFriendlyName(), b)) {
                    z = true;
                    deviceInfo = next;
                    break;
                }
            }
            return !z ? a(context) : deviceInfo;
        }

        @Nullable
        public final RegisteredProductInfo getRegisteredProductDetail(@NotNull List<RegisteredProductInfo> registeredProducts, @NotNull String serialNumber) {
            Intrinsics.checkNotNullParameter(registeredProducts, "registeredProducts");
            Intrinsics.checkNotNullParameter(serialNumber, "serialNumber");
            if (!registeredProducts.isEmpty()) {
                for (RegisteredProductInfo registeredProductInfo : registeredProducts) {
                    String productSerialNumber = registeredProductInfo.getProductSerialNumber();
                    if (!(productSerialNumber == null || productSerialNumber.length() == 0) && Intrinsics.areEqual(registeredProductInfo.getProductSerialNumber(), serialNumber)) {
                        return registeredProductInfo;
                    }
                }
            }
            return null;
        }

        @NotNull
        public final String getTAG() {
            return Utils.f6016a;
        }

        public final boolean isValidCardNumber(@NotNull String cardNumber) {
            Intrinsics.checkNotNullParameter(cardNumber, "cardNumber");
            if (!(cardNumber.length() == 0)) {
                Regex regex = new Regex("^4[0-9]{2,12}(?:[0-9]{3})?$");
                Regex regex2 = new Regex("^5[1-5][0-9]{1,14}$");
                if (regex.matches(cardNumber) || regex2.matches(cardNumber)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isValidCvv(@NotNull String cvv) {
            Intrinsics.checkNotNullParameter(cvv, "cvv");
            return !(cvv.length() == 0) && cvv.length() == 3;
        }

        public final boolean isValidExpiryMonth(@NotNull String month) {
            Intrinsics.checkNotNullParameter(month, "month");
            if (!(month.length() == 0) && month.length() == 2) {
                int parseInt = Integer.parseInt(month);
                if (1 <= parseInt && parseInt < 13) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isValidExpiryYear(@NotNull String year) {
            Intrinsics.checkNotNullParameter(year, "year");
            boolean z = true;
            if ((year.length() == 0) || year.length() != 2) {
                return false;
            }
            int i = Calendar.getInstance().get(1);
            int parseInt = Integer.parseInt(get4DigitYear(year));
            if (i != parseInt && parseInt < i) {
                z = false;
            }
            return z;
        }
    }
}
