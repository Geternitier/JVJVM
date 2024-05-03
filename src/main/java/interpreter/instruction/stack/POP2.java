package interpreter.instruction.stack;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class POP2 implements Instruction {
    public POP2(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pop();
        thread.top().getOperandStack().pop();
    }

    @Override
    public String toString(){
        return "pop2";
    }
}
