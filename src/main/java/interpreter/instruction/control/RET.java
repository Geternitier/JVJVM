package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.*;
import runtime.classdata.Method;

public class RET implements Instruction {
    private final int index;

    public RET(ProgramCounter programCounter, Method method) {
        index = programCounter.readUnsignedByte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Reference reference = curFrame.getLocalVariables().getReference(index);
        assert reference instanceof ReturnAddress;
        curFrame.getProgramCounter().setPosition(((ReturnAddress) reference).getAddress());
    }
}
