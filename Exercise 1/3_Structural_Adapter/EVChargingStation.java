// Chosen because different EVs use incompatible charging standards (Tesla, CHAdeMO, CCS).
// Adapter converts each car's interface into a common format so the universal charger can work with all.

import java.util.Scanner;

// Target interface (expected by Universal Charger)
interface ChargePort {
    void connectToCharger();
}

// Adaptee 1: Tesla car (incompatible API)
class TeslaCar {
    public void plugInTeslaPort() {
        System.out.println("[Tesla]  Plugged into Tesla Supercharger port");
    }
}

// Adaptee 2: Nissan car
class NissanCar {
    public void useCHAdeMO() {
        System.out.println("[Nissan] Charging via CHAdeMO standard");
    }
}

// Adaptee 3: BMW car
class BMWCar {
    public void useCCS() {
        System.out.println("[BMW] Charging via CCS fast charger");
    }
}

// Adapters
class TeslaAdapter implements ChargePort {
    private TeslaCar tesla;

    public TeslaAdapter(TeslaCar tesla) {
        this.tesla = tesla;
    }

    @Override
    public void connectToCharger() {
        tesla.plugInTeslaPort();
    }
}

class NissanAdapter implements ChargePort {
    private NissanCar nissan;

    public NissanAdapter(NissanCar nissan) {
        this.nissan = nissan;
    }

    @Override
    public void connectToCharger() {
        nissan.useCHAdeMO();
    }
}

class BMWAdapter implements ChargePort {
    private BMWCar bmw;

    public BMWAdapter(BMWCar bmw) {
        this.bmw = bmw;
    }

    @Override
    public void connectToCharger() {
        bmw.useCCS();
    }
}

// Client: Universal EV Charger
class UniversalCharger {
    public void startCharging(ChargePort port) {
        System.out.println("Connecting to universal EV charger...");
        port.connectToCharger();
        System.out.println(" Charging started successfully!\n");
    }
}

// Demo
public class EVChargingStation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UniversalCharger charger = new UniversalCharger();

        System.out.println("===== Universal EV Charging Station =====");
        System.out.println("Available EV brands:");
        System.out.println("1. Tesla");
        System.out.println("2. Nissan");
        System.out.println("3. BMW");
        System.out.print("Choose your EV (1/2/3): ");

        int choice = sc.nextInt();
        ChargePort adapter = null;

        switch (choice) {
            case 1:
                TeslaCar tesla = new TeslaCar();
                adapter = new TeslaAdapter(tesla);
                break;
            case 2:
                NissanCar nissan = new NissanCar();
                adapter = new NissanAdapter(nissan);
                break;
            case 3:
                BMWCar bmw = new BMWCar();
                adapter = new BMWAdapter(bmw);
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Tesla.");
                adapter = new TeslaAdapter(new TeslaCar());
        }

        charger.startCharging(adapter);
        sc.close();
    }
}
