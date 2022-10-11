package com.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.util.book.DataBook;
import com.util.book.PhotoDataBook;
import com.util.itext.PDFBackground;

@Service
public class PdfService {

	public byte[] createBookPhotos(List<DataBook> dataBooks, String nameBrand, LocalDate initialDate, LocalDate finalDate) {
		Document document = new Document(new Rectangle(900, 595));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, bos);
			writer.setPageEvent(new PDFBackground());
			generateInitialPage(nameBrand, initialDate, finalDate, document);
			for(DataBook dataBook: dataBooks) {
				for(PhotoDataBook photoDataBook: dataBook.getPhotoDataBooks()) {
					addPage(document,dataBook,photoDataBook);		
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
		return bos.toByteArray();
	}

	public void generateInitialPage(String nameBrand, LocalDate initialDate, LocalDate finalDate, Document document)
			throws DocumentException {

		document.open();
		float fntSize;
		fntSize = 90f;
		
		Paragraph brandText = new Paragraph(
				new Phrase(400f, nameBrand, FontFactory.getFont(FontFactory.defaultEncoding, fntSize)));
		brandText.setPaddingTop(1000f);
		Paragraph dateText = new Paragraph(new Phrase(10f, "", FontFactory.getFont(FontFactory.defaultEncoding, 50f)));
		dateText.add(initialDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - "
				+ finalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		Paragraph emissionInformationText = new Paragraph();
		emissionInformationText.add("Emitido em: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		PdfPTable table = new PdfPTable(1);
		table.setHorizontalAlignment(0);
		PdfPCell cellOne = new PdfPCell(brandText);
		PdfPCell cellTwo = new PdfPCell(dateText);

		cellOne.setBorder(Rectangle.NO_BORDER);
		cellOne.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellOne.setPaddingTop(150f);
		cellTwo.setBorder(Rectangle.NO_BORDER);
		cellTwo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table.setHorizontalAlignment(table.ALIGN_CENTER);
		table.addCell(cellOne);
		table.addCell(cellTwo);

		emissionInformationText.setAlignment(emissionInformationText.ALIGN_BOTTOM);

		document.add(emissionInformationText);

		document.add(table);
	}

	public void addPage(Document document, DataBook dataBook,PhotoDataBook photoDataBook) {
		try {
			document.newPage();
			PdfPTable outer = new PdfPTable(new float[] { 50, 50 });
			outer.setWidthPercentage(100f);
			addTableInformation(outer,dataBook.getDate(),dataBook.getNamePromoter(),dataBook.getNameShop(),dataBook.getNameProject(),photoDataBook.getSection());
			addImage(outer,photoDataBook.getUrlImage());
			document.add(outer);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void addImage(PdfPTable outer,String urlImage) throws IOException, DocumentException {
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		String url = urlImage;
		byte[] fileContent = getImageBytes(url);
		Image image = Image.getInstance(fileContent);
		image.scaleAbsolute(400F, 500f);
		image.setScaleToFitLineWhenOverflow(true);
		PdfPCell cellImage = new PdfPCell(image);
		cellImage.setBorder(PdfPCell.NO_BORDER);
		cellImage.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cellImage.setPaddingLeft(20f);
		table.addCell(cellImage);
		PdfPCell cellTable = new PdfPCell(table);
		cellTable.setBorder(0);
		cellTable.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		outer.addCell(cellTable);
	}

	public void addTableInformation(PdfPTable outer,String date, String namePromoter, String nameShop, String nameProject, String section) throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		table.setWidthPercentage(20f);
		PdfPCell cellDate = new PdfPCell(
				new Phrase(20f, date, FontFactory.getFont(FontFactory.defaultEncoding, 20f)));
		PdfPCell cellBrand = new PdfPCell(new Phrase(400f,"", FontFactory.getFont(FontFactory.defaultEncoding, 20f)));
		PdfPCell cellShop = new PdfPCell(new Phrase(400f, nameShop, FontFactory.getFont(FontFactory.defaultEncoding, 20f)));
		PdfPCell cellSection = new PdfPCell(new Phrase(20f, section, FontFactory.getFont(FontFactory.defaultEncoding, 20f)));
		PdfPCell cellPromoter = new PdfPCell(new Phrase(20f, namePromoter, FontFactory.getFont(FontFactory.defaultEncoding, 20f)));
		PdfPCell cellProject = new PdfPCell(new Phrase(20f, nameProject, FontFactory.getFont(FontFactory.defaultEncoding, 20f)));
		
		cellDate.setBorder(PdfPCell.NO_BORDER);
		cellBrand.setBorder(PdfPCell.NO_BORDER);
		cellShop.setBorder(PdfPCell.NO_BORDER);
		cellSection.setBorder(PdfPCell.NO_BORDER);
		cellProject.setBorder(PdfPCell.NO_BORDER);
		cellPromoter.setBorder(PdfPCell.NO_BORDER);
		
		table.addCell(cellDate);
		table.addCell(cellShop);
		table.addCell(cellPromoter);
		table.addCell(cellProject);
		table.addCell(cellSection);
		
		PdfPCell cellTable = new PdfPCell(table);
		cellTable.setBorder(0);
		outer.addCell(cellTable);
	}

	private byte[] getImageBytes(String imageUrl) throws IOException {
		URL url = new URL(imageUrl);

		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try (java.io.InputStream stream = url.openStream()) {
			byte[] buffer = new byte[4096];

			while (true) {
				int bytesRead = stream.read(buffer);
				if (bytesRead < 0) {
					break;
				}
				output.write(buffer, 0, bytesRead);
			}
		}

		return output.toByteArray();
	}
}