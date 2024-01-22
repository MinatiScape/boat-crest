package androidx.security.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.daead.AesSivKeyManager;
import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.integration.android.AndroidKeysetManager;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.google.crypto.tink.subtle.Base64;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class EncryptedSharedPreferences implements SharedPreferences {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f1678a;
    public final List<SharedPreferences.OnSharedPreferenceChangeListener> b = new ArrayList();
    public final String c;
    public final Aead d;
    public final DeterministicAead e;

    /* loaded from: classes.dex */
    public enum PrefKeyEncryptionScheme {
        AES256_SIV(AesSivKeyManager.aes256SivTemplate());
        
        private final KeyTemplate mDeterministicAeadKeyTemplate;

        PrefKeyEncryptionScheme(KeyTemplate keyTemplate) {
            this.mDeterministicAeadKeyTemplate = keyTemplate;
        }

        public KeyTemplate getKeyTemplate() {
            return this.mDeterministicAeadKeyTemplate;
        }
    }

    /* loaded from: classes.dex */
    public enum PrefValueEncryptionScheme {
        AES256_GCM(AesGcmKeyManager.aes256GcmTemplate());
        
        private final KeyTemplate mAeadKeyTemplate;

        PrefValueEncryptionScheme(KeyTemplate keyTemplate) {
            this.mAeadKeyTemplate = keyTemplate;
        }

        public KeyTemplate getKeyTemplate() {
            return this.mAeadKeyTemplate;
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1679a;

        static {
            int[] iArr = new int[c.values().length];
            f1679a = iArr;
            try {
                iArr[c.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1679a[c.INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1679a[c.LONG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1679a[c.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1679a[c.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1679a[c.STRING_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements SharedPreferences.Editor {

        /* renamed from: a  reason: collision with root package name */
        public final EncryptedSharedPreferences f1680a;
        public final SharedPreferences.Editor b;
        public AtomicBoolean d = new AtomicBoolean(false);
        public final List<String> c = new CopyOnWriteArrayList();

        public b(EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences.Editor editor) {
            this.f1680a = encryptedSharedPreferences;
            this.b = editor;
        }

        public final void a() {
            if (this.d.getAndSet(false)) {
                for (String str : this.f1680a.getAll().keySet()) {
                    if (!this.c.contains(str) && !this.f1680a.e(str)) {
                        this.b.remove(this.f1680a.b(str));
                    }
                }
            }
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            a();
            this.b.apply();
            b();
            this.c.clear();
        }

        public final void b() {
            for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : this.f1680a.b) {
                for (String str : this.c) {
                    onSharedPreferenceChangeListener.onSharedPreferenceChanged(this.f1680a, str);
                }
            }
        }

        public final void c(String str, byte[] bArr) {
            if (!this.f1680a.e(str)) {
                this.c.add(str);
                if (str == null) {
                    str = "__NULL__";
                }
                try {
                    Pair<String, String> c = this.f1680a.c(str, bArr);
                    this.b.putString((String) c.first, (String) c.second);
                    return;
                } catch (GeneralSecurityException e) {
                    throw new SecurityException("Could not encrypt data: " + e.getMessage(), e);
                }
            }
            throw new SecurityException(str + " is a reserved key for the encryption keyset.");
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor clear() {
            this.d.set(true);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            a();
            try {
                return this.b.commit();
            } finally {
                b();
                this.c.clear();
            }
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor putBoolean(@Nullable String str, boolean z) {
            ByteBuffer allocate = ByteBuffer.allocate(5);
            allocate.putInt(c.BOOLEAN.getId());
            allocate.put(z ? (byte) 1 : (byte) 0);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor putFloat(@Nullable String str, float f) {
            ByteBuffer allocate = ByteBuffer.allocate(8);
            allocate.putInt(c.FLOAT.getId());
            allocate.putFloat(f);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor putInt(@Nullable String str, int i) {
            ByteBuffer allocate = ByteBuffer.allocate(8);
            allocate.putInt(c.INT.getId());
            allocate.putInt(i);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor putLong(@Nullable String str, long j) {
            ByteBuffer allocate = ByteBuffer.allocate(12);
            allocate.putInt(c.LONG.getId());
            allocate.putLong(j);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor putString(@Nullable String str, @Nullable String str2) {
            if (str2 == null) {
                str2 = "__NULL__";
            }
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            ByteBuffer allocate = ByteBuffer.allocate(length + 8);
            allocate.putInt(c.STRING.getId());
            allocate.putInt(length);
            allocate.put(bytes);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor putStringSet(@Nullable String str, @Nullable Set<String> set) {
            if (set == null) {
                set = new ArraySet<>();
                set.add("__NULL__");
            }
            ArrayList<byte[]> arrayList = new ArrayList(set.size());
            int size = set.size() * 4;
            for (String str2 : set) {
                byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
                arrayList.add(bytes);
                size += bytes.length;
            }
            ByteBuffer allocate = ByteBuffer.allocate(size + 4);
            allocate.putInt(c.STRING_SET.getId());
            for (byte[] bArr : arrayList) {
                allocate.putInt(bArr.length);
                allocate.put(bArr);
            }
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        @NonNull
        public SharedPreferences.Editor remove(@Nullable String str) {
            if (!this.f1680a.e(str)) {
                this.b.remove(this.f1680a.b(str));
                this.c.remove(str);
                return this;
            }
            throw new SecurityException(str + " is a reserved key for the encryption keyset.");
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        STRING(0),
        STRING_SET(1),
        INT(2),
        LONG(3),
        FLOAT(4),
        BOOLEAN(5);
        
        private final int mId;

        c(int i) {
            this.mId = i;
        }

        public static c fromId(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return null;
                                }
                                return BOOLEAN;
                            }
                            return FLOAT;
                        }
                        return LONG;
                    }
                    return INT;
                }
                return STRING_SET;
            }
            return STRING;
        }

        public int getId() {
            return this.mId;
        }
    }

    public EncryptedSharedPreferences(@NonNull String str, @NonNull String str2, @NonNull SharedPreferences sharedPreferences, @NonNull Aead aead, @NonNull DeterministicAead deterministicAead) {
        this.c = str;
        this.f1678a = sharedPreferences;
        this.d = aead;
        this.e = deterministicAead;
    }

    @NonNull
    public static SharedPreferences create(@NonNull String str, @NonNull String str2, @NonNull Context context, @NonNull PrefKeyEncryptionScheme prefKeyEncryptionScheme, @NonNull PrefValueEncryptionScheme prefValueEncryptionScheme) throws GeneralSecurityException, IOException {
        DeterministicAeadConfig.register();
        AeadConfig.register();
        AndroidKeysetManager.Builder withSharedPref = new AndroidKeysetManager.Builder().withKeyTemplate(prefKeyEncryptionScheme.getKeyTemplate()).withSharedPref(context, "__androidx_security_crypto_encrypted_prefs_key_keyset__", str);
        KeysetHandle keysetHandle = withSharedPref.withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + str2).build().getKeysetHandle();
        AndroidKeysetManager.Builder withSharedPref2 = new AndroidKeysetManager.Builder().withKeyTemplate(prefValueEncryptionScheme.getKeyTemplate()).withSharedPref(context, "__androidx_security_crypto_encrypted_prefs_value_keyset__", str);
        KeysetHandle keysetHandle2 = withSharedPref2.withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + str2).build().getKeysetHandle();
        DeterministicAead deterministicAead = (DeterministicAead) keysetHandle.getPrimitive(DeterministicAead.class);
        return new EncryptedSharedPreferences(str, str2, context.getSharedPreferences(str, 0), (Aead) keysetHandle2.getPrimitive(Aead.class), deterministicAead);
    }

    public String a(String str) {
        try {
            String str2 = new String(this.e.decryptDeterministically(Base64.decode(str, 0), this.c.getBytes()), StandardCharsets.UTF_8);
            if (str2.equals("__NULL__")) {
                return null;
            }
            return str2;
        } catch (GeneralSecurityException e) {
            throw new SecurityException("Could not decrypt key. " + e.getMessage(), e);
        }
    }

    public String b(String str) {
        if (str == null) {
            str = "__NULL__";
        }
        try {
            return Base64.encode(this.e.encryptDeterministically(str.getBytes(StandardCharsets.UTF_8), this.c.getBytes()));
        } catch (GeneralSecurityException e) {
            throw new SecurityException("Could not encrypt key. " + e.getMessage(), e);
        }
    }

    public Pair<String, String> c(String str, byte[] bArr) throws GeneralSecurityException {
        String b2 = b(str);
        return new Pair<>(b2, Base64.encode(this.d.encrypt(bArr, b2.getBytes(StandardCharsets.UTF_8))));
    }

    @Override // android.content.SharedPreferences
    public boolean contains(@Nullable String str) {
        if (!e(str)) {
            return this.f1678a.contains(b(str));
        }
        throw new SecurityException(str + " is a reserved key for the encryption keyset.");
    }

    public final Object d(String str) {
        if (!e(str)) {
            if (str == null) {
                str = "__NULL__";
            }
            try {
                String b2 = b(str);
                String string = this.f1678a.getString(b2, null);
                if (string != null) {
                    ByteBuffer wrap = ByteBuffer.wrap(this.d.decrypt(Base64.decode(string, 0), b2.getBytes(StandardCharsets.UTF_8)));
                    wrap.position(0);
                    switch (a.f1679a[c.fromId(wrap.getInt()).ordinal()]) {
                        case 1:
                            int i = wrap.getInt();
                            ByteBuffer slice = wrap.slice();
                            wrap.limit(i);
                            String charBuffer = StandardCharsets.UTF_8.decode(slice).toString();
                            if (charBuffer.equals("__NULL__")) {
                                return null;
                            }
                            return charBuffer;
                        case 2:
                            return Integer.valueOf(wrap.getInt());
                        case 3:
                            return Long.valueOf(wrap.getLong());
                        case 4:
                            return Float.valueOf(wrap.getFloat());
                        case 5:
                            return Boolean.valueOf(wrap.get() != 0);
                        case 6:
                            ArraySet arraySet = new ArraySet();
                            while (wrap.hasRemaining()) {
                                int i2 = wrap.getInt();
                                ByteBuffer slice2 = wrap.slice();
                                slice2.limit(i2);
                                wrap.position(wrap.position() + i2);
                                arraySet.add(StandardCharsets.UTF_8.decode(slice2).toString());
                            }
                            if (arraySet.size() == 1 && "__NULL__".equals(arraySet.valueAt(0))) {
                                return null;
                            }
                            return arraySet;
                        default:
                            return null;
                    }
                }
                return null;
            } catch (GeneralSecurityException e) {
                throw new SecurityException("Could not decrypt value. " + e.getMessage(), e);
            }
        }
        throw new SecurityException(str + " is a reserved key for the encryption keyset.");
    }

    public boolean e(String str) {
        return "__androidx_security_crypto_encrypted_prefs_key_keyset__".equals(str) || "__androidx_security_crypto_encrypted_prefs_value_keyset__".equals(str);
    }

    @Override // android.content.SharedPreferences
    @NonNull
    public SharedPreferences.Editor edit() {
        return new b(this, this.f1678a.edit());
    }

    @Override // android.content.SharedPreferences
    @NonNull
    public Map<String, ?> getAll() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : this.f1678a.getAll().entrySet()) {
            if (!e(entry.getKey())) {
                String a2 = a(entry.getKey());
                hashMap.put(a2, d(a2));
            }
        }
        return hashMap;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(@Nullable String str, boolean z) {
        Object d = d(str);
        return (d == null || !(d instanceof Boolean)) ? z : ((Boolean) d).booleanValue();
    }

    @Override // android.content.SharedPreferences
    public float getFloat(@Nullable String str, float f) {
        Object d = d(str);
        return (d == null || !(d instanceof Float)) ? f : ((Float) d).floatValue();
    }

    @Override // android.content.SharedPreferences
    public int getInt(@Nullable String str, int i) {
        Object d = d(str);
        return (d == null || !(d instanceof Integer)) ? i : ((Integer) d).intValue();
    }

    @Override // android.content.SharedPreferences
    public long getLong(@Nullable String str, long j) {
        Object d = d(str);
        return (d == null || !(d instanceof Long)) ? j : ((Long) d).longValue();
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public String getString(@Nullable String str, @Nullable String str2) {
        Object d = d(str);
        return (d == null || !(d instanceof String)) ? str2 : (String) d;
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public Set<String> getStringSet(@Nullable String str, @Nullable Set<String> set) {
        Set<String> arraySet;
        Object d = d(str);
        if (d instanceof Set) {
            arraySet = (Set) d;
        } else {
            arraySet = new ArraySet<>();
        }
        return arraySet.size() > 0 ? arraySet : set;
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(@NonNull SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.b.add(onSharedPreferenceChangeListener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(@NonNull SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.b.remove(onSharedPreferenceChangeListener);
    }
}
