package interpreter.instruction.comparisons;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class IF_ICMPGT implements Instruction {
    private final int branch;

    public IF_ICMPGT(ProgramCounter programCounter, Method method) {
        branch = programCounter.readUnsignedShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int value2 = curFrame.getOperandStack().popInt();
        int value1 = curFrame.getOperandStack().popInt();
        if(value1 > value2){
            ProgramCounter pc = curFrame.getProgramCounter();
            pc.setPosition(pc.getPosition() + branch - 3);
        }
    }

    @Override
    public String toString(){
        return "if_icmpgt "+branch;
    }
}
