package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class c implements Converter<ResponseBody, Byte> {

    /* renamed from: a  reason: collision with root package name */
    public static final c f15609a = new c();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Byte convert(ResponseBody responseBody) throws IOException {
        return Byte.valueOf(responseBody.string());
    }
}
