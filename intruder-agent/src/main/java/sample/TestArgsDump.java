package sample;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TestArgsDump implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "TestArgs", null,
				"java/lang/Object", null);

		cw.visitSource("TestArgs.java", null);

		cw.visitInnerClass("java/util/Map$Entry", "java/util/Map", "Entry",
				ACC_PUBLIC + ACC_STATIC + ACC_ABSTRACT + ACC_INTERFACE);

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(6, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>",
					"()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "LTestArgs;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
					"([Ljava/lang/String;)V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			Label l1 = new Label();
			Label l2 = new Label();
			mv.visitTryCatchBlock(l0, l1, l2, "java/io/IOException");
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLineNumber(12, l3);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitInsn(DUP);
			mv.visitVarInsn(ASTORE, 4);
			mv.visitInsn(ARRAYLENGTH);
			mv.visitVarInsn(ISTORE, 3);
			mv.visitInsn(ICONST_0);
			mv.visitVarInsn(ISTORE, 2);
			Label l4 = new Label();
			mv.visitJumpInsn(GOTO, l4);
			Label l5 = new Label();
			mv.visitLabel(l5);
			mv.visitFrame(Opcodes.F_FULL, 5, new Object[] {
					"[Ljava/lang/String;", Opcodes.TOP, Opcodes.INTEGER,
					Opcodes.INTEGER, "[Ljava/lang/String;" }, 0,
					new Object[] {});
			mv.visitVarInsn(ALOAD, 4);
			mv.visitVarInsn(ILOAD, 2);
			mv.visitInsn(AALOAD);
			mv.visitVarInsn(ASTORE, 1);
			Label l6 = new Label();
			mv.visitLabel(l6);
			mv.visitLineNumber(13, l6);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;");
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(Ljava/lang/String;)V");
			Label l7 = new Label();
			mv.visitLabel(l7);
			mv.visitLineNumber(12, l7);
			mv.visitIincInsn(2, 1);
			mv.visitLabel(l4);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ILOAD, 2);
			mv.visitVarInsn(ILOAD, 3);
			mv.visitJumpInsn(IF_ICMPLT, l5);
			Label l8 = new Label();
			mv.visitLabel(l8);
			mv.visitLineNumber(15, l8);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
					"getProperties", "()Ljava/util/Properties;");
			mv.visitVarInsn(ASTORE, 1);
			Label l9 = new Label();
			mv.visitLabel(l9);
			mv.visitLineNumber(16, l9);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Properties",
					"entrySet", "()Ljava/util/Set;");
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator",
					"()Ljava/util/Iterator;");
			mv.visitVarInsn(ASTORE, 3);
			Label l10 = new Label();
			mv.visitJumpInsn(GOTO, l10);
			Label l11 = new Label();
			mv.visitLabel(l11);
			mv.visitFrame(Opcodes.F_FULL, 4, new Object[] {
					"[Ljava/lang/String;", "java/util/Properties", Opcodes.TOP,
					"java/util/Iterator" }, 0, new Object[] {});
			mv.visitVarInsn(ALOAD, 3);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next",
					"()Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "java/util/Map$Entry");
			mv.visitVarInsn(ASTORE, 2);
			Label l12 = new Label();
			mv.visitLabel(l12);
			mv.visitLineNumber(17, l12);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;");
			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder",
					"<init>", "()V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map$Entry",
					"getKey", "()Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder",
					"append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
			mv.visitLdcInsn("=");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder",
					"append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map$Entry",
					"getValue", "()Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder",
					"append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder",
					"toString", "()Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(Ljava/lang/String;)V");
			mv.visitLabel(l10);
			mv.visitLineNumber(16, l10);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 3);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator",
					"hasNext", "()Z");
			mv.visitJumpInsn(IFNE, l11);
			mv.visitLabel(l0);
			mv.visitLineNumber(21, l0);
			mv.visitTypeInsn(NEW, "java/io/File");
			mv.visitInsn(DUP);
			mv.visitLdcInsn("a.txt");
			mv.visitMethodInsn(INVOKESPECIAL, "java/io/File", "<init>",
					"(Ljava/lang/String;)V");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "createNewFile",
					"()Z");
			mv.visitInsn(POP);
			mv.visitLabel(l1);
			Label l13 = new Label();
			mv.visitJumpInsn(GOTO, l13);
			mv.visitLabel(l2);
			mv.visitLineNumber(22, l2);
			mv.visitFrame(Opcodes.F_FULL, 2, new Object[] {
					"[Ljava/lang/String;", "java/util/Properties" }, 1,
					new Object[] { "java/io/IOException" });
			mv.visitVarInsn(ASTORE, 2);
			Label l14 = new Label();
			mv.visitLabel(l14);
			mv.visitLineNumber(24, l14);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/IOException",
					"printStackTrace", "()V");
			mv.visitLabel(l13);
			mv.visitLineNumber(28, l13);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitInsn(RETURN);
			Label l15 = new Label();
			mv.visitLabel(l15);
			mv.visitLocalVariable("args", "[Ljava/lang/String;", null, l3, l15,
					0);
			mv.visitLocalVariable("s", "Ljava/lang/String;", null, l6, l7, 1);
			mv.visitLocalVariable("properties", "Ljava/util/Properties;", null,
					l9, l15, 1);
			mv.visitLocalVariable("e", "Ljava/util/Map$Entry;", null, l12, l10,
					2);
			mv.visitLocalVariable("e1", "Ljava/io/IOException;", null, l14,
					l13, 2);
			mv.visitMaxs(3, 5);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}
