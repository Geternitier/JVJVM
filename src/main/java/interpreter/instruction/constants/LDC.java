package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LDC implements Instruction {
    private final int index;

    public LDC(ProgramCounter programCounter, Method method) {
        index = programCounter.ubyte();
    }

    @Override
    public void run(JThread thread) {
        // TODO
    }
}
