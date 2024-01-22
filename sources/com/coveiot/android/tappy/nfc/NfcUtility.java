package com.coveiot.android.tappy.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import com.coveiot.android.tappy.utils.HexUtils;
import com.coveiot.sdk.ble.api.BleUUID;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class NfcUtility {
    @Nullable
    public static IsoDep b;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f6013a = "NfcUtility";
    @NotNull
    public static byte[] c = {BleUUID.CMD_ID_90, 0};
    public static byte d = 97;
    @NotNull
    public static byte[] e = {-124, -64, 0, 0};
    @NotNull
    public static byte[] f = {Byte.MIN_VALUE, -64, 0, 0};
    public static byte g = Byte.MIN_VALUE;
    public static byte h = -124;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(String str) {
        }

        public final boolean connect(@NotNull Intent intent) throws Exception {
            Intrinsics.checkNotNullParameter(intent, "intent");
            setIsoDep(IsoDep.get((Tag) intent.getParcelableExtra("android.nfc.extra.TAG")));
            IsoDep isoDep = getIsoDep();
            if (isoDep != null) {
                isoDep.setTimeout(20000);
            }
            IsoDep isoDep2 = getIsoDep();
            if (isoDep2 != null) {
                isoDep2.connect();
                return true;
            }
            return true;
        }

        public final byte getCLA() {
            return NfcUtility.g;
        }

        public final byte getCLA_SECURE() {
            return NfcUtility.h;
        }

        @NotNull
        public final byte[] getGET_MORE() {
            return NfcUtility.f;
        }

        @NotNull
        public final byte[] getGET_MORE_SECURE() {
            return NfcUtility.e;
        }

        @Nullable
        public final IsoDep getIsoDep() {
            return NfcUtility.b;
        }

        public final byte getMORE_RESPONSE() {
            return NfcUtility.d;
        }

        @NotNull
        public final byte[] getResponseCode(@Nullable byte[] bArr) {
            byte[] bArr2 = new byte[2];
            if (bArr != null) {
                System.arraycopy(bArr, bArr.length - 2, bArr2, 0, 2);
                Intrinsics.checkNotNullExpressionValue(HexUtils.bin2hex(bArr2), "bin2hex(respCode)");
            }
            return bArr2;
        }

        @Nullable
        public final byte[] getResponseData(@Nullable byte[] bArr) {
            return (bArr == null || bArr.length <= 2) ? bArr : ArraysKt___ArraysJvmKt.copyOfRange(bArr, 0, bArr.length - 2);
        }

        @NotNull
        public final byte[] getSUCCESS_RESPONSE() {
            return NfcUtility.c;
        }

        @NotNull
        public final String getTAG() {
            return NfcUtility.f6013a;
        }

        public final boolean hasMoreResponse(@NotNull byte[] responseCode) {
            Intrinsics.checkNotNullParameter(responseCode, "responseCode");
            return responseCode[0] == getMORE_RESPONSE();
        }

        public final boolean hasNFC(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return NfcAdapter.getDefaultAdapter(context) != null;
        }

        public final boolean isConnected(@NotNull Intent intent) throws Exception {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
            if (tag != null) {
                setIsoDep(IsoDep.get(tag));
            }
            if (getIsoDep() == null) {
                return false;
            }
            IsoDep isoDep = getIsoDep();
            Intrinsics.checkNotNull(isoDep);
            return isoDep.isConnected();
        }

        public final boolean isNFCOn(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return NfcAdapter.getDefaultAdapter(context).isEnabled();
        }

        @Nullable
        public final String parseSerialNumber(@Nullable String str) {
            byte[] bArr = new byte[4];
            System.arraycopy(HexUtils.hex2bin(str), 15, bArr, 0, 4);
            return HexUtils.bin2hex(bArr);
        }

        public final void setCLA(byte b) {
            NfcUtility.g = b;
        }

        public final void setCLA_SECURE(byte b) {
            NfcUtility.h = b;
        }

        public final void setGET_MORE(@NotNull byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<set-?>");
            NfcUtility.f = bArr;
        }

        public final void setGET_MORE_SECURE(@NotNull byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<set-?>");
            NfcUtility.e = bArr;
        }

        public final void setIsoDep(@Nullable IsoDep isoDep) {
            NfcUtility.b = isoDep;
        }

        public final void setMORE_RESPONSE(byte b) {
            NfcUtility.d = b;
        }

        public final void setSUCCESS_RESPONSE(@NotNull byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<set-?>");
            NfcUtility.c = bArr;
        }

        public final boolean startListeningNFC(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(activity);
            if (defaultAdapter != null) {
                Intent intent = new Intent(activity, activity.getClass());
                intent.addFlags(PKIFailureInfo.duplicateCertReq);
                defaultAdapter.enableForegroundDispatch(activity, PendingIntent.getActivity(activity, 0, intent, 301989888), new IntentFilter[]{new IntentFilter("android.nfc.action.TECH_DISCOVERED")}, new String[][]{new String[]{IsoDep.class.getName()}});
                return true;
            }
            return false;
        }

        public final boolean stopListeningNFC() {
            if (getIsoDep() != null) {
                IsoDep isoDep = getIsoDep();
                Intrinsics.checkNotNull(isoDep);
                isoDep.close();
                return true;
            }
            return false;
        }

        @Nullable
        public final String transmit(@NotNull String apdu) throws Exception {
            Intrinsics.checkNotNullParameter(apdu, "apdu");
            byte[] stringToBin = HexUtils.stringToBin(apdu);
            Intrinsics.checkNotNullExpressionValue(stringToBin, "stringToBin(apdu)");
            IsoDep isoDep = getIsoDep();
            Intrinsics.checkNotNull(isoDep);
            byte[] transceive = isoDep.transceive(stringToBin);
            String bin2hex = HexUtils.bin2hex(transceive);
            byte[] responseCode = getResponseCode(transceive);
            while (hasMoreResponse(responseCode)) {
                a("**********RECEIVED RESPONSE: " + bin2hex);
                if (stringToBin[0] == getCLA()) {
                    a("**********SENDING MORE COMMAND: " + HexUtils.bin2hex(stringToBin));
                    IsoDep isoDep2 = getIsoDep();
                    Intrinsics.checkNotNull(isoDep2);
                    transceive = isoDep2.transceive(getGET_MORE());
                } else if (stringToBin[0] == getCLA_SECURE()) {
                    a("**********SENDING MORE SECURE COMMAND: " + HexUtils.bin2hex(stringToBin));
                    IsoDep isoDep3 = getIsoDep();
                    Intrinsics.checkNotNull(isoDep3);
                    transceive = isoDep3.transceive(getGET_MORE_SECURE());
                }
                a("**********RECEIVED MORE RESPONSE: " + HexUtils.bin2hex(transceive));
                responseCode = getResponseCode(transceive);
                bin2hex = HexUtils.bin2hex(getResponseData(HexUtils.hex2bin(bin2hex))) + HexUtils.bin2hex(transceive);
            }
            return bin2hex;
        }
    }
}
