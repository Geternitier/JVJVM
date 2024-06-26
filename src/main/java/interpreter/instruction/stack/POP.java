package interpreter.instruction.stack;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class POP implements Instruction {
    public POP(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pop();
    }

    @Override
    public String toString(){
        return "pop";
    }
}
