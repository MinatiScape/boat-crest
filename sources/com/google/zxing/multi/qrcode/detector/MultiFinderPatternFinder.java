package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class MultiFinderPatternFinder extends FinderPatternFinder {
    public static final FinderPatternInfo[] g = new FinderPatternInfo[0];
    public static final FinderPattern[] h = new FinderPattern[0];
    public static final FinderPattern[][] i = new FinderPattern[0];

    /* loaded from: classes11.dex */
    public static final class b implements Serializable, Comparator<FinderPattern> {
        private b() {
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            double estimatedModuleSize = finderPattern2.getEstimatedModuleSize() - finderPattern.getEstimatedModuleSize();
            if (estimatedModuleSize < 0.0d) {
                return -1;
            }
            return estimatedModuleSize > 0.0d ? 1 : 0;
        }
    }

    public MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        BitMatrix image = getImage();
        int height = image.getHeight();
        int width = image.getWidth();
        int i2 = ((height * 3) / 388 < 3 || z) ? 3 : 3;
        int[] iArr = new int[5];
        for (int i3 = i2 - 1; i3 < height; i3 += i2) {
            FinderPatternFinder.doClearCounts(iArr);
            int i4 = 0;
            for (int i5 = 0; i5 < width; i5++) {
                if (image.get(i5, i3)) {
                    if ((i4 & 1) == 1) {
                        i4++;
                    }
                    iArr[i4] = iArr[i4] + 1;
                } else if ((i4 & 1) != 0) {
                    iArr[i4] = iArr[i4] + 1;
                } else if (i4 == 4) {
                    if (FinderPatternFinder.foundPatternCross(iArr) && handlePossibleCenter(iArr, i3, i5)) {
                        FinderPatternFinder.doClearCounts(iArr);
                        i4 = 0;
                    } else {
                        FinderPatternFinder.doShiftCounts2(iArr);
                        i4 = 3;
                    }
                } else {
                    i4++;
                    iArr[i4] = iArr[i4] + 1;
                }
            }
            if (FinderPatternFinder.foundPatternCross(iArr)) {
                handlePossibleCenter(iArr, i3, width);
            }
        }
        FinderPattern[][] k = k();
        ArrayList arrayList = new ArrayList();
        for (FinderPattern[] finderPatternArr : k) {
            ResultPoint.orderBestPatterns(finderPatternArr);
            arrayList.add(new FinderPatternInfo(finderPatternArr));
        }
        if (arrayList.isEmpty()) {
            return g;
        }
        return (FinderPatternInfo[]) arrayList.toArray(g);
    }

    public final FinderPattern[][] k() throws NotFoundException {
        List<FinderPattern> possibleCenters = getPossibleCenters();
        int size = possibleCenters.size();
        int i2 = 3;
        if (size >= 3) {
            char c = 0;
            if (size == 3) {
                return new FinderPattern[][]{(FinderPattern[]) possibleCenters.toArray(h)};
            }
            Collections.sort(possibleCenters, new b());
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            while (i3 < size - 2) {
                FinderPattern finderPattern = possibleCenters.get(i3);
                if (finderPattern != null) {
                    int i4 = i3 + 1;
                    while (i4 < size - 1) {
                        FinderPattern finderPattern2 = possibleCenters.get(i4);
                        if (finderPattern2 != null) {
                            float estimatedModuleSize = (finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) / Math.min(finderPattern.getEstimatedModuleSize(), finderPattern2.getEstimatedModuleSize());
                            float f = 0.5f;
                            float f2 = 0.05f;
                            if (Math.abs(finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) <= 0.5f || estimatedModuleSize < 0.05f) {
                                int i5 = i4 + 1;
                                while (i5 < size) {
                                    FinderPattern finderPattern3 = possibleCenters.get(i5);
                                    if (finderPattern3 != null) {
                                        float estimatedModuleSize2 = (finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) / Math.min(finderPattern2.getEstimatedModuleSize(), finderPattern3.getEstimatedModuleSize());
                                        if (Math.abs(finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) <= f || estimatedModuleSize2 < f2) {
                                            FinderPattern[] finderPatternArr = new FinderPattern[i2];
                                            finderPatternArr[c] = finderPattern;
                                            finderPatternArr[1] = finderPattern2;
                                            finderPatternArr[2] = finderPattern3;
                                            ResultPoint.orderBestPatterns(finderPatternArr);
                                            FinderPatternInfo finderPatternInfo = new FinderPatternInfo(finderPatternArr);
                                            float distance = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getBottomLeft());
                                            float distance2 = ResultPoint.distance(finderPatternInfo.getTopRight(), finderPatternInfo.getBottomLeft());
                                            float distance3 = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getTopRight());
                                            float estimatedModuleSize3 = (distance + distance3) / (finderPattern.getEstimatedModuleSize() * 2.0f);
                                            if (estimatedModuleSize3 <= 180.0f && estimatedModuleSize3 >= 9.0f && Math.abs((distance - distance3) / Math.min(distance, distance3)) < 0.1f) {
                                                double d = distance;
                                                double d2 = distance3;
                                                float sqrt = (float) Math.sqrt((d * d) + (d2 * d2));
                                                if (Math.abs((distance2 - sqrt) / Math.min(distance2, sqrt)) < 0.1f) {
                                                    arrayList.add(finderPatternArr);
                                                }
                                            }
                                        }
                                    }
                                    i5++;
                                    i2 = 3;
                                    c = 0;
                                    f = 0.5f;
                                    f2 = 0.05f;
                                }
                            }
                        }
                        i4++;
                        i2 = 3;
                        c = 0;
                    }
                }
                i3++;
                i2 = 3;
                c = 0;
            }
            if (!arrayList.isEmpty()) {
                return (FinderPattern[][]) arrayList.toArray(i);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
