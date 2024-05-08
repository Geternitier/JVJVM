package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.*;
import runtime.classdata.ConstantPool;
import runtime.classdata.Method;
import runtime.classdata.constant.MethodRef;

public class INVOKESTATIC implements Instruction {
    private final int index;

    public INVOKESTATIC(ProgramCounter pc, Method method){
        index = pc.readShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        ConstantPool constantPool = thread.top().getJClass().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        LocalVariables localVariables = curFrame.getOperandStack().popArgs(methodRef.getType());
        try {
            Method method = methodRef.getMethod();
            LocalVariables variables = new LocalVariables(method.getCode().getMaxLocals());
            localVariables.copyTo(0, method.countArgc(), variables, 0);
            thread.getVirtualMachine().getInterpreter().invoke(method, thread, localVariables);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
        // TODO
        // thread.getVirtualMachine().getInterpreter().invoke(method, thread, args);
    }

    @Override
    public String toString(){
        return "invokestatic "+index;
    }
}
