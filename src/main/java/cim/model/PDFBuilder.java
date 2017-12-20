package cim.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import cim.entity.CarOwner;
import cim.entity.InsuranceCompany;
import cim.entity.QuoteRequest;
import cim.entity.Vehicle;

public class PDFBuilder extends AbstractPDFViewer {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
        		cim.entity.UserInfo uinf = (cim.entity.UserInfo)model.get("userinfo");
			InsuranceCompany insuranceCompany = (InsuranceCompany)model.get("insuranceCompany");
			QuoteRequest qr = (QuoteRequest) model.get("quoteRequest");
			CarOwner co=qr.getVehicle().getVehicleOwner();
			Vehicle vehicle = qr.getVehicle();
			String baseaddr=(String)model.get("addr");
			Font header = font;
			header.setColor(BaseColor.RED);
			header.setSize(20);
			Paragraph headerP = new Paragraph(insuranceCompany.getInsuranceCompanyName(),header);
			headerP.setAlignment(Element.ALIGN_CENTER);
			document.add(headerP);
			header.setColor(BaseColor.BLACK);
			header.setSize(14);
			headerP= new Paragraph("Contact : " + insuranceCompany.getInsuranceCompanyContact(),header);
			headerP.setAlignment(Element.ALIGN_CENTER);
			document.add(headerP);
		        
			header.setColor(BaseColor.BLUE);
			header.setSize(16);
			Paragraph titleP;
			if(qr.getVehicle().isInsured()) {
				titleP = new Paragraph("Policy for " + qr.getVehicle().getVehiclePlate(),header);
			}else {
			 titleP = new Paragraph("Quote Request for " + qr.getVehicle().getVehiclePlate(),header);
			}
			titleP.setAlignment(Element.ALIGN_CENTER);
			document.add(titleP);
			
			header.setColor(BaseColor.BLACK);
			header.setSize(14);
			titleP = new Paragraph("Driver Information",header);
			titleP.setAlignment(Element.ALIGN_CENTER);
			document.add(titleP);
			
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(75.0f);
	        table.setWidths(new float[] {5.0f, 5.0f});
	        table.setSpacingBefore(10);
	        PdfPCell cell = new PdfPCell();
	       
	        cell.setPadding(5);
	        cell.setPhrase(new Phrase("First Name"));
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	     
	        table.addCell(cell);
	        
	        
	        cell.setPhrase(new Phrase(co.getFirstName()));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Last Name"));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(co.getLastName()));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Driver License No"));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(co.getDriverLicenseNo()));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Driver License Expiration"));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(co.getDriverLicenseExpiration()));
	        table.addCell(cell);
	        document.add(table);
	        Phrase phrase = new Phrase();
	        phrase.add("\n \n");
	        document.add(phrase);
	        
	        titleP = new Paragraph("Vehicle Information",header);
	        titleP.setAlignment(Element.ALIGN_CENTER);
	        document.add(titleP);
	        
	        String addr=request.getRequestURI();
	        String builder = addr.substring(0, addr.lastIndexOf("/"));
	        
	        PdfPTable vtable = new PdfPTable(2);
	        vtable.setWidthPercentage(75.0f);
	        vtable.setWidths(new float[] {5.0f, 5.0f});
	        vtable.setSpacingBefore(10);
	        
	        cell.setPhrase(new Phrase("Vehicle Plate"));
	        vtable.addCell(cell);
	        cell.setPhrase(new Phrase(vehicle.getVehiclePlate()));
	        vtable.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Vehicle Make"));
	        vtable.addCell(cell);
	        cell.setPhrase(new Phrase(vehicle.getVehicleMake()));
	        vtable.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Vehicle Model"));
	        vtable.addCell(cell);
	        cell.setPhrase(new Phrase(vehicle.getVehicleModel()));
	        vtable.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Vehicle Year"));
	        vtable.addCell(cell);
	        cell.setPhrase(new Phrase(vehicle.getVehicleYear()));
	        vtable.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Vehicle KM"));
	        vtable.addCell(cell);
	        cell.setPhrase(new Phrase(vehicle.getVehicleKM()));
	        vtable.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Vehicle Transmission"));
	        vtable.addCell(cell);
	        cell.setPhrase(new Phrase(vehicle.getVehicleTransmission()));
	        vtable.addCell(cell);
	        
	        document.add(vtable);
	     
	        
	        Paragraph body = new Paragraph();
	        if(!qr.getVehicle().isInsured()) {
	        body.add("\n 	Quoted Insurance value for above driver and vehicle for an insurance term of " + qr.getQuoteTerm() +" months is estimated to be "+
	        qr.getQuoteCost() +" $. If you accept this quote please click 'Accept' below!");}else {
	        	body.add("\n 	Policy Insurance value for above driver and vehicle for an insurance term of " + qr.getQuoteTerm() +" months is agreed to be "+
	        	        qr.getQuoteCost() +" $.");
	        }
	        document.add(body);
	        phrase.add("\n");
	        
	        Paragraph back = new Paragraph();
	        header.setColor(BaseColor.BLUE);
	        back.setFont(header);
	        
	        
	        if(uinf.getRole().equals("ROLE_CAROWNER")) {
	        		if(!qr.getVehicle().isInsured()) {
	        	Paragraph accept = new Paragraph();
	        	Chunk acceptChunk = new Chunk("Accept");
	        	Chunk declineChunk = new Chunk("Decline");
	        	
	        	acceptChunk.setAnchor(baseaddr+builder+"/acceptQuote?id="+qr.getQuoteId());
	        	accept.add("\n");
	        	accept.add(acceptChunk);
	        	accept.setAlignment(Element.ALIGN_CENTER);
	        	document.add(accept);
	        	Paragraph decline = new Paragraph();
	        	decline.add("\n");
	        	declineChunk.setAnchor(baseaddr+builder+"/declineQuote?id="+qr.getQuoteId());
	        	decline.add(declineChunk);
	        	decline.setAlignment(Element.ALIGN_CENTER);
	        document.add(decline);
	        		}
	        }
	        back.add("\n\n\n");
	        Chunk chunk = new Chunk("Click Here to Go Back!");
	        if(uinf.getRole().equals("ROLE_INSURANCE")) {
	        chunk.setAnchor(baseaddr+builder+"/show?pending");
	        }else if(uinf.getRole().equals("ROLE_CAROWNER")) {
	        	if(qr.getVehicle().isInsured()) {
	        		chunk.setAnchor(baseaddr+builder+"/showVehicle?id="+qr.getVehicle().getVehicleId());
	    	        
	        		}else {
	        			chunk.setAnchor(baseaddr+builder+"/quoteRequest?id="+qr.getInsuranceCompany().getInsuranceCompanyId());
	        	        	
	        	}
	        	}
	        back.add(chunk);
	        back.setAlignment(Element.ALIGN_CENTER);
	        
	        document.add(back);
	     
	        
	}		

}
