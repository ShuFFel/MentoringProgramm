package company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class Helper {

    Class<?> aClass = null;
    Logger log = LogManager.getLogger(Helper.class);
    public Helper() {
        while (true){
            log("Menu: \n1-start \n0-exit \n");
            Scanner scanner = new Scanner(System.in);
            Integer chose = scanner.nextInt();
            if(chose == 1) {
                log("Input full path to jar: ");
                String path = scanner.next();
                log("Input jar name: ");
                String jarName = scanner.next();
                try {
                    String url = "file://"+path+"/" + jarName;
                    URL[] urlFromPath = new URL[]{new URL(url)};
                    URLClassLoader loader = new URLClassLoader(urlFromPath);
                    log("Input full class name: \n");
                    String className = scanner.next();
                    aClass = loader.loadClass(className);
                    log("Output: ");
                    Constructor<?> constructor = aClass.getConstructor();
                    Object obj = constructor.newInstance();
                    Method method = aClass.getMethod("Say");
                    String res = method.invoke(obj).toString();
                    log.info(res);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                break;
            }
        }
    }
    private void log(String s){
        log.info(s);
    }
}
