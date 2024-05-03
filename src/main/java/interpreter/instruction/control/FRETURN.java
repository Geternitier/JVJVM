package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FRETURN implements Instruction {

    public FRETURN(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        float value = thread.top().getOperandStack().popFloat();
        thread.pop();
        thread.top().getOperandStack().pushFloat(value);
    }

    @Override
    public String toString(){
        return "freturn";
    }
}
