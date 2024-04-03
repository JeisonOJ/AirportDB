package controller;

import model.AirplaneModel;

public class AirplaneController {

    public static AirplaneModel instanceModel(){
        return new AirplaneModel();
    }

    public static String showAllAirplanes(){
        StringBuilder message = new StringBuilder();
        message.append("");

        instanceModel().findAll();
        return message.toString();
    }

}
