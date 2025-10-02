// Chosen because multiple devices (purifiers, apps, traffic boards) must react to sensor updates.
// The Observer pattern lets all subscribers get notified automatically whenever AQI changes.

import java.util.*;

// Observer interface
interface Observer {
    void update(int aqi);
}

// Subject
class AirQualitySensor {
    private List<Observer> observers = new ArrayList<>();
    private int aqi;

    public void attach(Observer obs) { observers.add(obs); }
    public void detach(Observer obs) { observers.remove(obs); }

    public void setAQI(int value) {
        this.aqi = value;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer obs : observers) obs.update(aqi);
    }
}

// Concrete observers
class AirPurifier implements Observer {
    public void update(int aqi) {
        if (aqi > 150) System.out.println("[AirPurifier] AQI " + aqi + "  Turning ON purifiers");
        else System.out.println("[AirPurifier] AQI " + aqi + "  Air is clean, purifiers OFF");
    }
}

class TrafficControlBoard implements Observer {
    public void update(int aqi) {
        if (aqi > 200) System.out.println("[TrafficControl] AQI " + aqi + "  Reducing traffic flow");
        else System.out.println("[TrafficControl] AQI " + aqi + "  Normal traffic");
    }
}

class CitizenApp implements Observer {
    public void update(int aqi) {
        if (aqi > 100) System.out.println("[CitizenApp] AQI " + aqi + " Warning: Limit outdoor activities");
        else System.out.println("[CitizenApp] AQI " + aqi + "  Safe to go outside");
    }
}

// Main
public class SmartCityAQI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AirQualitySensor sensor = new AirQualitySensor();
        sensor.attach(new AirPurifier());
        sensor.attach(new TrafficControlBoard());
        sensor.attach(new CitizenApp());

        System.out.println("Enter AQI readings (type -1 to exit):");
        while (true) {
            System.out.print("AQI> ");
            int aqi = sc.nextInt();
            if (aqi == -1) break;
            sensor.setAQI(aqi);
            System.out.println();
        }
        sc.close();
    }
}
