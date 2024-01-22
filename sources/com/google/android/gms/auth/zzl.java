package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.RequiresPermission;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzbw;
import com.google.android.gms.internal.auth.zzby;
import com.google.android.gms.internal.auth.zzdc;
import com.google.android.gms.internal.auth.zzh;
import com.google.android.gms.internal.auth.zzhs;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
@ShowFirstParty
/* loaded from: classes6.dex */
public class zzl {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    @ShowFirstParty
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";
    @ShowFirstParty
    public static final String[] zza = {"com.google", "com.google.work", "cn.google"};
    @ShowFirstParty
    @SuppressLint({"InlinedApi"})
    public static final String zzb = "androidPackageName";

    /* renamed from: a  reason: collision with root package name */
    public static final ComponentName f8219a = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    public static final Logger b = zzd.zza("GoogleAuthUtil");

    public static /* synthetic */ TokenData a(Account account, String str, Bundle bundle, IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle zze = com.google.android.gms.internal.auth.zze.zzb(iBinder).zze(account, str, bundle);
        if (zze != null) {
            return e(zze);
        }
        throw new IOException("Service call returned null");
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        */
    public static /* bridge */ /* synthetic */ java.lang.Object c(java.lang.Object r0) {
        /*
            h(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.zzl.c(java.lang.Object):java.lang.Object");
    }

    public static void clearToken(Context context, String str) throws GoogleAuthException, IOException {
        zze(context, str, 0L);
    }

    public static void d(Intent intent) {
        if (intent != null) {
            try {
                Intent.parseUri(intent.toUri(1), 1);
                return;
            } catch (URISyntaxException unused) {
                throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
            }
        }
        throw new IllegalArgumentException("Callback cannot be null.");
    }

    public static TokenData e(Bundle bundle) throws GoogleAuthException, IOException {
        TokenData tokenData;
        Parcelable.Creator<TokenData> creator = TokenData.CREATOR;
        ClassLoader classLoader = TokenData.class.getClassLoader();
        if (classLoader != null) {
            bundle.setClassLoader(classLoader);
        }
        Bundle bundle2 = bundle.getBundle("tokenDetails");
        if (bundle2 == null) {
            tokenData = null;
        } else {
            if (classLoader != null) {
                bundle2.setClassLoader(classLoader);
            }
            tokenData = (TokenData) bundle2.getParcelable("TokenData");
        }
        if (tokenData != null) {
            return tokenData;
        }
        String string = bundle.getString(ErrorConstants.GENERIC_ERROR);
        Preconditions.checkNotNull(string);
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzby zza2 = zzby.zza(string);
        if (!zzby.zzb(zza2)) {
            if (!zzby.NETWORK_ERROR.equals(zza2) && !zzby.SERVICE_UNAVAILABLE.equals(zza2) && !zzby.INTNERNAL_ERROR.equals(zza2) && !zzby.AUTH_SECURITY_ERROR.equals(zza2) && !zzby.ACCOUNT_NOT_PRESENT.equals(zza2)) {
                throw new GoogleAuthException(string);
            }
            throw new IOException(string);
        }
        b.w("isUserRecoverableError status: ".concat(String.valueOf(zza2)), new Object[0]);
        throw new UserRecoverableAuthException(string, intent);
    }

    public static Object f(Context context, ComponentName componentName, d dVar, long j) throws IOException, GoogleAuthException {
        BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
        GmsClientSupervisor gmsClientSupervisor = GmsClientSupervisor.getInstance(context);
        try {
            try {
                if (gmsClientSupervisor.bindService(componentName, blockingServiceConnection, "GoogleAuthUtil")) {
                    try {
                        return dVar.zza(blockingServiceConnection.getService());
                    } catch (RemoteException | InterruptedException | TimeoutException e) {
                        Log.i("GoogleAuthUtil", "Error on service connection.", e);
                        throw new IOException("Error on service connection.", e);
                    }
                }
                throw new IOException("Could not bind to service.");
            } finally {
                gmsClientSupervisor.unbindService(componentName, blockingServiceConnection, "GoogleAuthUtil");
            }
        } catch (SecurityException e2) {
            Log.w("GoogleAuthUtil", String.format("SecurityException while bind to auth service: %s", e2.getMessage()));
            throw new IOException("SecurityException while binding to Auth service.", e2);
        }
    }

    public static Object g(Task task, String str) throws IOException, ApiException {
        try {
            return Tasks.await(task);
        } catch (InterruptedException e) {
            String format = String.format("Interrupted while waiting for the task of %s to finish.", str);
            b.w(format, new Object[0]);
            throw new IOException(format, e);
        } catch (CancellationException e2) {
            String format2 = String.format("Canceled while waiting for the task of %s to finish.", str);
            b.w(format2, new Object[0]);
            throw new IOException(format2, e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof ApiException) {
                throw ((ApiException) cause);
            }
            String format3 = String.format("Unable to get a result for %s due to ExecutionException.", str);
            b.w(format3, new Object[0]);
            throw new IOException(format3, e3);
        }
    }

    public static List<AccountChangeEvent> getAccountChangeEvents(Context context, int i, String str) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(str, "accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        i(context, 8400000);
        AccountChangeEventsRequest accountChangeEventsRequest = new AccountChangeEventsRequest();
        accountChangeEventsRequest.setAccountName(str);
        accountChangeEventsRequest.setEventIndex(i);
        zzdc.zze(context);
        if (zzhs.zzd() && m(context)) {
            try {
                AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) g(zzh.zza(context).zzb(accountChangeEventsRequest), "account change events retrieval");
                h(accountChangeEventsResponse);
                return accountChangeEventsResponse.getEvents();
            } catch (ApiException e) {
                j(e, "account change events retrieval");
            }
        }
        return (List) f(context, f8219a, new b(accountChangeEventsRequest), 0L);
    }

    public static String getAccountId(Context context, String str) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(str, "accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        i(context, 8400000);
        return getToken(context, str, "^^_account_id_^^", new Bundle());
    }

    public static String getToken(Context context, Account account, String str) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, str, new Bundle());
    }

    public static Object h(Object obj) throws IOException {
        if (obj != null) {
            return obj;
        }
        b.w("Service call returned null.", new Object[0]);
        throw new IOException("Service unavailable.");
    }

    public static void i(Context context, int i) throws GoogleAuthException {
        try {
            GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context.getApplicationContext(), i);
        } catch (GooglePlayServicesIncorrectManifestValueException e) {
            e = e;
            throw new GoogleAuthException(e.getMessage(), e);
        } catch (GooglePlayServicesNotAvailableException e2) {
            e = e2;
            throw new GoogleAuthException(e.getMessage(), e);
        } catch (GooglePlayServicesRepairableException e3) {
            throw new GooglePlayServicesAvailabilityException(e3.getConnectionStatusCode(), e3.getMessage(), e3.getIntent());
        }
    }

    @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
    @Deprecated
    public static void invalidateToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken("com.google", str);
    }

    public static void j(ApiException apiException, String str) {
        b.w("%s failed via GoogleAuthServiceClient, falling back to previous approach:\n%s", str, Log.getStackTraceString(apiException));
    }

    public static void k(Context context, Bundle bundle) {
        String str = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", str);
        String str2 = zzb;
        if (TextUtils.isEmpty(bundle.getString(str2))) {
            bundle.putString(str2, str);
        }
        bundle.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
    }

    public static void l(Account account) {
        if (account != null) {
            if (!TextUtils.isEmpty(account.name)) {
                String[] strArr = zza;
                for (int i = 0; i < 3; i++) {
                    if (strArr[i].equals(account.type)) {
                        return;
                    }
                }
                throw new IllegalArgumentException("Account type not supported");
            }
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
        throw new IllegalArgumentException("Account cannot be null");
    }

    public static boolean m(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context, 17895000) != 0) {
            return false;
        }
        List<String> zzl = zzhs.zzb().zzl();
        String str = context.getApplicationInfo().packageName;
        for (String str2 : zzl) {
            if (str2.equals(str)) {
                return false;
            }
        }
        return true;
    }

    @ShowFirstParty
    @TargetApi(23)
    public static Bundle removeAccount(Context context, final Account account) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context);
        l(account);
        i(context, 8400000);
        zzdc.zze(context);
        if (zzhs.zze() && m(context)) {
            try {
                Bundle bundle = (Bundle) g(zzh.zza(context).zzd(account), "account removal");
                h(bundle);
                return bundle;
            } catch (ApiException e) {
                j(e, "account removal");
            }
        }
        return (Bundle) f(context, f8219a, new d() { // from class: com.google.android.gms.auth.zzg
            @Override // com.google.android.gms.auth.d
            public final Object zza(IBinder iBinder) {
                Bundle zzf = com.google.android.gms.internal.auth.zze.zzb(iBinder).zzf(account);
                if (zzf != null) {
                    return zzf;
                }
                throw new IOException("Service call returned null.");
            }
        }, 0L);
    }

    @TargetApi(26)
    public static Boolean requestGoogleAccountsAccess(Context context) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context);
        i(context, 11400000);
        String str = context.getApplicationInfo().packageName;
        zzdc.zze(context);
        if (zzhs.zze() && m(context)) {
            try {
                Bundle bundle = (Bundle) g(zzh.zza(context).zze(str), "google accounts access request");
                String string = bundle.getString(ErrorConstants.GENERIC_ERROR);
                Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
                zzby zza2 = zzby.zza(string);
                if (zzby.SUCCESS.equals(zza2)) {
                    return Boolean.TRUE;
                }
                if (zzby.zzb(zza2)) {
                    Logger logger = b;
                    String valueOf = String.valueOf(zza2);
                    logger.w("isUserRecoverableError status: " + valueOf, new Object[0]);
                    throw new UserRecoverableAuthException(string, intent);
                }
                throw new GoogleAuthException(string);
            } catch (ApiException e) {
                j(e, "google accounts access request");
            }
        }
        return (Boolean) f(context, f8219a, new c(str), 0L);
    }

    public static TokenData zza(Context context, final Account account, final String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        final Bundle bundle2;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        Preconditions.checkNotEmpty(str, "Scope cannot be empty or null.");
        l(account);
        i(context, 8400000);
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = new Bundle(bundle);
        }
        k(context, bundle2);
        zzdc.zze(context);
        if (zzhs.zze() && m(context)) {
            try {
                Bundle bundle3 = (Bundle) g(zzh.zza(context).zzc(account, str, bundle2), "token retrieval");
                h(bundle3);
                return e(bundle3);
            } catch (ApiException e) {
                j(e, "token retrieval");
            }
        }
        return (TokenData) f(context, f8219a, new d() { // from class: com.google.android.gms.auth.zzf
            @Override // com.google.android.gms.auth.d
            public final Object zza(IBinder iBinder) {
                return zzl.a(account, str, bundle2, iBinder);
            }
        }, 0L);
    }

    @ShowFirstParty
    public static void zze(Context context, String str, long j) throws GoogleAuthException, IOException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        i(context, 8400000);
        Bundle bundle = new Bundle();
        k(context, bundle);
        zzdc.zze(context);
        if (zzhs.zze() && m(context)) {
            com.google.android.gms.internal.auth.zzg zza2 = zzh.zza(context);
            zzbw zzbwVar = new zzbw();
            zzbwVar.zza(str);
            try {
                g(zza2.zza(zzbwVar), "clear token");
                return;
            } catch (ApiException e) {
                j(e, "clear token");
            }
        }
        f(context, f8219a, new a(str, bundle), 0L);
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        l(account);
        return zza(context, account, str, bundle).zza();
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2, bundle);
    }
}
