import java.util.Stack;

/**
 * Command Pattern: Design an "Undo/Redo" feature for a text editor that saves each operation as a command.
 */
// Command Interface
interface TextCommand {
    void execute();
    void undo();
}

// Receiver Class
class TextEditor {
    private StringBuilder text = new StringBuilder();

    public void appendText(String newText) {
        text.append(newText);
    }

    public void deleteText(int length) {
        int start = text.length() - length;
        if (start >= 0) {
            text.delete(start, text.length());
        }
    }

    public String getText() {
        return text.toString();
    }
}

// Concrete Command Classes
class AppendTextCommand implements TextCommand {
    private TextEditor textEditor;
    private String textToAppend;

    public AppendTextCommand(TextEditor textEditor, String textToAppend) {
        this.textEditor = textEditor;
        this.textToAppend = textToAppend;
    }

    @Override
    public void execute() {
        textEditor.appendText(textToAppend);
    }

    @Override
    public void undo() {
        textEditor.deleteText(textToAppend.length());
    }
}

// Invoker Class
class TextEditorInvoker {
    private Stack<TextCommand> commandHistory = new Stack<>();
    private Stack<TextCommand> redoStack = new Stack<>();

    public void executeCommand(TextCommand command) {
        command.execute();
        commandHistory.push(command);
        redoStack.clear();
    }

    public void undoCommand() {
        if (!commandHistory.isEmpty()) {
            TextCommand command = commandHistory.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redoCommand() {
        if (!redoStack.isEmpty()) {
            TextCommand command = redoStack.pop();
            command.execute();
            commandHistory.push(command);
        }
    }
}

// Client Code
public class Texteditor {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        TextEditorInvoker invoker = new TextEditorInvoker();

        TextCommand appendHello = new AppendTextCommand(textEditor, "Hello ");
        TextCommand appendWorld = new AppendTextCommand(textEditor, "World!");

        invoker.executeCommand(appendHello);
        System.out.println("Text: " + textEditor.getText());

        invoker.executeCommand(appendWorld);
        System.out.println("Text: " + textEditor.getText());

        invoker.undoCommand();
        System.out.println("After undo: " + textEditor.getText());

        invoker.redoCommand();
        System.out.println("After redo: " + textEditor.getText());
    }
}

/**
Explanation:
1. `Command` is the command interface that defines `execute()` and `undo()` methods for all commands.
2. `TextEditor` is the receiver class that performs the actual text editing operations.
3. `AppendTextCommand` is a concrete command class that implements the `Command` interface to append text and provides an undo operation to delete the appended text.
4. `TextEditorInvoker` is the invoker class that executes commands, maintains a history for undo, and provides a stack for redo operations.
5. `TextEditorApp` is the client that creates commands and uses the invoker to execute, undo, and redo them.
*/
