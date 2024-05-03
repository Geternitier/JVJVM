package interpreter.instruction.comparisons;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.reference.Reference;
import runtime.classdata.Method;

public class IF_ACMPEQ implements Instruction {
    private final int branch;

    public IF_ACMPEQ(ProgramCounter programCounter, Method method) {
        branch = programCounter.readUnsignedShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Reference value2 = curFrame.getOperandStack().popReference();
        Reference value1 = curFrame.getOperandStack().popReference();
        if(value1 == value2){
            ProgramCounter pc = curFrame.getProgramCounter();
            pc.setPosition(pc.getPosition() + branch - 3);
        }
    }

    @Override
    public String toString(){
        return "if_acmpeq "+branch;
    }
}
