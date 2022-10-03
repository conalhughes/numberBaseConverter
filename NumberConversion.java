/**
 * convert a number of a given base to another base by first converting to decimal
 *
 * @author conalhughes
 * @version 1.0
 */
import java.util.*;
import java.lang.Math;
public class NumberConversion
{
    public static void main(String [] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number to be converted, followed by its base");
        String input = sc.nextLine(); // reads number as a string in case of any letters
        int base1 = Integer.parseInt(sc.nextLine()); // first base
        System.out.println("Enter the base to be converted to:");
        int base2 = Integer.parseInt(sc.nextLine()); // target base

        String lookup = "0123456789ABCDEF"; // lookup string used to help convert from one base to another

        int dec = toDecimal(input, base1, lookup); // convert to decimal
        String output = fromDecimal(dec, base2, lookup); // convert from decimal

        System.out.println(input + " in base " + base2 + " is: " + output); // print result to screen
    }

    /*
     * decimal numbers (base 10) are in the form a*(10^0) + b*(10^1) + c*(10^2) + ..... + x*(10^n) for an n digit number
     * any other base is the exact same logic
     * for example binary (base 2) is a*(2^n), hexadecimal (base 16) is a*(16^n)
     * converting to decimal first allows the program to multiply by these indices before converting back to another base
     */
    public static int toDecimal(String s, int base, String lookup)
    {
        int x = 0; // decimal number
        // p represents the current character in the original string
        // i represents the current power of the 
        for (int p = 0, i = s.length()-1; p < s.length(); p++, i--) 
        {
            char c = s.charAt(p); // current digit being investigated
            int val = lookup.indexOf(c); // find numerical value
            x += val * (int)Math.pow(base,i); // multiply value*(base^i)
        }
        return x;
    }
    /*
     * when converting back from decimal, the modulus (%) function is used
     * when you % by the base of the number, you get the least significant digit
     * after dividing by the base, the least significant digit increases by an order
     */
    public static String fromDecimal(int x, int base, String lookup)
    {
        String s = ""; // resulting number
        while(x != 0)
        {
            int val = x % base; // get the least significant digit
            s = lookup.charAt(val) + s; // adds it to the front of the new number
            x /= base; // divide by base, increasing the order of the least significnt digit
        }
        return s;
    }
}