package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class f implements Converter<ResponseBody, Float> {

    /* renamed from: a  reason: collision with root package name */
    public static final f f15612a = new f();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Float convert(ResponseBody responseBody) throws IOException {
        return Float.valueOf(responseBody.string());
    }
}
