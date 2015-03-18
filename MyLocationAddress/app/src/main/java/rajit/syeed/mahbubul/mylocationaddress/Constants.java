package rajit.syeed.mahbubul.mylocationaddress;

/**
 * Created by rajit on 18/03/15.
 */
public final class Constants {

    public final static String LOCATION_KEY = "location-key";
    public final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "rajit.syeed.mahbubul.mylocationaddress";

    //used to pass the receiver class of the address
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";

    //send back the result of the location reverse geocoding
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";

    //used to pass the location object containg the longitude and latitude to resolve in address.
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";
}
