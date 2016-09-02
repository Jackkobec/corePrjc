package corepr.controller;

import corepr.controller.interfaces.IPostController;
import corepr.db.AppDataContainer;
import corepr.model.common.Address;
import corepr.model.office.PostOffice;
import corepr.utils.geolocation.mapFramework.ShowMapMarkerFrame;
import corepr.utils.geolocation.controller.GoogleMapsAPI;
import corepr.utils.geolocation.controller.GoogleMapsAPIImpl;
import corepr.utils.geolocation.controller.Location;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PostController implements IPostController {

    private AppDataContainer appDataContainer;

    public PostController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    // todo pass location to the method
    @Override
    public void showOfficesOnMap() {
        //todo create class post office and change ticket and db
        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        Location myLocation  = googleMapsAPI.findLocation("Україна", "Київ", "Вулиця Старокиївська", "10");

        List<Location> locations = new ArrayList();

        List<PostOffice> postOffices = appDataContainer.getPostOffices();

        // todo replace by stream
        for (PostOffice pst: postOffices){
            Address addr = pst.getAddress();
            locations.add(googleMapsAPI.findLocation("Ukraine", addr.getCity(), addr.getStreet(), addr.getHouseNum()));
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new ShowMapMarkerFrame(myLocation, locations);
            }
        });


    }
}
