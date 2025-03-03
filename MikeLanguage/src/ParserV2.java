import java.io.*;

import java.util.*;

public class ParserV2 {

    ArrayList<String> codeList = new ArrayList<String>();
    ArrayList<String> types = new ArrayList<String>();
    private HashMap<String, Integer> variablesDictionary = new HashMap<>();

    String typesFile = "src/Types.txt";
    String variablesFile = "src/Variables.txt";

    String tempString;
    String tempIntString;

    public void PrintCode(String codeToProcess)
    {
       // codeToProcess = codeToProcess.replace("\n", " ");
        String[] codeArray = codeToProcess.split(";");
        codeArray = codeToProcess.split("\n");
        codeList.addAll(Arrays.asList(codeArray));

        ProcessCode();
    }
//2
    public void ProcessCode()
    {
        //first check for variables in code against variables in Textfile
        //check for types
        ReadFiles();
        CodeLineByLine();
        //next check for operators , swap out variables if necessary, do assignments


    }

    //3
    //store variables in local variable list.
    //store types in local type list
    public void ReadFiles()
    {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(variablesFile));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] variableBreakdown = line.split(" ");

               variablesDictionary.put(variableBreakdown[0], Integer.parseInt(variableBreakdown[1]));
               System.out.println("Var Name: " + variableBreakdown[0] + " Var Value: " + variableBreakdown[1]);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Variables file not found. Create Variables.txt and put in src..." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(typesFile));
            String line;
            while((line = reader.readLine()) != null)
            {
                if(!line.isEmpty()) {
                    types.add(line.toLowerCase());
                    System.out.println("Type Name: " + line);
                }

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Variables file not found. Create Variables.txt and put in src..." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void WriteToFile()
    {
        try
        {
            FileWriter writer = new FileWriter("src/Variables.txt", true);

            writer.write(tempString + " " + tempIntString + "\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot write to Variables.txt... " + e.getMessage());
        }

    }
    public void CodeLineByLine()
    {
        for(String line : codeList)
        {
            //parenthesis
            //multiplication + division l->r
            //addition/subtraction l->r
            //if = assign
            //if == compare
            //use var keyword to instantiate new or overwrite var - i.e. int ten = 10; //assignment
            //first check does it start with a 'type' if so require = sign && it is assignment

            for(String s: types)
            {
                if(line.startsWith(s + " "))
                {
                    //this is a assignment
                    if(line.contains("="))
                    {
                        String[] lineOfCode = line.split(" ");

                        int value = Integer.parseInt(lineOfCode[3].replaceAll("[^0-9]", ""));
                        variablesDictionary.put(lineOfCode[1], value);
                        System.out.println("Var Name: " + lineOfCode[1] + " Var Value: " + lineOfCode[3]);

                        tempString = lineOfCode[1];
                        tempIntString = lineOfCode[3];

                        WriteToFile();
                        break;
                    }
                    else{
                        System.out.println("Assignment has variable name in correct spot but need '=' sign");}
                }
            }


        }

    }
    }
