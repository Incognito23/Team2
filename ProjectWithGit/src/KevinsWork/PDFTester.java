package KevinsWork;

import static org.junit.Assert.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import com.itextpdf.text.DocumentException;

public class PDFTester
{
	private Desktop desktop;
	private BarLinesPDF pdf;
	private DataToArray data;
	
		@Before
		public void setUp() throws DocumentException, IOException
		{
			data = new DataToArray();
			pdf = new BarLinesPDF();
			desktop = null;
		}

//Testing BarLinesPDF ------------------------------------------------------------------
		
		@Test //Tests to see if the PDF has a null destination. If it works, open it.
		public void notNullPDFDestination()
		{
			assertFalse(pdf.DEST.equals(null));
		}
		@Test //Tests to see if the PDF has an empty destination. If it works, open it.
		public void emptyPDFDestination()
		{
			assertFalse(pdf.DEST.equals(""));
		}
		@Test //Tests to see if the PDF creation was successful for Test.txt. If it works, open it.
		public void testPDF() throws DocumentException, IOException
		{
			data.textFile = "Test.txt";
			pdf.DEST = ("test.pdf");
			pdf.convertPDF();
			desktop = (Desktop.isDesktopSupported()) ? Desktop.getDesktop() : null;
			desktop.open(new File("test.pdf"));
		}
		
		@Test //Tests to see if the PDF creation was successful for EmptyFile.txt. If it works, open it.
		public void testEmptyFilePDF() throws DocumentException, IOException
		{
			data.textFile = "EmptyFile.txt";
			pdf.DEST = ("EmptyFile.pdf");
			pdf.convertPDF();
			desktop = (Desktop.isDesktopSupported()) ? Desktop.getDesktop() : null;
			desktop.open(new File("EmptyFile.pdf"));
		}
		
		@Test //Tests to see if the PDF creation was successful for MoonlightSonata.txt. If it works, open it.
		public void testMoonlightSonataPDF() throws DocumentException, IOException
		{
			data.textFile = "MoonlightSonata.txt";
			pdf.DEST = ("MoonlightSonata.pdf");
			pdf.convertPDF();
			desktop = (Desktop.isDesktopSupported()) ? Desktop.getDesktop() : null;
			desktop.open(new File("MoonlightSonata.pdf"));
		}
		
		@Test //Tests to see if the PDF creation was successful for RememberingRain.txt. If it works, open it.
		public void testRememberingRainPDF() throws DocumentException, IOException
		{
			data.textFile = "RememberingRain.txt";
			pdf.DEST = ("RememberingRain.pdf");
			pdf.convertPDF();
			desktop = (Desktop.isDesktopSupported()) ? Desktop.getDesktop() : null;
			desktop.open(new File("RememberingRain.pdf"));
		}
}
