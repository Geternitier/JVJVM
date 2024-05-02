package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class NOP implements Instruction {
    public NOP(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {

    }
}
