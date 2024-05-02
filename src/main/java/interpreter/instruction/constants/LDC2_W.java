package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LDC2_W implements Instruction {
    private final int index;

    public LDC2_W(ProgramCounter programCounter, Method method) {
        index = programCounter.ushort();
    }

    @Override
    public void run(JThread thread) {
        // TODO
    }
}
