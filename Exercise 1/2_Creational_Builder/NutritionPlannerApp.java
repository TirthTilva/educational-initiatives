// Chosen because a nutrition plan must be built step-by-step (calories, protein, carbs, vitamins).
// Builder separates construction process from representation, producing flexible personalized plans.

import java.util.*;

// Product class
class NutritionPlan {
    private int calories;
    private String proteinSource;
    private String carbSource;
    private String vitaminPack;
    private List<String> allergies;

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProteinSource(String proteinSource) {
        this.proteinSource = proteinSource;
    }

    public void setCarbSource(String carbSource) {
        this.carbSource = carbSource;
    }

    public void setVitaminPack(String vitaminPack) {
        this.vitaminPack = vitaminPack;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public void showPlan() {
        System.out.println("\n===== Your Personalized Nutrition Plan =====");
        System.out.println("Calories per day : " + calories);
        System.out.println("Protein source   : " + proteinSource);
        System.out.println("Carb source      : " + carbSource);
        System.out.println("Vitamin pack     : " + vitaminPack);
        if (allergies != null && !allergies.isEmpty()) {
            System.out.println("Allergies noted  : " + String.join(", ", allergies));
        } else {
            System.out.println("Allergies noted  : None");
        }
        System.out.println("============================================");
    }
}

// Builder Interface
interface PlanBuilder {
    void setCalories(int calories);
    void setProtein(String protein);
    void setCarbs(String carbs);
    void setVitamins(String vitamins);
    void setAllergies(List<String> allergies);
    NutritionPlan getPlan();
}

// Concrete Builder
class NutritionPlanBuilder implements PlanBuilder {
    private NutritionPlan plan;

    public NutritionPlanBuilder() {
        this.plan = new NutritionPlan();
    }

    public void setCalories(int calories) {
        plan.setCalories(calories);
    }

    public void setProtein(String protein) {
        plan.setProteinSource(protein);
    }

    public void setCarbs(String carbs) {
        plan.setCarbSource(carbs);
    }

    public void setVitamins(String vitamins) {
        plan.setVitaminPack(vitamins);
    }

    public void setAllergies(List<String> allergies) {
        plan.setAllergies(allergies);
    }

    public NutritionPlan getPlan() {
        return plan;
    }
}

// Director
class NutritionDirector {
    private PlanBuilder builder;

    public NutritionDirector(PlanBuilder builder) {
        this.builder = builder;
    }

    public void constructPlan(int calories, String protein, String carbs, String vitamins, List<String> allergies) {
        builder.setCalories(calories);
        builder.setProtein(protein);
        builder.setCarbs(carbs);
        builder.setVitamins(vitamins);
        builder.setAllergies(allergies);
    }
}

// Client
public class NutritionPlannerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlanBuilder builder = new NutritionPlanBuilder();
        NutritionDirector director = new NutritionDirector(builder);

        System.out.println("===== Custom Nutrition Plan Generator =====");
        System.out.print("Enter daily calorie goal: ");
        int calories = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Preferred protein source (Chicken/Fish/Tofu/Beans): ");
        String protein = sc.nextLine();

        System.out.print("Preferred carb source (Rice/Quinoa/Pasta/Potatoes): ");
        String carbs = sc.nextLine();

        System.out.print("Vitamin preference (Multivitamin/Calcium/Vitamin-D): ");
        String vitamins = sc.nextLine();

        System.out.print("Do you have allergies? (comma separated, leave blank if none): ");
        String allergyInput = sc.nextLine();
        List<String> allergies = new ArrayList<>();
        if (!allergyInput.trim().isEmpty()) {
            allergies = Arrays.asList(allergyInput.split(","));
        }

        // Build the plan
        director.constructPlan(calories, protein, carbs, vitamins, allergies);
        NutritionPlan plan = builder.getPlan();

        // Show result
        plan.showPlan();

        sc.close();
    }
}
