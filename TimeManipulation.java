import java.util.regex.*;

/**
 * TimeManipulation program to add minutes to a string
 * that represents time and to output the calculated result
 * as a string in a 12hr format "[H]H:MM {AM|PM}"
 *
 * @author  Warrington Bloomfield
 * @since   06-04-2017
 */
public class TimeManipulation {

    static String invalidFormat = "Invalid time entered. " +
            "Please enter a time value that conforms to the 12hr format, [H]H:MM {AM|PM} ";

    static String invalidNumber = "Invalid minutes entered. " +
            "Please enter an integer value to add those minutes. ";


    public static String meridiem;
    public static Pattern pattern;
    public static Matcher regexMatch;
    public static StringBuilder stringBuilder;
    public static StringBuilder result;
    public static boolean addGivenMins;
    public static int hoursGiven;
    public static int minsGiven;
    public static int resultingHours;
    public static int resultingMins;
    public static int meridiemFluctuationCount; // times to switch meridiem (am/pm) value
    public static StringBuilder resultStr;
    private static int hoursToDeduct;
    private static int hoursToAdd;


    public static void main(String[] args) {
        System.out.print("Adding 200 minutes to 9:13 AM = ");
        addMinutes("9:13 AM", 200);

        System.out.print("Adding 1630 minutes to 9:00 AM = ");
        addMinutes("9:00 AM", 1630);

        System.out.print("Adding -1 minutes to 9:00 AM = ");
        addMinutes("9:00 AM", -1);

        System.out.print("Adding -1270 minutes to 9:00 AM = ");
        addMinutes("9:00 AM", -1270);

        System.out.print("Adding -1630 minutes to 9:00 AM = ");
        addMinutes("9:00 AM", -1630);

        System.out.print("Adding -1 to 12:00 PM = ");
        addMinutes("12:00 PM", -1);

        System.out.print("Adding 1 to 12:00 PM = ");
        addMinutes("12:00 PM", 1);
    }

    /**
     * Adds minutes to a given time formatted as [H]H:MM {AM|PM}
     *
     * @param time the 12hr formatted string representing time
     * @param minsAdded signed integer value
     */
    public static void addMinutes(String time, Integer minsAdded) {

        if(time==null || !validateTimeGiven(time)){  //  checks for invalid time format
            System.out.println(invalidFormat);
            return ;
        }

        if(minsAdded==null){
            System.out.println(invalidNumber);
            return ;
        }

        //Sets boolean value for adding or subtracting minutes based on integer sign
        addGivenMins = minsAdded>0 ? true : false;

        meridiem = getMeridiem(time);
        String hour = getHour(time);
        String mins = getMins(time);
        if(addGivenMins){
             resultStr = addValues(minsAdded, hour, mins);
        }else{
             resultStr = minusValues(minsAdded, hour, mins);
        }
        System.out.println(resultStr.toString());
    }

    /**
     * Adds minutes to the given time
     *
     * @param minsToBeAdded positive integer value
     * @param hour string representation of the hour supplied by the user
     * @param minutes string representation of the mins supplied by the user
     * @return StringBuilder value of the result
     */
    public static StringBuilder addValues(int minsToBeAdded, String hour, String minutes) {
        hoursGiven = Integer.valueOf(hour);
        minsGiven = Integer.valueOf(minutes);
        hoursToAdd = minsToBeAdded/60;
        int minsToAdd = minsToBeAdded%60;

        resultingHours = hoursGiven + hoursToAdd;
        resultingMins = minsGiven + minsToAdd;
        formatNewTime();
        return result;
    }

    /**
     * Formats the new time in accordance to  "[H]H:MM {AM|PM}"
     */
    public static void formatNewTime() {
        result = new StringBuilder();
        adjustMeridiem();
        formatHours();
        formatMinutes();
        result.append(meridiem.toUpperCase());
    }

    /**
     * Formats minutes to the correct format
     */
    public static void formatMinutes() {
        result.append(resultingHours + ":");
        if(resultingMins<=9){
            result.append("0" + resultingMins + " ");
        }else{
            result.append(resultingMins + " ");
        }
    }

    /**
     * Subtracts minutes from the given time
     *
     * @param minsSubtracted negative integer value
     * @param hour string representation of the hour supplied by the user
     * @param minutes string representation of the mins supplied by the user
     * @return StringBuilder value of the result
     */
    public static StringBuilder minusValues(int minsSubtracted, String hour, String minutes) {
        minsSubtracted = Math.abs(minsSubtracted);
        meridiemFluctuationCount = 0;
        hoursGiven = Integer.valueOf(hour);
        minsGiven = Integer.valueOf(minutes);
        hoursToDeduct = minsSubtracted/60;
        int minsToDeduct = minsSubtracted%60;

        if(minsToDeduct>minsGiven){
            hoursToDeduct++;
            minsGiven += 60;
        }

        resultingHours = hoursGiven - hoursToDeduct;
        setHourAntiClockwise();
        resultingMins = minsGiven - minsToDeduct;
        resultingMins = Math.abs(resultingMins);
        formatNewTime();
        return result;
    }

