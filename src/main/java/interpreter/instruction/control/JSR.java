package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.reference.ReturnAddress;
import runtime.classdata.Method;

public class JSR implements Instruction {
    private final int branch;

    public JSR(ProgramCounter programCounter, Method method) {
        branch = programCounter.readUnsignedShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        ProgramCounter pc = curFrame.getProgramCounter();
        ReturnAddress returnAddress = new ReturnAddress(pc.getPosition());
        curFrame.getOperandStack().pushReference(returnAddress);
        pc.setPosition(pc.getPosition() + branch - 3);
    }

    @Override
    public String toString(){
        return "jsr "+branch;
    }
}
