import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class Parser {

    public ArrayList<String> output = new ArrayList<>();

    public ArrayList<String> codeList = new ArrayList<>();

    public ArrayList<String> integerList = new ArrayList<>();

    public Dictionary<String, Integer> integerVariables = new Hashtable<>();

    public void ClearCode()
    {
        output.clear();
        codeList.clear();
        integerList.clear();
    }

    public void PrintCode(String codeToProcess) {

        ClearCode();

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



}
