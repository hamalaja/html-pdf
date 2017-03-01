package vn.hamalaja.converter.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer.PageSize;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import vn.hamalaja.converter.util.DateExUtils;

/**
 * @author lamhm
 *
 */
public class HtmlToPdfConverter {
	private static final String root = new File("").getAbsolutePath();
	private static final String pdfPath = "update/pdf";


	public static void doConvert(String fileName, String htmlFile) {
		try {
			File input = new File(root, htmlFile);
			Document doc = Jsoup.parse(input, "UTF-8");
			doc.select("div.content").html("hello");

			String folderGen = DateExUtils.format(System.currentTimeMillis(), "yyyyMMdd");
			String pdfFolder = Files.createDirectories(Paths.get(pdfPath + "/" + folderGen)).toString();

			CYaHPConverter converter = new CYaHPConverter();
			Map<Object, Object> properties = new HashMap<Object, Object>();
			properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
			properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, root + "/Fonts/");

			FileOutputStream out = new FileOutputStream(new File(pdfFolder, "/" + fileName + ".pdf"));
			ArrayList<Object> headerFooterList = new ArrayList<Object>();
			headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter("<img style=\"position: absolute; width:720px;height:60px;\" src=\"file:///" + root
					+ "/templates/assets/images/footer.png" + "\"/>", IHtmlToPdfTransformer.CHeaderFooter.FOOTER));

			converter
					.convertToPdf(doc.toString(), new PageSize(21d, 30d, 1d, 1d, 2d, 1d), headerFooterList, "file:///" + root + "/templates/", out, properties);

			out.flush();
			out.close();
			CYaHPConverter.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
