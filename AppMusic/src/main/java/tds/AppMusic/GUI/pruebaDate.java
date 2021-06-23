package tds.AppMusic.GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pruebaDate {
    public static void main(String[] args) {
        String date_time = "Wed 30 20:44:44 CEST 2021";
        SimpleDateFormat dateParser = new SimpleDateFormat("E dd hh:mm:ss zzz yyyy");

            try {
                Date date = dateParser.parse(date_time);
                System.out.println(date);



            } catch (ParseException e) {
                e.printStackTrace();
            }


    }
/*
    public void pruebaDate1(){
        Date dateReal = new Date();
        String stringDateReal = dateReal.toString();
        System.out.println(stringDateReal);

        SimpleDateFormat formatter = new SimpleDateFormat("dow mon dd hh:mm:ss zzz yyyy");
        try {
            Date dateCopy = formatter.parse(stringDateReal);
            String stringDateCopy = dateCopy.toString();
            System.out.println(stringDateCopy);
            // System.out.println(stringDateCopy.equals(stringDateReal));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void pruebaDate2(){

    }
*/
}

/*
    String date_time = "Wed Jun 23 15:51:52 CEST 2021";
    SimpleDateFormat dateParser = new SimpleDateFormat("dow mon dd hh:mm:ss zzz yyyy");
        {
                try {
                Date date = dateParser.parse(date_time);
                System.out.println(date);


                } catch (ParseException e) {
                e.printStackTrace();
                }
       }

 */