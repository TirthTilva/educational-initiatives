// Chosen because multiple independent checks (fact-check, toxicity, bias, credibility)
// can be applied in sequence. Each handler decides to stop or pass to the next, making it flexible and extensible.

import java.util.Scanner;

// Handler interface
interface NewsHandler {
    void setNext(NewsHandler next);
    void handle(String article);
}

// Abstract base handler
abstract class AbstractNewsHandler implements NewsHandler {
    protected NewsHandler next;
    public void setNext(NewsHandler next) { this.next = next; }

    public void handle(String article) {
        if (process(article) && next != null) {
            next.handle(article);
        }
    }

    protected abstract boolean process(String article);
}

// Concrete handlers
class FactCheckHandler extends AbstractNewsHandler {
    protected boolean process(String article) {
        if (article.toLowerCase().contains("earth is flat")) {
            System.out.println("[FactCheck] Fake claim detected!");
            return false;
        }
        System.out.println("[FactCheck] Passed fact check");
        return true;
    }
}

class ToxicityCheckHandler extends AbstractNewsHandler {
    protected boolean process(String article) {
        if (article.toLowerCase().contains("hate") || article.toLowerCase().contains("stupid")) {
            System.out.println("[ToxicityCheck] Toxic language detected");
            return false;
        }
        System.out.println("[ToxicityCheck] No toxic language");
        return true;
    }
}

class BiasCheckHandler extends AbstractNewsHandler {
    protected boolean process(String article) {
        if (article.toLowerCase().contains("biased")) {
            System.out.println("[BiasCheck] Political bias detected");
            return false;
        }
        System.out.println("[BiasCheck] Neutral content");
        return true;
    }
}

class SourceCredibilityHandler extends AbstractNewsHandler {
    protected boolean process(String article) {
        if (article.toLowerCase().contains("whatsapp forward")) {
            System.out.println("[SourceCredibility] Untrusted source");
            return false;
        }
        System.out.println("[SourceCredibility] Trusted source");
        return true;
    }
}

// Main
public class FakeNewsPipeline {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Build chain
        FactCheckHandler fact = new FactCheckHandler();
        ToxicityCheckHandler toxic = new ToxicityCheckHandler();
        BiasCheckHandler bias = new BiasCheckHandler();
        SourceCredibilityHandler source = new SourceCredibilityHandler();

        fact.setNext(toxic);
        toxic.setNext(bias);
        bias.setNext(source);

        System.out.println("Enter news article text (type 'exit' to quit):");
        while (true) {
            System.out.print("> ");
            String article = sc.nextLine();
            if (article.equalsIgnoreCase("exit")) break;
            System.out.println("---- Analyzing ----");
            fact.handle(article);
            System.out.println();
        }
        sc.close();
    }
}
