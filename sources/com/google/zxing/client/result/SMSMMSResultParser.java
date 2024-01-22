package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes11.dex */
public final class SMSMMSResultParser extends ResultParser {
    public static void g(Collection<String> collection, Collection<String> collection2, String str) {
        int indexOf = str.indexOf(59);
        if (indexOf < 0) {
            collection.add(str);
            collection2.add(null);
            return;
        }
        collection.add(str.substring(0, indexOf));
        String substring = str.substring(indexOf + 1);
        collection2.add(substring.startsWith("via=") ? substring.substring(4) : null);
    }

    @Override // com.google.zxing.client.result.ResultParser
    public SMSParsedResult parse(Result result) {
        String str;
        String substring;
        String massagedText = ResultParser.getMassagedText(result);
        String str2 = null;
        if (!massagedText.startsWith("sms:") && !massagedText.startsWith("SMS:") && !massagedText.startsWith("mms:") && !massagedText.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> e = ResultParser.e(massagedText);
        boolean z = false;
        if (e == null || e.isEmpty()) {
            str = null;
        } else {
            str2 = e.get("subject");
            str = e.get("body");
            z = true;
        }
        int indexOf = massagedText.indexOf(63, 4);
        if (indexOf >= 0 && z) {
            substring = massagedText.substring(4, indexOf);
        } else {
            substring = massagedText.substring(4);
        }
        int i = -1;
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        while (true) {
            int i2 = i + 1;
            int indexOf2 = substring.indexOf(44, i2);
            if (indexOf2 > i) {
                g(arrayList, arrayList2, substring.substring(i2, indexOf2));
                i = indexOf2;
            } else {
                g(arrayList, arrayList2, substring.substring(i2));
                String[] strArr = ResultParser.e;
                return new SMSParsedResult((String[]) arrayList.toArray(strArr), (String[]) arrayList2.toArray(strArr), str2, str);
            }
        }
    }
}
