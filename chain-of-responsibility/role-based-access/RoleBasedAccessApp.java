// Proxy Pattern - Access Control
interface ResourceAccess {
    void accessResource(String userRole);
}

// Real Subject Class
class RealResourceAccess implements ResourceAccess {
    @Override
    public void accessResource(String userRole) {
        System.out.println(userRole + " is accessing the resource.");
    }
}

// Proxy Class for Access Control
class ResourceAccessProxy implements ResourceAccess {
    private RealResourceAccess realResourceAccess;

    public ResourceAccessProxy() {
        this.realResourceAccess = new RealResourceAccess();
    }

    @Override
    public void accessResource(String userRole) {
        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Manager")) {
            realResourceAccess.accessResource(userRole);
        } else {
            System.out.println("Access denied for role: " + userRole);
        }
    }
}

// Chain of Responsibility Pattern - Permission Validation
abstract class PermissionHandler {
    protected PermissionHandler nextHandler;

    public void setNextHandler(PermissionHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String permission);
}

// Concrete Handler Classes
class ReadPermissionHandler extends PermissionHandler {
    @Override
    public void handleRequest(String permission) {
        if (permission.equalsIgnoreCase("Read")) {
            System.out.println("Read permission granted.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(permission);
        }
    }
}

class WritePermissionHandler extends PermissionHandler {
    @Override
    public void handleRequest(String permission) {
        if (permission.equalsIgnoreCase("Write")) {
            System.out.println("Write permission granted.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(permission);
        }
    }
}

class DeletePermissionHandler extends PermissionHandler {
    @Override
    public void handleRequest(String permission) {
        if (permission.equalsIgnoreCase("Delete")) {
            System.out.println("Delete permission granted.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(permission);
        } else {
            System.out.println("Permission denied: " + permission);
        }
    }
}

// Client Code
public class RoleBasedAccessApp {
    public static void main(String[] args) {
        // Proxy Pattern - Access Control
        ResourceAccess resourceAccess = new ResourceAccessProxy();
        resourceAccess.accessResource("Admin");
        resourceAccess.accessResource("User");

        // Chain of Responsibility - Permission Validation
        PermissionHandler readHandler = new ReadPermissionHandler();
        PermissionHandler writeHandler = new WritePermissionHandler();
        PermissionHandler deleteHandler = new DeletePermissionHandler();

        readHandler.setNextHandler(writeHandler);
        writeHandler.setNextHandler(deleteHandler);

        System.out.println("\nPermission Validation:");
        readHandler.handleRequest("Read");
        readHandler.handleRequest("Write");
        readHandler.handleRequest("Delete");
        readHandler.handleRequest("Execute");
    }
}

/**
Explanation:
1. `ResourceAccess` is the interface for the Proxy pattern that defines access to the resource.
2. `RealResourceAccess` is the real subject class that provides access to the resource.
3. `ResourceAccessProxy` is the proxy class that controls access to the resource based on the user's role.
4. `PermissionHandler` is the abstract base class for the Chain of Responsibility pattern, defining the method `handleRequest()` for permission validation.
5. `ReadPermissionHandler`, `WritePermissionHandler`, and `DeletePermissionHandler` are concrete handler classes that validate specific permissions.
6. `RoleBasedAccessApp` is the client that demonstrates both access control using Proxy and permission validation using Chain of Responsibility.
*/
