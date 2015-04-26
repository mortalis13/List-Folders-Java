package tests;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;
import java.util.*;

public class Find {
  
  static String text="";
  static final String nl="\n";

  public static class Finder extends SimpleFileVisitor<Path> {

    private int numMatches = 0;

    Finder() {
    }

    // Prints the total number of
    // matches to standard out.
    void done() {
      System.out.println("Matched: " + numMatches);
    }

    // Invoke the pattern matching
    // method on each file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
      String name=file.getFileName().toString();
      text+=name+nl;
      return CONTINUE;
    }

    // Invoke the pattern matching
    // method on each directory.
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
      String name=dir.getFileName().toString();
      name="["+name+"]"+nl;
      text+=name;
      return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
      System.err.println(exc);
      return CONTINUE;
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Start");
    
    String path = "C:/1-Roman/Documents/8-test/list-test/en/";

    Path startingDir = Paths.get(path);
    Finder finder = new Finder();
    Files.walkFileTree(startingDir, finder);
    
    System.out.println(text);
    
    System.out.println("End");
  }
}
