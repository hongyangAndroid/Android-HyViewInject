package com.zhy.util.ioc.processor;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;

public class ProxyInfo
{
	private String packageName;
	private String targetClassName;
	private String proxyClassName;
	private TypeElement typeElement;

	private int layoutId;

	private Map<Integer, ViewInfo> idViewMap = new HashMap<Integer, ViewInfo>();

	public static final String PROXY = "PROXY";

	public ProxyInfo(String packageName, String className)
	{
		this.packageName = packageName;
		this.targetClassName = className;
		this.proxyClassName = className + "$$" + PROXY;
	}

	public void putViewInfo(int id, ViewInfo viewInfo)
	{
		idViewMap.put(id, viewInfo);
	}

	public Map<Integer, ViewInfo> getIdViewMap()
	{
		return idViewMap;
	}

	public String getProxyClassFullName()
	{
		return packageName + "." + proxyClassName;
	}

	public String generateJavaCode()
	{

		StringBuilder builder = new StringBuilder();
		builder.append("// Generated code from HyViewInjector. Do not modify!\n");
		builder.append("package ").append(packageName).append(";\n\n");

		builder.append("import android.view.View;\n");
		builder.append("import com.zhy.util.ioc.Finder;\n");
		builder.append("import com.zhy.util.ioc.processor.AbstractInjector;\n");
		builder.append('\n');

		builder.append("public class ").append(proxyClassName);
		builder.append("<T extends ").append(getTargetClassName()).append(">");
		builder.append(" implements AbstractInjector<T>");
		builder.append(" {\n");

		generateInjectMethod(builder);
		builder.append('\n');

		builder.append("}\n");
		return builder.toString();

	}

	private String getTargetClassName()
	{
		return targetClassName.replace("$",".");
	}

	private void generateInjectMethod(StringBuilder builder)
	{

		builder.append("  @Override ")
				.append("public void inject(final Finder finder, final T target, Object source) {\n");

		if (layoutId > 0)
		{
			builder.append("finder.setContentView( source , ");
			builder.append(layoutId + " );\n");
		}

		builder.append("    View view;\n");

		for (Integer key : idViewMap.keySet())
		{
			ViewInfo viewInfo = idViewMap.get(key);
			builder.append("    view = ");
			builder.append("finder.findViewById(source , ");
			builder.append(viewInfo.getId() + " );\n");
			builder.append("    target.").append(viewInfo.getName())
					.append(" = ");
			builder.append("finder.castView( view ").append(", ")
					.append(viewInfo.getId()).append(" , \"");
			builder.append(viewInfo.getName() + " \" );");
		}

		builder.append("  }\n");
	}

	public TypeElement getTypeElement()
	{
		return typeElement;
	}

	public void setTypeElement(TypeElement typeElement)
	{
		this.typeElement = typeElement;
	}

	public int getLayoutId()
	{
		return layoutId;
	}

	public void setLayoutId(int layoutId)
	{
		this.layoutId = layoutId;
	}

}
