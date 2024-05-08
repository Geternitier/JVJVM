package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.*;
import runtime.classdata.ConstantPool;
import runtime.classdata.Method;
import runtime.classdata.constant.MethodRef;
import runtime.reference.ClassReference;
import runtime.reference.FieldReference;
import runtime.reference.Reference;
import runtime.reference.StringReference;

public class INVOKEVIRTUAL implements Instruction {
    private final int index;

    public INVOKEVIRTUAL(ProgramCounter pc, Method method){
        index = pc.readShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        ConstantPool constantPool = thread.top().getJClass().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        LocalVariables localVariables = curFrame.getOperandStack().popArgs(methodRef.getType());
        Reference reference = curFrame.getOperandStack().popReference();

        if(reference instanceof FieldReference){
            JClass jClass = ((FieldReference) reference).getJClass();
            try {
                Method method = jClass.getMethod(methodRef.getName(), methodRef.getType());
                if(method.getName().equals("getClass") &&
                        method.getDescriptor().equals("()Ljava/lang/Class;") &&
                        method.getJClass().getName().equals("java/lang/Object")){
                    curFrame.getOperandStack().pushReference(new ClassReference(methodRef.getJClass().getThisClass()));
                    return;
                }
                LocalVariables variables = new LocalVariables(method.getCode().getMaxLocals());
                variables.set(0, new Variable(reference), Reference.class);
                localVariables.copyTo(0, method.countArgc(), variables, 1);
                thread.getVirtualMachine().getInterpreter().invoke(method, thread, variables);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.toString());
            }
        } else if(reference instanceof StringReference) {
            try {
                JClass jClass = methodRef.getJClass().getClassLoader().loadClass("Ljava/lang/String;");
                Method method = jClass.getMethod(methodRef.getName(), methodRef.getType());
                LocalVariables variables = new LocalVariables(method.getCode().getMaxLocals());
                variables.set(0, new Variable(reference), Reference.class);
                localVariables.copyTo(0, method.countArgc(), variables, 1);
                thread.getVirtualMachine().getInterpreter().invoke(method, thread, variables);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.toString());
            }
            // TODO
        }
    }

    @Override
    public String toString(){
        return "invokevirtual "+index;
    }
}