    /**
     * Sets the resulting hour anti-clockwise in time
     */
    public static void setHourAntiClockwise() {
        if(resultingHours<0){
             meridiemFluctuationCount = 1;
            if(Math.abs(resultingHours) >= 12){
                meridiemFluctuationCount += Math.abs(resultingHours) / 12;
                resultingHours = 12 - Math.abs(resultingHours) % 12;
            }else{
                resultingHours = 12 - Math.abs(resultingHours);
            }
        }else if(resultingHours>0 && resultingHours<12 && (hoursGiven < hoursToDeduct || hoursGiven==12)){
            meridiemFluctuationCount = 1;
        }
    }

    /**
     * Formats the hour value to match 12 hr format
     */
    public static int formatHours() {
        if(resultingHours>12){
            resultingHours = resultingHours % 12;
            if(resultingHours==0){
                resultingHours = 12;
            }
        }else if(resultingHours==0){
            resultingHours = 12;
        }
        return resultingHours;
    }

    /**
     * Adjusts the meridiem (am/pm) value
     * to reflects the new time. Function returns if
     * the hour remains within the same 12-hr meridiem
     */
    public static void adjustMeridiem() {
        if (keepSameMeridiem()) return;

        if(resultingHours>=12 && meridiemFluctuationCount ==0){
            int value = resultingHours / 12;
            while(value!=0){
                meridiem = setMeridiem(meridiem);
                value--;
            }
        }
        else{
            while(meridiemFluctuationCount !=0){
                meridiem = setMeridiem(meridiem);
                meridiemFluctuationCount--;
            }
        }
    }

    /**
     * Checks the meridiem time
     *
     * @return  true if hour is kept within the same
     *            meridiem, false otherwise
     */
    private static boolean keepSameMeridiem() {
        if(hoursGiven==12 && hoursToAdd<12 && addGivenMins){
            return true;
        }
        return false;
    }


    /**
     * Switches the meridiem time
     *
     * @param meridiem current meridiem value
     * @return  new meridiem value
     */
    public static String setMeridiem(String meridiem) {
        if(meridiem.equals("pm")){
            meridiem= "am";
        }else if (meridiem.equals("am")){
            meridiem= "pm";
        }
        return meridiem;
    }

    /**
     * Gets the meridiem value (am/pm) based on the string value given
     *
     * @param time "[H]H:MM {AM|PM}" formatted string
     * @return true if time is formatted correctly, false if not
     */
    public static boolean validateTimeGiven(final String time){
        pattern = Pattern.compile("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");
        regexMatch = pattern.matcher(time);
        return regexMatch.matches();
    }


    /**
     * Gets the meridiem value (am/pm) based on the string value given
     *
     * @param time "[H]H:MM {AM|PM}" formatted string
     * @return string value of the meridiem found
     */
    public static String getMeridiem(String time){
        setRegexMatcher(time, "[A-Za-z]{2,2}");
        while(regexMatch.find()){
            if(regexMatch.group().length() != 0){
               stringBuilder.append(regexMatch.group().trim().toLowerCase());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Sets the proper regex to allow string search capability
     *
     * @param time "[H]H:MM {AM|PM}" formatted string
     * @param regex pattern to allow search for a part of the string
     */
    public static void setRegexMatcher(String time, String regex) {
        pattern = Pattern.compile(regex);
        regexMatch = pattern.matcher(time);
        stringBuilder = new StringBuilder();
    }

    /**
     * Gets the hour value based on the string value given
     *
     * @param time "[H]H:MM {AM|PM}" formatted string
     * @return string value of the hour found
     */
    public static String getHour(String time){
        setRegexMatcher(time, "^[^\\:]*");
        while(regexMatch.find()){
            if(regexMatch.group().length() != 0){
                stringBuilder.append(regexMatch.group().trim());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Gets the mins value based on the string value given
     *
     * @param time "[H]H:MM {AM|PM}" formatted string
     * @return string value of the mins found
     */
    public static String getMins(String time){
        setRegexMatcher(time, ":(.+)[0-9]");
        while(regexMatch.find()){
            if(regexMatch.group().length() != 0){
                stringBuilder.append(regexMatch.group().trim().replace(":", ""));
            }
        }
        return stringBuilder.toString();
    }
}
