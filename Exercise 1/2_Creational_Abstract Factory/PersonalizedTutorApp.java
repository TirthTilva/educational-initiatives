// Chosen because different learner types need consistent sets of materials (lesson + activity).
// Abstract Factory creates related objects together without exposing the creation logic.

import java.util.Scanner;

// Product Interfaces
interface LessonMaterial {
    void display();
}

interface PracticeActivity {
    void start();
}

// Concrete Products for Visual Learner
class Infographic implements LessonMaterial {
    @Override
    public void display() {
        System.out.println("[Visual] Showing infographic with diagrams & charts ");
    }
}

class VideoLesson implements PracticeActivity {
    @Override
    public void start() {
        System.out.println("[Visual] Playing animated video tutorial ");
    }
}

// Concrete Products for Auditory Learner
class Podcast implements LessonMaterial {
    @Override
    public void display() {
        System.out.println("[Auditory] Streaming podcast explanation");
    }
}

class Transcript implements PracticeActivity {
    @Override
    public void start() {
        System.out.println("[Auditory] Reading text transcript for revision");
    }
}

// Concrete Products for Kinesthetic Learner
class Quiz implements LessonMaterial {
    @Override
    public void display() {
        System.out.println("[Kinesthetic] Starting interactive drag-and-drop quiz ");
    }
}

class Simulation implements PracticeActivity {
    @Override
    public void start() {
        System.out.println("[Kinesthetic] Running hands-on coding simulation ");
    }
}

// Abstract Factory
interface LearningFactory {
    LessonMaterial createLesson();
    PracticeActivity createActivity();
}

// Concrete Factories
class VisualLearnerFactory implements LearningFactory {
    public LessonMaterial createLesson() {
        return new Infographic();
    }
    public PracticeActivity createActivity() {
        return new VideoLesson();
    }
}

class AuditoryLearnerFactory implements LearningFactory {
    public LessonMaterial createLesson() {
        return new Podcast();
    }
    public PracticeActivity createActivity() {
        return new Transcript();
    }
}

class KinestheticLearnerFactory implements LearningFactory {
    public LessonMaterial createLesson() {
        return new Quiz();
    }
    public PracticeActivity createActivity() {
        return new Simulation();
    }
}

// Client
public class PersonalizedTutorApp {
    private LessonMaterial lesson;
    private PracticeActivity activity;

    public PersonalizedTutorApp(LearningFactory factory) {
        lesson = factory.createLesson();
        activity = factory.createActivity();
    }

    public void startLearning() {
        lesson.display();
        activity.start();
    }

    // Demo with user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Personalized AI Tutor =====");
        System.out.println("Choose your learning style:");
        System.out.println("1. Visual (Infographic + Video)");
        System.out.println("2. Auditory (Podcast + Transcript)");
        System.out.println("3. Kinesthetic (Quiz + Simulation)");
        System.out.print("Enter choice (1/2/3): ");

        int choice = sc.nextInt();
        LearningFactory factory;

        switch (choice) {
            case 1:
                factory = new VisualLearnerFactory();
                break;
            case 2:
                factory = new AuditoryLearnerFactory();
                break;
            case 3:
                factory = new KinestheticLearnerFactory();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Visual learner.");
                factory = new VisualLearnerFactory();
        }

        PersonalizedTutorApp tutor = new PersonalizedTutorApp(factory);
        System.out.println("\n--- Starting Your Session ---");
        tutor.startLearning();

        sc.close();
    }
}
