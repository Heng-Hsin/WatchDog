package test;

import java.util.prefs.Preferences;

public class PreferenceTest {
  private Preferences prefs;

  public void setPreference() {
    // This will define a node in which the preferences can be stored
    prefs = Preferences.userRoot().node(this.getClass().getName());
    String ID1 = "Test1";
    String ID2 = "Test2";
    String ID3 = "Test3";

    // First we will get the values
    // Define a boolean value
    System.out.println(prefs.getBoolean(ID1, true));
    // Define a string with default "Hello World
    System.out.println(prefs.get(ID2, "Hello There"));
    // Define a integer with default 50
    System.out.println(prefs.getInt(ID3, 50));

    // now set the values
    prefs.putBoolean(ID1, false);
    prefs.put(ID2, "Hello Ttt");
    prefs.putInt(ID3, 45);

    // Delete the preference settings for the first value
    prefs.remove(ID1);

  }
  
  public void setPreference2(){
	  
	// Retrieve the user preference node for the package com.mycompany
	  Preferences prefs = Preferences.userNodeForPackage(test.PreferenceTest.class);

	  // Preference key name
	  final String PREF_NAME = "name_of_preference";

	  // Set the value of the preference
	  String newValue = "a string";
	  prefs.put(PREF_NAME, newValue);

	  // Get the value of the preference;
	  // default value is returned if the preference does not exist
	  String defaultValue = "default string";
	  String propertyValue = prefs.get(PREF_NAME, defaultValue); // "a string"
  }

  public static void main(String[] args) {
    PreferenceTest test = new PreferenceTest();
    test.setPreference();
  }
} 
