package com.bk.convertnumber;

/**
 *
 * @author BASHIR
 */
public class Convert {

    public Convert() {
    }

    final private static String[] units = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
        "Nineteen"};
    final private static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
        "Ninety"};

    private static String convert(Integer number) {

        if (number < 20) {
            return units[number];
        }
        if (number < 100) {
            return tens[number / 10] + ((number % 10 > 0) ? " " + convert(number % 10) : "");
        }
        if (number < 1000) {
            return units[number / 100] + " Hundred" + ((number % 100 > 0) ? " and " + convert(number % 100) : "");
        }
        if (number < 1000000) {
            return convert(number / 1000) + " Thousand " + ((number % 1000 > 0) ? " " + convert(number % 1000) : "");
        }
        return convert(number / 1000000) + " Million "
                + ((number % 1000000 > 0) ? " " + convert(number % 1000000) : "");
    }

    public static String convertNumberToEnglishWords(String number) throws NumberFormatException {

        if (isFloating(number)) {
            int x = castToInt(number);
            String point = getPoint(number);

            String st1 = convert(x);
            String st2 = " Point " + convert(Integer.parseInt(getPoint(number)));

            return st1 + "" + st2;
        } else {
            Integer i = Integer.parseInt(number);
            String result = convert(i);

            return result;
        }

//        return null;//convert(i);
    }

    private static boolean isFloating(String number) {
        if (number.contains(".")) {
            return true;
        } else {
            return false;
        }

    }

    private static int castToInt(String number) {

        int value = (int) Float.parseFloat(number);
        return value;

    }

    private static String getPoint(String number) {

        for (int i = 0; i < number.length(); i++) {

            if (number.charAt(i) == '.') {
                String cv = number.substring(i + 1, number.length());
                return cv;
            }
        }

        return null;
    }

    // ----------------------------------------------------------
    public static String convertNumberToArabicWords(String number) throws NumberFormatException {

        // check if the input string is number or not
        Double.parseDouble(number);

        // check if its floating point number or not
        if (number.contains(".")) { // yes
            // the number
            String theNumber = number.substring(0, number.indexOf('.'));
            // the floating point number
            String theFloat = number.substring(number.indexOf('.') + 1);
            // check how many digits in the number 1:x 2:xx 3:xxx 4:xxxx 5:xxxxx
            // 6:xxxxxx
            switch (theNumber.length()) {
                
                case 1:
                    return convertOneDigits(theNumber) + " فاصلة " + convertTwoDigits(theFloat);
                case 2:
                    return convertTwoDigits(theNumber) + " فاصلة " + convertTwoDigits(theFloat);
                case 3:
                    return convertThreeDigits(theNumber) + " فاصلة " + convertTwoDigits(theFloat);
                case 4:
                    return convertFourDigits(theNumber) + " فاصلة " + convertTwoDigits(theFloat);
                case 5:
                    return convertFiveDigits(theNumber) + " فاصلة " + convertTwoDigits(theFloat);
                case 6:
                    return convertSixDigits(theNumber) + " فاصلة " + convertTwoDigits(theFloat);
                default:
                    return "";
            }
        } else {
            
                       // System.err.println("number.length() : "+number.length());

            switch (number.length()) {


                case 1:
                    return convertOneDigits(number); //1
                case 2:
                    return convertTwoDigits(number); //10
                case 3:
                    return convertThreeDigits(number); // 100   110 001 
                case 4:
                    return convertFourDigits(number);// 1000    
                case 5:
                    return convertFiveDigits(number); //100 00
                case 6:{
                    return convertSixDigits(number); //100 000 999999
                
                }
                default:
                    return "";
            }

        }
    }

    // -------------------------------------------
    private static String convertOneDigits(String oneDigit) {
        switch (Integer.parseInt(oneDigit)) {
            case 1:
                return "واحد";
            case 2:
                return "إثنان";
            case 3:
                return "ثلاثه";
            case 4:
                return "أربعه";
            case 5:
                return "خمسه";
            case 6:
                return "سته";
            case 7:
                return "سبعه";
            case 8:
                return "ثمانيه";
            case 9:
                return "تسعه";
            default:
                return "";
        }
    }

    private static String convertTwoDigits(String twoDigits) {
        String returnAlpha = "00";
        // check if the first digit is 0 like 0x
        if (twoDigits.charAt(0) == '0' && twoDigits.charAt(1) != '0') { // yes
            // convert two digits to one
            return convertOneDigits(String.valueOf(twoDigits.charAt(1)));
        } else { // no
            // check the first digit 1x 2x 3x 4x 5x 6x 7x 8x 9x
            switch (getIntVal(twoDigits.charAt(0))) {
                case 1: { // 1x
                    if (getIntVal(twoDigits.charAt(1)) == 1) {
                        return "إحدى عشر";
                    }
                    if (getIntVal(twoDigits.charAt(1)) == 2) {
                        return "إثنى عشر";
                    } else {
                        return convertOneDigits(String.valueOf(twoDigits.charAt(1))) + " " + "عشر";
                    }
                }
                case 2: // 2x x:not 0
                    returnAlpha = "عشرون";
                    break;
                case 3: // 3x x:not 0
                    returnAlpha = "ثلاثون";
                    break;
                case 4: // 4x x:not 0
                    returnAlpha = "أربعون";
                    break;
                case 5: // 5x x:not 0
                    returnAlpha = "خمسون";
                    break;
                case 6: // 6x x:not 0
                    returnAlpha = "ستون";
                    break;
                case 7: // 7x x:not 0
                    returnAlpha = "سبعون";
                    break;
                case 8: // 8x x:not 0
                    returnAlpha = "ثمانون";
                    break;
                case 9: // 9x x:not 0
                    returnAlpha = "تسعون";
                    break;
                default:
                    returnAlpha = "";
                    break;
            }
        }

        // 20 - 99
        // x0 x:not 0,1
        if (convertOneDigits(String.valueOf(twoDigits.charAt(1))).length() == 0) {
            return returnAlpha;
        } else { // xx x:not 0
            return convertOneDigits(String.valueOf(twoDigits.charAt(1))) + " و " + returnAlpha;
        }
    }

    private static String convertThreeDigits(String threeDigits) {

        //  110 001 
//        System.err.println("threeDigits : "+threeDigits);
        switch (getIntVal(threeDigits.charAt(0))) {

            case 1: { // 100 - 199
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 10x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 100
                        return "مائه";
                    } else { // 10x x: is not 0
                        return "مائه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else {// 1xx x: is not 0                 
                    return "مائه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));   
                }
            }
            case 2: { // 200 - 299
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "مائتين";
                    } else { // 20x x:not 0
                        return "مائتين" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "مائتين" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }
            }
            case 3:// 300 - 399
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "ثلاثمئه";
                    } else { // 20x x:not 0
                        return "ثلاثمئه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "ثلاثمئه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }

            case 4:// 400 - 499
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "أربعمائه";
                    } else { // 20x x:not 0
                        return "أربعمائه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "أربعمائه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }

            case 5:// 500 - 599
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "خمسمائه";
                    } else { // 20x x:not 0
                        return "خمسمائه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "خمسمائه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }
            case 6:// 600 - 699
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "ستمائه";
                    } else { // 20x x:not 0
                        return "ستمائه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "ستمائه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }
            case 7:// 700 - 799
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "سبمعمائه";
                    } else { // 20x x:not 0
                        return "سبمعمائه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "سبمعمائه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }
            case 8:// 800 - 899
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "ثمانمائه";
                    } else { // 20x x:not 0
                        return "ثمانمائه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "ثمانمائه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }
            case 9: { // 900 - 999            
                if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                    if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                        return "تسعمائه";
                    } else { // 20x x:not 0
                        return "تسعمائه" + " و " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 2xx x:not 0
                    return "تسعمائه" + " و " + convertTwoDigits(threeDigits.substring(1, 3));
                }
            }
            case 0: { // 000 - 099
                if (threeDigits.charAt(1) == '0') { // 00x
                    if (threeDigits.charAt(2) == '0') { // 000
                        return "";
                    } else { // 00x x:not 0
                        return convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                    }
                } else { // 0xx x:not 0
                    return convertTwoDigits(threeDigits.substring(1, 3));
                }
            }
            default:
                return "";
        }
    }

    private static String convertFourDigits(String fourDigits) {
        // xxxx
        switch (getIntVal(fourDigits.charAt(0))) {

            case 1: { // 1000 - 1999

//                System.err.println("charAt  " + fourDigits.charAt(0));
//                System.err.println("ccccccc  "+fourDigits);
// 110001
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 10xx x:not 0
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 100x x:not 0
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 1000
                            return "ألف";
                        } else { // 100x x:not 0
//                            System.err.println("is : " + getIntVal(fourDigits.charAt(3)));
                            return "ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 10xx x:not 0

                        return "ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 1xxx x:not 0
                    return "ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }

            case 2: { // 2000 - 2999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "ألفين";
                        } else { // 200x x:not 0
                            return "ألفين" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "ألفين" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "ألفين" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }
            case 3: { // 3000 - 3999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "ثلاثه ألف";
                        } else { // 200x x:not 0
                            return "ثلاثه ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "ثلاثه ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "ثلاثه ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }
            case 4: { // 4000 - 4999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "أربعه ألف";
                        } else { // 200x x:not 0
                            return "أربعه ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "أربعه ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "أربعه ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }
            case 5: { // 5000 - 5999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "خمسه ألف";
                        } else { // 200x x:not 0
                            return "خمسه ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "خمسه ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "خمسه ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }
            case 6: { // 6000 - 6999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "سته ألف";
                        } else { // 200x x:not 0
                            return "سته ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "سته ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "سته ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }
            case 7: { // 7000 - 7999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "سبعه ألف";
                        } else { // 200x x:not 0
                            return "سبعه ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "سبعه ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "سبعه ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }
            case 8: { // 8000 - 8999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "ثمانيه ألف";
                        } else { // 200x x:not 0
                            return "ثمانيه ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "ثمانيه ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "ثمانيه ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }
            case 9: { // 9000 - 9999
                if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                    if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                        if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                            return "تسعة ألف";
                        } else { // 200x x:not 0
                            return "تسعة ألف" + " و " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                        }
                    } else { // 20xx x:not 0
                        return "تسعة ألف" + " و " + convertTwoDigits(fourDigits.substring(2, 4));
                    }
                } else { // 2xxx x:not 0
                    return "تسعة ألف" + " و " + convertThreeDigits(fourDigits.substring(1, 4));
                }
            }

//            { // 3000 - 9999
//                if (getIntVal(fourDigits.charAt(1)) == 0) { // x0xx x:not 0
//                    if (getIntVal(fourDigits.charAt(2)) == 0) { // x00x x:not 0
//                        if (getIntVal(fourDigits.charAt(3)) == 0) { // x000 x:not 0
//                            return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ألاف";
//                        } else { // x00x x:not 0
//                            return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ألاف" + " و "
//                                    + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
//                        }
//                    } else { // x0xx x:not 0
//                        return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ألاف" + " و "
//                                + convertTwoDigits(fourDigits.substring(2, 3));
//                    }
//                } else { // xxxx x:not 0
//                    return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ألاف" + " و "
//                            + convertThreeDigits(fourDigits.substring(1, 4));
//                }
//            }
            default:
                return "";
        }
    }

    private static String convertFiveDigits(String fiveDigits) {
        if (convertThreeDigits(fiveDigits.substring(2, 5)).length() == 0) { // xx000
            // x:not
            // 0
            return convertTwoDigits(fiveDigits.substring(0, 2)) + " ألف ";
        } else { // xxxxx x:not 0
            return convertTwoDigits(fiveDigits.substring(0, 2)) + " ألفا " + " و "
                    + convertThreeDigits(fiveDigits.substring(2, 5));
        }
    }

    private static String convertSixDigits(String sixDigits) {
         
//        System.err.println("all : "+sixDigits);
//        System.err.println("sixDigits sub: "+sixDigits.substring(3,6));        
//        System.err.println("Its is : "+convertOneDigits(sixDigits.substring(3, 6)));
    
        // 1 100 001 
        if (convertThreeDigits(sixDigits.substring(2, 6)).length() == 0) { // xxx000
            // x:not
            // 0
            int  va = Integer.parseInt(sixDigits.substring(3,6));
            
            if (va>0 && va<=9) {
                 return convertThreeDigits(sixDigits.substring(0, 3)) + " ألفا " + " و "
                    + convertOneDigits(sixDigits.substring(3, 6));
            }
            else {
            return convertThreeDigits(sixDigits.substring(0, 3)) + " ألف ";
            }
            
        } else { // xxxxxx x:not 0
            return convertThreeDigits(sixDigits.substring(0, 3)) + " ألفا " + " و "
                    + convertThreeDigits(sixDigits.substring(3, 6));
        }
    }

    private static int getIntVal(char c) {
        return Integer.parseInt(String.valueOf(c));
    }

}
