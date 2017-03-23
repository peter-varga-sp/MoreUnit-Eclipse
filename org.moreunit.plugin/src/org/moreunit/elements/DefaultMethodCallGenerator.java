/**
 *
 */
package org.moreunit.elements;

import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.core.LocalVariable;
import org.eclipse.jdt.internal.core.SourceMethod;
import org.eclipse.jdt.internal.core.SourceMethodElementInfo;

/**
 * @author vargape
 */
public class DefaultMethodCallGenerator
{

    // private final ClassTypeFacade classTypeFacade;

    private final String classUnderTestName;

    private String beanName = "bean";

    public DefaultMethodCallGenerator(ClassTypeFacade classTypeFacade)
    {
        super();
        // this.classTypeFacade = classTypeFacade;
        this.classUnderTestName = classTypeFacade.getType().getElementName();
    }

    String generateDeafultMethodCall(IMethod methodUnderTest)
    {
        StringBuilder result = new StringBuilder();

        result.append(getBeanDeclatationLine());
        result.append(getParameterDeclarationLines(methodUnderTest));
        result.append(getMethodCallLine(methodUnderTest));

        return result.toString();
    }

    private String getBeanDeclatationLine()
    {
        return "\t" + classUnderTestName + " " + beanName + " = new " + classUnderTestName + "();\n";
    }

    private String getParameterDeclarationLines(IMethod methodUnderTest)
    {
        String result = "";

        try
        {
            for (int i = 0; i < methodUnderTest.getParameters().length; i++)
            {
                ILocalVariable param = methodUnderTest.getParameters()[i];
                LocalVariable locParam = (LocalVariable) param;

                Object elementInfo = locParam.getElementInfo();
                elementInfo.toString();

                String typeSignature = param.getTypeSignature();
                String signatureSimpleName = Signature.getSignatureSimpleName(typeSignature);

                result = result + signatureSimpleName + " " + param.getElementName() + " = new " + signatureSimpleName + "();\n";
            }
        }
        catch (JavaModelException e)
        {

        }
        return result;
    }

    private String getMethodCallLine(IMethod methodUnderTest)
    {
        try
        {
            SourceMethod sm = (SourceMethod) methodUnderTest;
            SourceMethodElementInfo info = (SourceMethodElementInfo) sm.getElementInfo();
            String returnType = new String(info.getReturnTypeName());
            return "\t" + returnType + " result = " + beanName + "." + methodUnderTest.getElementName() + "(" + getParameterNames(methodUnderTest.getParameterNames()) + ");\n";
        }
        catch (JavaModelException e)
        {
            return "";
        }
    }

    private String getParameterNames(String[] parameters)
    {
        String result = "";
        for (int i = 0; i < parameters.length; i++)
        {
            String param = parameters[i];
            if(i > 0)
            {
                result = result + ", ";
            }
            result = result + param;
        }
        return result;
    }

}
