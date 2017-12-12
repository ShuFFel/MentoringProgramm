package company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyLoader extends ClassLoader {
    private String currentRoot = null;
    public MyLoader(String root) throws FileNotFoundException {
        super(MyLoader.class.getClassLoader());
        File f = new File(root);
        if (f.isDirectory()){
            currentRoot = root;
        }
        else{
            throw new FileNotFoundException();
        }
    }
    public byte[] findClassBytes(String className){

        try{
            String pathName = currentRoot + File.separatorChar +
                    className.replace('.', File.separatorChar) +
                    ".class";

            FileInputStream inFile =
                    new FileInputStream(pathName);
            byte[] classBytes = new byte[inFile.available()];
            inFile.read(classBytes);

            return classBytes;
        }
        catch (java.io.IOException ioEx)
        {
            return null;
        }
    }

    public Class findClass(String name)throws ClassNotFoundException{

        byte[] classBytes = findClassBytes(name);
        if (classBytes==null){
            throw new ClassNotFoundException();
        }
        else{
            return defineClass(name, classBytes, 0, classBytes.length);
        }
    }
}
