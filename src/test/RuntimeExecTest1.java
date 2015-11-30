package test;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class RuntimeExecTest1 {

	public static void main(String[] args) {
		try {
			System.out.println("Opening notepad");
			Runtime runTime = Runtime.getRuntime();
			Process process = runTime.exec("notepad");
			try {
				String temp=ManagementFactory.getRuntimeMXBean().getName();
				System.out.println(temp);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Closing notepad");
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}