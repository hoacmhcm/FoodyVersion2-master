package hoa14110071.chieuthusau.foodyversion2.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by minhh on 14-May-17.
 */

public class RetrofitBuild {
    private static Retrofit retrofit = null;

    public static Retrofit RetrofitBuild(String baseURL) {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }
        return retrofit;
    }
}
