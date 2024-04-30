package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ICONST_2 implements Instruction {

    public static ICONST_2 iconst_2(ProgramCounter pc, Method method){
        return new ICONST_2();
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
