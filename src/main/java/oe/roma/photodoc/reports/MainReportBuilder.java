package oe.roma.photodoc.reports;

import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.Number;
import oe.roma.photodoc.domain.Customer;
import oe.roma.photodoc.domain.CustomerObject;
import oe.roma.photodoc.domain.File;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by us8610 on 03.06.14.
 */
@SuppressWarnings("unchecked")
public class MainReportBuilder extends AbstractJExcelView {

    private static final String URL = "http://10.93.1.55:88/photodoc/";

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      WritableWorkbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // get data model which is passed by the Spring container
        List<Customer> list = (List<Customer>) model.get("list");

        WritableFont text1 = new WritableFont(WritableFont.TIMES, 12);
        WritableFont text3 = new WritableFont(WritableFont.TIMES, 12);
        text3.setColour(jxl.format.Colour.BLUE);

        DateFormat customDateFormat = new DateFormat ("dd.MM.yyyy");

        WritableCellFormat dateFormat = new WritableCellFormat (text1,customDateFormat);
        dateFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        dateFormat.setAlignment(Alignment.CENTRE);

        //для рєєстр
        WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
        WritableCellFormat main = new WritableCellFormat(cellFont);

        //для контроролю
        WritableCellFormat header = new WritableCellFormat(cellFont);
        header.setBorder(Border.ALL, BorderLineStyle.THIN);


        WritableCellFormat normal = new WritableCellFormat(text1);
        normal.setBorder(Border.ALL, BorderLineStyle.THIN);
        normal.setWrap(true);

        WritableCellFormat normalh = new WritableCellFormat(text3);
        normalh.setBorder(Border.ALL, BorderLineStyle.THIN);
        normalh.setWrap(true);

        WritableFont text2 = new WritableFont(WritableFont.TIMES, 12);

        WritableCellFormat normal1 = new WritableCellFormat(text2);
        normal1.setBorder(Border.ALL, BorderLineStyle.THIN);

        WritableCellFormat normal2 = new WritableCellFormat(text2);
        normal2.setBorder(Border.ALL, BorderLineStyle.THIN);
        normal2.setAlignment(Alignment.CENTRE);
        normal2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

        main.setAlignment(Alignment.CENTRE);
        main.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

        header.setAlignment(Alignment.CENTRE);
        header.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

        normal.setAlignment(Alignment.CENTRE);
        normal.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

        normalh.setAlignment(Alignment.CENTRE);
        normalh.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);



        // create a new Excel sheet

        WritableSheet sheet = workbook.createSheet("Звіт", 0);

        int start=0;
        sheet.addCell(new Label(0, start, "РЕМ", normal2));
        sheet.addCell(new Label(1, start, "Номер договору", normal2));
        sheet.addCell(new Label(2, start, "Номер лічильника", normal2));
        sheet.addCell(new Label(3, start, "Споживач", normal2));
        sheet.addCell(new Label(4, start, "Об'єкт", normal2));
        sheet.addCell(new Label(5, start, "Адреса об'єкту", normal2));
        sheet.addCell(new Label(6, start, "Вид фотодокументу", normal2));
        sheet.addCell(new Label(7, start, "Дата фотодокументу", normal2));
        sheet.addCell(new Label(8, start, "Інспектор", normal2));
        sheet.addCell(new Label(9, start, "Фото", normal2));
        for(int i=0;i<10;i++)
            sheet.setColumnView(i,40);
        start++;
        for(Customer customer : list){
            CustomerObject object = customer.getObjects().get(0);
            File currentFile = object.getFiles().get(0);
            URL  url = new URL(URL + currentFile.getName());
            sheet.addCell(new Label(0, start, currentFile.getDepartment().getName(),normal));
            sheet.addCell(new Label(1, start, customer.getContract_number(),normal));
            sheet.addCell(new Label(2, start, object.getCounter_number(),normal));
            sheet.addCell(new Label(3, start, customer.getName(),normal));
            sheet.addCell(new Label(4, start, object.getName(),normal));
            sheet.addCell(new Label(5, start, object.getAddress(),normal));
            sheet.addCell(new Label(6, start, currentFile.getTypeDocument().getName(),normal));
            sheet.addCell(new DateTime(7, start, currentFile.getDate(),dateFormat));
            sheet.addCell(new Label(8, start, object.getInspector(),normal));
            WritableHyperlink hl = new WritableHyperlink(9, start,url);
            sheet.addHyperlink(hl);
            sheet.addCell(new Label(9,start, currentFile.getName(),normalh));
            start++;
        }
    }
}