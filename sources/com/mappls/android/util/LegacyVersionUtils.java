package com.mappls.android.util;

import java.io.File;
/* loaded from: classes11.dex */
public class LegacyVersionUtils {
    private static final String DEFAULT_DIRECTORY_PREFIX = "MapplsAnalyticsAPI.Images.";
    private static final String FILE_PREFIX = "MP_IMG_";

    public static void removeLegacyResidualImageFiles(File file) {
        File[] listFiles;
        try {
            if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    removeLegacyResidualImageFiles(file2);
                }
            }
            if (file.getName().contains(DEFAULT_DIRECTORY_PREFIX) || file.getName().contains(FILE_PREFIX)) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }
}
