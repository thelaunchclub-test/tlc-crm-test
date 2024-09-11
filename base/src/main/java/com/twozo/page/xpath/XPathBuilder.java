package com.twozo.page.xpath;

public class XPathBuilder {

    public static String getXPath(final XPath xpath) {
        return String.format(xpath.position() == 0 ? "//%s[@%s='%s']" : "(//%s[@%s='%s'])[%d]",
                xpath.tagName().getTag(),
                xpath.attributeName().getAttribute(),
                xpath.attributeValue(),
                xpath.position());
    }

    public static String getXPathByText(final String value) {
        return String.format("//*[text()='%s']", value);
    }

    public static String getXPathByTag(final String value) {
        return String.format("//*[@%s]", value);
    }

    public static String getXpathByContains(final String value){
        return String.format("//*[ contains (text(),'%s')]",value);
    }
}
