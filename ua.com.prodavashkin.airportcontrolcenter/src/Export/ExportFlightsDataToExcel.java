package Export;

import DataBase.ConnectingToDataBase;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class ExportFlightsDataToExcel {

    private static String flightNumber;
    private static String portArrival;
    private static String portDepartured;
    private static String price;


    public static void exportToExcel () throws ParseException, SQLException {
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Flights List");
        List<FlightsDataModel> dataList = fillData();

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Flight Number");
        row.createCell(1).setCellValue("Port Arrival");
        row.createCell(2).setCellValue("Port Departure");
        row.createCell(3).setCellValue("Price, $");

        for (FlightsDataModel dataModel : dataList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }
        
        Date date = new Date(System.currentTimeMillis());
        String customerDate = (1900 + date.getYear()) + "_" + (1 + date.getMonth()) + "_" + date.getDate() + "_" + date.getHours() + "_" + date.getMinutes() + "_" + date.getSeconds(); 
        
        try (FileOutputStream out = new FileOutputStream(new File(customerDate+"_Flight_List.xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Excel file saved !", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, FlightsDataModel dataModel) {

        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getFlightNumber());
        row.createCell(1).setCellValue(dataModel.getPortArrival());
        row.createCell(2).setCellValue(dataModel.getPortDepartured());
        row.createCell(3).setCellValue(dataModel.getPrice());
    }
    
    private static List<FlightsDataModel> fillData() throws SQLException {

        List<FlightsDataModel> dataModels = new ArrayList<>();

        ConnectingToDataBase connection = new ConnectingToDataBase();
        connection.connect();
        connection.rs = connection.stmt.executeQuery(
           "SELECT fn.id, fn.flight_number, p.port, p2.port AS p2, fn.price " +
                "FROM flight_number AS fn " +
                "INNER JOIN port AS p ON fn.port_arrived = p.id " +
                "INNER JOIN port2 AS p2 ON fn.port_departured = p2.id ");
        
        while (connection.rs.next()) {
            flightNumber = connection.rs.getString("flight_number");
            portArrival = connection.rs.getString("port");
            portDepartured = connection.rs.getString("p2");
            price = connection.rs.getString("price");
            dataModels.add(new FlightsDataModel(flightNumber, portArrival, portDepartured, price));
        }
        return dataModels;
    }
    
}    
