package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.Constant;
import runtime.classdata.constant.FloatConstant;
import runtime.classdata.constant.IntegerConstant;

public class LDC implements Instruction {
    private final int index;

    public LDC(ProgramCounter programCounter, Method method) {
        index = programCounter.ubyte();
    }

    public LDC(int index){this.index = index;}

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Constant constant = curFrame.getDynamicLink().getConstant(index);
        if(constant instanceof IntegerConstant){
            curFrame.getOperandStack().pushInt(((IntegerConstant) constant).getValue());
        } else if(constant instanceof FloatConstant){
            curFrame.getOperandStack().pushFloat(((FloatConstant) constant).getValue());
        } else {
            // TODO
        }
    }

    @Override
    public String toString(){
        return "ldc " + index;
    }
}
