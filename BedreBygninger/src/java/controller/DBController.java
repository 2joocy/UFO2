package controller;

import DbHandler.*;
import entities.Building;
import entities.Report;
import entities.User;
import exceptions.DatabaseConnectionException;
import exceptions.UserExistsException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 * This class is a controller for our mapper classes.
 */
public class DBController {
 
    DBBuildingHandler dbb;
    DBUserHandler dbu;
    DBHTMLPresenter dbhtml;
    ImageHandler img;
    
    public DBController(Connection conn) {
        dbb = new DBBuildingHandler(conn);
        dbu = new DBUserHandler(conn);
        dbhtml = new DBHTMLPresenter(conn);
        img = new ImageHandler(conn);
    }
    
    /**
     * DBBuildingHandler functions
     */
    
    
    /**
     * This class creates a object, of type Building. 
     * It afterwards uploads this object to the database accordingly
    @param address Address of the building
    @param cadastral Image of district, of the address
    @param builtYear Year, of making of the building
    @param area District of the building
    @param zipcode Zipcode of the building
    @param city City of the building
    @param conditionText Grade of the building, described by Polygon worker
    @param service Service description
    @param extraText Description text, rather useless
    @param dateCreated Specific date of creation of the server
    @param fk_idUser The user ID, of the user that the building belongs to
     * @return
     * @throws java.sql.SQLException
    
    */
    public int addBuilding(String address, String cadastral, String builtYear,
            String area, String zipcode, String city, String conditionText,
            String service, String extraText, String dateCreated, int fk_idUser) throws SQLException {
        return dbb.addBuilding(address, cadastral, builtYear, area, zipcode, city, conditionText, service, extraText, dateCreated, fk_idUser);
    }
    
    
    /**
     * Returns a object, of type Building, given the input parameter it's given.
     * The function searches the database, and returns a building object 
     * according to the building ID it's given
     * @param idBuilding Integer value, used as pointer for the database, or as ID
     * @return 
     */
    
    public Building getBuilding(int idBuilding) throws SQLException {
        return dbb.getBuilding(idBuilding);
    }
    
    /**
     * Method used for JUnit Testing. Uses Address as pointer in the database, 
     * rather than the ID due to technical reasons
     * @param address Address of the building, used to find the building in the Database
     * @return 
     */
    
    public Building getBuilding(String address) throws SQLException { 
        return dbb.getBuilding(address);
    }
    
    /**
     * Method returns a Integer, given a building ID. It then selects the 
     * field from the database, called fk_idReport, which is the report ID.
     * @param BuildingId Integer value, used as pointer for the database, or as ID
     * @return 
     */
    
    public int getFkIdReport(int BuildingId) throws SQLException {
        return dbb.getFkIdReport(BuildingId);
    }
    
    /**
     * Method returns a Report Object, given a ID, as a pointer, to
     * search the database.
     * @param reportId ID of the report. Integer value, as ID, used as a search pointer
     * @return 
     */
    
    public Report getReport(int reportId) throws SQLException {
        return dbb.getReport(reportId);
    } 
    
    /**
     * Returns the amount of reports belonging to the user
     * @param idUser ID of the user
     * @return 
     */
    
    public int getReportCount(int idUser) throws SQLException{
        return dbb.getReportCount(idUser);
    }
    
    /**
     * Returns a Report Object, given the id of the building.
     * @param buildingId ID of the building
     * @return 
     */
    
    public Report getReportFromBuildingId(int buildingId) throws SQLException {
        return dbb.getReportFromBuildingId(buildingId);
    }
    
    /**
     * Uses input of Report Object type, and edits the existing row in the database.
     * @param report Report Object
     */
    
    public void editReport(Report report) throws SQLException {
        dbb.editReport(report);
    }
    
    /**
     * Method creates a row inside the database, given a Report Object. 
     * The Report Object has a pointer inside, called idReport, which is 
     * used to refer to the report, belonging to the room report created
     * @param remarks Boolean value, used to hint the user at the show report screen
     * @param damage Boolean value, used to hint the user at the show report screen
     * @param damageDate Date of damage
     * @param damageWhere Placement of damage
     * @param damageHappened Specific placement of damage
     * @param damageRepaired Damage repairs, etc.
     * @param damageWater Damage Type
     * @param damageRot Damage Type
     * @param damageMold Damage Type
     * @param damageFire Damage Type
     * @param damageOther Damage Type
     * @param wallRemark Boolean value, used to hint the user at the show report screen
     * @param wallText Description of the wall
     * @param roofRemark Boolean value, used to hint the user at the show report screen
     * @param roofText Description of the roof
     * @param floorRemark Boolean value, used to hint the user at the show report screen
     * @param floorText Description of the floor
     * @param moistureScan Boolean value, used to hint the user at the show report screen
     * @param moistureScanText Discussion, and conclusions of the moisture scan
     * @param moistureScanMeasured Value of the moisture scan
     * @param conclusionText Final conclusion of the room
     * @param idReport Pointer, towards the belonging report
     */
    
