package com.google.android.youtube.player;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.youtube.player.internal.ab;
import com.google.android.youtube.player.internal.y;
/* loaded from: classes10.dex */
public enum YouTubeInitializationResult {
    SUCCESS,
    INTERNAL_ERROR,
    UNKNOWN_ERROR,
    SERVICE_MISSING,
    SERVICE_VERSION_UPDATE_REQUIRED,
    SERVICE_DISABLED,
    SERVICE_INVALID,
    ERROR_CONNECTING_TO_SERVICE,
    CLIENT_LIBRARY_UPDATE_REQUIRED,
    NETWORK_ERROR,
    DEVELOPER_KEY_INVALID,
    INVALID_APPLICATION_SIGNATURE;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10480a;

        static {
            int[] iArr = new int[YouTubeInitializationResult.values().length];
            f10480a = iArr;
            try {
                iArr[YouTubeInitializationResult.SERVICE_MISSING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10480a[YouTubeInitializationResult.SERVICE_DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10480a[YouTubeInitializationResult.SERVICE_VERSION_UPDATE_REQUIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class b implements DialogInterface.OnClickListener {
        public final Activity h;
        public final Intent i;
        public final int j;

        public b(Activity activity, Intent intent, int i) {
            this.h = (Activity) ab.a(activity);
            this.i = (Intent) ab.a(intent);
            this.j = ((Integer) ab.a(Integer.valueOf(i))).intValue();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            try {
                this.h.startActivityForResult(this.i, this.j);
                dialogInterface.dismiss();
            } catch (ActivityNotFoundException e) {
                y.a("Can't perform resolution for YouTubeInitalizationError", e);
            }
        }
    }

    public final Dialog getErrorDialog(Activity activity, int i) {
        return getErrorDialog(activity, i, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.app.Dialog getErrorDialog(android.app.Activity r7, int r8, android.content.DialogInterface.OnCancelListener r9) {
        /*
            r6 = this;
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            r0.<init>(r7)
            if (r9 == 0) goto La
            r0.setOnCancelListener(r9)
        La:
            int[] r9 = com.google.android.youtube.player.YouTubeInitializationResult.a.f10480a
            int r1 = r6.ordinal()
            r1 = r9[r1]
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == r4) goto L26
            if (r1 == r3) goto L1d
            if (r1 == r2) goto L26
            r1 = 0
            goto L2e
        L1d:
            java.lang.String r1 = com.google.android.youtube.player.internal.z.a(r7)
            android.content.Intent r1 = com.google.android.youtube.player.internal.z.a(r1)
            goto L2e
        L26:
            java.lang.String r1 = com.google.android.youtube.player.internal.z.a(r7)
            android.content.Intent r1 = com.google.android.youtube.player.internal.z.b(r1)
        L2e:
            com.google.android.youtube.player.YouTubeInitializationResult$b r5 = new com.google.android.youtube.player.YouTubeInitializationResult$b
            r5.<init>(r7, r1, r8)
            com.google.android.youtube.player.internal.m r8 = new com.google.android.youtube.player.internal.m
            r8.<init>(r7)
            int r7 = r6.ordinal()
            r7 = r9[r7]
            if (r7 == r4) goto L8b
            if (r7 == r3) goto L7c
            if (r7 == r2) goto L65
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Unexpected errorReason: "
            java.lang.String r9 = r6.name()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            int r0 = r9.length()
            if (r0 == 0) goto L5b
            java.lang.String r8 = r8.concat(r9)
            goto L61
        L5b:
            java.lang.String r9 = new java.lang.String
            r9.<init>(r8)
            r8 = r9
        L61:
            r7.<init>(r8)
            throw r7
        L65:
            java.lang.String r7 = r8.h
            android.app.AlertDialog$Builder r7 = r0.setTitle(r7)
            java.lang.String r9 = r8.i
            android.app.AlertDialog$Builder r7 = r7.setMessage(r9)
            java.lang.String r8 = r8.j
        L73:
            android.app.AlertDialog$Builder r7 = r7.setPositiveButton(r8, r5)
            android.app.AlertDialog r7 = r7.create()
            return r7
        L7c:
            java.lang.String r7 = r8.e
            android.app.AlertDialog$Builder r7 = r0.setTitle(r7)
            java.lang.String r9 = r8.f
            android.app.AlertDialog$Builder r7 = r7.setMessage(r9)
            java.lang.String r8 = r8.g
            goto L73
        L8b:
            java.lang.String r7 = r8.b
            android.app.AlertDialog$Builder r7 = r0.setTitle(r7)
            java.lang.String r9 = r8.c
            android.app.AlertDialog$Builder r7 = r7.setMessage(r9)
            java.lang.String r8 = r8.d
            goto L73
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.youtube.player.YouTubeInitializationResult.getErrorDialog(android.app.Activity, int, android.content.DialogInterface$OnCancelListener):android.app.Dialog");
    }

    public final boolean isUserRecoverableError() {
        int i = a.f10480a[ordinal()];
        return i == 1 || i == 2 || i == 3;
    }
}
