package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by BrandonWard on 1/22/2015.
 */

public class Factory {

    public IMgr getManager(String name) throws BusinessException {
        try {
            Class c = Class.forName(getImplName(name));
            return (IMgr) c.newInstance();
        } catch (Exception e) {
            throw new BusinessException("Factory Exception: " + e.getMessage());
        }
    }

    private String getImplName(String name) throws Exception {
        FileInputStream fis = new FileInputStream("Factory.properties");
        Properties props = new Properties();
        props.load(fis);
        fis.close();
        return props.getProperty(name);
    }
}