    public void addRoomReport(boolean remarks, boolean damage, String damageDate,
                String damageWhere, String damageHappened,
                String damageRepaired, boolean damageWater, boolean damageRot, boolean damageMold,
                boolean damageFire, String damageOther, boolean wallRemark, String wallText,
                boolean roofRemark, String roofText, boolean floorRemark, String floorText,
                boolean moistureScan, String moistureScanText, String moistureScanMeasured,
                String conclusionText, int idReport) throws SQLException {
        dbb.addRoomReport(remarks, damage, damageDate, damageWhere, damageHappened, damageRepaired, damageWater, damageRot, damageMold, damageFire, damageOther, wallRemark, wallText, roofRemark, roofText, floorRemark, floorText, moistureScan, moistureScanText, moistureScanMeasured, conclusionText, idReport);
    }
    
    /**
     * Returns a integer, showing how many room reports exist, under the building
     * @param idReport Pointer towards the report
     * @return 
     */
    
    public int countRooms(int idReport) throws SQLException {
        return dbb.countRooms(idReport);
    }
    
    /**
     * Removes a row inside the database, given a pointer using the id of the report
     * @param reportId Id of the report
     */
    
    public void removeReport(int reportId) throws SQLException {
        dbb.removeReport(reportId);
    }
    
    /**
     * Updates the value "service" inside the coloumn, of the building table.
     * @param id Id of the building
     */
    
    public void requestService(int id) throws SQLException {
        dbb.requestService(id);
    }
    
    /**
     * Updates the value "service" inside the coloumn, of the building table
     * @param idBuilding ID of the building
     */
    
    public void setReviewed(int idBuilding) throws SQLException {
        dbb.setReviewed(idBuilding);
    }
    
    /**
     * Updates row inside the database, given the id of the following 
     * building to be edited
     * @param address Address of the building
     * @param cadastral Cadastral of the area
     * @param builtYear Year built
     * @param area Area belonging to the building
     * @param zipcode Zipcode belonging to the building
     * @param city City belonging to the building
     * @param condition Grade of the building, given by polygon
     * @param extraText Extra comments, etc.
     * @param idBuilding Id of the building
     */
    
    public void editBuilding(String address, String cadastral, String builtYear,
                String area, String zipcode, String city, String condition,
                String extraText, int idBuilding) throws SQLException {
        dbb.editBuilding(address, cadastral, builtYear, area, zipcode, city, condition, extraText, idBuilding);
    }
    
    /**
     * Method returns a Integer, showing how many buildings exist, under the ID of the user.
     * @param idUser Id of the user
     * @return 
     */
    
    public int getBuildingCount(int idUser) throws SQLException {
        return dbb.getBuildingCount(idUser);
    }
    
    /**
     * Contrast to getBuildingCount, that uses a pointer, idUser, this returns a Integer, given no pointers.
     * @return 
     */
    
    public int getBuildingCount() throws SQLException {
        return dbb.getBuildingCount();
    }
    
    /**
     * Method returns a Integer, showing the amount of buildings, awaiting service.
     * @return 
     */
    
    public int getAwaitingService() throws SQLException {
        return dbb.getAwaitingService();
    }
    
    
    /**
     * Returns the id of the employee, that has reviewed the service / report
     * @param idBuilding Id of the building
     * @return 
     */
    
    public int getEmployeeFromIdBuilding(int idBuilding) throws SQLException {
        return dbb.getEmployeeFromIdBuilding(idBuilding);
    }
    
    /**
     * Returns number 
     * @param id
     * @return 
     */
    
    public int getAwaitingReview(int id) throws SQLException {
        return dbb.getAwaitingReview(id);
    }
    
    public void removeBuilding(int buildingId) throws SQLException {
        dbb.removeBuilding(buildingId);
    }
    
    public void removePicture(int pictureId) throws SQLException {
        dbb.removePicture(pictureId);
    }
    
    /**
     * DBUserHandler functions
     */
    
    public User checkLogin(String email, String password) throws SQLException {
        return dbu.checkLogin(email, password);
    }
    
    public void registerUser(String email, String password, String businessName, String phone, String status, String fullName, String createdDate) throws SQLException, UserExistsException {
        dbu.registerUser(email, password, businessName, phone, status, fullName, createdDate);
    }
    
    public boolean userExists(String username) throws SQLException {
        return dbu.userExists(username);
    }
    
