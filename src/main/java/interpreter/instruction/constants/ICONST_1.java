package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ICONST_1 implements Instruction {

    public static ICONST_1 iconst_1(ProgramCounter pc, Method method){
        return new ICONST_1();
    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(1);
    }

    @Override
    public String toString(){
        return "iconst_1";
    }
}
