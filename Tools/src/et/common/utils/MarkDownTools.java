package et.common.utils;


//import com.vladsch.flexmark.Extension;
//import com.vladsch.flexmark.ast.Node;
//import com.vladsch.flexmark.ext.tables.TablesExtension;
//import com.vladsch.flexmark.html.HtmlRenderer;
//import com.vladsch.flexmark.parser.Parser;
//import com.vladsch.flexmark.parser.ParserEmulationProfile;
//import com.vladsch.flexmark.util.options.MutableDataSet;

/**
 * MarkDown工具包
 * @author lyx
 * @date:   2018年4月28日 上午11:04:10
 */
public class MarkDownTools {
//	Gradle导入包
//	compile group: 'com.vladsch.flexmark', name: 'flexmark', version: '0.32.22'
//	    compile group: 'com.vladsch.flexmark', name: 'flexmark-util', version: '0.32.22'
//	    compile group: 'com.vladsch.flexmark', name: 'flexmark-ext-tables', version: '0.32.22'
	/**
	 * 将markdown转换为 html
	 * @param markdownStr
	 * @return
	 */
//	public static String markdownToHtml(String markdownStr) {
//		MutableDataSet options = new MutableDataSet(); 
//		options.setFrom(ParserEmulationProfile.MARKDOWN); 
//		options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[] { TablesExtension.create()})); 
//		Parser parser = Parser.builder(options).build(); 
//		HtmlRenderer renderer = HtmlRenderer.builder(options).build(); 
//		Node document = parser.parse(markdownStr); 
//		String html = renderer.render(document);
//		html = cssStyleFilling(html);
//		return html;
//	}
	
	
	/**
	 * 设置html标签的样式
	 * @param htmlStr
	 * @return
	 */
	public static String cssStyleFilling(String htmlStr) {
		String h1 = "<h1 style=\"font-size: 25px;\">";
		String h2 = "<h2 style=\"font-size: 23px;\">";
		String h3 = "<h3 style=\"font-size: 20px;\">";
		String table = "<table style=\"border-collapse:collapse;\">";
		String th = "<th style=\" border: 1px solid #DDDDDD;padding: 5px 8px;\" ";
		String td = "<td style=\" border: 1px solid #DDDDDD;padding: 5px 8px;\" ";
		
		htmlStr = htmlStr.replace("<h1>", h1);
		htmlStr = htmlStr.replace("<h2>", h2);
		htmlStr = htmlStr.replace("<h3>", h3);
		htmlStr = htmlStr.replace("<table>", table);
		htmlStr = htmlStr.replace("<th ", th);
		htmlStr = htmlStr.replace("<td ", td);
		String head = "<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"  <meta charset=\"UTF-8\">\r\n" + 
					"  <title></title>\r\n" + 
					"</head>\r\n" + 
					"<body style=\"color: #333333;\">\r\n";
		String end = "</body>\r\n" + 
				"</html>";
		return head+htmlStr+end;
	}
}
