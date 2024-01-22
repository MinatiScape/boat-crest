package com.coveiot.android.fitnessbuddies.utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyWalkDetails;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.szabh.smable3.entity.BleNotification;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class CoveUtils {
    @NotNull
    public static final CoveUtils INSTANCE = new CoveUtils();

    /* loaded from: classes4.dex */
    public static final class BuddyWalkDataComparator implements Comparator<BuddyWalkDetails> {
        @Override // java.util.Comparator
        public int compare(@NotNull BuddyWalkDetails buddyWalkData, @NotNull BuddyWalkDetails t1) {
            Intrinsics.checkNotNullParameter(buddyWalkData, "buddyWalkData");
            Intrinsics.checkNotNullParameter(t1, "t1");
            String str = buddyWalkData.date;
            Intrinsics.checkNotNull(str);
            String str2 = t1.date;
            Intrinsics.checkNotNull(str2);
            return str.compareTo(str2);
        }
    }

    @NotNull
    public final List<String> extractUrls(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("((?:http|https)://(?:[\\w_-]+(?:(?:\\.[\\w_-]+)+))(?:[\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-]))", 2).matcher(text);
        while (matcher.find()) {
            String substring = text.substring(matcher.start(0), matcher.end(0));
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(substring);
        }
        return arrayList;
    }

    @Nullable
    public final Bitmap getContactPhotoUri(@NotNull String modbileNumber, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(modbileNumber, "modbileNumber");
        Intrinsics.checkNotNullParameter(context, "context");
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_avatar);
        try {
            Cursor query = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(modbileNumber)), new String[]{"display_name", "_id"}, null, null, null);
            String str = null;
            if (query != null) {
                while (query.moveToNext()) {
                    str = query.getString(query.getColumnIndexOrThrow("_id"));
                }
                query.close();
            }
            if (str == null) {
                return null;
            }
            try {
                InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(str)));
                if (openContactPhotoInputStream != null) {
                    decodeResource = BitmapFactory.decodeStream(openContactPhotoInputStream);
                    openContactPhotoInputStream.close();
                    return decodeResource;
                }
                return decodeResource;
            } catch (IOException e) {
                e.printStackTrace();
                return decodeResource;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return decodeResource;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x008d  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.coveiot.utils.model.CoveContact> getContacts(@org.jetbrains.annotations.NotNull android.content.Context r22) {
        /*
            r21 = this;
            r0 = r22
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.content.ContentResolver r8 = r22.getContentResolver()
            android.net.Uri r3 = android.provider.ContactsContract.Contacts.CONTENT_URI
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "display_name ASC"
            r2 = r8
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)
            if (r9 == 0) goto Lb6
            int r2 = r9.getCount()
            if (r2 <= 0) goto Lb6
            r2 = 0
            r10 = r2
        L27:
            boolean r2 = r9.moveToNext()
            if (r2 == 0) goto Lb3
            java.lang.String r2 = "_id"
            int r2 = r9.getColumnIndex(r2)
            java.lang.String r18 = r9.getString(r2)
            java.lang.String r2 = "display_name"
            int r2 = r9.getColumnIndex(r2)
            java.lang.String r19 = r9.getString(r2)
            java.lang.String r2 = "has_phone_number"
            int r2 = r9.getColumnIndex(r2)
            int r2 = r9.getInt(r2)
            if (r2 <= 0) goto L27
            android.net.Uri r3 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            r4 = 0
            r15 = 1
            java.lang.String[] r6 = new java.lang.String[r15]
            r20 = 0
            r6[r20] = r18
            r7 = 0
            java.lang.String r5 = "contact_id = ?"
            r2 = r8
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)
        L5f:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r3 = r2.moveToNext()
            if (r3 == 0) goto Lae
            java.lang.String r3 = "data1"
            int r3 = r2.getColumnIndex(r3)
            java.lang.String r3 = r2.getString(r3)
            r4 = 1
            long r10 = r10 + r4
            com.coveiot.utils.model.CoveContact r4 = new com.coveiot.utils.model.CoveContact
            r12 = r4
            r13 = r18
            r14 = r19
            r5 = r15
            r15 = r3
            r16 = r10
            r12.<init>(r13, r14, r15, r16)
            java.util.Iterator r3 = r1.iterator()
        L87:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto La5
            java.lang.Object r6 = r3.next()
            com.coveiot.utils.model.CoveContact r6 = (com.coveiot.utils.model.CoveContact) r6
            if (r6 == r4) goto La3
            java.lang.String r7 = r4.getPhoneNumber()
            java.lang.String r6 = r6.getPhoneNumber()
            boolean r6 = android.telephony.PhoneNumberUtils.compare(r0, r7, r6)
            if (r6 == 0) goto L87
        La3:
            r15 = r5
            goto La7
        La5:
            r15 = r20
        La7:
            if (r15 != 0) goto Lac
            r1.add(r4)
        Lac:
            r15 = r5
            goto L5f
        Lae:
            r2.close()
            goto L27
        Lb3:
            r9.close()
        Lb6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnessbuddies.utils.CoveUtils.getContacts(android.content.Context):java.util.List");
    }

    @NotNull
    public final Calendar getLastMondayDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        while (c.get(7) != 2) {
            c.add(7, -1);
        }
        Intrinsics.checkNotNullExpressionValue(c, "c");
        return c;
    }

    public final boolean isAppInstalled(@NotNull Context context, @NotNull String uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            context.getPackageManager().getPackageInfo(uri, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final boolean isEmpty(@Nullable List<?> list) {
        return list == null || list.size() == 0;
    }

    public final boolean isNetConnected(@Nullable Context context) {
        Intrinsics.checkNotNull(context);
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public final boolean isValidPhoneNumber(@NotNull String phoneNo) {
        Intrinsics.checkNotNullParameter(phoneNo, "phoneNo");
        try {
            if (StringsKt__StringsKt.contains$default((CharSequence) phoneNo, (CharSequence) "+", false, 2, (Object) null)) {
                phoneNo = m.replace$default(phoneNo, "+", "", false, 4, (Object) null);
            }
            if (StringsKt__StringsKt.contains$default((CharSequence) phoneNo, (CharSequence) HexStringBuilder.DEFAULT_SEPARATOR, false, 2, (Object) null)) {
                phoneNo = new Regex("\\s+").replace(phoneNo, "");
            }
            if (StringsKt__StringsKt.contains$default((CharSequence) phoneNo, (CharSequence) "-", false, 2, (Object) null)) {
                phoneNo = new Regex("-").replace(phoneNo, "");
            }
            if (phoneNo.length() < 6) {
                return false;
            }
            Long.parseLong(phoneNo);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void openWhatsApp(@NotNull Context context, @NotNull String smsNumber, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(smsNumber, "smsNumber");
        Intrinsics.checkNotNullParameter(message, "message");
        if (isAppInstalled(context, BleNotification.WHATS_APP)) {
            List<String> extractUrls = extractUrls(message);
            Regex regex = new Regex(extractUrls.get(0));
            String replace = regex.replace(message, extractUrls.get(0) + "?s=w");
            String replace$default = m.replace$default(m.replace$default(smsNumber, HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null), "+", "", false, 4, (Object) null);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", replace);
            intent.addFlags(1073741824);
            intent.putExtra("jid", replace$default + "@s.whatsapp.net");
            intent.setPackage(BleNotification.WHATS_APP);
            context.startActivity(intent);
        }
    }

    public final void openWhatsAppGenericShareMultiple(@NotNull Context context, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        if (isAppInstalled(context, BleNotification.WHATS_APP)) {
            List<String> extractUrls = extractUrls(message);
            Regex regex = new Regex(extractUrls.get(0));
            String replace = regex.replace(message, extractUrls.get(0) + "?s=w");
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", replace);
            intent.addFlags(1073741824);
            intent.setPackage(BleNotification.WHATS_APP);
            context.startActivity(intent);
        }
    }
}
