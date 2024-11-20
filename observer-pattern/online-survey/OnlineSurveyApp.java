import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Observer Pattern - Subject Interface
interface SurveySubject {
    void subscribe(Participant participant);
    void unsubscribe(Participant participant);
    void notifyParticipants();
}

// Observer Pattern - Concrete Subject Class
class Survey implements SurveySubject {
    private List<Participant> participants = new ArrayList<>();
    private String surveyResults;

    @Override
    public void subscribe(Participant participant) {
        participants.add(participant);
        System.out.println(participant.getName() + " has subscribed to survey results.");
    }

    @Override
    public void unsubscribe(Participant participant) {
        participants.remove(participant);
        System.out.println(participant.getName() + " has unsubscribed from survey results.");
    }

    @Override
    public void notifyParticipants() {
        for (Participant participant : participants) {
            participant.update(surveyResults);
        }
    }

    public void setSurveyResults(String results) {
        this.surveyResults = results;
        System.out.println("Survey results updated: " + results);
        notifyParticipants();
    }
}

// Observer Pattern - Observer Interface
interface Participant {
    void update(String results);
    String getName();
}

// Observer Pattern - Concrete Observer Class
class SurveyParticipant implements Participant {
    private String name;

    public SurveyParticipant(String name) {
        this.name = name;
    }

    @Override
    public void update(String results) {
        System.out.println(name + " received survey results: " + results);
    }

    @Override
    public String getName() {
        return name;
    }
}

// Memento Pattern - Memento Class
class SurveyAnswersMemento {
    private String answers;

    public SurveyAnswersMemento(String answers) {
        this.answers = answers;
    }

    public String getAnswers() {
        return answers;
    }
}

// Memento Pattern - Originator Class
class SurveyAnswers {
    private String answers;

    public void setAnswers(String answers) {
        this.answers = answers;
        System.out.println("Survey answers set to: " + answers);
    }

    public SurveyAnswersMemento save() {
        System.out.println("Saving current survey answers.");
        return new SurveyAnswersMemento(answers);
    }

    public void restore(SurveyAnswersMemento memento) {
        this.answers = memento.getAnswers();
        System.out.println("Survey answers restored to: " + answers);
    }
}

// Memento Pattern - Caretaker Class
class SurveyAnswersHistory {
    private Stack<SurveyAnswersMemento> history = new Stack<>();

    public void save(SurveyAnswersMemento memento) {
        history.push(memento);
    }

    public SurveyAnswersMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}

// Client Code
public class OnlineSurveyApp {
    public static void main(String[] args) {
        // Observer Pattern - Survey and Participants
        Survey survey = new Survey();
        Participant participant1 = new SurveyParticipant("Alice");
        Participant participant2 = new SurveyParticipant("Bob");

        survey.subscribe(participant1);
        survey.subscribe(participant2);

        survey.setSurveyResults("Survey on favorite programming languages: Java is the most popular.");

        // Memento Pattern - Survey Answers
        SurveyAnswers surveyAnswers = new SurveyAnswers();
        SurveyAnswersHistory history = new SurveyAnswersHistory();

        surveyAnswers.setAnswers("Alice: Java, Bob: Python");
        history.save(surveyAnswers.save());

        surveyAnswers.setAnswers("Alice: C++, Bob: JavaScript");
        history.save(surveyAnswers.save());

        // Undo to previous answers
        surveyAnswers.restore(history.undo());
        surveyAnswers.restore(history.undo());
    }
}

/**
Explanation:
1. `SurveySubject` is the observer pattern's subject interface that defines methods for subscribing, unsubscribing, and notifying participants.
2. `Survey` is the concrete subject that maintains a list of participants and notifies them of survey results.
3. `Participant` is the observer interface that defines the `update()` method for receiving survey results.
4. `SurveyParticipant` is the concrete observer that implements the `update()` method to receive survey result notifications.
5. `SurveyAnswersMemento` is the memento class that stores the survey answers.
6. `SurveyAnswers` is the originator class that can create and restore its state using mementos.
7. `SurveyAnswersHistory` is the caretaker class that manages the history of saved survey answers.
8. `OnlineSurveyApp` is the client that uses both observer and memento patterns to manage survey notifications and answer history.
*/
