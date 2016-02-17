package com.diffey.view.rxzhihu.util;

import java.util.List;

/**
 * Created by diff on 2016/2/4.
 */
public class HtmlUtils {
    public static String structHtml(String oriStr, List<String> cssList) {
        StringBuilder htmlString = new StringBuilder("<html><head>");
        for (String css : cssList) {
            htmlString.append(structCssLink(css));
        }
        htmlString.append("</head><body>");
        htmlString.append("<style>img{max-width:340px !important;}</style>");
        htmlString.append(oriStr);
        htmlString.append("</body></html>");
        return htmlString.toString();
    }

    public static String structCssLink(String css) {
        return "<link type=\\\"text/css\\\" rel=\\\"stylesheet\\\" href=\\\"" + css + "\">";
    }
}
