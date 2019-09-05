package com.code.repository.util;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 */
public abstract class PackageScaner {

	/**
	 * 获取指定包下所有的classname 包括jar包下的class
	 */
	public static List<String> getClassNamesByPackage(String packageName) {
		List<String> resultList = new ArrayList<String>();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			String rootDir = packageName.replaceAll("\\.", "/");
			Enumeration<URL> resourceUrls = classLoader.getResources(rootDir);

			while (resourceUrls.hasMoreElements()) {
				URL url = resourceUrls.nextElement();
				String protocol = url.getProtocol();

				if ("file".equals(protocol)) {

					File file = new File(url.toURI());
					doFindMatchFiles(packageName, file, resultList);

				} else if ("jar".equals(protocol)) {

					JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
					doFindMatchJarFiles(rootDir, jarFile, resultList);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	/**
	 * 获取指定包下所有的classname 不包括jar包下的class
	 */
	public static List<String> getClassesExcludedJar(String packageName) {
		List<String> resultList = new ArrayList<String>();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			String rootDir = packageName.replaceAll("\\.", "/");
			URL url = loader.getResource(rootDir);
			File file = new File(url.toURI());

			doFindMatchFiles(packageName, file, resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	/**
	 * 递归处理文件夹下的文件
	 */
	private static void doFindMatchFiles(String packageName, File file, List<String> classList) {
		try {
			if (file.isFile()) {
				// class
				if (file.getName().endsWith(".class")) {
					classList.add(getSimpleName(packageName, ".class"));
				}
			} else if (file.isDirectory()) {

				File[] files = file.listFiles();
				for (File subFile : files) {
					String subPackageName = packageName + "." + subFile.getName();
					doFindMatchFiles(subPackageName, subFile, classList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历jar包下的classes
	 */
	public static void doFindMatchJarFiles(String rootDir, JarFile jar, List<String> classList) {

		Enumeration<JarEntry> entries = jar.entries();

		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			String fileName = entry.getName();

			if (fileName.startsWith(rootDir) && fileName.endsWith(".class")) {

				String packageName = fileName.replaceAll("/", ".");
				classList.add(getSimpleName(packageName, ".class"));
			}
		}
	}

	/**
	 * 文件名转类名
	 */
	private static String getSimpleName(String fileName, String suffix) {
		String simpleName = fileName.substring(0, fileName.length() - suffix.length());
		return simpleName;
	}

	/**
	 * 获取包下所有Class
	 */
	public static List<Class<?>> getClassesByPackage(String packageName) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		try {
			List<String> list = getClassNamesByPackage(packageName);
			for (String className : list) {
				Class<?> clazz = Class.forName(className);
				classList.add(clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	public static void main(String[] args) {
		try {

			// List<String> list = PackageScaner.getClassNamesByPackage("com.alibaba.cainiao.vinci._demos");
			// for (String str : list) {
			// System.out.println(str);
			// }
			List<Class<?>> list2 = PackageScaner.getClassesByPackage("com.alibaba.cainiao.vinci._demos.bpmn");
			for (Class<?> clazz : list2) {
				System.out.println(clazz);
			}

			// List<String> list2 = PackageScaner.getClassesExcludedJar("sss.22");
			//
			// for (String str : list2) {
			// System.out.println(str);
			// }

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}