import java.sql.*;

public class Main {
    //Veri tabanı URL,kullanıcı adı ve  şifresi sabit olarak tanımlanıyor.
    private  static  final String DB_URL ="jdbc:postgresql://localhost:5432/University";
    private static final String DB_USER="postgres";
    private static final String DB_PASSWORD="40427034004";
    public static void main(String[] args) throws SQLException {
        //Veritabanı bağlantısı için Connection nesnesi tanımlanıyor.
        Connection connect =null;

        try{
            //Veri tabanına bağlantı kuruluyr.
            connect=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //Otomatik commit özelliği kapatıldı.
            connect.setAutoCommit(false);
            //INSERT SQL  komutu kullanılmak için PreapredStatment nesnesi oluşturuldu.
            PreparedStatement prSql = connect.prepareStatement("INSERT INTO employees(name,position,salary) VALUES(?,?,?) ");

            //Veriler giriliyor.
            prSql.setString(1,"Oğuz");
            prSql.setString(2,"Chef");
            prSql.setInt(3,3400);
            prSql.executeUpdate();

            prSql.setString(1,"Mahmut");
            prSql.setString(2,"CEO");
            prSql.setInt(3,4200);
            prSql.executeUpdate();

            prSql.setString(1,"Elif");
            prSql.setString(2,"Manager");
            prSql.setInt(3,3800);
            prSql.executeUpdate();

            prSql.setString(1,"Polat");
            prSql.setString(2,"Product Manager");
            prSql.setInt(3,3800);
            prSql.executeUpdate();

            prSql.setString(1,"Memati");
            prSql.setString(2,"Security");
            prSql.setInt(3,4000);
            prSql.executeUpdate();


            connect.commit();//İşlemler tamamlanınca veritabanına commit yapılıyor.

            //PreparedStatement ve Connection nesneleri kapatılıyor.
            prSql.close();
            connect.close();
        }catch (SQLException e){
            //Hata meydana gelirse veritabanı değişiklikleri geri alınıyor.
            if (connect!=null){
                connect.rollback();
            }
            System.out.println(e.getMessage());//Hata mesajı yazdırılıyor.
        }
    }
}