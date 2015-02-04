package com.zhy.util.ioc.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import com.zhy.util.ioc.annotation.InjectView;

@SupportedAnnotationTypes("com.zhy.util.ioc.annotation.InjectView")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ViewInjectProcessor extends AbstractProcessor
{
	private Map<String, ProxyInfo> mProxyMap = new HashMap<String, ProxyInfo>();
	
	private Elements elementUtils;

	@Override
	public synchronized void init(ProcessingEnvironment env)
	{
		super.init(env);
		elementUtils = env.getElementUtils();
	}
	
	

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv)
	{

		String fqClassName, className, packageName;
		// Map<String, VariableElement> fields = new HashMap<String,
		// VariableElement>();

		for (Element ele : roundEnv.getElementsAnnotatedWith(InjectView.class))
		{

			processingEnv.getMessager().printMessage(Kind.NOTE, "ele = " + ele);

			if (ele.getKind() == ElementKind.CLASS)
			{
				TypeElement classElement = (TypeElement) ele;

				PackageElement packageElement = (PackageElement) ele
						.getEnclosingElement();
				fqClassName = classElement.getQualifiedName().toString();
				className = classElement.getSimpleName().toString();
				packageName = packageElement.getQualifiedName().toString();

				int layoutId = classElement.getAnnotation(InjectView.class)
						.value();

				ProxyInfo proxyInfo = mProxyMap.get(fqClassName);
				if (proxyInfo != null)
				{
					proxyInfo.setLayoutId(layoutId);
				} else
				{
					proxyInfo = new ProxyInfo(packageName, className);
					proxyInfo.setTypeElement(classElement);
					proxyInfo.setLayoutId(layoutId);
					mProxyMap.put(fqClassName, proxyInfo);

				}
				processingEnv.getMessager().printMessage(
						Kind.NOTE,
						"annatated class : packageName = " + packageName
								+ " , className = " + className
								+ " , fqClassName = " + fqClassName);

			} else if (ele.getKind() == ElementKind.FIELD)
			{


				VariableElement varElement = (VariableElement) ele;
				TypeElement classElement = (TypeElement) ele.getEnclosingElement();
				
				
				fqClassName = classElement.getQualifiedName().toString();
				PackageElement packageElement = elementUtils.getPackageOf(classElement);
				
				packageName = packageElement.getQualifiedName().toString();
//				className = classElement.getSimpleName().toString();
				
				className = getClassName(classElement, packageName);

				int id = varElement.getAnnotation(InjectView.class).value();
				String fieldName = varElement.getSimpleName().toString();
				String fieldType = varElement.asType().toString();

				processingEnv.getMessager().printMessage(
						Kind.NOTE,
						"annatated field : fieldName = "
								+ varElement.getSimpleName().toString()
								+ " , id = " + id + " , fileType = "
								+ fieldType);

				ProxyInfo proxyInfo = mProxyMap.get(fqClassName);
				if (proxyInfo == null)
				{
					proxyInfo = new ProxyInfo(packageName, className);
					mProxyMap.put(fqClassName, proxyInfo);
					proxyInfo.setTypeElement(classElement);
				}
				proxyInfo.putViewInfo(id,
						new ViewInfo(id, fieldName, fieldType));

			}

		}

		for (String key : mProxyMap.keySet())
		{
			ProxyInfo proxyInfo = mProxyMap.get(key);
			try
			{

				JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
						proxyInfo.getProxyClassFullName(),
						proxyInfo.getTypeElement());
				Writer writer = jfo.openWriter();
				writer.write(proxyInfo.generateJavaCode());
				writeLog(proxyInfo.generateJavaCode());
				writer.flush();
				writer.close();
			} catch (IOException e)
			{
				error(proxyInfo.getTypeElement(),
						"Unable to write injector for type %s: %s",
						proxyInfo.getTypeElement(), e.getMessage());
			}

		}

		return true;
	}
	
	private static String getClassName(TypeElement type, String packageName)
	{
		int packageLen = packageName.length() + 1;
		return type.getQualifiedName().toString().substring(packageLen)
				.replace('.', '$');
	}
	
	private void error(Element element, String message, Object... args)
	{
		if (args.length > 0)
		{
			message = String.format(message, args);
		}
		processingEnv.getMessager().printMessage(Kind.ERROR, message, element);
	}

	private void writeLog(String str)
	{
		try
		{
			FileWriter fw = new FileWriter(new File("D:/process2.txt"), true);
			fw.write(str + "\n");
			fw.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
