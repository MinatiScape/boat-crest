package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;
/* loaded from: classes12.dex */
public class b {
    public static int a(byte b) throws DecoderException {
        int digit = Character.digit((char) b, 16);
        if (digit != -1) {
            return digit;
        }
        throw new DecoderException("Invalid URL encoding: not a valid digit (radix 16): " + ((int) b));
    }

    public static char b(int i) {
        return Character.toUpperCase(Character.forDigit(i & 15, 16));
    }
}
