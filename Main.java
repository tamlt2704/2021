import org.apache.log4j.Logger;

public class Main {
    static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        User user = new User(args[0]);
        log.info(user.getName());
        //System.out.println(user.getName());
    }
}
