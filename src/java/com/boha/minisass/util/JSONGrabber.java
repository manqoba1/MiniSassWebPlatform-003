package com.boha.minisass.util;

import com.boha.minisass.dto.EvaluationDTO;
import com.boha.minisass.transfer.RequestDTO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author aubreyM
 */
public class JSONGrabber {

    public static void main(String[] args) {

        //create the object you want in the servlet
        RequestDTO req = new RequestDTO();
        req.setRequestType(101);

        EvaluationDTO e = new EvaluationDTO();
        e.setComment("water is clean");
        e.setOxygen(0.6);
        e.setPH(0.8);
        e.setRemarks("ggggggg");
        e.setScore(0.2);
        e.setWaterClarity(0.3);
        e.setWaterTemperature(0.3);
        e.setEvaluationDate(new Date().getTime());
        e.setTeamMemberID(1);
        e.setConditionsID(1);
        e.setEvaluationSiteID(1);

        req.setEvaluation(e);

        //turn the REquestDTO object into a JSON string
        Gson gson = new Gson();
        String json = gson.toJson(req);

        System.out.println("JSON created\n" + json);
        JSONGrabber grabber = new JSONGrabber();

        /*int[] A = {10000000, 900000000, 300000000, -100000000, 500000000};
         //System.out.println("fhds" + grabber.run());
         String str1 = "sifiso", str2 = "fisos";//"Sifiso","sfosii"
         if (checkAnagram(str1, str2)) {
         System.out.println(str2 + " is anagram of " + str1);
         } else {
         System.out.println("Strings are not anagrams.");
         }

         // printOdd();
         if (checkUnique("si")) {
         System.out.println("Unique");
         } else {
         System.out.println("Not Unique");
         }*/
        //sendSMS();
        int[] A = {-1, 3, -4, -5, 1, -6, 2, 1};
        /*A[0] = ;
         A[1] = 3;
         A[2] = -4;
         A[3] = 5;
         A[4] = 1;
         A[5] = -6;
         A[6] = 2;
         A[7] = 1;*/

        System.out.println(equi(A) + "");
    }

    private static void printOdd() {
        for (int i = 1; i < 99; i++) {
            if (i % 2 == 0) {

            } else {
                System.out.println(i + "");
            }
        }
    }

    /*  private static void sendSMS() {
     try {
     TwilioRestClient client = new TwilioRestClient("ACb90cf3b5d65901e089504ca8f1c688ff", "782c267eaa0f112ecfab966c801b2a88");
     List<NameValuePair> params = new ArrayList<NameValuePair>();
     params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
     params.add(new BasicNameValuePair("To", "+27610189037"));
     params.add(new BasicNameValuePair("From", "+27875503938"));

     MessageFactory messageFactory = client.getAccount().getMessageFactory();
     Message message = messageFactory.create(params);
     System.out.println(message.getSid());
     } catch (TwilioRestException e) {
     System.out.println(e.getErrorMessage());
     }
     }*/
    private static boolean checkUnique(String word) {
        char[] charWord = word.toLowerCase().toCharArray();

        for (int i = 0; i < word.length(); ++i) {

            for (int x = 0; x < charWord.length; ++x) {
                if (charWord[x] == word.charAt(i)) {
                    return false;
                }
            }

        }
        return true;
    }

    private static void fibi() {
        int febCount = 15;
        int[] feb = new int[febCount];
        feb[0] = 0;
        feb[1] = 1;
        for (int i = 2; i < febCount; i++) {

            feb[i] = feb[i - 1] + feb[i - 2];

        }

        for (int i = 0; i < febCount; i++) {
            System.out.print(feb[i] + " ");
        }

    }

    public int sun(int[] A) {
        int sum = 0, Q, P, x = 0;
        System.out.println("fhds" + A.length);

        int len = A.length;
        for (int i = 0; i < A.length; i++) {
            Q = (int) (Math.random() * len);
            P = (int) (Math.random() * len);
            System.out.println("Q" + A[Q] + " " + "P" + A[P]);
            sum = A[Q] + A[P];
            if (sum > 1000000000) {
                x = 2;
                break;

            }
            System.out.println("break" + x);
        }
        return sum;
    }

