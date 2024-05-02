package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ACONST_NULL implements Instruction {
    public ACONST_NULL(ProgramCounter programCounter, Method method) {
        // TODO
    }

    @Override
    public void run(JThread thread) {

    }
}
