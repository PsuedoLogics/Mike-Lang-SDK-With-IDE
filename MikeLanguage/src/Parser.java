import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class Parser {

    private ArrayList<String> output = new ArrayList<>();

    private ArrayList<String> codeList = new ArrayList<>();

    private ArrayList<String> integerList = new ArrayList<>();

    private String stringToDoMathOn;

    public Dictionary<String, Integer> integerVariables = new Hashtable<>();

    public void ClearCode()
    {
        output.clear();
        codeList.clear();
        integerList.clear();
    }

    public void PrintCode(String codeToProcess) {

        ClearCode();
        codeToProcess = codeToProcess.replace("\n", " ");
        String[] codeArray = codeToProcess.split(";");
        codeList.addAll(Arrays.asList(codeArray));

        Syntax();
        ProcessCode();
        OutputCode();
    }

    public void Syntax()
    {
        //foreach line of code
        for(String s: codeList)
        {
            String[] lineOfCode = s.split(" ");

            for(int l = 0; l < lineOfCode.length; l++)
            {
               if(lineOfCode[l].equalsIgnoreCase("int"))
               {
                   if(lineOfCode[l+2].equalsIgnoreCase("="))
                   {


                        System.out.println( "Variable type: " + lineOfCode[l] + " Variable Name: "+ lineOfCode[l+1] + " Operator: "+ lineOfCode[l+2] + " Saved Value: " + lineOfCode[l+3]);


                       integerVariables.put(lineOfCode[l+1], Integer.parseInt(lineOfCode[l+3]));

                       integerList.add(lineOfCode[l + 1]);
                   }
               }

            }
        }
    }

   public void ProcessCode()
   {
            for(String code : codeList) {

                if(code.contains("+") || code.contains("-") || code.contains("*") || code.contains("/"))
                {
                    stringToDoMathOn = code;
                    DoMath();
                }

                if (code.contains("print"))
                {

                    code = code.replace("print", "");


                    for(String s: integerList)
                    {
                        if(code.contains(s))
                        {
                            code = code.replace(s, integerVariables.get(s).toString());
                        }
                    }

                    output.add(code);
                }

            }

   }

   public void OutputCode()
   {
       String temp = "";
       for(String s : output)
       {
           temp = temp + "" + s;
           System.out.println(s);

       }
       Main.outputLabel.setText(temp);
   }

   public void DoMath()
   {

       for(char c: stringToDoMathOn.toCharArray())
       {
           if(c == '+')
           {
               String[] additionStrings = stringToDoMathOn.split("\\+");
               additionStrings[0] = additionStrings[0].trim();
               additionStrings[1] = additionStrings[1].trim();

               for(String var : integerList)
               {
                   if(additionStrings[0].contains(var))
                       additionStrings[0] = integerVariables.get(var).toString();
                   if(additionStrings[1].contains(var))
                       additionStrings[1] = integerVariables.get(var).toString();
               }

               try {
                   int out = Integer.parseInt(additionStrings[0]) + Integer.parseInt(additionStrings[1]);
                   output.add(String.valueOf(out));
               }
               catch(Exception e) {
                   output.add(e + " variable does not exist...");
               }

           }
       }

   }


}
