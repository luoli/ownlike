package com.ownliked.util.system.web;

/**
 * 屏蔽html带来的不和谐字符
 * @author jianglijia
 *
 */
public class HtmlRegexp {

	public static String escapeToHTML(String source) {
        if (source == null) return null;
        StringBuffer buf = new StringBuffer("");
        char ch = ' ';
        for (int i = 0; i < source.length(); i++) {
            ch = source.charAt(i);
            switch (ch) {
                case '<':
                    buf.append("&lt;");
                    break;
                case '>':
                    buf.append("&gt;");
                    break;
                case '"':
                    buf.append("&#34");
                    break;
                case '\'':
                    buf.append("&#39");
                    break;
                case '\n':
                    buf.append("&nbsp;");
                    break;
                default:
                    buf.append(ch);
                    break;
            }
        }
        return buf.toString();
    }
	
}
