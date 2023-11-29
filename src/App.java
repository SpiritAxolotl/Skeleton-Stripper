import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

class App {
  public static void main(String[] args) throws FileNotFoundException {
    //preprocessing that removes comments ("//")
    File temp = new File("resources/simfiles/temp.txt");
    try {
      temp.createNewFile();
      temp.deleteOnExit();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    //delete previous output files
    for (File f : new File("resources/simfiles/out").listFiles()) {
      f.delete();
    }
    for (File f : new File("resources/simfiles/pre").listFiles()) {
      f.delete();
    }
    for (File f : new File("resources/simfiles/in").listFiles()) {
      {
        PrintWriter printer = new PrintWriter(temp);
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
          String line = scan.nextLine();
          if (line.contains("//")) {
            int length = line.indexOf("//");
            //if it's the "dance single" line
            if (line.length() >= 3 && line.substring(0,4).equals("//--")) {
              printer.println(line.strip());
            //remove all other comments
            } else if (line.substring(0, length).strip().length() > 0) {
              printer.println(line.substring(0, length).strip());
            }
          } else {
            printer.println(line.strip());
          }
        }
        scan.close();
        printer.close();
      }
      {
        File simfile = new File("resources/simfiles/pre/"+f.getName());
        try {
          simfile.createNewFile();
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        PrintWriter printer = new PrintWriter(simfile);
        Scanner scan = new Scanner(temp);
        while (scan.hasNextLine()) {
          String line = scan.nextLine();
          printer.println(line);
        }
        scan.close();
        printer.close();
      }
    }
    
    
    //allowed stuff (if it doesn't exist, adds it)
    String[] allowedSections = {
      "TITLE",
      "SUBTITLE",
      "ARTIST",
      "TITLETRANSLIT",
      "SUBTITLETRANSLIT",
      "ARTISTTRANSLIT",
      "LYRICSPATH",
      "MUSIC",
      "DISPLAYBPM", //delete if not included
      "OFFSET",
      "STOPS", //idk what to do for "FREEZES" so I might include a case for that later
      "BPMS",
      "TIMESIGNATURES",
      "DELAYS"
    };
    //reset the values of these if included. adds them if they don't exist
    String[] resetSections = {
      "GENRE",
      "CREDIT",
      "BANNER",
      "BACKGROUND",
      "CDTITLE",
      "SAMPLESTART",
      "SAMPLELENGTH",
      "SELECTABLE",
      "BGCHANGES", //"BGCHANGES2", "BGCHANGES3", "ANIMATIONS",
      "FGCHANGES",
      "ATTACKS",
      "WARPS",
      "KEYSOUNDS"
    };
    
    //for (;;) {
      //if in allowedsections go until semicolon
      //if in resetsections replace with template
      //hardcode chart replacement to be one empty measure on a challenge chart
    //}
    
    //everything else gets removed
    System.out.print("Done :D");
  }
}