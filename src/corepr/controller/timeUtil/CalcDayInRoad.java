package corepr.controller.timeUtil;

import corepr.model.common.Address;
import corepr.utils.geolocation.controller.GoogleMapsAPI;
import corepr.utils.geolocation.controller.GoogleMapsAPIImpl;
import corepr.utils.geolocation.controller.Location;


public class CalcDayInRoad {

    public static final String COUNTRY = "Ukraine";
    public static final int SPEED = 40;
    public static final int HOUR_IN_DAY = 24;


    public static int dayInRoad (Address addressFrom, Address addressTo){

        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

        Location locationStart = googleMapsAPI.findLocation(COUNTRY, addressFrom.getCity(),
                addressFrom.getStreet(), addressFrom.getHouseNum());
        Location locationFinish = googleMapsAPI.findLocation(COUNTRY, addressTo.getCity(),
                addressTo.getStreet(), addressTo.getHouseNum());
        double distance = googleMapsAPI.getDistance(locationStart,locationFinish);

        // currentTime + distance/60 ;
        int hourInTravel = (int) ((distance/SPEED));
        int dayFinish = 0;

        if (hourInTravel % HOUR_IN_DAY != 0) {
            dayFinish = hourInTravel / HOUR_IN_DAY + 1;
        } else dayFinish = hourInTravel / HOUR_IN_DAY;

        return dayFinish;
    }
}
