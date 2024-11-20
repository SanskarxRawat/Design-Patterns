// Mediator Pattern - Workflow Mediator
import java.util.ArrayList;
import java.util.List;

interface WorkflowMediator {
    void notify(Service service, String event);
}

abstract class Service {
    protected WorkflowMediator mediator;

    public Service(WorkflowMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void execute();
}

class EmailService extends Service {
    public EmailService(WorkflowMediator mediator) {
        super(mediator);
    }

    @Override
    public void execute() {
        System.out.println("Email Service: Sending email...");
        mediator.notify(this, "EmailSent");
    }
}

class PaymentService extends Service {
    public PaymentService(WorkflowMediator mediator) {
        super(mediator);
    }

    @Override
    public void execute() {
        System.out.println("Payment Service: Processing payment...");
        mediator.notify(this, "PaymentProcessed");
    }
}

class NotificationService extends Service {
    public NotificationService(WorkflowMediator mediator) {
        super(mediator);
    }

    @Override
    public void execute() {
        System.out.println("Notification Service: Sending notification...");
    }
}

class WorkflowCoordinator implements WorkflowMediator {
    private EmailService emailService;
    private PaymentService paymentService;
    private NotificationService notificationService;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void notify(Service service, String event) {
        if (event.equals("EmailSent")) {
            System.out.println("Workflow Coordinator: Email sent, now processing payment...");
            paymentService.execute();
        } else if (event.equals("PaymentProcessed")) {
            System.out.println("Workflow Coordinator: Payment processed, now sending notification...");
            notificationService.execute();
        }
    }
}

// Chain of Responsibility Pattern - Task Approvals
abstract class TaskApprovalHandler {
    protected TaskApprovalHandler nextHandler;

    public void setNextHandler(TaskApprovalHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void approveTask(String task);
}

class ManagerApprovalHandler extends TaskApprovalHandler {
    @Override
    public void approveTask(String task) {
        if (task.equals("Standard")) {
            System.out.println("Manager: Approved standard task.");
        } else if (nextHandler != null) {
            nextHandler.approveTask(task);
        } else {
            System.out.println("Approval denied for task: " + task);
        }
    }
}

class DirectorApprovalHandler extends TaskApprovalHandler {
    @Override
    public void approveTask(String task) {
        if (task.equals("Complex")) {
            System.out.println("Director: Approved complex task.");
        } else if (nextHandler != null) {
            nextHandler.approveTask(task);
        } else {
            System.out.println("Approval denied for task: " + task);
        }
    }
}

class CEOApprovalHandler extends TaskApprovalHandler {
    @Override
    public void approveTask(String task) {
        if (task.equals("Critical")) {
            System.out.println("CEO: Approved critical task.");
        } else {
            System.out.println("Approval denied for task: " + task);
        }
    }
}

// Client Code
public class WorkflowAutomationApp {
    public static void main(String[] args) {
        // Mediator Pattern - Coordinating Services
        WorkflowCoordinator coordinator = new WorkflowCoordinator();
        EmailService emailService = new EmailService(coordinator);
        PaymentService paymentService = new PaymentService(coordinator);
        NotificationService notificationService = new NotificationService(coordinator);

        coordinator.setEmailService(emailService);
        coordinator.setPaymentService(paymentService);
        coordinator.setNotificationService(notificationService);

        System.out.println("Starting workflow:");
        emailService.execute();

        // Chain of Responsibility Pattern - Task Approvals
        TaskApprovalHandler manager = new ManagerApprovalHandler();
        TaskApprovalHandler director = new DirectorApprovalHandler();
        TaskApprovalHandler ceo = new CEOApprovalHandler();

        manager.setNextHandler(director);
        director.setNextHandler(ceo);

        System.out.println("\nTask Approvals:");
        manager.approveTask("Standard");
        manager.approveTask("Complex");
        manager.approveTask("Critical");
        manager.approveTask("Unknown");
    }
}

/**
Explanation:
1. `WorkflowMediator` is the mediator interface that defines the `notify()` method for coordinating between services.
2. `Service` is the abstract base class representing different services in the workflow.
3. `EmailService`, `PaymentService`, and `NotificationService` are concrete services that execute specific tasks.
4. `WorkflowCoordinator` is the mediator that manages communication between services.
5. `TaskApprovalHandler` is the abstract base class for the Chain of Responsibility pattern that defines the `approveTask()` method for task approvals.
6. `ManagerApprovalHandler`, `DirectorApprovalHandler`, and `CEOApprovalHandler` are concrete handlers that approve tasks based on their level of authority.
7. `WorkflowAutomationApp` is the client code that demonstrates the workflow automation using both Mediator and Chain of Responsibility patterns.
*/
