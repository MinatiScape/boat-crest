package org.jose4j.jwa;

import java.security.Security;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class AlgorithmAvailability {

    /* renamed from: a  reason: collision with root package name */
    public static Logger f15507a = LoggerFactory.getLogger(AlgorithmAvailability.class);

    public static boolean isAvailable(String str, String str2) {
        Set<String> algorithms = Security.getAlgorithms(str);
        for (String str3 : algorithms) {
            if (str3.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        f15507a.debug("{} is NOT available for {}. Algorithms available from underlying JCE: {}", str2, str, algorithms);
        return false;
    }
}
