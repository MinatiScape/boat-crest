package com.github.siyamed.shapeimageview.path;

import android.content.Context;
import com.github.siyamed.shapeimageview.path.parser.IoUtil;
import com.github.siyamed.shapeimageview.path.parser.PathInfo;
import com.github.siyamed.shapeimageview.path.parser.SvgToPath;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public class SvgUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final Map<Integer, PathInfo> f7967a = new ConcurrentHashMap();

    public static final PathInfo readSvg(Context context, int i) {
        Map<Integer, PathInfo> map = f7967a;
        PathInfo pathInfo = map.get(Integer.valueOf(i));
        if (pathInfo == null) {
            InputStream inputStream = null;
            try {
                inputStream = context.getResources().openRawResource(i);
                PathInfo sVGFromInputStream = SvgToPath.getSVGFromInputStream(inputStream);
                map.put(Integer.valueOf(i), sVGFromInputStream);
                return sVGFromInputStream;
            } finally {
                IoUtil.closeQuitely(inputStream);
            }
        }
        return pathInfo;
    }
}
