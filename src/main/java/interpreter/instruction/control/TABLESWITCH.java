package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

import java.util.LinkedHashMap;
import java.util.Map;

public class TABLESWITCH implements Instruction {
    private final int opAddress;
    private final int padding;
    private final int defaultOffset;
    private final int low;
    private final int high;
    private final Map<Integer, Integer> table = new LinkedHashMap<>();

    public TABLESWITCH(ProgramCounter programCounter, Method method) {
        opAddress = programCounter.getPosition() - 1;
        padding = programCounter.readPadding();
        defaultOffset = programCounter.readInt();
        low = programCounter.readInt();
        high = programCounter.readInt();
        for(int i = low;i <= high;i++){
            table.put(i, programCounter.readInt());
        }
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int index = curFrame.getOperandStack().popInt();
        if(index < low || index > high){
            curFrame.getProgramCounter().setPosition(opAddress + defaultOffset);
        } else {
            curFrame.getProgramCounter().setPosition(opAddress + table.get(index));
        }
    }

    @Override
    public String toString(){
        return "tableswitch";
    }
}
