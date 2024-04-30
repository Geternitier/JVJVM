package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ICONST_0 implements Instruction {

    public static ICONST_0 iconst_0(ProgramCounter pc, Method method){
        return new ICONST_0();
    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(0);
    }

    @Override
    public String toString(){
        return "iconst_0";
    }
}
