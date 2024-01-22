package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class d implements Converter<ResponseBody, Character> {

    /* renamed from: a  reason: collision with root package name */
    public static final d f15610a = new d();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Character convert(ResponseBody responseBody) throws IOException {
        String string = responseBody.string();
        if (string.length() == 1) {
            return Character.valueOf(string.charAt(0));
        }
        throw new IOException("Expected body of length 1 for Character conversion but was " + string.length());
    }
}
