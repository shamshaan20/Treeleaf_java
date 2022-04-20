package com.diwash.ManyToMany.exporter;

import com.diwash.ManyToMany.Models.Employee;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EmployeePDFExporter {
    private List<Employee> listEmployees;

    public EmployeePDFExporter(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }
    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.RED);

        cell.setPhrase(new Phrase("Employee Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);

    }
    private void writeTableData(PdfPTable table){
        for(Employee employee: listEmployees){
            table.addCell(String.valueOf(employee.getEmployeeId()));
            table.addCell(employee.getFirstName());
            table.addCell(employee.getLastName());
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());

        doc.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List Of Employees", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f,3.5f,3.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        doc.add(table);
        doc.close();
    }
}
