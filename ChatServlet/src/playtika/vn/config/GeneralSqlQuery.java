package playtika.vn.config;

public class GeneralSqlQuery {
    public final static String SQLINSERT = "INSERT INTO `usersdata` (`UserName`, `Password`, `Time`) VALUES (?, ?, ?);";
    public final static String SQLSELECT = "SELECT * FROM usersdata;";
}
