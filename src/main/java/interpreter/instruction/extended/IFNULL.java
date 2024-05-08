package interpreter.instruction.extended;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.reference.NULL;
import runtime.reference.Reference;

public class IFNULL implements Instruction {
    private final int branch;

    public IFNULL(ProgramCounter programCounter, Method method){
        branch = programCounter.readShort();
    }


    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Reference reference = curFrame.getOperandStack().popReference();
        if(reference instanceof NULL){
            ProgramCounter pc = curFrame.getProgramCounter();
            pc.setPosition(pc.getPosition() + branch - 3);
        }
    }

    @Override
    public String toString(){
        return "ifnull "+branch;
    }
}
