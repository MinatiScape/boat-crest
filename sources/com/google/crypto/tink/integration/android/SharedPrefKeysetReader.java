package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.Hex;
import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;
/* loaded from: classes10.dex */
public final class SharedPrefKeysetReader implements KeysetReader {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f10857a;
    public final String b;

    public SharedPrefKeysetReader(Context context, String str, String str2) throws IOException {
        if (str != null) {
            this.b = str;
            Context applicationContext = context.getApplicationContext();
            if (str2 == null) {
                this.f10857a = PreferenceManager.getDefaultSharedPreferences(applicationContext);
                return;
            } else {
                this.f10857a = applicationContext.getSharedPreferences(str2, 0);
                return;
            }
        }
        throw new IllegalArgumentException("keysetName cannot be null");
    }

    public final byte[] a() throws IOException {
        try {
            String string = this.f10857a.getString(this.b, null);
            if (string != null) {
                return Hex.decode(string);
            }
            throw new FileNotFoundException(String.format("can't read keyset; the pref value %s does not exist", this.b));
        } catch (ClassCastException | IllegalArgumentException unused) {
            throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", this.b));
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        return Keyset.parseFrom(a(), ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        return EncryptedKeyset.parseFrom(a(), ExtensionRegistryLite.getEmptyRegistry());
    }
}
