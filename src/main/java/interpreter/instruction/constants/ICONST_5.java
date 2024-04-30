package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ICONST_5 implements Instruction {

    public static ICONST_5 iconst_5(ProgramCounter pc, Method method){
        return new ICONST_5();
    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(5);
    }

    @Override
    public String toString(){
        return "iconst_5";
    }
}
