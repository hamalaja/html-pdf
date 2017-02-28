package vn.hamalaja.converter;

import vn.hamalaja.converter.bo.HtmlToPdfConverter;

/**
 * @author lamhm
 *
 */
public class ConvertHtmlToPdfApplication {
	public static void main(String[] args) {
		HtmlToPdfConverter.doConvert("pdf_file_name", "templates/index.html");
	}

}
