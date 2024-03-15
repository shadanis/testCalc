import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private  static  int[] values = {0 , 1, 4, 5, 9 , 10, 40, 50, 90, 100};
    private  static  String[] rims = {"", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner in = new Scanner(System.in);
        System.out.println("Input: ");
        String strIn = in.nextLine();

        System.out.println("Output:");
        try {
            System.out.println(calc(strIn));
        }
        catch (Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        in.close();
    }

    public  static  String calc(String input) throws Exception
    {
        String[] strs = input.split("[+*/-]");
        if (strs.length == 1)
            throw  new Exception("т.к. строка не является математической операцией");
        else if (strs.length == 3)
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        else if (strs.length == 2)
        {
            int a = 0;
            int b = 0;
            boolean flag = false;
            try {
                a = Integer.parseInt(strs[0].trim());
                b = Integer.parseInt(strs[1].trim());
            }
            catch (NumberFormatException ex){
                flag = true;
            }
            if(flag) {
                try {
                    a = Rim.valueOf(strs[0].trim()).getValue();
                    b = Rim.valueOf(strs[1].trim()).getValue();
                }
                catch (Exception ex)
                {

                }
                if (a == 0 || b == 0)
                    throw new Exception("т.к. используются одновременно разные системы счисления");




            }

            int res = 0;
            int index = input.indexOf('+');
            if (index != -1)
            {
                res = a + b;
            }
            index = input.indexOf('-');
            if (index != -1)
            {
                if (a - b <= 0) throw  new Exception("т.к. в римской системе нет отрицательных чисел");
                res = a-b;
            }
            index = input.indexOf('*');
            if (index != -1)
            {
                res = a * b;
            }
            index = input.indexOf('/');
            if (index != -1)
            {
                res = a / b;
            }
            if (flag) {

                return toRim(res);
            }
            else
                return  Integer.toString(res);
            //return "OK";
        }
        else throw new Exception("dsadas");

        //return  "ok";
    }

    public static  String toRim(int value)
    {
        int index = find(value, 0, values.length - 1);
        if(value == values[index])
            return rims[index];
        return  rims[index] + toRim(value-values[index]);
    }

    public  static int find(int value, int first, int last){
        if(first == last)
            return first;
        if (values[first] == value)
            return  first;
        if (values[last] == value)
            return  last;
        int median = (first + last) / 2;
        if (median == first)
            return first;
        if (value == values[median])
            return  median;
        if (value > values[median])
            return find(value, median, last);
        else
            return find(value, first, median);
    }
}