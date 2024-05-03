package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.reference.NULL;

public class ACONST_NULL implements Instruction {
    public ACONST_NULL(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushReference(new NULL());
    }
}
