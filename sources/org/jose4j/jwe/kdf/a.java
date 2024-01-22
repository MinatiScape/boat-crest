package org.jose4j.jwe.kdf;

import com.coveiot.sdk.ble.api.BleUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f15523a;
    public static Class<ConcatenationKeyDerivationFunctionWithSha256> b;

    static {
        Logger logger = LoggerFactory.getLogger(a.class);
        f15523a = logger;
        String property = System.getProperty("org.jose4j.jwe.kdf.ConcatenationKeyDerivationFunctionWithSha256");
        if (property != null) {
            try {
                Class cls = Class.forName(property);
                b = cls;
                ConcatenationKeyDerivationFunctionWithSha256 concatenationKeyDerivationFunctionWithSha256 = (ConcatenationKeyDerivationFunctionWithSha256) cls.newInstance();
                concatenationKeyDerivationFunctionWithSha256.kdf(new byte[]{124, BleUUID.CMD_ID_AF, 43, 14, -71, BleUUID.CMD_ID_B8, -84, 75, 115, 73, -52, -39, com.htsmart.wristband2.a.a.a.Y0, -58, 77, BleUUID.CMD_ID_AD}, 512, new byte[8]);
                logger.debug("Using custom ConcatenationKeyDerivationFunctionWithSha256 implementation: " + concatenationKeyDerivationFunctionWithSha256.getClass());
            } catch (Throwable th) {
                b = null;
                Logger logger2 = f15523a;
                logger2.debug("Using jose4j's concatenation key derivation function implementation because of problems with " + property, th);
            }
        }
    }

    public static ConcatenationKeyDerivationFunctionWithSha256 a(String str) {
        Class<ConcatenationKeyDerivationFunctionWithSha256> cls = b;
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (Exception e) {
                Logger logger = f15523a;
                logger.debug("Unable to create new instance of " + b, (Throwable) e);
            }
        }
        return new ConcatKeyDerivationFunction("SHA-256", str);
    }
}
