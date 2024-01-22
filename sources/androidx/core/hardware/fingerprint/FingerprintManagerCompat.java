package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;
@Deprecated
/* loaded from: classes.dex */
public class FingerprintManagerCompat {

    /* renamed from: a  reason: collision with root package name */
    public final Context f1055a;

    /* loaded from: classes.dex */
    public static abstract class AuthenticationCallback {
        public void onAuthenticationError(int i, CharSequence charSequence) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int i, CharSequence charSequence) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        }
    }

    /* loaded from: classes.dex */
    public static final class AuthenticationResult {

        /* renamed from: a  reason: collision with root package name */
        public final CryptoObject f1056a;

        public AuthenticationResult(CryptoObject cryptoObject) {
            this.f1056a = cryptoObject;
        }

        public CryptoObject getCryptoObject() {
            return this.f1056a;
        }
    }

    /* loaded from: classes.dex */
    public class a extends FingerprintManager.AuthenticationCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AuthenticationCallback f1058a;

        public a(AuthenticationCallback authenticationCallback) {
            this.f1058a = authenticationCallback;
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationError(int i, CharSequence charSequence) {
            this.f1058a.onAuthenticationError(i, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationFailed() {
            this.f1058a.onAuthenticationFailed();
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationHelp(int i, CharSequence charSequence) {
            this.f1058a.onAuthenticationHelp(i, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
            this.f1058a.onAuthenticationSucceeded(new AuthenticationResult(FingerprintManagerCompat.b(b.b(authenticationResult))));
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class b {
        @RequiresPermission("android.permission.USE_FINGERPRINT")
        @DoNotInline
        public static void a(Object obj, Object obj2, CancellationSignal cancellationSignal, int i, Object obj3, Handler handler) {
            ((FingerprintManager) obj).authenticate((FingerprintManager.CryptoObject) obj2, cancellationSignal, i, (FingerprintManager.AuthenticationCallback) obj3, handler);
        }

        @DoNotInline
        public static FingerprintManager.CryptoObject b(Object obj) {
            return ((FingerprintManager.AuthenticationResult) obj).getCryptoObject();
        }

        @DoNotInline
        public static FingerprintManager c(Context context) {
            int i = Build.VERSION.SDK_INT;
            if (i == 23) {
                return (FingerprintManager) context.getSystemService(FingerprintManager.class);
            }
            if (i <= 23 || !context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
                return null;
            }
            return (FingerprintManager) context.getSystemService(FingerprintManager.class);
        }

        @RequiresPermission("android.permission.USE_FINGERPRINT")
        @DoNotInline
        public static boolean d(Object obj) {
            return ((FingerprintManager) obj).hasEnrolledFingerprints();
        }

        @RequiresPermission("android.permission.USE_FINGERPRINT")
        @DoNotInline
        public static boolean e(Object obj) {
            return ((FingerprintManager) obj).isHardwareDetected();
        }

        @DoNotInline
        public static CryptoObject f(Object obj) {
            FingerprintManager.CryptoObject cryptoObject = (FingerprintManager.CryptoObject) obj;
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                return new CryptoObject(cryptoObject.getMac());
            }
            return null;
        }

        @DoNotInline
        public static FingerprintManager.CryptoObject g(CryptoObject cryptoObject) {
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.getMac());
            }
            return null;
        }
    }

    public FingerprintManagerCompat(Context context) {
        this.f1055a = context;
    }

    @Nullable
    @RequiresApi(23)
    public static FingerprintManager a(@NonNull Context context) {
        return b.c(context);
    }

    @RequiresApi(23)
    public static CryptoObject b(FingerprintManager.CryptoObject cryptoObject) {
        return b.f(cryptoObject);
    }

    @RequiresApi(23)
    public static FingerprintManager.AuthenticationCallback c(AuthenticationCallback authenticationCallback) {
        return new a(authenticationCallback);
    }

    @RequiresApi(23)
    public static FingerprintManager.CryptoObject d(CryptoObject cryptoObject) {
        return b.g(cryptoObject);
    }

    @NonNull
    public static FingerprintManagerCompat from(@NonNull Context context) {
        return new FingerprintManagerCompat(context);
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public void authenticate(@Nullable CryptoObject cryptoObject, int i, @Nullable androidx.core.os.CancellationSignal cancellationSignal, @NonNull AuthenticationCallback authenticationCallback, @Nullable Handler handler) {
        FingerprintManager a2;
        if (Build.VERSION.SDK_INT < 23 || (a2 = a(this.f1055a)) == null) {
            return;
        }
        b.a(a2, d(cryptoObject), cancellationSignal != null ? (CancellationSignal) cancellationSignal.getCancellationSignalObject() : null, i, c(authenticationCallback), handler);
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean hasEnrolledFingerprints() {
        FingerprintManager a2;
        return Build.VERSION.SDK_INT >= 23 && (a2 = a(this.f1055a)) != null && b.d(a2);
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean isHardwareDetected() {
        FingerprintManager a2;
        return Build.VERSION.SDK_INT >= 23 && (a2 = a(this.f1055a)) != null && b.e(a2);
    }

    /* loaded from: classes.dex */
    public static class CryptoObject {

        /* renamed from: a  reason: collision with root package name */
        public final Signature f1057a;
        public final Cipher b;
        public final Mac c;

        public CryptoObject(@NonNull Signature signature) {
            this.f1057a = signature;
            this.b = null;
            this.c = null;
        }

        @Nullable
        public Cipher getCipher() {
            return this.b;
        }

        @Nullable
        public Mac getMac() {
            return this.c;
        }

        @Nullable
        public Signature getSignature() {
            return this.f1057a;
        }

        public CryptoObject(@NonNull Cipher cipher) {
            this.b = cipher;
            this.f1057a = null;
            this.c = null;
        }

        public CryptoObject(@NonNull Mac mac) {
            this.c = mac;
            this.b = null;
            this.f1057a = null;
        }
    }
}
