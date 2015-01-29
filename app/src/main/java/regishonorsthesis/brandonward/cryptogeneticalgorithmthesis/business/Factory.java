package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import android.util.Log;

import java.io.InputStream;
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
            Log.e("Factory", "Fatal error in Factory" + e.getMessage());
            throw new BusinessException("Factory Exception: " + e.getMessage());
        }
    }

    private String getImplName(String name) throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("Factory.properties");
        Properties props = new Properties();
        props.load(is);
        is.close();
        return props.getProperty(name);
    }
}
