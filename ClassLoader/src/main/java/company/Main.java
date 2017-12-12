package company;

public class Main {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(Helper.class.getClassLoader());
        System.out.println(MyLoader.class.getClassLoader());
        Helper helper = new Helper();
    }
}
