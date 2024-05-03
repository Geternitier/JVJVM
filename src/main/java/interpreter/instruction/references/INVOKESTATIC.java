package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.OperandStack;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.MethodRef;

public class INVOKESTATIC implements Instruction {
    private final Method method;

    public INVOKESTATIC(ProgramCounter pc, Method method){
        MethodRef methodRef = (MethodRef) method.getJClass().getConstantPool().getConstant(pc.readUnsignedShort());
        this.method = methodRef.getMethod();
    }

    @Override
    public void run(JThread thread) {
        System.out.println(this);

        OperandStack stack = thread.top().getOperandStack();
        // TODO
        // LocalVariables args = stack.popVariables(method.countArgc());
        // thread.getVirtualMachine().getInterpreter().invoke(method, thread, args);
    }

    @Override
    public String toString(){
        return String.format("invokestatic %s:%s:%s", method.getJClass().getName(), method.getName(), method.getDescriptor());
    }
}
