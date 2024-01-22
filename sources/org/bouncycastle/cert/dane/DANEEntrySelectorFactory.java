package org.bouncycastle.cert.dane;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes12.dex */
public class DANEEntrySelectorFactory {

    /* renamed from: a  reason: collision with root package name */
    public final DigestCalculator f14480a;

    public DANEEntrySelectorFactory(DigestCalculator digestCalculator) {
        this.f14480a = digestCalculator;
    }

    public DANEEntrySelector createSelector(String str) throws DANEException {
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(str.substring(0, str.indexOf(64)));
        try {
            OutputStream outputStream = this.f14480a.getOutputStream();
            outputStream.write(uTF8ByteArray);
            outputStream.close();
            byte[] digest = this.f14480a.getDigest();
            return new DANEEntrySelector(Strings.fromByteArray(Hex.encode(digest)) + "._smimecert." + str.substring(str.indexOf(64) + 1));
        } catch (IOException e) {
            throw new DANEException("Unable to calculate digest string: " + e.getMessage(), e);
        }
    }
}
