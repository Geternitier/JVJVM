package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ICONST_2 implements Instruction {
    public ICONST_2(ProgramCounter pc, Method method){

    }
    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(2);
    }

    @Override
    public String toString(){
        return "iconst_2";
    }
}
