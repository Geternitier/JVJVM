package interpreter.instruction;

public class UnimplementedInstructionError extends Error{
    public UnimplementedInstructionError(int opcode) {
        super(String.format("Unimplemented opcode %d", opcode));
    }
}
