/*
 * Dr. K
 * CSC 210
 * Program 1
 * Takes input from text file w number, current base, base to convert to, and max bits, and completes the operation as indicated.
 */

import java.util.Scanner;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{

        File inp = new File("inp.txt"); //read in the file.

        try (Scanner sc = new Scanner(inp)) {

            int numInstr = Integer.parseInt(sc.nextLine()); //first line is instr in block and second is num of blocks
            int numBlocks = Integer.parseInt(sc.nextLine());
            Cache myCache = new Cache(numInstr, numBlocks); //make your cache
            CPU myCPU = new CPU(myCache); //make your cpu-- put cache in it
            int lineNum = 0;

            while(sc.hasNextLine()){ 
                String currentInstr = sc.nextLine(); //read in the next line
                
                if (currentInstr.equals("CLEAR")){ //if the instr is to clear
                    myCPU.clear();
                    lineNum++; //there was another instruction
                }
                else if (currentInstr.length() > 2){ //if not a blank line
                    if (currentInstr.substring(0, 3).equals("DEL")){ //if to del
                    myCPU.delBlock(Integer.parseInt(currentInstr.substring(currentInstr.indexOf(" ") + 1,currentInstr.length()))); //delete block num (whatever comes after the del)
                    }
                    else{ //otherwise its just trying to search
                        Numb instrNum = new Numb(currentInstr,16,10,8);
                        myCPU.search(instrNum); //search in the cpu for the spec address
                    }
                    lineNum++; //either one is considered an instr
                }
                else{ //must just be blank line
                    //////system.out.println("fueisnfoisen");
                }

                if ((lineNum) % 100 == 0){ //every 100th instr
                    System.out.println(lineNum);
                    System.out.println(myCPU);
                }
            }
        }catch (FileNotFoundException e) { 
        }
    } 
}
    
