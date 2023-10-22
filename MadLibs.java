import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class MadLibs {

  /** Returns an array of strings from the contents of a text file. */
  public static String[] textToArray(String fileName)
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));

      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
    } 
    finally {
      temp = temp.trim();
  
      String[] array = temp.split(" ");
      return array;
    }
  }
  
  /** Returns a string with deleted punctuation and underscores replaced with spaces. */
  private static String cleanBlank(String word) {
    String newWord = word.substring(1, word.length()).toLowerCase();
    
    for (int i = 0; i < newWord.length(); i++) {
      if (newWord.charAt(i) == '_') {
        newWord = newWord.substring(0, i) + " " + newWord.substring(i + 1);
      }
      if ((i == newWord.length() - 1) && !Character.isAlphabetic(newWord.charAt(i))) {
        newWord = newWord.substring(0, i);
      }
    }
    
    return newWord;
  }

  /** Returns a story with certain words replaced with user-provided ones. */
  public static String replace(String story) {
    String[] storyArray = textToArray(story);

    Scanner input = new Scanner(System.in);
    int count = 0;
    for (String word: storyArray){
      if (word.contains("*")){
        System.out.println("Enter a " + cleanBlank(word) + ": ");
        storyArray[count] = input.nextLine();
        
        if (word.charAt(word.length() - 1) == (',')) {
          storyArray[count] = storyArray[count] + ",";
        } else if (word.charAt(word.length() - 1) == ('.')) {
          storyArray[count] = storyArray[count] + ".";
        }
      } 
      count++;
    }
    input.close();

    String finalStory = "";
    for (String word: storyArray) {
      finalStory += (word + " ");
    }
    
    return finalStory;
  }

  public static void main(String[] args)
  {
    System.out.println("Hello, welcome to Mad Libs! \n");
    System.out.println(replace("template.txt"));
  }
  
 
}
