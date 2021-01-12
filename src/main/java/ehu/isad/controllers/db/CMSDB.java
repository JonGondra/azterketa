package ehu.isad.controllers.db;

import ehu.isad.model.CMSModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CMSDB {

    private static final CMSDB instance = new CMSDB();
    private static final DBController dbcontroller = DBController.getController();

    private CMSDB() {}

    public static CMSDB getInstance() {
        return instance;
    }

    public void addToDB(CMSModel cmsModel){
        String query = "INSERT INTO checksums VALUES(1,'"+cmsModel.getVersion()+"','"+cmsModel.getMd5()+"','README')";
        dbcontroller.execSQL(query);
    }

    public CMSModel getFromDB(String url,String md5){
        CMSModel cmsModel = null;
        String query = "SELECT version FROM checksums where md5='"+md5+"'";
        ResultSet rs = dbcontroller.execSQL(query);
        try {
            while (rs.next()) {
                String version  = rs.getString("version");
                cmsModel = new CMSModel(url,md5,version);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        return cmsModel;
    }

    public Boolean begiratuDBan(String md5) {
        boolean aurkituta=true;
        String query = "select idCMS from checksums where md5='" + md5 + "'";
        ResultSet rs = dbcontroller.execSQL(query);

        try {
            aurkituta = rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return aurkituta;
    }
    }

