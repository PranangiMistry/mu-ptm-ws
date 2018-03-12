package com.integ.ptm;

import com.integ.ptm.service.ClientService;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBServiceFactory implements Factory<List<DBService>> {

    @Context
    UriInfo info;

    @Override
    public List<DBService> provide() {
        List<DBService> list=new ArrayList<>();
        Properties prop = new Properties();
        InputStream input = null;
        try {
            prop.load(getClass().getResourceAsStream("/DBService.properties"));
            String[] p=info.getPath().split("/");
            String[] s=prop.getProperty(p[0]).split(",");
            if(s.length>1) {
                for (int i = 0; i < s.length; i++) {
                    Class c = Class.forName(s[i]);
                    list.add((DBService) c.newInstance());
                }
            }else{

                Class c = Class.forName(prop.getProperty(p[0]));
                list.add((DBService) c.newInstance());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("provide");
        return list;
    }

    @Override
    public void dispose(List<DBService> dbservice) {
        System.out.println("dispose");
    }
    public static Binder getBinder() {
        System.out.println("binder");
        return new AbstractBinder() {
            @Override
            protected  void configure() {
                bindFactory(DBServiceFactory.class).to(new TypeLiteral<List<DBService>>() {
                });
            }
        };
    }

}
