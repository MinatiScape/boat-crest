package com.google.zxing.client.result;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.net.MailTo;
import com.clevertap.android.sdk.Constants;
import com.google.zxing.Result;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public final class EmailAddressResultParser extends ResultParser {
    public static final Pattern f = Pattern.compile(Constants.SEPARATOR_COMMA);

    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String str;
        String str2;
        String str3;
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.startsWith(MailTo.MAILTO_SCHEME) && !massagedText.startsWith("MAILTO:")) {
            if (EmailDoCoMoResultParser.i(massagedText)) {
                return new EmailAddressParsedResult(massagedText);
            }
            return null;
        }
        String substring = massagedText.substring(7);
        int indexOf = substring.indexOf(63);
        if (indexOf >= 0) {
            substring = substring.substring(0, indexOf);
        }
        try {
            String f2 = ResultParser.f(substring);
            String[] split = !f2.isEmpty() ? f.split(f2) : null;
            Map<String, String> e = ResultParser.e(massagedText);
            if (e != null) {
                if (split == null && (str3 = e.get(TypedValues.TransitionType.S_TO)) != null) {
                    split = f.split(str3);
                }
                String str4 = e.get("cc");
                String[] split2 = str4 != null ? f.split(str4) : null;
                String str5 = e.get("bcc");
                String[] split3 = str5 != null ? f.split(str5) : null;
                str2 = e.get("body");
                strArr = split;
                strArr3 = split3;
                strArr2 = split2;
                str = e.get("subject");
            } else {
                strArr = split;
                strArr2 = null;
                strArr3 = null;
                str = null;
                str2 = null;
            }
            return new EmailAddressParsedResult(strArr, strArr2, strArr3, str, str2);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
