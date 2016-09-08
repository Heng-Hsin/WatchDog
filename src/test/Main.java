package test;
import java.util.prefs.Preferences;

public class Main {
  public static void main(String args[]) throws Exception {
    Preferences prefsRoot = Preferences.userRoot();
    Preferences myPrefs = prefsRoot.node("PreferenceExample");

    myPrefs.put("A", "a");
    myPrefs.put("B", "b");
    myPrefs.put("C", "c");

    System.out.println("Node's absolute path: " + myPrefs.absolutePath());

  }
}