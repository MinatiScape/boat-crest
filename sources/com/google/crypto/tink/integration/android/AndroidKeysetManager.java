package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.KeysetManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.ProviderException;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class AndroidKeysetManager {
    public static final String d = "AndroidKeysetManager";

    /* renamed from: a  reason: collision with root package name */
    public final KeysetWriter f10851a;
    public final Aead b;
    @GuardedBy("this")
    public KeysetManager c;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10853a;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            f10853a = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10853a[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10853a[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10853a[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public /* synthetic */ AndroidKeysetManager(Builder builder, a aVar) throws GeneralSecurityException, IOException {
        this(builder);
    }

    public static /* synthetic */ boolean b() {
        return e();
    }

    public static KeyTemplate.OutputPrefixType d(OutputPrefixType outputPrefixType) {
        int i = a.f10853a[outputPrefixType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return KeyTemplate.OutputPrefixType.CRUNCHY;
                    }
                    throw new IllegalArgumentException("Unknown output prefix type");
                }
                return KeyTemplate.OutputPrefixType.RAW;
            }
            return KeyTemplate.OutputPrefixType.LEGACY;
        }
        return KeyTemplate.OutputPrefixType.TINK;
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT >= 23;
    }

    @GuardedBy("this")
    @Deprecated
    public synchronized AndroidKeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager add = this.c.add(keyTemplate);
        this.c = add;
        g(add);
        return this;
    }

    public synchronized AndroidKeysetManager delete(int i) throws GeneralSecurityException {
        KeysetManager delete = this.c.delete(i);
        this.c = delete;
        g(delete);
        return this;
    }

    public synchronized AndroidKeysetManager destroy(int i) throws GeneralSecurityException {
        KeysetManager destroy = this.c.destroy(i);
        this.c = destroy;
        g(destroy);
        return this;
    }

    public synchronized AndroidKeysetManager disable(int i) throws GeneralSecurityException {
        KeysetManager disable = this.c.disable(i);
        this.c = disable;
        g(disable);
        return this;
    }

    public synchronized AndroidKeysetManager enable(int i) throws GeneralSecurityException {
        KeysetManager enable = this.c.enable(i);
        this.c = enable;
        g(enable);
        return this;
    }

    public final boolean f() {
        return this.b != null && e();
    }

    public final void g(KeysetManager keysetManager) throws GeneralSecurityException {
        try {
            if (f()) {
                keysetManager.getKeysetHandle().write(this.f10851a, this.b);
            } else {
                CleartextKeysetHandle.write(keysetManager.getKeysetHandle(), this.f10851a);
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return this.c.getKeysetHandle();
    }

    public synchronized boolean isUsingKeystore() {
        return f();
    }

    @Deprecated
    public synchronized AndroidKeysetManager promote(int i) throws GeneralSecurityException {
        return setPrimary(i);
    }

    @Deprecated
    public synchronized AndroidKeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager rotate = this.c.rotate(keyTemplate);
        this.c = rotate;
        g(rotate);
        return this;
    }

    public synchronized AndroidKeysetManager setPrimary(int i) throws GeneralSecurityException {
        KeysetManager primary = this.c.setPrimary(i);
        this.c = primary;
        g(primary);
        return this;
    }

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public KeysetReader f10852a = null;
        public KeysetWriter b = null;
        public String c = null;
        public Aead d = null;
        public boolean e = true;
        public KeyTemplate f = null;
        public KeyStore g = null;
        @GuardedBy("this")
        public KeysetManager h;

        public synchronized AndroidKeysetManager build() throws GeneralSecurityException, IOException {
            if (this.c != null) {
                this.d = f();
            }
            this.h = e();
            return new AndroidKeysetManager(this, null);
        }

        public final KeysetManager d() throws GeneralSecurityException, IOException {
            Aead aead = this.d;
            if (aead != null) {
                try {
                    return KeysetManager.withKeysetHandle(KeysetHandle.read(this.f10852a, aead));
                } catch (InvalidProtocolBufferException | GeneralSecurityException e) {
                    Log.w(AndroidKeysetManager.d, "cannot decrypt keyset: ", e);
                }
            }
            return KeysetManager.withKeysetHandle(CleartextKeysetHandle.read(this.f10852a));
        }

        @Deprecated
        public Builder doNotUseKeystore() {
            this.c = null;
            this.e = false;
            return this;
        }

        public final KeysetManager e() throws GeneralSecurityException, IOException {
            try {
                return d();
            } catch (FileNotFoundException e) {
                Log.w(AndroidKeysetManager.d, "keyset not found, will generate a new one", e);
                if (this.f != null) {
                    KeysetManager add = KeysetManager.withEmptyKeyset().add(this.f);
                    KeysetManager primary = add.setPrimary(add.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
                    if (this.d != null) {
                        primary.getKeysetHandle().write(this.b, this.d);
                    } else {
                        CleartextKeysetHandle.write(primary.getKeysetHandle(), this.b);
                    }
                    return primary;
                }
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
        }

        public final Aead f() throws GeneralSecurityException {
            AndroidKeystoreKmsClient androidKeystoreKmsClient;
            if (!AndroidKeysetManager.b()) {
                Log.w(AndroidKeysetManager.d, "Android Keystore requires at least Android M");
                return null;
            }
            if (this.g != null) {
                androidKeystoreKmsClient = new AndroidKeystoreKmsClient.Builder().setKeyStore(this.g).build();
            } else {
                androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
            }
            boolean b = androidKeystoreKmsClient.b(this.c);
            if (!b) {
                try {
                    AndroidKeystoreKmsClient.generateNewAeadKey(this.c);
                } catch (GeneralSecurityException | ProviderException e) {
                    Log.w(AndroidKeysetManager.d, "cannot use Android Keystore, it'll be disabled", e);
                    return null;
                }
            }
            try {
                return androidKeystoreKmsClient.getAead(this.c);
            } catch (GeneralSecurityException | ProviderException e2) {
                if (!b) {
                    Log.w(AndroidKeysetManager.d, "cannot use Android Keystore, it'll be disabled", e2);
                    return null;
                }
                throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.c), e2);
            }
        }

        @Deprecated
        public Builder withKeyTemplate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
            this.f = KeyTemplate.create(keyTemplate.getTypeUrl(), keyTemplate.getValue().toByteArray(), AndroidKeysetManager.d(keyTemplate.getOutputPrefixType()));
            return this;
        }

        public Builder withMasterKeyUri(String str) {
            if (str.startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                if (this.e) {
                    this.c = str;
                    return this;
                }
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            throw new IllegalArgumentException("key URI must start with android-keystore://");
        }

        public Builder withSharedPref(Context context, String str, String str2) throws IOException {
            if (context != null) {
                if (str != null) {
                    this.f10852a = new SharedPrefKeysetReader(context, str, str2);
                    this.b = new SharedPrefKeysetWriter(context, str, str2);
                    return this;
                }
                throw new IllegalArgumentException("need a keyset name");
            }
            throw new IllegalArgumentException("need an Android context");
        }

        public Builder withKeyTemplate(KeyTemplate keyTemplate) {
            this.f = keyTemplate;
            return this;
        }
    }

    public AndroidKeysetManager(Builder builder) throws GeneralSecurityException, IOException {
        this.f10851a = builder.b;
        this.b = builder.d;
        this.c = builder.h;
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager add = this.c.add(keyTemplate);
        this.c = add;
        g(add);
        return this;
    }
}
