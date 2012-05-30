package com.alibaba.intruder.agent.transform;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.alibaba.intruder.agent.util.Logger;

/**
 * @author zili.dengzl
 * 
 */
public class DefaultTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader cl, String arg1, Class<?> arg2,
			ProtectionDomain arg3, byte[] classBytes)
			throws IllegalClassFormatException {
		Logger.info("DefaultTransformer:: transform class...");
		Logger.debug("DefaultTransformer's classloader = "
				+ DefaultTransformer.class.getClassLoader().toString());

		ClassReader cr = new ClassReader(classBytes);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassAdapter classAdapter = new DefaultClassAdapter(cw);
		cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
		byte[] transfromedClassBytes = cw.toByteArray();

		return transfromedClassBytes;
	}

}

class DefaultClassAdapter extends ClassAdapter {

	public DefaultClassAdapter(ClassVisitor cv) {
		super(cv);
	}

	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		MethodVisitor wrappedMv = mv;
		if (mv != null && name.equals("foo")) {
			Logger.debug("DefaultTransformer::handler foo");
			wrappedMv = new DefaultMethodAdapter(mv);
		}
		return wrappedMv;
	}
}

class DefaultMethodAdapter extends MethodAdapter {
	public DefaultMethodAdapter(MethodVisitor mv) {
		super(mv);
	}

	public void visitCode() {
		// handle when method start
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
				"Ljava/io/PrintStream;");
		mv.visitLdcInsn("start , ");
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
				"print", "(Ljava/lang/String;)V");

	}

	@Override
	public void visitInsn(int opcode) {
		// handle when method return
		if (opcode == Opcodes.RETURN) {
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;");
			mv.visitLdcInsn("method end. \n ");
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
					"print", "(Ljava/lang/String;)V");
		}
		mv.visitInsn(opcode);
	}
}