    private static boolean checkAnagram(String word, String anagram) {
        if (word.length() != anagram.length()) {
            return false;
        }
        char[] worChar = word.toLowerCase().toCharArray();
        StringBuilder builder = new StringBuilder(anagram.toLowerCase());

        for (Character c : worChar) {
            int index = builder.indexOf(String.valueOf(c));
            if (index == -1) {
                return false;
            }
            builder.delete(index, index + 1);
        }

        return true;
    }

    public void run() {

        String csvFile = "C:/Users/CodeTribe1/Downloads/RiversExcel.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        List<String[]> list = new ArrayList<String[]>();
        try {

            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                System.out.println(country.length);
                switch (country.length) {
                    case 1:
                        list.add(country);
                        System.out.println("name= " + country[0]);
                        break;
                    case 2:
                        list.add(country);
                        System.out.println("name= " + country[0] + " , name1=" + country[1]);
                        break;
                    case 3:
                        list.add(country);
                        System.out.println("name= " + country[0] + " , name1=" + country[1] + " , name2=" + country[2]);
                        break;
                    case 4:
                        list.add(country);
                        System.out.println("name= " + country[0] + " , name1=" + country[1] + " , name2=" + country[2]
                                + " , name3=" + country[3]);
                        break;
                    case 5:
                        list.add(country);
                        System.out.println("name= " + country[0] + " , name1=" + country[1] + " , name2=" + country[2]
                                + " , name3=" + country[3] + " , name4=" + country[4]);
                        break;
                    case 6:
                        list.add(country);
                        System.out.println("name= " + country[0] + " , name1=" + country[1] + " , name2=" + country[2]
                                + " , name3=" + country[3] + " , name4=" + country[4] + " , name5=" + country[5]
                        );
                        break;
                    case 7:
                        list.add(country);
                        System.out.println("name= " + country[0] + " , name1=" + country[1] + " , name2=" + country[2]
                                + " , name3=" + country[3] + " , name4=" + country[4] + " , name5=" + country[5]
                                + " , name6=" + country[6]);
                        break;
                    case 8:
                        list.add(country);
                        System.out.println("name= " + country[0] + " , name1=" + country[1] + " , name2=" + country[2]
                                + " , name3=" + country[3] + " , name4=" + country[4] + " , name5=" + country[5]
                                + " , name6=" + country[6] + " , name7=" + country[7]);
                        break;
                    case 9:
                        break;

                }
                // System.out.println("code= " + country[2] +" = "+country.length);
                /*if (country[0].equalsIgnoreCase(country[0])) {
                 String[] name = country[2].split(",");
                 if (name.length >1) {
                 System.out.println("name= "+name[0]+" name2="+name[1]);
                 list.add(name);

                 // System.out.println("name= "+name[0]+" name2="+name[1] +" name3="+name[2]);
                 //System.out.println("Country [code= " + country[0] + " , name=" + country[2].split(",")[0] + "]");
                 }
                 if (name.length == 3) {
                 System.out.println("name= "+name[0]+" name2="+name[1] +" name3="+name[2]);
                 list.add(name);
                 }
                 }*/

            }

            for (int i = 0; i < list.size(); i++) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
    }

    public static int solution(int[] a) {
        int result = -1;

        long[] b = IntStream.range(0, a.length).mapToLong(i -> a[i]).toArray();
        long sum = Arrays.stream(b).sum();

        long left = 0;
        long right = 0;

        for (int i = 0; i < b.length; i++) {
            long j = b[i];
            left += j;
            right = sum - left;
            if (left - j == right && (0 <= i && i < b.length)) {
//System.out.println("index " + i);
                result = i;
                //break;
            }
        }

        return result;

    }

    public static int equi(int[] A) {
        long sum = 0;
        long leftSum = 0;

        for (int i = 0; i < A.length; i++) {
            leftSum += A[i];
        }
        for (int i = 0; i < A.length; i++) {
            if (sum == leftSum - A[i]) {
                return i;
            }
            sum += A[i];
            leftSum -= A[i];
        }
        return -1;

    }
}

/* THE RESULT

 JSON created
 {"requestType":101,"province":{"provinceID":3,"provinceName":"Gauteng"}}

 */
