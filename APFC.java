import java.util.Scanner;

public class APFC {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" Automatic Power Factor Correction");
        System.out.println("======================================");

        System.out.print("Enter real power P (kW): ");
        double p = sc.nextDouble();

        System.out.print("Enter present power factor: ");
        double pf1 = sc.nextDouble();

        System.out.print("Enter target power factor: ");
        double pf2 = sc.nextDouble();

        if (p <= 0 || pf1 <= 0 || pf1 > 1 || pf2 <= 0 || pf2 > 1) {
            System.out.println("Invalid input.");
            sc.close();
            return;
        }

        if (pf2 <= pf1) {
            System.out.println(
                "Target power factor must be greater than the present power factor."
            );
            sc.close();
            return;
        }

        // Initial phase angle
        double phi1 = Math.acos(pf1);

        // Target phase angle
        double phi2 = Math.acos(pf2);

        // Required reactive power compensation
        double qc = p * (Math.tan(phi1) - Math.tan(phi2));

        // Initial and final reactive power
        double qInitial = p * Math.tan(phi1);
        double qFinal = p * Math.tan(phi2);

        // Apparent power before and after correction
        double sInitial = p / pf1;
        double sFinal = p / pf2;

        System.out.println("\n---------- RESULTS ----------");

        System.out.printf("Real Power              : %.2f kW%n", p);
        System.out.printf("Initial Power Factor    : %.3f%n", pf1);
        System.out.printf("Target Power Factor     : %.3f%n", pf2);

        System.out.printf("Initial Reactive Power  : %.2f kVAR%n", qInitial);
        System.out.printf("Required Capacitor Bank: %.2f kVAR%n", qc);
        System.out.printf("Final Reactive Power    : %.2f kVAR%n", qFinal);

        System.out.printf("Initial Apparent Power  : %.2f kVA%n", sInitial);
        System.out.printf("Final Apparent Power    : %.2f kVA%n", sFinal);

        System.out.println("-----------------------------");

        System.out.printf(
            "Power factor improved from %.3f to %.3f%n",
            pf1, pf2
        );

        sc.close();
    }
}
