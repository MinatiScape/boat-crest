package com.coveiot.android.navigation.utils;

import android.content.Context;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.navigation.models.FavouritePlaceData;
import com.coveiot.android.theme.R;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.a;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class NavigationUtilsKt {
    @NotNull
    public static final Pair<Integer, Integer> addEtaToCurrentTime(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(11, i);
        calendar.add(12, i2);
        return new Pair<>(Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0043 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0063 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int getBandManevurId(int r1) {
        /*
            r0 = 36
            if (r1 == r0) goto L6f
            switch(r1) {
                case 0: goto L6b;
                case 1: goto L67;
                case 2: goto L63;
                case 3: goto L5f;
                case 4: goto L5b;
                case 5: goto L57;
                case 6: goto L53;
                case 7: goto L4f;
                case 8: goto L4b;
                case 9: goto L4b;
                case 10: goto L4b;
                default: goto L7;
            }
        L7:
            switch(r1) {
                case 13: goto L6b;
                case 14: goto L5f;
                case 15: goto L63;
                case 16: goto L57;
                case 17: goto L6b;
                case 18: goto L5f;
                case 19: goto L6b;
                case 20: goto L5f;
                case 21: goto L4f;
                default: goto La;
            }
        La:
            switch(r1) {
                case 26: goto L6b;
                case 27: goto L6b;
                case 28: goto L6b;
                case 29: goto L5f;
                case 30: goto L5f;
                case 31: goto L5f;
                case 32: goto L4f;
                case 33: goto L4f;
                default: goto Ld;
            }
        Ld:
            switch(r1) {
                case 41: goto L47;
                case 42: goto L43;
                case 43: goto L3f;
                case 44: goto L43;
                default: goto L10;
            }
        L10:
            switch(r1) {
                case 50: goto L3b;
                case 51: goto L37;
                case 52: goto L33;
                case 53: goto L2f;
                case 54: goto L2b;
                case 55: goto L27;
                case 56: goto L23;
                case 57: goto L1f;
                default: goto L13;
            }
        L13:
            switch(r1) {
                case 65: goto L1b;
                case 66: goto L1b;
                case 67: goto L1b;
                case 68: goto L1b;
                case 69: goto L1b;
                case 70: goto L1b;
                case 71: goto L1b;
                case 72: goto L1b;
                case 73: goto L43;
                case 74: goto L43;
                case 75: goto L3f;
                default: goto L16;
            }
        L16:
            r1 = 35015(0x88c7, float:4.9066E-41)
            goto L72
        L1b:
            r1 = 35002(0x88ba, float:4.9048E-41)
            goto L72
        L1f:
            r1 = 35024(0x88d0, float:4.9079E-41)
            goto L72
        L23:
            r1 = 35023(0x88cf, float:4.9078E-41)
            goto L72
        L27:
            r1 = 35022(0x88ce, float:4.9076E-41)
            goto L72
        L2b:
            r1 = 35021(0x88cd, float:4.9075E-41)
            goto L72
        L2f:
            r1 = 35020(0x88cc, float:4.9073E-41)
            goto L72
        L33:
            r1 = 35019(0x88cb, float:4.9072E-41)
            goto L72
        L37:
            r1 = 35018(0x88ca, float:4.907E-41)
            goto L72
        L3b:
            r1 = 35017(0x88c9, float:4.9069E-41)
            goto L72
        L3f:
            r1 = 35005(0x88bd, float:4.9052E-41)
            goto L72
        L43:
            r1 = 35004(0x88bc, float:4.9051E-41)
            goto L72
        L47:
            r1 = 35007(0x88bf, float:4.9055E-41)
            goto L72
        L4b:
            r1 = 35001(0x88b9, float:4.9047E-41)
            goto L72
        L4f:
            r1 = 35003(0x88bb, float:4.905E-41)
            goto L72
        L53:
            r1 = 35006(0x88be, float:4.9054E-41)
            goto L72
        L57:
            r1 = 35011(0x88c3, float:4.9061E-41)
            goto L72
        L5b:
            r1 = 35009(0x88c1, float:4.9058E-41)
            goto L72
        L5f:
            r1 = 35013(0x88c5, float:4.9064E-41)
            goto L72
        L63:
            r1 = 35010(0x88c2, float:4.906E-41)
            goto L72
        L67:
            r1 = 35008(0x88c0, float:4.9057E-41)
            goto L72
        L6b:
            r1 = 35012(0x88c4, float:4.9062E-41)
            goto L72
        L6f:
            r1 = 35014(0x88c6, float:4.9065E-41)
        L72:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.navigation.utils.NavigationUtilsKt.getBandManevurId(int):int");
    }

    @NotNull
    public static final Triple<Long, Long, Long> getEtaForBand(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").setTimeZone(TimeZone.getDefault());
        long time = new Date((j * 1000) + currentTimeMillis).getTime() - new Date(currentTimeMillis).getTime();
        long j2 = (long) TimeConstants.DAY;
        long j3 = time / j2;
        long j4 = (long) TimeConstants.HOUR;
        return new Triple<>(Long.valueOf(j3), Long.valueOf((time % j2) / j4), Long.valueOf((time % j4) / 60000));
    }

    @NotNull
    public static final ArrayList<FavouritePlaceData> getFTUFavouritePlaces(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<FavouritePlaceData> arrayList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            switch (i) {
                case 1:
                    arrayList.add(new FavouritePlaceData(null, null, null, Integer.valueOf(i), context.getString(R.string.home), null, null, null));
                    break;
                case 2:
                    arrayList.add(new FavouritePlaceData(null, null, null, Integer.valueOf(i), context.getString(R.string.work), null, null, null));
                    break;
                case 3:
                    arrayList.add(new FavouritePlaceData(null, null, null, Integer.valueOf(i), context.getString(R.string.place_1), null, null, null));
                    break;
                case 4:
                    arrayList.add(new FavouritePlaceData(null, null, null, Integer.valueOf(i), context.getString(R.string.place_2), null, null, null));
                    break;
                case 5:
                    arrayList.add(new FavouritePlaceData(null, null, null, Integer.valueOf(i), context.getString(R.string.place_3), null, null, null));
                    break;
                case 6:
                    arrayList.add(new FavouritePlaceData(null, null, null, Integer.valueOf(i), context.getString(R.string.edit), null, null, null));
                    break;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0110  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final android.graphics.drawable.Drawable getManevurImages(@org.jetbrains.annotations.NotNull android.content.Context r2, int r3) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.navigation.utils.NavigationUtilsKt.getManevurImages(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    public static final int parseColor(@NotNull String colorString) {
        Intrinsics.checkNotNullParameter(colorString, "colorString");
        if (m.startsWith$default(colorString, HexStringBuilder.DEFAULT_PREFIX, false, 2, null)) {
            String substring = colorString.substring(2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            String lowerCase = substring.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            return Integer.parseInt(lowerCase, a.checkRadix(16));
        }
        return Integer.parseInt(colorString);
    }

    @NotNull
    public static final <T> ArrayList<T> replaceItemsInList(@NotNull ArrayList<T> list, @NotNull List<Integer> indices, @NotNull List<? extends T> newItems) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(indices, "indices");
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        ArrayList<T> arrayList = new ArrayList<>(list);
        int min = Math.min(indices.size(), newItems.size());
        for (int i = 0; i < min; i++) {
            int intValue = indices.get(i).intValue();
            T t = newItems.get(i);
            if (intValue >= 0 && intValue < arrayList.size()) {
                arrayList.set(intValue, t);
            }
        }
        return arrayList;
    }
}
