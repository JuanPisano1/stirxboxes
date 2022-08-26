package utils;

import org.openqa.selenium.By;

public class LocatorGenerator {

    private static String replaceString(String[] list, String value) {
        return list[1].substring(1).replaceFirst("%s", value);
    }

    private static String replaceString(String[] list, String value, String replace) {
        return list[1].substring(1).replaceFirst(replace, value);
    }

    private static String[] splitSelector(String s) {
        return s.split(":", 2);
    }

    private static String getByType(String[] list) {
        return list[0].split("\\.")[1];
    }

    public static By addMissignValue(By selector, String value){
        String s = selector.toString();
        String[] list = splitSelector(s);
        String byType = getByType(list);
        s = replaceString(list, value);

        return switchByType(s, byType);
    }

    public static By addMissignValue(By selector, String value, String replace){
        String s = selector.toString();
        String[] list = splitSelector(s);
        String byType = getByType(list);
        s = replaceString(list, value, replace);

        return switchByType(s, byType);
    }

    private static By switchByType(String s, String byType){ //TODO
        By ret = null;
        switch (byType){
            case "xpath":
                ret = By.xpath(s);
                break;
            case "cssSelector":
                ret = By.cssSelector(s);
                break;
            case "id":
                ret = By.id(s);
                break;
            case "name":
                ret = By.name(s);
                break;
            case "tagName":
                ret = By.tagName(s);
                break;
            case "className":
                ret = By.className(s);
                break;
            case "linkText":
                ret = By.linkText(s);
                break;
            case "partialLinkText":
                ret = By.partialLinkText(s);
                break;
        }
        return ret;
    }
}
