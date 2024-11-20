// Facade Pattern - Unified Patient Data Access
class PatientDataFacade {
    private PersonalInfoService personalInfoService;
    private MedicalHistoryService medicalHistoryService;
    private BillingService billingService;

    public PatientDataFacade() {
        personalInfoService = new PersonalInfoService();
        medicalHistoryService = new MedicalHistoryService();
        billingService = new BillingService();
    }

    public void getPatientData(String patientId) {
        System.out.println("Fetching unified patient data for ID: " + patientId);
        personalInfoService.getPersonalInfo(patientId);
        medicalHistoryService.getMedicalHistory(patientId);
        billingService.getBillingInfo(patientId);
    }
}

class PersonalInfoService {
    public void getPersonalInfo(String patientId) {
        System.out.println("Fetching personal information for patient ID: " + patientId);
    }
}

class MedicalHistoryService {
    public void getMedicalHistory(String patientId) {
        System.out.println("Fetching medical history for patient ID: " + patientId);
    }
}

class BillingService {
    public void getBillingInfo(String patientId) {
        System.out.println("Fetching billing information for patient ID: " + patientId);
    }
}

// Chain of Responsibility Pattern - Treatment Flow
abstract class TreatmentHandler {
    protected TreatmentHandler nextHandler;

    public void setNextHandler(TreatmentHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleTreatment(String condition);
}

class GeneralPractitioner extends TreatmentHandler {
    @Override
    public void handleTreatment(String condition) {
        if (condition.equals("general")) {
            System.out.println("General Practitioner: Treating general condition.");
        } else if (nextHandler != null) {
            nextHandler.handleTreatment(condition);
        } else {
            System.out.println("No handler available for condition: " + condition);
        }
    }
}

class Specialist extends TreatmentHandler {
    @Override
    public void handleTreatment(String condition) {
        if (condition.equals("special")) {
            System.out.println("Specialist: Treating special condition.");
        } else if (nextHandler != null) {
            nextHandler.handleTreatment(condition);
        } else {
            System.out.println("No handler available for condition: " + condition);
        }
    }
}

class Surgeon extends TreatmentHandler {
    @Override
    public void handleTreatment(String condition) {
        if (condition.equals("surgery")) {
            System.out.println("Surgeon: Performing surgery.");
        } else if (nextHandler != null) {
            nextHandler.handleTreatment(condition);
        } else {
            System.out.println("No handler available for condition: " + condition);
        }
    }
}

// Client Code
public class HospitalManagementApp {
    public static void main(String[] args) {
        // Facade Pattern - Unified Patient Data Access
        PatientDataFacade patientDataFacade = new PatientDataFacade();
        System.out.println("\nUnified Patient Data Access:");
        patientDataFacade.getPatientData("P12345");

        // Chain of Responsibility Pattern - Treatment Flow
        TreatmentHandler gp = new GeneralPractitioner();
        TreatmentHandler specialist = new Specialist();
        TreatmentHandler surgeon = new Surgeon();

        gp.setNextHandler(specialist);
        specialist.setNextHandler(surgeon);

        System.out.println("\nTreatment Flow:");
        gp.handleTreatment("general");
        gp.handleTreatment("special");
        gp.handleTreatment("surgery");
        gp.handleTreatment("unknown");
    }
}

/*
Explanation:
1. `PatientDataFacade` is the Facade class that provides a unified interface to access patient data from multiple services.
2. `PersonalInfoService`, `MedicalHistoryService`, and `BillingService` are subsystems that provide specific patient information.
3. `TreatmentHandler` is the abstract base class for the Chain of Responsibility pattern that defines the `handleTreatment()` method for the treatment flow.
4. `GeneralPractitioner`, `Specialist`, and `Surgeon` are concrete handlers that provide specific treatments based on the patient's condition.
5. `HospitalManagementApp` is the client code that demonstrates using both the Facade pattern for unified patient data access and the Chain of Responsibility pattern for managing the treatment flow.
*/
