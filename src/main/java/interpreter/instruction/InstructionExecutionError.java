package interpreter.instruction;

public class InstructionExecutionError extends Error {
    public InstructionExecutionError(String s) {
        super(s);
    }
}
