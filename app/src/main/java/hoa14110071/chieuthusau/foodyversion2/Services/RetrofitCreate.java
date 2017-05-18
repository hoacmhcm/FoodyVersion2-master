package hoa14110071.chieuthusau.foodyversion2.Services;

/**
 * Created by minhh on 14-May-17.
 */

public class RetrofitCreate {
    public static final String BASE_URL = "http://192.168.1.18/foody/api/";
    public static String IMGE_URL ="http://192.168.1.18/foody/images/";

    public static Services getService()
    {
        return RetrofitBuild.RetrofitBuild(BASE_URL).create(Services.class);
    }
}
