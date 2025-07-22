package org.mawenhao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 研究JDK新特性(相比较与JDK8)
 *
 * @author mawenhao 2023/5/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Jdk17 {
	private Integer version;

	public static void main(String[] args) {
		//jdk10开始,局部变量定义可以使用关键字var使得对象声明更加简洁
		var abc = 19;
		var jdk17 = new Jdk17(17);
		System.out.println(jdk17);

		//jdk14开始, switch关键字的新用法, 叫做switch表达式
		//case标签可用
		String version = switch (jdk17.getVersion()) {
			case 18 -> "18";
			case 17 -> "17";
			default -> "null";
		};
		System.out.println(version);
		//jdk15开始, 新增文本块text block
		String newStr = """
				Hello
				World
				""";
		System.out.printf(newStr);
		//jdk15开始, 字符串格式化可以使用formatted
		String hi = "Hello, %s".formatted("World");
		System.out.println(hi);
	}
}