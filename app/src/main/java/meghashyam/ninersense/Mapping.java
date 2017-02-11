package meghashyam.ninersense;

/**
 * Created by megha on 12/7/2016.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class    Mapping extends Thread  {

    public static ArrayList<String> addresses = new ArrayList<String>();
    public static ArrayList<String> deviceNames=new ArrayList<String>();
    public static ArrayList<String> MACAddresses=new ArrayList<String>();
    String rasp="B8:27:EB:24:BB:75";
    static String ip;


    public static void AnaLyzeNetwork(){
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("nmap -sn -oG ip.txt 192.168.0.0/24");// 192.168.1.0/24  laptop servr address in our case scans all 256 ip addresses from 192.168.1.0-255
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(pr.getInputStream()));
            String line = null;
            while((line=input.readLine()) != null) {
                if (line.contains("Nmap scan report for")){
                    String[] elements = line.split(" ");
                    int end = elements.length-1;
                    String ip_address = elements[end];
                    String line2 = input.readLine();
                    if (line2.contains("Host is up")){
                        addresses.add(ip_address);
                        String line3=input.readLine();
                        if(line3.startsWith("MAC")){
                            MACAddresses.add(line3.substring(13,30));
                            deviceNames.add(line3.substring(32, line3.length()-1));}
                    }
                }
            }
            for(String j:addresses)
                System.out.println(j);
            for(String i:deviceNames)
                System.out.println(i);
            for(String z:MACAddresses)
                System.out.println(z);

            int exitVal = pr.waitFor();
            if(exitVal!=0){
                System.out.println("Exited with error code "+exitVal);
            }
            else{
                System.out.println("Network Analyzed Successfully.");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static String FindIPByMAC(String s){
        String a="Error";
        AnaLyzeNetwork();
        Object o=s;
        //int z=0;
        int z=MACAddresses.indexOf(o);
        if(z==-1){
            System.out.println("Raspberry not found");
        }
        else if(deviceNames.get(z)=="Raspberry Pi Foundation")
        {
            a=addresses.get(z);
        }

        return a;
    }

    public static boolean IfDatabaseConnectionAvailable(){
        boolean conectivity;
        if(Mapping.FindIPByMAC("B8:27:EB:7F:F2:EF")=="error"){
            conectivity = false;
        }
        else{
            System.out.println("Connected to Raspberry ");
            conectivity = true;
        }
        return conectivity;
    }
}