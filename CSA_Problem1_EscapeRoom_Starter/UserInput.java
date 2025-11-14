/**
 * Validate user input according to string array of acceptable inputs.
 * 
 * @author GShorr, PLTW
 * @version 4/16/2019
 */
import java.util.Scanner;

 public class UserInput
{
    /**
     * Verifies that one of the string array provided as an argument will be
     * returned. 
     * <P>
     * Example usage:
     * String input = User.getValidInput("yes", "no", "y", "n");
     * <P>
     * Only yes, no, y, and n are valid inputs. If the user types anything
     * else, they will be prompted to re-enter.
     * <P>
     * Uppercase versions of the commands are acceptable and automatically
     * converted to lowercase prior to validating.
     * <P>
     * @return the valid command the user entered
     * 
     */
    public static String getValidInput(String[] validInputs)
    {
        String input = "";
        boolean valid = false;
        do
        {
            input = getLine().toLowerCase();
            for(String str : validInputs)
            {
                if(input.equals(str.toLowerCase())) {
                    System.out.println("This is valid.");
                    valid = true;
                }
            }
            if(!valid)
                System.out.print("Invalid input. Please try again\n>");
        }
        while(!valid);
        if (input.equals("q") || input.equals("quit")) {
            return "q";
        } else if (input.equals("h") || input.equals("help") || input.equals("?")) {
            return "h";
        } else if (input.equals("l") || input.equals("left")) {
            return "l";
        } else if (input.equals("r") || input.equals("right")) {
            return "r";
        } else if (input.equals("u") || input.equals("up")) {
            return "u";
        } else if (input.equals("d") || input.equals("down")) {
            return "d";
        } else if (input.equals("replay")) {
            return "re";
        } else if (input.equals("p") || input.equals("pickup")) {
            return "p";
        } else if (input.equals("jr") || input.equals("jump")) {
            return "jr";
        } else if (input.equals("jl") || input.equals("jump left")) {
            return "jl";
        } else if (input.equals("ju") || input.equals("jump up")) {
            return "ju";
        } else if (input.equals("jd") || input.equals("jump down")) {
            return "jd";
        }
        return "h";
    }
    
    public static String getLine()
    {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }


  }