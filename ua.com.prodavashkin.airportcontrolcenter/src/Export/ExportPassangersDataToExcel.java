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


public class ExportPassangersDataToExcel {

    private static String firstName;
    private static String lastName;
    private static String sex;
    private static String birthday;
    private static String nationality;
    private static String passport;
    private static String flightNumber;
    private static String salunClass;


    public static void exportToExcel () throws ParseException, SQLException {
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Passanger List");
        List<PassangerDataModel> dataList = fillData();

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("First Name");
        row.createCell(1).setCellValue("Last Name");
        row.createCell(2).setCellValue("Sex");
        row.createCell(3).setCellValue("Birthday");
        row.createCell(4).setCellValue("Nationality");
        row.createCell(5).setCellValue("Passport");
        row.createCell(6).setCellValue("Flight Number");
        row.createCell(7).setCellValue("Salun Class");

        for (PassangerDataModel dataModel : dataList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }
        Date date = new Date(System.currentTimeMillis());
        String customerDate = (1900 + date.getYear()) + "_" + (1 + date.getMonth()) + "_" + date.getDate() + "_" + date.getHours() + "_" + date.getMinutes() + "_" + date.getSeconds(); 
        try (FileOutputStream out = new FileOutputStream(new File(customerDate+"_Passanger.xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Excel file saved !", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, PassangerDataModel dataModel) {

        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getFirstName());
        row.createCell(1).setCellValue(dataModel.getLastName());
        row.createCell(2).setCellValue(dataModel.getSex());
        row.createCell(3).setCellValue(dataModel.getBirthday());
        row.createCell(4).setCellValue(dataModel.getNationality());
        row.createCell(5).setCellValue(dataModel.getPassport());
        row.createCell(6).setCellValue(dataModel.getFlightNumber());
        row.createCell(7).setCellValue(dataModel.getSalunClass());
    }
    
    private static List<PassangerDataModel> fillData() throws SQLException {

        List<PassangerDataModel> dataModels = new ArrayList<>();

        ConnectingToDataBase connection = new ConnectingToDataBase();
        connection.connect();
        connection.rs = connection.stmt.executeQuery(
                "SELECT p.first_name, p.last_name, s.sex, p.date_of_birthday, p.nationality, p.passport, f.flight_number, salun.salun_type_full "
                + "FROM passangers AS p "
                + "INNER JOIN sex AS s ON p.sex = s.id "
                + "INNER JOIN flight_number AS f ON p.flyght_number = f.id "
                + "INNER JOIN salun_class AS salun ON p.salun_class = salun.id");
        
        while (connection.rs.next()) {
            firstName = connection.rs.getString("first_name");
            lastName = connection.rs.getString("last_name");
            sex = connection.rs.getString("sex");
            birthday = connection.rs.getString("date_of_birthday");
            nationality= connection.rs.getString("nationality");
            passport = connection.rs.getString("passport");
            flightNumber = connection.rs.getString("flight_number");
            salunClass = connection.rs.getString("salun_type_full");
            dataModels.add(new PassangerDataModel(lastName, firstName, lastName, sex, birthday, nationality, passport, flightNumber, salunClass));
        }
        return dataModels;
    }
    
}    
