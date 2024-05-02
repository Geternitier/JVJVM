package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DCONST_1  implements Instruction {
    public DCONST_1(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushDouble(1);
    }
}
