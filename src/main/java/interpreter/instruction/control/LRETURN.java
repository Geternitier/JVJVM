package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LRETURN implements Instruction {

    public LRETURN(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        long value = thread.top().getOperandStack().popLong();
        thread.pop();
        thread.top().getOperandStack().pushLong(value);
    }

    @Override
    public String toString(){
        return "lreturn";
    }
}
