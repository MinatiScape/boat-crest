package com.sifli.watchfacelibrary;

import java.util.Comparator;
/* loaded from: classes12.dex */
public class WatchfaceFileComparator implements Comparator<WatchfaceFile> {
    @Override // java.util.Comparator
    public int compare(WatchfaceFile watchfaceFile, WatchfaceFile watchfaceFile2) {
        int compare;
        if (watchfaceFile.getFileExtension().equals("gif") && watchfaceFile2.getFileExtension().equals("gif")) {
            compare = Integer.compare(watchfaceFile.getFileData().length, watchfaceFile2.getFileData().length);
        } else if (watchfaceFile.getFileExtension().equals("gif")) {
            return 1;
        } else {
            if (watchfaceFile2.getFileExtension().equals("gif")) {
                return -1;
            }
            compare = Integer.compare(watchfaceFile.getFileData().length, watchfaceFile2.getFileData().length);
        }
        return compare * (-1);
    }
}
