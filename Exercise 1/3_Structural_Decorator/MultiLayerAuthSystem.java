// Chosen because login security can be enhanced by stacking layers (password, OTP, biometric).
// Decorator wraps the base authenticator with extra checks dynamically without altering core code.

import java.util.Scanner;

// Component interface
interface Authenticator {
    boolean authenticate();
}

// Concrete Component
class BasicPasswordAuth implements Authenticator {
    private String correctPassword = "admin123"; // demo password
    private String enteredPassword;

    public BasicPasswordAuth(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    @Override
    public boolean authenticate() {
        if (enteredPassword.equals(correctPassword)) {
            System.out.println("[PasswordAuth]  Password verified");
            return true;
        } else {
            System.out.println("[PasswordAuth]  Invalid password");
            return false;
        }
    }
}

// Decorator (abstract)
abstract class AuthDecorator implements Authenticator {
    protected Authenticator wrappedAuth;

    public AuthDecorator(Authenticator wrappedAuth) {
        this.wrappedAuth = wrappedAuth;
    }
}

// Concrete Decorator 1 → OTP
class OtpAuthDecorator extends AuthDecorator {
    private String correctOtp = "1234"; // demo OTP
    private String enteredOtp;

    public OtpAuthDecorator(Authenticator wrappedAuth, String enteredOtp) {
        super(wrappedAuth);
        this.enteredOtp = enteredOtp;
    }

    @Override
    public boolean authenticate() {
        if (wrappedAuth.authenticate()) {
            if (enteredOtp.equals(correctOtp)) {
                System.out.println("[OTPAuth]  OTP verified");
                return true;
            } else {
                System.out.println("[OTPAuth]  Invalid OTP");
                return false;
            }
        }
        return false;
    }
}

// Concrete Decorator 2 → Biometric
class BiometricAuthDecorator extends AuthDecorator {
    private boolean biometricMatch;

    public BiometricAuthDecorator(Authenticator wrappedAuth, boolean biometricMatch) {
        super(wrappedAuth);
        this.biometricMatch = biometricMatch;
    }

    @Override
    public boolean authenticate() {
        if (wrappedAuth.authenticate()) {
            if (biometricMatch) {
                System.out.println("[BiometricAuth]  Biometric verified");
                return true;
            } else {
                System.out.println("[BiometricAuth]  Biometric mismatch");
                return false;
            }
        }
        return false;
    }
}

// Concrete Decorator 3 → Geo-location
class GeoLocationAuthDecorator extends AuthDecorator {
    private String allowedCountry = "India"; // allowed region
    private String userCountry;

    public GeoLocationAuthDecorator(Authenticator wrappedAuth, String userCountry) {
        super(wrappedAuth);
        this.userCountry = userCountry;
    }

    @Override
    public boolean authenticate() {
        if (wrappedAuth.authenticate()) {
            if (userCountry.equalsIgnoreCase(allowedCountry)) {
                System.out.println("[GeoAuth]  Location verified (" + userCountry + ")");
                return true;
            } else {
                System.out.println("[GeoAuth]  Login denied from " + userCountry);
                return false;
            }
        }
        return false;
    }
}

// Client
public class MultiLayerAuthSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Multi-Layer Authentication System =====");

        // Step 1: Basic Password
        System.out.print("Enter password: ");
        String pwd = sc.nextLine();
        Authenticator auth = new BasicPasswordAuth(pwd);

        // Step 2: Ask if OTP is required
        System.out.print("Do you want OTP authentication? (yes/no): ");
        String otpChoice = sc.nextLine();
        if (otpChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter OTP (demo=1234): ");
            String otp = sc.nextLine();
            auth = new OtpAuthDecorator(auth, otp);
        }

        // Step 3: Ask if Biometric is required
        System.out.print("Do you want Biometric authentication? (yes/no): ");
        String bioChoice = sc.nextLine();
        if (bioChoice.equalsIgnoreCase("yes")) {
            System.out.print("Biometric match? (true/false): ");
            boolean bioMatch = sc.nextBoolean();
            sc.nextLine(); // consume newline
            auth = new BiometricAuthDecorator(auth, bioMatch);
        }

        // Step 4: Ask if Geo-location is required
        System.out.print("Do you want Geo-location authentication? (yes/no): ");
        String geoChoice = sc.nextLine();
        if (geoChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter your country: ");
            String country = sc.nextLine();
            auth = new GeoLocationAuthDecorator(auth, country);
        }

        // Final Authentication
        System.out.println("\n--- Authenticating... ---");
        boolean result = auth.authenticate();

        if (result) {
            System.out.println(" Access Granted! Welcome User 🎉");
        } else {
            System.out.println(" Access Denied!");
        }

        sc.close();
    }
}