    public String getUserFromDB(int id) throws SQLException {
        return dbu.getUserFromDB(id);
    }
    
    public int countUnConfirmed() throws SQLException {
        return dbu.countUnConfirmed();
    }
    
    public void confirmUser(int id) throws SQLException {
        dbu.confirmUser(id);
    }
    
    public void confirmUser(String email) throws SQLException {
        dbu.confirmUser(email);
    }
    
    public void removeUser(int id) throws SQLException {
        dbu.removeUser(id);
    }
    
    public void removeUser(String email) throws SQLException {
        dbu.removeUser(email);
    }
    
    public void denyUser(int id) throws SQLException {
        dbu.denyUser(id);
    }
    
    public void denyUser(String email) throws SQLException {
        dbu.denyUser(email);
    }
    
    public void updatePassword(String username, String password) throws SQLException {
        dbu.updatePassword(username, password);
    }
    
    public String forgotPass(String email, String businessName) throws SQLException {
        return dbu.forgotPass(email, businessName);
    }
    
    public boolean correctPass(String password, String email) throws SQLException {
        return dbu.correctPass(password, email);
    }
    
    public void updateEmail(String email, int id) throws SQLException {
        dbu.updateEmail(email, id);
    }
    
    /**
     * DBHTMLPresenter functions
     */
    
    /**
     * 
     * @param idUser
     * @param role
     * @return
     * @throws SQLException
     * @throws DatabaseConnectionException 
     */
    
    public String getBuildings(int idUser, String role) throws SQLException, DatabaseConnectionException {
        return dbhtml.getBuildings(idUser, role);
    }
    
    public String getReportOverview(int idBuilding) throws DatabaseConnectionException, SQLException {
        return dbhtml.getReportOverview(idBuilding);
    }
    
    public String getReportOverviewWithReportID(int idReport) throws DatabaseConnectionException, SQLException {
        return dbhtml.getReportOverviewWithReportID(idReport);
    }
    
    public String getRooms(int id) throws DatabaseConnectionException, SQLException {
        return dbhtml.getRooms(id);
    }
    
    public String showRoomReport(int id) throws SQLException, DatabaseConnectionException {
        return dbhtml.showRoomReport(id);
    }
    
    public String getService(int idUser) throws SQLException, DatabaseConnectionException {
        return dbhtml.getService(idUser);
    }
    
    public String createMenu(String status) throws DatabaseConnectionException, SQLException {
        return dbhtml.createMenu(dbb, dbu, status);
    }
    
    public String getAwaitingServiceCustomer(String parameter, int id, String userRole) throws DatabaseConnectionException, SQLException {
        return dbhtml.getAwaitingServiceCustomer(dbb, parameter, id, userRole);
    }
    
    public String printAwaitingService() throws DatabaseConnectionException, SQLException, ClassNotFoundException {
        return dbhtml.printAwaitingService();
    }
    
    public String printAwaitingReview(int id) throws DatabaseConnectionException, SQLException, ClassNotFoundException {
        return dbhtml.printAwaitingReview(id);
    }
    
    public String getUnConfirmed() throws DatabaseConnectionException, SQLException {
        return dbhtml.getUnConfirmed();
    }
    
    public String getImageHTML(int id) {
        return dbhtml.getImageHTML(id);
    }
    
    public String getImageHTML(int id, int width, int height) {
        return dbhtml.getImageHTML(id, width, height);
    }
    
    /**
     * ImageHandler functions
     */
    
    public int uploadImage(String description, String type, Part filePart) {
        return img.uploadImage(description, type, filePart);
    }
    
    public int uploadMainImage(String description, String type, Part filePart, int buildingID, int fk_idMainPicture) {
        return img.uploadMainImage(description, type, filePart, buildingID, fk_idMainPicture);
    }
    
    public void uploadThumbnail(int pictureID, String type, BufferedImage image) throws IOException {
        img.uploadThumbnail(pictureID, type, image);
    }
    
    public void uploadThumbnail(int pictureID, String type, ByteArrayOutputStream bOut) throws IOException {
        img.uploadThumbnail(pictureID, type, bOut);
    }
    
    public int uploadRoofImage(String description, String type, Part filePart, int reportID) {
        return img.uploadRoofImage(description, type, filePart, reportID);
    }
    
    public int uploadOuterRoofImage(String description, String type, Part filePart, int reportID) {
        return img.uploadOuterRoofImage(description, type, filePart, reportID);
    }
    
    public BufferedImage resize(BufferedImage image, int width, int height) {
        return img.resize(image, width, height);
    }
    
    public BufferedImage getImageFromPart(Part imagePart) throws IOException {
        return img.getImageFromPart(imagePart);
    }
    
}